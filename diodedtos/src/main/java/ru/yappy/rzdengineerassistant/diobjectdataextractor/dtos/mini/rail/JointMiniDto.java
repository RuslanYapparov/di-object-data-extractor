package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail;

/** Класс минимизированного DTO сущности рельсового стыка.
 *
 * @param id идентификатор рельсового стыка
 * @param type тип рельсового стыка (изолирующий или токопроводящий)
 * @param lineSide сторонность рельсовой нити, на которой расположен рельсовый стык
 * @param coordinate км.метр расположения стыка
 */
public record JointMiniDto(Long id,
                           String type,
                           String lineSide,
                           Float coordinate) {}