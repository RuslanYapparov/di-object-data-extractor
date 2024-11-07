package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationWayPlaceDto;

/**
 * Интерфейс, создающий тип DTO рельсового стыка, расположенного на станционном пути.
 */
public interface StationWayJoint {

    /**
     * Геттер DTO конкретного места станционного пути, на котором расположен стык.
     *
     * @return DTO конкретного места станционного пути
     */
    StationWayPlaceDto getStationWayPlace();

}
