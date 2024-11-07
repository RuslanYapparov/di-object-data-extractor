package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.infr;

/**
 * Объект-проекция для получения основной (минимальной) информации о станционном пути - идентификатор, номер
 *  и минимальную информацию о стации, мэппится в объект
 *  {@link ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto}
 */
public interface StationWayProjection {

    /**
     * Предоставляет идентификатор станционного пути.
     *
     * @return идентификатор станционного пути
     */
    Long getId();

    /**
     * Предоставляет номер станционного пути.
     *
     * @return номер станционного пути
     */
    String getName();

    /**
     * Предоставляет минимальную информацию о станции, к которой относится.
     *
     * @return минимальная информация о станции
     */
    StationProjection getStation();

}