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
import ru.yappy.rzdengineerassistant.diobjectdataextractor.client.p.DiodePObjInfoClient;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.*;

/**
 * Реализация интерфейса {@link DiodePObjInfoClient}, клиент для запроса информации об объектах путевой инфраструктуры
 * (обслуживаемых предприятиями хозяйства пути дирекции инфраструктуры) с сервера DiObjectDataExtractor.
 */
@Service
public class DiodePObjInfoClientImpl extends DiodeClient implements DiodePObjInfoClient {
    /** Константа, содержащая часть URL-адреса, общую для всех методов-запросов данного клиента. */
    public static final String API_PREFIX = "/api/v1/objinfo/p";

    /**
     * Конструктор объекта-клиента, получающий зависимости из Spring-контейнера.
     *
     * @param serverUrlPrefix префикс URL-адреса сервера, значение содержится в application.properties
     * @param restTemplateBuilder объект-билдер Spring-клиента для выполнения HTTP-запросов
     */
    @Autowired
    public DiodePObjInfoClientImpl(@Value("${diode.server.url.prefix}") String serverUrlPrefix,
                                   RestTemplateBuilder restTemplateBuilder) {
        super(restTemplateBuilder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrlPrefix + API_PREFIX))
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                .build()
        );
    }

    /**
     * Отправляет запрос и обрабатывает ответ от сервера с информацией о рельсе по его id и признаку типа пути (wayType)
     * для создания характиеристики рельса или места излома.
     *
     * @param railId идентификатор рельса
     * @param wayType признак типа пути
     * @return сущность ответа сервера с информацией о рельсе или об ошибке
     * (поле locationDescription объекта {@link RailInfoDto})
     */
    @Override
    public RailInfoDto getRailInfoDtoByIdAndWayType(Long railId, WayType wayType) {
        ResponseEntity<RailInfoDto> responseEntity;
        try {
            responseEntity = restTemplate.exchange(
                    "/rail/{rail_id}?waytype={waytype}",
                    HttpMethod.GET,
                    new HttpEntity<>(createDefaultDiodeHeaders()),
                    RailInfoDto.class,
                    railId, wayType);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new ObjectNotFoundException("полная информация о рельсе", railId);
        } catch (HttpStatusCodeException exception) {
            throw new ExternalServiceException("DiObjectDataExtractor", exception.getStatusCode().value(),
                    exception.getResponseBodyAsString());
        }
        if (responseEntity.getBody() == null) {
            throw new IllegalStateException("При выполнении запроса к сервису 'DiObjectDataExtractor' для получения " +
                    "информации для создания характеристики рельса, получен ответ с кодом '" +
                    responseEntity.getStatusCode() + "', но отсутствующим телом ответа.");
        }
        return responseEntity.getBody();
    }

    /**
     * Отправляет запрос и обрабатывает ответ от сервера с информацией о рельсовом стыке определенного типа по его id,
     * признаку типа пути (wayType) и признаку типа стыка (jointType) для создания характеристики рельсового стыка.
     *
     * @param jointId идентификатор рельсового стыка в базе данных
     * @param wayType признак типа пути
     * @param jointType признак типа стыка
     * @return DTO с информацией о рельсовом стыке или об ошибке (поле locationDescription) объекта {@link JointInfoDto})
     */
    @Override
    public JointInfoDto getJointInfoDtoByIdAndWayTypeAndJointType(Long jointId, WayType wayType, JointType jointType) {
        ResponseEntity<JointInfoDto> responseEntity;
        try {
            responseEntity = restTemplate.exchange(
                    "/joint/{joint_id}?waytype={waytype}&jointtype={jointtype}",
                    HttpMethod.GET,
                    new HttpEntity<>(createDefaultDiodeHeaders()),
                    JointInfoDto.class,
                    jointId, wayType, jointType);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new ObjectNotFoundException("полная информация о рельсовом стыке", jointId);
        } catch (HttpStatusCodeException exception) {
            throw new ExternalServiceException("DiObjectDataExtractor", exception.getStatusCode().value(),
                    exception.getResponseBodyAsString());
        }
        if (responseEntity.getBody() == null) {
            throw new IllegalStateException("При выполнении запроса к сервису 'DiObjectDataExtractor' для получения " +
                    "информации для создания характеристики рельсового стыка, получен ответ с кодом '" +
                    responseEntity.getStatusCode() + "', но отсутствующим телом ответа.");
        }
        return responseEntity.getBody();
    }

}