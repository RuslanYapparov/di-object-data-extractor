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
import ru.yappy.rzdengineerassistant.diobjectdataextractor.client.p.impl.DiodePObjInfoClientImpl;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.*;

import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@RestClientTest(value = DiodePObjInfoClientImpl.class,
        properties = { "diode.server.url.prefix=http://localhost:8070" })
@ContextConfiguration(classes = { DiodePObjInfoClientImpl.class })
public class DiodePObjInfoClientMockTest {
    private final DiodePObjInfoClient client;
    private final MockRestServiceServer server;
    private final ObjectMapper objectMapper;

    @Autowired
    public DiodePObjInfoClientMockTest(DiodePObjInfoClient client,
                                       MockRestServiceServer server,
                                       ObjectMapper objectMapper) {
        this.client = client;
        this.server = server;
        this.objectMapper = objectMapper;
    }

    @Test
    public void getRailInfoDtoByIdAndWayType_whenGetResponseFromServer_thenReturnCorrectDto()
            throws JsonProcessingException {
        RailInfoDto railInfoDto = new RailInfoDto(
                "перегон ТЕСТ - ТЕСТ",
                null,
                null,
                new BrokenRailExtraInfoDto(LocalDate.now(), 0, 0),
                null
        );
        server.expect(requestTo("http://localhost:8070/api/v1/objinfo/p/rail/777?waytype=INTERSTATION_TRACK"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(railInfoDto), MediaType.APPLICATION_JSON));

        RailInfoDto response =
                client.getRailInfoDtoByIdAndWayType(777L, WayType.INTERSTATION_TRACK);

        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(railInfoDto);
    }

