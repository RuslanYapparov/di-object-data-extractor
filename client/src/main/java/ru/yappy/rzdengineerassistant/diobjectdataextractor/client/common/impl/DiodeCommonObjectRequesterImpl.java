package ru.yappy.rzdengineerassistant.diobjectdataextractor.client.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ExternalServiceException;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.client.DiodeClient;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.client.common.DiodeCommonObjectRequester;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto;

/**
 * Реализация интерфейса {@link DiodeCommonObjectRequester}, клиент для запроса информации об объектах и сущностях,
 * характерных для всех хозяйств ОАО "РЖД", необходимой для работы пользователей в приложении.
 */
@Service
public class DiodeCommonObjectRequesterImpl extends DiodeClient implements DiodeCommonObjectRequester {
    /** Константа, содержащая часть URL-адреса, общую для всех методов-запросов данного клиента. */
    public static final String API_PREFIX = "/api/v1/objects/common";

    /**
     * Конструктор объекта-клиента, получающий зависимости из Spring-контейнера.
     *
     * @param serverUrlPrefix префикс URL-адреса сервера, значение содержится в application.properties
     * @param restTemplateBuilder объект-билдер Spring-клиента для выполнения HTTP-запросов
     */
    @Autowired
    public DiodeCommonObjectRequesterImpl(@Value("${diode.server.url.prefix}") String serverUrlPrefix,
                                           RestTemplateBuilder restTemplateBuilder) {
        super(restTemplateBuilder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrlPrefix + API_PREFIX))
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                .build()
        );
    }

    /**
     * Запрашивает минимальную информацию для отображения вариантов станционных путей, находящихся в границах станции
     * по ее идентификатору.
     *
     * @param stationId идентификатор станции
     * @return сущность ответа сервера, содержащая массив объектов с минимальной информацией о станционных путях
     */
    @Override
    public ResponseEntity<StationWayMiniDto[]> getStationWayMiniDtosByStationId(Long stationId) {
        ResponseEntity<StationWayMiniDto[]> responseEntity;

        try {
            responseEntity = restTemplate.exchange(
                    "/stationway/mini/{station_id}",
                    HttpMethod.GET,
                    new HttpEntity<>(createDefaultDiodeHeaders()),
                    StationWayMiniDto[].class,
                    stationId);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new ObjectNotFoundException("массив вариантов станционных путей для станции", stationId);
        } catch (HttpStatusCodeException exception) {
            throw new ExternalServiceException("DiObjectDataExtractor", exception.getStatusCode().value(),
                    exception.getResponseBodyAsString());
        }
        return responseEntity;
    }

}
