package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.rail;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWayPlaceEntity;

/**
 * Объект-проекция для получения основной (минимальной) информации о токопроводящем рельсовом стыке, лежащем в главном
 * пути; мэппится в объект
 * {@link ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto}.
 */
public interface MainWayJointProjection {

    /** Предоставляет идентификатор рельсового стыка. */
    Long getId();

    /** Предоставляет тип рельсового стыка. */
    String getType();

    /** Предоставляет сторонность рельсовой нити, на которой лежит рельсовый стык. */
    String getLineSide();

    /** Предоставляет объект, описывающий координату места главного пути, относящуюся к рельсовому стыку. */
    MainWayPlaceEntity getMainWayPlace();

}