    @Test
    public void getRailInfoDtoByIdAndWayType_whenGetIOExceptionFromServer_thenThrowResourceAccessException() {
        server.expect(requestTo("http://localhost:8070/api/v1/objinfo/p/rail/777?waytype=INTERSTATION_TRACK"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withException(new IOException("Test IOException")));

        ResourceAccessException exception =
                assertThrows(ResourceAccessException.class,
                        () -> client.getRailInfoDtoByIdAndWayType(777L, WayType.INTERSTATION_TRACK));
        assertThat(exception.getMessage()).isEqualTo("I/O error on GET request " +
                "for \"http://localhost:8070/api/v1/objinfo/p/rail/777\": Test IOException");
    }

    @Test
    public void getRailInfoDtoByIdAndWayType_whenGetBadRequestResponseFromServer_thenThrowExternalServiceException() {
        server.expect(requestTo("http://localhost:8070/api/v1/objinfo/p/rail/777?waytype=INTERSTATION_TRACK"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withBadRequest());

        ExternalServiceException exception = assertThrows(ExternalServiceException.class, () ->
                client.getRailInfoDtoByIdAndWayType(777L, WayType.INTERSTATION_TRACK));
        assertThat(exception.getMessage()).isEqualTo("При выполнении запроса к внешнему сервису " +
                "'DiObjectDataExtractor' получен ответ с кодом '400' и сообщением: \"\"");
    }

    @Test
    public void getRailInfoDtoByIdAndWayType_whenGetNotFoundResponseFromServer_thenThrowObjectNotFoundException() {
        server.expect(requestTo("http://localhost:8070/api/v1/objinfo/p/rail/777?waytype=INTERSTATION_TRACK"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withResourceNotFound());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () ->
                client.getRailInfoDtoByIdAndWayType(777L, WayType.INTERSTATION_TRACK));
        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект 'полная " +
                "информация о рельсе' с ид=777");
    }

    @Test
    public void getRailInfoDtoByIdAndWayType_whenGet200ResponseWithoutBody_thenThrowIllegalStateException() {
        server.expect(requestTo("http://localhost:8070/api/v1/objinfo/p/rail/777?waytype=INTERSTATION_TRACK"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withSuccess());

        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                client.getRailInfoDtoByIdAndWayType(777L, WayType.INTERSTATION_TRACK));
        assertThat(exception.getMessage()).isEqualTo("При выполнении запроса к сервису " +
                "'DiObjectDataExtractor' для получения информации для создания характеристики рельса, " +
                "получен ответ с кодом '200 OK', но отсутствующим телом ответа.");
    }

    @Test
    public void getJointInfoDto_whenGetResponseFromServer_thenReturnCorrectDto()
            throws JsonProcessingException {
        JointInfoDto jointInfoDto = new JointInfoDto(
                "перегон ТЕСТ - ТЕСТ",
                null,
                null,
                null,
                null
        );
        server.expect(requestTo("http://localhost:8070/api/v1/objinfo/p/joint/777?" +
                        "waytype=INTERSTATION_TRACK&jointtype=CONDUCTING"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(jointInfoDto), MediaType.APPLICATION_JSON));

        JointInfoDto response = client.getJointInfoDtoByIdAndWayTypeAndJointType(777L,
                WayType.INTERSTATION_TRACK, JointType.CONDUCTING);

        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(jointInfoDto);
    }

    @Test
    public void getJointInfoDto_whenGetIOExceptionFromServer_thenThrowResourceAccessException() {
        server.expect(requestTo("http://localhost:8070/api/v1/objinfo/p/joint/777?" +
                        "waytype=INTERSTATION_TRACK&jointtype=ISOLATING_SIGNAL"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withException(new IOException("Test IOException")));

        ResourceAccessException exception =
                assertThrows(ResourceAccessException.class,
                        () -> client.getJointInfoDtoByIdAndWayTypeAndJointType(777L,
                                WayType.INTERSTATION_TRACK, JointType.ISOLATING_SIGNAL));
        assertThat(exception.getMessage()).isEqualTo("I/O error on GET request " +
                "for \"http://localhost:8070/api/v1/objinfo/p/joint/777\": Test IOException");
    }

    @Test
    public void getJointInfoDto_whenGetBadRequestResponseFromServer_thenThrowExternalServiceException() {
        server.expect(requestTo("http://localhost:8070/api/v1/objinfo/p/joint/777?" +
                        "waytype=INTERSTATION_TRACK&jointtype=CONDUCTING"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withBadRequest());

        ExternalServiceException exception = assertThrows(ExternalServiceException.class, () ->
                client.getJointInfoDtoByIdAndWayTypeAndJointType(777L,
                        WayType.INTERSTATION_TRACK, JointType.CONDUCTING));
        assertThat(exception.getMessage()).isEqualTo("При выполнении запроса к внешнему сервису " +
                "'DiObjectDataExtractor' получен ответ с кодом '400' и сообщением: \"\"");
    }

    @Test
    public void getJointInfoDto_whenGetNotFoundResponseFromServer_thenThrowObjectNotFoundException() {
        server.expect(requestTo("http://localhost:8070/api/v1/objinfo/p/joint/777?" +
                        "waytype=INTERSTATION_TRACK&jointtype=CONDUCTING"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withResourceNotFound());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () ->
                client.getJointInfoDtoByIdAndWayTypeAndJointType(777L,
                        WayType.INTERSTATION_TRACK, JointType.CONDUCTING));
        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект 'полная " +
                "информация о рельсовом стыке' с ид=777");
    }

    @Test
    public void getJointInfoDto_whenGet200ResponseWithoutBody_thenThrowIllegalStateException() {
        server.expect(requestTo("http://localhost:8070/api/v1/objinfo/p/joint/777?" +
                        "waytype=INTERSTATION_TRACK&jointtype=CONDUCTING"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
                .andRespond(withSuccess());

        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                client.getJointInfoDtoByIdAndWayTypeAndJointType(777L,
                        WayType.INTERSTATION_TRACK, JointType.CONDUCTING));
        assertThat(exception.getMessage()).isEqualTo("При выполнении запроса к сервису " +
                "'DiObjectDataExtractor' для получения информации для создания характеристики " +
                "рельсового стыка, получен ответ с кодом '200 OK', но отсутствующим телом ответа.");
    }

}