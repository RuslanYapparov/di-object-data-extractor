package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm;

/** Класс минимизированного DTO сущности линейного участка дистанции пути.
 *
 * @param id идентификатор линейного участка дистанции пути
 * @param number номер линейного участка дистанции пути
 */
public record WayLinearSectionMiniDto(Long id,
                                      Integer number) {}
