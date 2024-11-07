package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationNameDto;

/** Класс минимизированного DTO сущности стрелочного перевода железнодорожной станции.
 *
 * @param id идентификатор стрелочного перевода
 * @param number номер стрелочного перевода (тип данных - String, т.к. помимо цифр могут быть буквы)
 * @param station мини-DTO сущности железнодорожной станции
 */
public record SwitchMiniDto(Long id,
                            String number,
                            StationNameDto station) {}