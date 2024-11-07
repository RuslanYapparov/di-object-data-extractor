package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.RailDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.wayc.WayCharacteristicsDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocMetaDataDto;

import java.util.Objects;

/** Класс DTO-объекта, содержащего исчерпывающую информацию для создания характеристики рельса или места излома. */
public final class RailInfoDto extends PObjInfoDto {
    /** Строка с названием конкретной локации (например, "перегон Кое-гденьск - Где-либо" или "станция Где-нибудьск"). */
    private final String locationDescription;
    /** Информация о характеристиках пути в конкретном месте. */
    private final WayCharacteristicsDto wayCharacteristics;
    /** Информация о самом рельсе. */
    private final RailDto rail;
    /** Информация, необходимая для создания характеристики места излома. */
    private final BrokenRailExtraInfoDto brokenRailExtraInfo;

    /** Конструктор DTO-класса, принимающий все параметры, хранящие информацию для создания характеристики. */
    public RailInfoDto(String locationDescription,
                       WayCharacteristicsDto wayCharacteristics,
                       RailDto rail,
                       BrokenRailExtraInfoDto brokenRailExtraInfo,
                       DocMetaDataDto metaData) {
        super(PObjectInfoType.P_OBJ_INFO_RAIL, metaData);
        this.locationDescription = locationDescription;
        this.wayCharacteristics = wayCharacteristics;
        this.rail = rail;
        this.brokenRailExtraInfo = brokenRailExtraInfo;
    }

    /**
     * Геттер DTO-объекта, хранящего полную информацию о рельсе.
     *
     * @return полная информация о рельсе
     */
    public RailDto getRail() {
        return rail;
    }

    /**
     * Геттер DTO-объекта, хранящего информацию об изломе рельса.
     *
     * @return информация об изломе рельса в виде специального объекта
     */
    public BrokenRailExtraInfoDto getBrokenRailExtraInfo() {
        return brokenRailExtraInfo;
    }

    /**
     * Геттер строки с названием локации.
     *
     * @return название локации
     */
    public String getLocationDescription() {
        return locationDescription;
    }

    /**
     * Геттер DTO, хранящего информацию о характеристиках пути в конкретном месте.
     *
     * @return информация о характеристиках пути в конкретном месте
     */
    public WayCharacteristicsDto getWayCharacteristics() {
        return wayCharacteristics;
    }

    /** Проверка на равенство (идентичность) двух объектов, используя их состояние (значения всех полей).
     *
     * @param o другой объект для сравнения
     * @return булевое значение, определяющее равенство объектов
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RailInfoDto that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(locationDescription, that.locationDescription) &&
                Objects.equals(wayCharacteristics, that.wayCharacteristics) &&
                Objects.equals(rail, that.rail) &&
                Objects.equals(brokenRailExtraInfo, that.brokenRailExtraInfo);
    }

    /** Получение хэш-кода объекта.
     *
     * @return числовое представление объекта на основании его состояния (значений всех полей)
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), locationDescription, wayCharacteristics, rail, brokenRailExtraInfo);
    }

}