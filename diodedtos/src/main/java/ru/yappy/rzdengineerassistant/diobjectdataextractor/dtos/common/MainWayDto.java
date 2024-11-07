package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common;

/** Класс минимизированного DTO сущности главного пути железнодорожного направления.
 *
 * @param id идентификатор главного пути
 * @param transportDirection DTO железнодорожного направления, к которому относится главный путь
 * @param number номер главного пути
 */
public record MainWayDto(Long id,
                         TransportDirectionDto transportDirection,
                         Integer number) {}