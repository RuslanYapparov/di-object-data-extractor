package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.rail;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationWayPlaceEntity;

/**
 * Объект-проекция для получения основной (минимальной) информации о токопроводящем рельсовом стыке, лежащем в главном
 * пути; мэппится в объект
 * {@link ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto}.
 */
public interface StationWayJointProjection {

    /** Предоставляет идентификатор рельсового стыка. */
    Long getId();

    /** Предоставляет тип рельсового стыка. */
    String getType();

    /** Предоставляет сторонность рельсовой нити, на которой лежит рельсовый стык. */
    String getLineSide();

    /** Предоставляет объект, описывающий координату места станционного пути, относящуюся к рельсовому стыку. */
    StationWayPlaceEntity getStationWayPlace();

}