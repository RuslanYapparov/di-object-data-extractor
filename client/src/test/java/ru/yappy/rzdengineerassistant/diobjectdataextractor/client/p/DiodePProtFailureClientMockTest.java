package ru.yappy.rzdengineerassistant.diobjectdataextractor.client.p;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.ResourceAccessException;
import ru.yappy.rzdengineerassistant.commonclasses.exception.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.client.p.impl.DiodePProtFailureClientImpl;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.mini.JointFailureProtCreatingDataDto;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@RestClientTest(value = DiodePSpecificObjectRequester.class,
        properties = { "diode.server.url.prefix=http://localhost:8070" })
@ContextConfiguration(classes = { DiodePProtFailureClientImpl.class })
public class DiodePProtFailureClientMockTest {
    private final DiodePProtFailureClient client;
    private final MockRestServiceServer server;
    private final ObjectMapper objectMapper;

    @Autowired
    public DiodePProtFailureClientMockTest(DiodePProtFailureClient client,
                                           MockRestServiceServer server,
                                           ObjectMapper objectMapper) {
        this.client = client;
        this.server = server;
        this.objectMapper = objectMapper;
    }

    @Test
    public void getJointFailureProtCreatingDataDto_whenGetResponseFromServer_thenReturnCorrectDto()
            throws JsonProcessingException {
        LocationDto location = createDefaultLocationDto();
        JointFailureProtCreatingDataDto jointProtDataDto = new JointFailureProtCreatingDataDto(
                new JointMiniDto[] { createDefaultJointMiniDto() },
                "ТЕСТ",
                List.of(DiodeTestDataCreator.createEmployeeDto()));

        server.expect(requestTo("http://localhost:8070/api/v1/protocol/failure/p/joint?type=ISOLATING_SIGNAL"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(location)))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(jointProtDataDto), MediaType.APPLICATION_JSON));

        ResponseEntity<JointFailureProtCreatingDataDto> response = client.getJointFailureProtCreatingDataDto(location,
                JointType.ISOLATING_SIGNAL);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE)).isTrue();
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);

        JointFailureProtCreatingDataDto responseDto = response.getBody();

        assertThat(responseDto).isNotNull();
        assertThat(responseDto).isNotEqualTo(jointProtDataDto);
        assertThat(Arrays.equals(responseDto.jointVariants(), jointProtDataDto.jointVariants())).isTrue();
        assertThat(responseDto.diodeLocationDescription()).isEqualTo("ТЕСТ");
        assertThat(responseDto.participants()).isEqualTo(jointProtDataDto.participants());
    }

    @Test
    public void getJointFailureProtCreatingDataDto_whenGetIOExceptionFromServer_thenThrowResourceAccessException()
            throws JsonProcessingException {
        server.expect(requestTo("http://localhost:8070/api/v1/protocol/failure/p/joint?type=ISOLATING_SWITCH"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(createDefaultLocationDto())))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withException(new IOException("Test IOException")));

        ResourceAccessException exception = assertThrows(ResourceAccessException.class, () ->
                client.getJointFailureProtCreatingDataDto(createDefaultLocationDto(), JointType.ISOLATING_SWITCH));
        assertThat(exception.getMessage()).isEqualTo("I/O error on GET request " +
                "for \"http://localhost:8070/api/v1/protocol/failure/p/joint\": Test IOException");
    }

    @Test
    public void getJointFailureProtCreatingDataDto_whenGetForbiddenResponseFromServer_thenThrowExternalServiceException()
            throws JsonProcessingException {
        server.expect(requestTo("http://localhost:8070/api/v1/protocol/failure/p/joint?type=CONDUCTING"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(createDefaultLocationDto())))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withForbiddenRequest());

        ExternalServiceException exception = assertThrows(ExternalServiceException.class,
                () -> client.getJointFailureProtCreatingDataDto(createDefaultLocationDto(), JointType.CONDUCTING));
        assertThat(exception.getMessage()).isEqualTo("При выполнении запроса к внешнему сервису " +
                "'DiObjectDataExtractor' получен ответ с кодом '403' и сообщением: \"\"");
    }

    @Test
    public void getJointFailureProtCreatingDataDto_whenGetNotFoundResponseFromServer_thenThrowExternalServiceException()
            throws JsonProcessingException {
        LocationDto location = createDefaultLocationDto();
        server.expect(requestTo("http://localhost:8070/api/v1/protocol/failure/p/joint?type=CONDUCTING"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(location)))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withResourceNotFound());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> client.getJointFailureProtCreatingDataDto(location, JointType.CONDUCTING));
        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                "'массив вариантов рельсовых стыков с типом CONDUCTING для конкретного места' со следующими " +
                "критериями выборки: " + location);
    }

    private LocationDto createDefaultLocationDto() {
        return new LocationDto(
                WayType.INTERSTATION_TRACK,
                7L,
                "ёж",
                7,
                7,
                7,
                LineSide.RIGHT
        );
    }

    private JointMiniDto createDefaultJointMiniDto() {
        return new JointMiniDto(
                        777L,
                        "ТЕСТОВЫЙ",
                        "ПРАВАЯ",
                        1.234f
        );
    }

}