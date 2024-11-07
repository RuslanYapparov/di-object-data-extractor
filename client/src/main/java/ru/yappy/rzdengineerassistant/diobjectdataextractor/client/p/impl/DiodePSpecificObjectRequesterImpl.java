package ru.yappy.rzdengineerassistant.diobjectdataextractor.client.p.impl;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.yappy.rzdengineerassistant.commonclasses.exception.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.client.DiodeClient;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.client.p.DiodePSpecificObjectRequester;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm.WayMaintenanceDistanceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm.WayMaintenanceDistanceMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.*;

/**
 * Реализация интерфейса {@link DiodePSpecificObjectRequester}, клиент для запроса информации об объектах хозяйства пути,
 * необходимой для работы пользователей в приложении.
 */
@Service
public class DiodePSpecificObjectRequesterImpl extends DiodeClient implements DiodePSpecificObjectRequester {
    /** Константа, содержащая часть URL-адреса, общую для всех методов-запросов данного клиента. */
    private static final String API_PREFIX = "/api/v1/objects/p";

    /**
     * Конструктор объекта-клиента, получающий зависимости из Spring-контейнера.
     *
     * @param serverUrlPrefix префикс URL-адреса сервера, значение содержится в application.properties
     * @param restTemplateBuilder объект-билдер Spring-клиента для выполнения HTTP-запросов
     */
    @Autowired
    public DiodePSpecificObjectRequesterImpl(@Value("${diode.server.url.prefix}") String serverUrlPrefix,
                                             RestTemplateBuilder restTemplateBuilder) {
        super(restTemplateBuilder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrlPrefix + API_PREFIX))
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                .build()
        );
    }

    /**
     * Запрашивает полную информацию о дистанции пути (ПЧ или ИЧ).
     *
     * @param id идентификатор дистанции пути (инфраструктуры)
     * @return сущность ответа сервера с информацией о дистанции пути,
     * содержащейся в объекте {@link WayMaintenanceDistanceDto}
     */
    @Override
    public ResponseEntity<WayMaintenanceDistanceDto> getWayMaintenanceDistanceDtoById(Long id) {
        ResponseEntity<WayMaintenanceDistanceDto> responseEntity;

        try {
            responseEntity = restTemplate.exchange(
                    "/distance/{id}",
                    HttpMethod.GET,
                    new HttpEntity<>(createDefaultDiodeHeaders()),
                    WayMaintenanceDistanceDto.class,
                    id);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new ObjectNotFoundException("дистанция пути", id);
        } catch (HttpStatusCodeException exception) {
            throw new ExternalServiceException("DiObjectDataExtractor", exception.getStatusCode().value(),
                    exception.getResponseBodyAsString());
        }
        return responseEntity;
    }

    /**
     * Запрашивает минимальную информацию для отображения вариантов дистанций пути (дистанций инфраструктуры),
     * находящихся в границах дирекции инфраструктуры по её аббревиатуре.
     *
     * @param directorateAbbreviation аббревиатура дирекции инфраструктуры
     * @return сущность ответа сервера, содержащая массив объектов с минимальной информацией о дистанциях пути
     */
    @Override
    public ResponseEntity<WayMaintenanceDistanceMiniDto[]> getAllWayMaintenanceDistanceMiniDtosByDirectorateAbbreviation(
            String directorateAbbreviation) {
        ResponseEntity<WayMaintenanceDistanceMiniDto[]> responseEntity;

        try {
            responseEntity = restTemplate.exchange(
                    "/distance?dirabbrev={directorateAbbreviation}",
                    HttpMethod.GET,
                    new HttpEntity<>(createDefaultDiodeHeaders()),
                    WayMaintenanceDistanceMiniDto[].class,
                    directorateAbbreviation);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new ObjectNotFoundException("массив вариантов дистанций пути для региональной дирекции инфраструктуры",
                    (Object) directorateAbbreviation);
        } catch (HttpStatusCodeException exception) {
            throw new ExternalServiceException("DiObjectDataExtractor", exception.getStatusCode().value(),
                    exception.getResponseBodyAsString());
        }
        return responseEntity;
    }

    /**
     * Запрашивает минимальную информацию для отображения вариантов рельсов, находящихся вблизи конкретного места,
     * заданного объектом {@link LocationDto}.
     *
     * @param locationDto объект, содержащий информацию о месте, вблизи которого будет выгружена информация о рельсах
     * @return сущность ответа сервера, содержащей массив объектов с минимальной информацией о рельсах
     */
    @Override
    public ResponseEntity<RailMiniDto[]> getRailMiniDtosByLocationDto(LocationDto locationDto) {
        HttpEntity<LocationDto> httpBody = new HttpEntity<>(locationDto, createDefaultDiodeHeaders());
        ResponseEntity<RailMiniDto[]> responseEntity;

        try {
            responseEntity = restTemplate.exchange("/rail",
                    HttpMethod.GET,
                    httpBody,
                    RailMiniDto[].class);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new ObjectNotFoundException("массив вариантов рельсов для конкретного места",
                    locationDto.toString());
        } catch (HttpStatusCodeException exception) {
            throw new ExternalServiceException("DiObjectDataExtractor", exception.getStatusCode().value(),
                    exception.getResponseBodyAsString());
        }
        return responseEntity;
    }

    /**
     * Запрашивает минимальную информацию для отображения вариантов рельсовых стыков определенного типа, находящихся
     * вблизи конкретного места, заданного объектом {@link LocationDto}.
     *
     * @param locationDto объект, содержащий информацию о месте, вблизи которого будет выгружена информация о стыках
     * @param jointType тип рельсовых стыков для выгрузки
     * @return сущность ответа сервера, содержащей массив объектов с минимальной информацией о рельсовых стыках
     */
    @Override
    public ResponseEntity<JointMiniDto[]> getJointMiniDtosByLocationAndType(LocationDto locationDto,
                                                                            JointType jointType) {
        HttpEntity<LocationDto> httpBody = new HttpEntity<>(locationDto, createDefaultDiodeHeaders());
        ResponseEntity<JointMiniDto[]> responseEntity;

        try {
            responseEntity = restTemplate.exchange("/joint?type={jointType}",
                    HttpMethod.GET,
                    httpBody,
                    JointMiniDto[].class,
                    jointType);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new ObjectNotFoundException("массив вариантов рельсовых стыков типа '" + jointType.getRuName() +
                    "' для конкретного места", locationDto.toString());
        } catch (HttpStatusCodeException exception) {
            throw new ExternalServiceException("DiObjectDataExtractor", exception.getStatusCode().value(),
                    exception.getResponseBodyAsString());
        }
        return responseEntity;
    }

}