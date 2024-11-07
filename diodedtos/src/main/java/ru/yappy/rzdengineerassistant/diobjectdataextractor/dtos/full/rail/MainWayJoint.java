package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.MainWayPlaceDto;

/**
 * Интерфейс, создающий тип DTO рельсового стыка, расположенного в главном пути.
 */
public interface MainWayJoint {

    /**
     * Геттер номера главного пути.
     *
     * @return номер главного пути
     */
    Integer getMainWayNumber();

    /**
     * Геттер DTO конкретного места главного пути, на котором расположен стык.
     *
     * @return DTO конкретного места главного пути
     */
    MainWayPlaceDto getMainWayPlace();

}
