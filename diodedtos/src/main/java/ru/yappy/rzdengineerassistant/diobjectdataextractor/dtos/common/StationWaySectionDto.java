package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto;

/**
 * Класс DTO сущности участка станционного пути.
 *
 * @param stationWay объект {@link StationWayMiniDto}, для которого определяется участок
 * @param startMeter значение метра начала участка
 * @param endMeter значение метра конца участка
 */
public record StationWaySectionDto(StationWayMiniDto stationWay,
                                   Integer startMeter,
                                   Integer endMeter) {}