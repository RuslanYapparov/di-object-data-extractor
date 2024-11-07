package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail;

/** Класс минимизированного DTO сущности рельса.
 *
 * @param id идентификатор рельса
 * @param lineSide сторонность рельсовой нити
 * @param railKind вид пути, на котором уложен рельс
 * @param startCoordinate км.метр начала рельса
 * @param endCoordinate км.метр конца рельса
 */
public record RailMiniDto(Long id,
                          String lineSide,
                          String railKind,
                          Float startCoordinate,
                          Float endCoordinate) {}