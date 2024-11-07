package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.infr;

/**
 * Объект-проекция для получения основной (минимальной) информации о станции - идентификатор и название;
 * мэппится в объект {@link ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationNameDto}
 */
public interface StationProjection {

    /**
     * Предоставляет идентификатор станции
     *
     * @return идентификатор станции
     */
    Long getId();

    /**
     * Предоставляет название станции
     *
     * @return название станции
     */
    String getName();

}