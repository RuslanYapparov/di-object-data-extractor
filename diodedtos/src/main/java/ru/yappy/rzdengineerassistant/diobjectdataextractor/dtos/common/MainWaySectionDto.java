package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common;

/**
 * Класс DTO сущности участка главного пути какого-либо железнодорожного направления.
 *
 * @param transportDirection объект {@link TransportDirectionDto}, для которого определяется участок пути
 * @param startKm значение километра начала участка
 * @param startMeter значение метра начала участка
 * @param endKm значение километра конца участка
 * @param endMeter значение метра конца участка
 */
public record MainWaySectionDto(
        TransportDirectionDto transportDirection,
        Integer startKm,
        Integer startMeter,
        Integer endKm,
        Integer endMeter) {}