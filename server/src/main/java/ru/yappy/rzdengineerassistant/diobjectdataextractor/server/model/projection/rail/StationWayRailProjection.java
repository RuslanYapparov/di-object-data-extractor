package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.rail;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationWaySectionEntity;

/**
 * Объект-проекция для получения основной (минимальной) информации о рельсе, лежащем в станционном пути; мэппится
 * в объект {@link ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.StationWayRailDto}.
 */
public interface StationWayRailProjection {
    /** Предоставляет идентификатор рельса. */
    Long getId();

    /** Предоставляет сторонность рельсовой нити, на которой лежит рельс. */
    String getLineSide();

    /** Предоставляет вид верхнего строения пути, к которолм уотносится рельс. */
    String getRailKind();

    /** Предоставляет объект, содержащий информацию об участке станционного пути, относящися к рельсу. */
    StationWaySectionEntity getStationWaySection();

}
