package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common;

/** Класс минимизированного DTO сущности железнодорожного направления.
 *
 * @param id идентификатор железнодорожного направления
 * @param name наименование железнодорожного направления
 */
public record TransportDirectionDto(Long id,
                                    String name) {}