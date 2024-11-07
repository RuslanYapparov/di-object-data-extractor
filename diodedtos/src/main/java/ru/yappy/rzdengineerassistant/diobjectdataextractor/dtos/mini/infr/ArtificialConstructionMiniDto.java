package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr;

/** Класс минимизированного DTO сущности искусственного сооружения.
 *
 * @param id идентификатор искусственного сооружения
 * @param type тип искусственного сооружения
 * @param name наименование искусственного сооружения
 */
public record ArtificialConstructionMiniDto(Long id,
                                            String type,
                                            String name) {}