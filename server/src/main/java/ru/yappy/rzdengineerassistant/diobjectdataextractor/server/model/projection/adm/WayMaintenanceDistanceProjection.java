package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.adm;

/** Объект-проекция, для получения основной информации о дистанции пути, мэппится в объект
 * {@link ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm.WayMaintenanceDistanceMiniDto}.
 */
public interface WayMaintenanceDistanceProjection {

    /** Предоставляет идентификатор дистанции пути. */
    Long getId();

    /** Предоставляет название дистанции пути. */
    String getName();

    /** Предоставляет номер дистанции пути. */
    Integer getNumber();

    /** Предоставляет признак дистанции инфраструктуры. */
    Boolean getIsIch();

}