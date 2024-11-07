package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.rail;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWaySectionEntity;

/**
 * Объект-проекция для получения основной (минимальной) информации о рельсе, лежащем на главном пути; мэппится в объект
 * {@link ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.RailMiniDto}.
 */
public interface MainWayRailProjection {

    /** Предоставляет идентификатор рельса. */
    Long getId();

    /** Предоставляет сторонность рельсовой нити, на которой лежит рельс. */
    String getLineSide();

    /** Предоставляет вид верхнего строения пути, к которому относится рельс. */
    String getRailKind();

    /** Предоставляет объект, описывающий участок главного пути, относящийся к рельсу. */
    MainWaySectionEntity getMainWaySection();

}