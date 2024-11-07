package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common;

/** Класс DTO сущности конкретного места главного пути железнодорожного направления.
 *
 * @param transportDirection железнодорожное направление, на котором находится описываемое место
 * @param km километр главного пути железнодорожного направления, на котором находится описываемое место
 * @param meterOnKm метр километра главного пути железнодорожного направления, на котором находится описываемое место
 */
public record MainWayPlaceDto(TransportDirectionDto transportDirection,
                              Integer km,
                              Integer meterOnKm) {}