package ru.yappy.rzdengineerassistant.diobjectdataextractor.client.p.impl;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.client.p.DiodePProtFailureClient;
import ru.yappy.rzdengineerassistant.commonclasses.exception.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.client.DiodeClient;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.mini.JointFailureProtCreatingDataDto;

/**
 * Реализация интерфейса {@link DiodePProtFailureClient}, клиент для запроса информации, необходимой для работы в
 * пользовательском интерфейсе при создании протокола отказа технических средств хозяйства пути.
 */
@Service
public class DiodePProtFailureClientImpl extends DiodeClient implements DiodePProtFailureClient {
    /** Константа, содержащая часть URL-адреса, общую для всех методов-запросов данного клиента. */
    public static final String API_PREFIX = "/api/v1/protocol/failure/p";

    /**
     * Конструктор объекта-клиента, получающий зависимости из Spring-контейнера.
     *
     * @param serverUrlPrefix префикс URL-адреса сервера, значение содержится в application.properties
     * @param restTemplateBuilder объект-билдер Spring-клиента для выполнения HTTP-запросов
     */
    @Autowired
    public DiodePProtFailureClientImpl(@Value("${diode.server.url.prefix}") String serverUrlPrefix,
                                       RestTemplateBuilder restTemplateBuilder) {
        super(restTemplateBuilder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrlPrefix + API_PREFIX))
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                .build()
        );
    }

    /**
     * Отправляет запрос и обрабатывает ответ от сервера с информацией, необходимой для работы пользователя с данными
     * протокола отказа технических средств хозяйства пути в пользовательском интерфейсе.
     *
     * @param locationDto DTO с данными конкретного места, для которого будет получена нужная информация
     * @param jointType   тип стыка для сбора информации
     * @return DTO c информацией для работы в пользовательском интерфейсе при создании протокола отказа рельсового
     * стыка - массив вариантов стыков определенного типа, список причастных работников, наименование локации
     */
    @Override
    public ResponseEntity<JointFailureProtCreatingDataDto> getJointFailureProtCreatingDataDto(LocationDto locationDto,
                                                                                              JointType jointType) {
        HttpEntity<LocationDto> httpBody = new HttpEntity<>(locationDto, createDefaultDiodeHeaders());
        ResponseEntity<JointFailureProtCreatingDataDto> responseEntity;

        try {
            responseEntity = restTemplate.exchange("/joint?type={jointType}",
                    HttpMethod.GET,
                    httpBody,
                    JointFailureProtCreatingDataDto.class,
                    jointType);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new ObjectNotFoundException("массив вариантов рельсовых стыков с типом " + jointType +
                    " для конкретного места", locationDto.toString());
        } catch (HttpStatusCodeException exception) {
            throw new ExternalServiceException("DiObjectDataExtractor", exception.getStatusCode().value(),
                    exception.getResponseBodyAsString());
        }
        return responseEntity;
    }

}