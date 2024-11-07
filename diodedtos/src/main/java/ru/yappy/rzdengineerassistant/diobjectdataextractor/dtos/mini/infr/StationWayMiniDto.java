package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationNameDto;

/** Класс минимизированного DTO сущности станционного пути железнодорожной станции.
 *
 * @param id идентификатор станционного пути
 * @param number номер (название) станционного пути (тип данных - String, т.к. Помимо цифр могут быть буквы)
 * @param station мини-DTO сущности железнодорожной станции
 */
public record StationWayMiniDto(Long id,
                                String number,
                                StationNameDto station) {}