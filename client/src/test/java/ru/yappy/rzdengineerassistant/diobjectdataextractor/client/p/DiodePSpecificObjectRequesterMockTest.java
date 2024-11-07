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
import org.yaml.snakeyaml.util.UriEncoder;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ExternalServiceException;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.client.p.impl.DiodePSpecificObjectRequesterImpl;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LineSide;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm.StructuralEnterpriseDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm.WayMaintenanceDistanceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm.WayMaintenanceDistanceMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.RailMiniDto;

import java.io.IOException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@RestClientTest(value = DiodePSpecificObjectRequester.class,
        properties = { "diode.server.url.prefix=http://localhost:8070" })
@ContextConfiguration(classes = { DiodePSpecificObjectRequesterImpl.class })
public class DiodePSpecificObjectRequesterMockTest {
    private final DiodePSpecificObjectRequester client;
    private final MockRestServiceServer server;
    private final ObjectMapper objectMapper;

    @Autowired
    public DiodePSpecificObjectRequesterMockTest(DiodePSpecificObjectRequester client,
                                                 MockRestServiceServer server,
                                                 ObjectMapper objectMapper) {
        this.client = client;
        this.server = server;
        this.objectMapper = objectMapper;
    }

    @Test
    public void getWayMaintenanceDistanceDtoById_whenGetResponseFromServer_thenReturnCorrectDto() throws Exception {
        WayMaintenanceDistanceDto wayMaintenanceDistanceDto = createDefaultWayMaintenanceDistanceDto();
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/distance/777"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(wayMaintenanceDistanceDto),
                        MediaType.APPLICATION_JSON));

        ResponseEntity<WayMaintenanceDistanceDto> response = client.getWayMaintenanceDistanceDtoById(777L);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE)).isTrue();
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);

        StructuralEnterpriseDto enterpriseDtoFromServer = response.getBody();

        assertThat(enterpriseDtoFromServer).isEqualTo(wayMaintenanceDistanceDto);
    }

    @Test
    public void getWayMaintenanceDistanceDtoById_whenGetIOExceptionFromServer_thenThrowResourceAccessException() {
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/distance/777"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withException(new IOException("Test IOException")));

        ResourceAccessException exception =
                assertThrows(ResourceAccessException.class, () -> client.getWayMaintenanceDistanceDtoById(777L));
        assertThat(exception.getMessage()).isEqualTo("I/O error on GET request " +
                "for \"http://localhost:8070/api/v1/objects/p/distance/777\": Test IOException");
    }

    @Test
    public void getWayMaintenanceDistanceDtoById_whenGetBadRequestResponseFromServer_thenThrowExternalServerException() {
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/distance/777"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withBadRequest());

        ExternalServiceException exception = assertThrows(ExternalServiceException.class,
                () -> client.getWayMaintenanceDistanceDtoById(777L));
        assertThat(exception.getMessage()).isEqualTo("При выполнении запроса к внешнему сервису " +
                "'DiObjectDataExtractor' получен ответ с кодом '400' и сообщением: \"\"");
    }

    @Test
    public void getWayMaintenanceDistanceDtoById_whenGetNotFoundResponseFromServer_thenThrowObjectNotFoundException() {
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/distance/777"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withResourceNotFound());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> client.getWayMaintenanceDistanceDtoById(777L));
        assertThat(exception.getMessage())
                .isEqualTo("Не удалось найти в базе данных объект 'дистанция пути' с ид=777");
    }

    @Test
    public void getAllWayMaintenanceDistanceMiniDtosByDirectorateAbbreviation_whenGetResponseFromServer_thenReturnCorrectDto()
            throws Exception {
        WayMaintenanceDistanceMiniDto[] wayMaintenanceDistanceDtos =
                new WayMaintenanceDistanceMiniDto[] { createDefaultWayMaintenanceDistanceMiniDto() };
        String encodedAbbreviation = UriEncoder.encode("ВЗДИ");
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/distance?dirabbrev=" + encodedAbbreviation))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(wayMaintenanceDistanceDtos),
                        MediaType.APPLICATION_JSON));

        ResponseEntity<WayMaintenanceDistanceMiniDto[]> response =
                client.getAllWayMaintenanceDistanceMiniDtosByDirectorateAbbreviation("ВЗДИ");
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE)).isTrue();
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isEqualTo(1);

        assertThat(response.getBody()[0]).isEqualTo(createDefaultWayMaintenanceDistanceMiniDto());
    }

    @Test
    public void getAllWayMaintenanceDistanceMiniDtosByDirectorateAbbreviation_whenGetIOExceptionFromServer_thenThrowException() {
        String encodedAbbreviation = UriEncoder.encode("ВЗДИ");
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/distance?dirabbrev=" + encodedAbbreviation))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withException(new IOException("Test IOException")));

        ResourceAccessException exception = assertThrows(ResourceAccessException.class,
                        () -> client.getAllWayMaintenanceDistanceMiniDtosByDirectorateAbbreviation("ВЗДИ"));
        assertThat(exception.getMessage()).isEqualTo("I/O error on GET request " +
                "for \"http://localhost:8070/api/v1/objects/p/distance\": Test IOException");
    }

    @Test
    public void getAllWayMaintenanceDistanceMiniDtosByDirectorateAbbreviation_whenGetInternalServerResponseFromServer_thenThrowException() {
        String encodedAbbreviation = UriEncoder.encode("ВЗДИ");
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/distance?dirabbrev=" + encodedAbbreviation))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withServerError());

        ExternalServiceException exception = assertThrows(ExternalServiceException.class,
                () -> client.getAllWayMaintenanceDistanceMiniDtosByDirectorateAbbreviation("ВЗДИ"));
        assertThat(exception.getMessage()).isEqualTo("При выполнении запроса к внешнему сервису " +
                "'DiObjectDataExtractor' получен ответ с кодом '500' и сообщением: \"\"");
    }

    @Test
    public void getAllWayMaintenanceDistanceMiniDtosByDirectorateAbbreviation_whenGetNotFoundResponseFromServer_thenThrowException() {
        String encodedAbbreviation = UriEncoder.encode("ВЗДИ");
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/distance?dirabbrev=" + encodedAbbreviation))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withResourceNotFound());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> client.getAllWayMaintenanceDistanceMiniDtosByDirectorateAbbreviation("ВЗДИ"));
        assertThat(exception.getMessage())
                .isEqualTo("Не удалось найти в базе данных объект 'массив вариантов дистанций пути для " +
                        "региональной дирекции инфраструктуры' с ид=ВЗДИ");
    }

    @Test
    public void getRailMiniDtosByLocationDto_whenGetResponseFromServer_thenReturnCorrectDto()
            throws JsonProcessingException {
        LocationDto location = createDefaultLocationDto();
        RailMiniDto[] railMiniDtos = new RailMiniDto[] { createDefaultRailMiniDto() };
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/rail"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(location)))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(railMiniDtos), MediaType.APPLICATION_JSON));

        ResponseEntity<RailMiniDto[]> response = client.getRailMiniDtosByLocationDto(location);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE)).isTrue();
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);

        RailMiniDto[] miniDtos = response.getBody();

        assertThat(miniDtos).isEqualTo(railMiniDtos);
    }

    @Test
    public void getRailMiniDtosByLocationDto_whenGetIOExceptionFromServer_thenThrowResourceAccessException()
            throws JsonProcessingException {
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/rail"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(createDefaultLocationDto())))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withException(new IOException("Test IOException")));

        ResourceAccessException exception =
                assertThrows(ResourceAccessException.class,
                        () -> client.getRailMiniDtosByLocationDto(createDefaultLocationDto()));
        assertThat(exception.getMessage()).isEqualTo("I/O error on GET request " +
                "for \"http://localhost:8070/api/v1/objects/p/rail\": Test IOException");
    }

    @Test
    public void getRailMiniDtosByLocationDto_whenGetForbiddenResponseFromServer_thenThrowExternalServiceException()
            throws JsonProcessingException {
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/rail"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(createDefaultLocationDto())))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withForbiddenRequest());

        ExternalServiceException exception = assertThrows(ExternalServiceException.class,
                () -> client.getRailMiniDtosByLocationDto(createDefaultLocationDto()));
        assertThat(exception.getMessage()).isEqualTo("При выполнении запроса к внешнему сервису " +
                "'DiObjectDataExtractor' получен ответ с кодом '403' и сообщением: \"\"");
    }

    @Test
    public void getRailMiniDtosByLocationDto_whenGetNotFoundResponseFromServer_thenThrowExternalServiceException()
            throws JsonProcessingException {
        LocationDto location = createDefaultLocationDto();
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/rail"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(location)))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withResourceNotFound());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> client.getRailMiniDtosByLocationDto(location));
        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                "'массив вариантов рельсов для конкретного места' со следующими критериями выборки: " + location);
    }

    @Test
    public void getJointMiniDtosByLocationAndType_whenGetResponseFromServer_thenReturnCorrectDto()
            throws JsonProcessingException {
        LocationDto location = createDefaultLocationDto();
        JointMiniDto[] jointMiniDtos = new JointMiniDto[] { createDefaultJointMiniDto() };
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/joint?type=ISOLATING_SIGNAL"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(location)))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(jointMiniDtos), MediaType.APPLICATION_JSON));

        ResponseEntity<JointMiniDto[]> response = client.getJointMiniDtosByLocationAndType(location,
                JointType.ISOLATING_SIGNAL);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE)).isTrue();
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);

        JointMiniDto[] miniDtos = response.getBody();

        assertThat(miniDtos).isEqualTo(jointMiniDtos);
    }

    @Test
    public void getJointMiniDtosByLocationAndType_whenGetIOExceptionFromServer_thenThrowResourceAccessException()
            throws JsonProcessingException {
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/joint?type=ISOLATING_SWITCH"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(createDefaultLocationDto())))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withException(new IOException("Test IOException")));

        ResourceAccessException exception =
                assertThrows(ResourceAccessException.class,
                        () -> client.getJointMiniDtosByLocationAndType(createDefaultLocationDto(),
                                JointType.ISOLATING_SWITCH));
        assertThat(exception.getMessage()).isEqualTo("I/O error on GET request " +
                "for \"http://localhost:8070/api/v1/objects/p/joint\": Test IOException");
    }

    @Test
    public void getJointMiniDtosByLocationAndType_whenGetForbiddenResponseFromServer_thenThrowExternalServiceException()
            throws JsonProcessingException {
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/joint?type=CONDUCTING"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(createDefaultLocationDto())))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withForbiddenRequest());

        ExternalServiceException exception = assertThrows(ExternalServiceException.class,
                () -> client.getJointMiniDtosByLocationAndType(createDefaultLocationDto(), JointType.CONDUCTING));
        assertThat(exception.getMessage()).isEqualTo("При выполнении запроса к внешнему сервису " +
                "'DiObjectDataExtractor' получен ответ с кодом '403' и сообщением: \"\"");
    }

    @Test
    public void getJointMiniDtosByLocationAndType_whenGetNotFoundResponseFromServer_thenThrowExternalServiceException()
            throws JsonProcessingException {
        LocationDto location = createDefaultLocationDto();
        server.expect(requestTo("http://localhost:8070/api/v1/objects/p/joint?type=CONDUCTING"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(location)))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withResourceNotFound());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> client.getJointMiniDtosByLocationAndType(location, JointType.CONDUCTING));
        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                "'массив вариантов рельсовых стыков типа 'токопроводящий' для конкретного места' со следующими " +
                "критериями выборки: " + location);
    }

    private WayMaintenanceDistanceDto createDefaultWayMaintenanceDistanceDto() {
        return new WayMaintenanceDistanceDto(
                777L,
                "ТЕСТ",
                "ДИ",
                Set.of(),
                7,
                true,
                Set.of(),
                Set.of()
        );
    }

    private WayMaintenanceDistanceMiniDto createDefaultWayMaintenanceDistanceMiniDto() {
        return new WayMaintenanceDistanceMiniDto(
                        777L,
                        "ТЕСТ",
                        7,
                        true
        );
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

    private RailMiniDto createDefaultRailMiniDto() {
        return new RailMiniDto(
                        777L,
                        "ЛЕВАЯ",
                        "БЕССТЫКОВОЙ",
                        1.234f,
                        4.321f
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