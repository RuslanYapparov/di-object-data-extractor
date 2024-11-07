package ru.yappy.rzdengineerassistant.diobjectdataextractor.client.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.core5.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.ResourceAccessException;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ExternalServiceException;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.client.common.impl.DiodeCommonObjectRequesterImpl;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationNameDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withResourceNotFound;

@RestClientTest(value = DiodeCommonObjectRequester.class,
        properties = { "diode.server.url.prefix=http://localhost:8070" })
@ContextConfiguration(classes = {DiodeCommonObjectRequesterImpl.class })
public class DiodeCommonObjectRequesterMockTest {
    private final DiodeCommonObjectRequester client;
    private final MockRestServiceServer server;
    private final ObjectMapper objectMapper;

    @Autowired
    public DiodeCommonObjectRequesterMockTest(DiodeCommonObjectRequester client,
                                              MockRestServiceServer server,
                                              ObjectMapper objectMapper) {
        this.client = client;
        this.server = server;
        this.objectMapper = objectMapper;
    }

    @Test
    public void getStationWayMiniDtosByStationId_whenGetResponseFromServer_thenReturnCorrectDto() throws Exception {
        StationWayMiniDto[] stationWayDtos = new StationWayMiniDto[] {
            new StationWayMiniDto(7L, "7 станции Кусь", new StationNameDto(777L, "КУСЬ")),
        };

        server.expect(requestTo("http://localhost:8070/api/v1/objects/common/stationway/mini/777"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withSuccess().body(objectMapper.writeValueAsString(stationWayDtos))
                        .contentType(MediaType.APPLICATION_JSON));

        ResponseEntity<StationWayMiniDto[]> response = client.getStationWayMiniDtosByStationId(777L);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE)).isTrue();
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);

        StationWayMiniDto[] miniDtos = response.getBody();

        assertThat(miniDtos).isEqualTo(stationWayDtos);
    }

    @Test
    public void getStationWayMiniDtosByStationId_whenGetIOExceptionFromServer_thenThrowResourceAccessException() {
        server.expect(requestTo("http://localhost:8070/api/v1/objects/common/stationway/mini/777"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withException(new IOException("Test IOException")));

        ResourceAccessException exception =
                assertThrows(ResourceAccessException.class, () -> client.getStationWayMiniDtosByStationId(777L));
        assertThat(exception.getMessage()).isEqualTo("I/O error on GET request " +
                "for \"http://localhost:8070/api/v1/objects/common/stationway/mini/777\": Test IOException");
    }

    @Test
    public void getStationWayMiniDtosByStationId_whenGetBadRequestResponseFromServer_thenThrowExternalServerException() {
        server.expect(requestTo("http://localhost:8070/api/v1/objects/common/stationway/mini/777"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withBadRequest());

        ExternalServiceException exception = assertThrows(ExternalServiceException.class,
                () -> client.getStationWayMiniDtosByStationId(777L));
        assertThat(exception.getMessage()).isEqualTo("При выполнении запроса к внешнему сервису " +
                "'DiObjectDataExtractor' получен ответ с кодом '400' и сообщением: \"\"");
    }

    @Test
    public void getStationWayMiniDtosByStationId_whenGetNotFoundResponseFromServer_thenThrowObjectNotFoundException() {
        server.expect(requestTo("http://localhost:8070/api/v1/objects/common/stationway/mini/777"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withResourceNotFound());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> client.getStationWayMiniDtosByStationId(777L));
        assertThat(exception.getMessage())
                .isEqualTo("Не удалось найти в базе данных объект 'массив вариантов станционных путей " +
                        "для станции' с ид=777");
    }

}