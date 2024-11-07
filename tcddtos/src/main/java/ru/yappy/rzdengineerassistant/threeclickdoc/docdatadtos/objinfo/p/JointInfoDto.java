package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.wayc.WayCharacteristicsDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocMetaDataDto;

import java.util.Objects;

/** Класс DTO со всей необходимой информацией для создания характеристики рельсового стыка. */
public final class JointInfoDto extends PObjInfoDto {
    /** Строка с названием конкретной локации (например, "перегон Кое-гденьск - Где-либо" или "станция Где-нибудьск"). */
    private final String locationDescription;
    /** DTO с информацией о расположении стыка из базы паспортных данных. */
    private final LocationDto jointLocation;
    /** DTO с информацией о характеристиках пути в конкретном месте. */
    private final WayCharacteristicsDto wayCharacteristics;
    /** DTO с информацией о самом стыке. */
    private final JointDto joint;

    /**
     * Конструктор DTO класса, принимающий все параметры с данными, необходимыми для создания характеристики.
     *
     * @param locationDescription строка с названием локации
     * @param jointLocation DTO с информацией о расположении стыка
     * @param wayCharacteristics DTO с информацией о характеристиках пути
     * @param joint DTO с информацией о стыке
     * @param metaData DTO с метаданными создаваемого документа
     */
    public JointInfoDto(String locationDescription,
                        LocationDto jointLocation,
                        WayCharacteristicsDto wayCharacteristics,
                        JointDto joint,
                        DocMetaDataDto metaData) {
        super(PObjectInfoType.P_OBJ_INFO_JOINT, metaData);
        this.locationDescription = locationDescription;
        this.jointLocation = jointLocation;
        this.wayCharacteristics = wayCharacteristics;
        this.joint = joint;
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
     * Геттер DTO с информацией о расположении стыка.
     *
     * @return DTO с информацией о расположении стыка
     */
    public LocationDto getJointLocation() {
        return jointLocation;
    }

    /**
     * Геттер DTO с информацией о характеристиках пути в конкретном месте.
     *
     * @return DTO с информацией о характеристиках пути
     */
    public WayCharacteristicsDto getWayCharacteristics() {
        return wayCharacteristics;
    }

    /**
     * Геттер DTO с информацией о самом стыке.
     *
     * @return DTO с информацией о стыке
     */
    public JointDto getJoint() {
        return joint;
    }

    /** Проверка на равенство (идентичность) двух объектов, используя их состояние (значения всех полей).
     *
     * @param o другой объект для сравнения
     * @return булевое значение, определяющее равенство объектов
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JointInfoDto that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(locationDescription, that.locationDescription) &&
                Objects.equals(jointLocation, that.jointLocation) &&
                Objects.equals(wayCharacteristics, that.wayCharacteristics) &&
                Objects.equals(joint, that.joint);
    }

    /** Получение хэш-кода объекта.
     *
     * @return числовое представление объекта на основании его состояния (значений всех полей)
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), locationDescription, jointLocation, wayCharacteristics, joint);
    }

}