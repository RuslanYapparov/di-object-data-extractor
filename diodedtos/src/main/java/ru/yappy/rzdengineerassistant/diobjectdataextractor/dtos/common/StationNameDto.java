package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm.StructuralEnterpriseMiniDto;

/** Класс минимизированного DTO сущности железнодорожной станции. */
public class StationNameDto extends StructuralEnterpriseMiniDto {

    /**
     * Конструктор минимизированного DTO сущности железнодорожной станции.
     *
     * @param id идентификатор станции (структурного предприятия)
     * @param name название станции (структурного предприятия)
     */
    public StationNameDto(Long id, String name) {
        super(id, name);
    }

}