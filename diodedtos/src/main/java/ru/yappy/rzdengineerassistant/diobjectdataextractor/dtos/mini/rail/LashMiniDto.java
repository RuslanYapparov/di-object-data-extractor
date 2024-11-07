package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail;

/** Класс минимизированного DTO сущности рельсовой плети.
 *
 * @param id идентификатор рельсовой плети
 * @param name номер (наименование) рельсовой плети
 * @param lineSide сторонность рельсовой нити, на которой уложена рельсовая плеть
 * @param startCoordinate км.метр начала рельсовой плети
 * @param endCoordinate км.метр конца рельсовой плети
 */
public record LashMiniDto(Long id,
                          String name,
                          String lineSide,
                          Float startCoordinate,
                          Float endCoordinate) {}