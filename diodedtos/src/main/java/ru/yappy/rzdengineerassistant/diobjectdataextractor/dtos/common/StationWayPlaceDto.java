package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto;

/** Класс DTO сущности конкретного места станционного пути железнодорожной станции.
 *
 * @param stationWay мини-DTO сущности станционного пути железнодорожной станции
 * @param meterOnStationWay метр станционного пути железнодорожной станции, на котором находится описываемое место
 */
public record StationWayPlaceDto(StationWayMiniDto stationWay,
                                 Integer meterOnStationWay) {}