package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.mini;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocMetaDataDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.*;

/** Класс DTO-объекта, содержащего минимально необходимую информацию для создания характеристики рельсового стыка. */
public final class JointInfoMiniDto extends PObjInfoDto {
    /** Информация о расположении стыка. */
    private final LocationDto location;
    /** Минимально необходимая информация о рельсовом стыке. */
    private final JointMiniDto joint;

    /**
     * Конструктор DTO-класса, принимающий необходимые минимальные данные для дальнейшего создания характеристики стыка.
     *
     * @param location DTO с информацией о расположении стыка
     * @param joint DTO с минимальной информацией о рельсовом стыке
     * @param metaData DTO с метаданными о создаваемом документе
     */
    public JointInfoMiniDto(LocationDto location,
                            JointMiniDto joint,
                            DocMetaDataDto metaData) {
        super(PObjectInfoType.P_OBJ_INFO_JOINT, metaData);
        this.location = location;
        this.joint = joint;
    }

    /**
     * Геттер DTO с данными о стыке, для которого создается характеристика.
     *
     * @return DTO с данными о стыке
     */
    public JointMiniDto getJoint() {
        return joint;
    }

    /**
     * Геттер DTO с данными о расположении стыка.
     *
     * @return DTO с данными о расположении стыка
     */
    public LocationDto getLocation() {
        return location;
    }

    /**
     * Возвращает строковое представление объекта с описанием всех составляющих объектов.
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "JointInfoMiniDto{" +
                "location=" + location +
                ", joint=" + joint +
                ", metaData=" + metaData +
                '}';
    }

}