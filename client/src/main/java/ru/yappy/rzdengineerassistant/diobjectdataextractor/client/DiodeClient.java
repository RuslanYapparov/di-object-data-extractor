package ru.yappy.rzdengineerassistant.diobjectdataextractor.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Абстрактный класс, содержащий поля и методы, характерные для всех объектов-клиентов модуля DiObjectDataExtractor.
 */
public abstract class DiodeClient {
    /** Spring-клиент, используемый для выполнения HTTP-запросов. */
    protected final RestTemplate restTemplate;

    /**
     * Конструктор суперкласса клиентов, принимающий объект {@link RestTemplate}.
     *
     * @param restTemplate Spring-клиент, используемый для выполнения HTTP-запросов
     */
    protected DiodeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Создает дефолтные HTTP-заголовки, используемые в запросах к серверу DiObjectDataExtractor.
     *
     * @return HTTP-заголовки
     */
    protected HttpHeaders createDefaultDiodeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

}