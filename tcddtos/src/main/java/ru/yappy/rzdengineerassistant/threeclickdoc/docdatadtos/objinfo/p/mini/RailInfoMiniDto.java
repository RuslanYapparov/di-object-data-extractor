package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.mini;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.RailMiniDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocMetaDataDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.*;

import java.util.Objects;

/**
 * DTO-класс, хранящий минимальную информацию о рельсе для создания документа-характеристики, собранную от пользователя.
 */
public final class RailInfoMiniDto extends PObjInfoDto {
    /** Информация о расположении рельса. */
    private final LocationDto location;
    /** Информация о самом рельсе. */
    private final RailMiniDto rail;
    /** Информация, необходимая для создания характеристики места излома. */
    private final BrokenRailExtraInfoDto brokenRailExtraInfo;

    /** Конструктор мини-DTO-класса, принимающий 4 основных объекта, хранящих информацию для создания характеристики. */
    public RailInfoMiniDto(LocationDto location,
                           RailMiniDto rail,
                           BrokenRailExtraInfoDto brokenRailExtraInfo,
                           DocMetaDataDto metaData) {
        super(PObjectInfoType.P_OBJ_INFO_RAIL, metaData);
        this.location = location;
        this.rail = rail;
        this.brokenRailExtraInfo = brokenRailExtraInfo;
    }

    /** Проверка на равенство (идентичность) двух объектов, используя их состояние (значения всех полей).
     *
     * @param o другой объект для сравнения
     * @return булевое значение, определяющее равенство объектов
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RailInfoMiniDto that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(location, that.location) &&
                Objects.equals(rail, that.rail) &&
                Objects.equals(brokenRailExtraInfo, that.brokenRailExtraInfo);
    }

    /** Получение хэш-кода объекта.
     *
     * @return числовое представление объекта на основании его состояния (значений всех полей)
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), location, rail, brokenRailExtraInfo);
    }

    /** Получение строкового представления объекта.
     *
     * @return строка с названием объекта и описанием значений всех полей
     */
    @Override
    public String toString() {
        return "RailInfoMiniDto{" +
                "location=" + location +
                ", rail=" + rail +
                ", brokenRailExtraInfo=" + brokenRailExtraInfo +
                ", PObjectInfoType=" + objectInfoType +
                ", docType=" + docType +
                ", metaData=" + metaData +
                '}';
    }

    /**
     * Геттер {@link LocationDto} объекта.
     *
     * @return DTO-объект, хранящий информацию о расположении рельса
     */
     public LocationDto getLocation() {
        return location;
    }

    /**
     * Геттер {@link RailMiniDto} объекта.
     *
     * @return DTO-объект, хранящий информацию о самом рельсе
     */
    public RailMiniDto getRail() {
        return rail;
    }

    /**
     * Геттер {@link BrokenRailExtraInfoDto} объекта.
     *
     * @return DTO-объект, хранящий дополнительную информацию о месте излома
     */
    public BrokenRailExtraInfoDto getBrokenRailExtraInfo() {
        return brokenRailExtraInfo;
    }

}