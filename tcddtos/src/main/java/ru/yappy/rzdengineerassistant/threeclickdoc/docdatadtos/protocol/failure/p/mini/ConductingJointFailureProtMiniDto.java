package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.mini;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.ConductingJointPlacingType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocMetaDataDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.ProtMetaDataDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.ComplexFailureInfoDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.*;

/**
 * DTO-класс, содержащий минимальную информацию, получаемую от пользователя из UI и необходимую для создания протокола
 * отказа из-за неисправности токопроводящего стыка.
 */
public final class ConductingJointFailureProtMiniDto extends PProtFailureDto {
    /** Минимальная информация о токопроводящем стыке, выбранная пользователем из предложенных вариантов. */
    private final JointMiniDto joint;
    /** Вариант перечисления, характеризующий тип пути (перегон, главный на станции, станционный), на котором
     * находится выбранный стык. */
    private final WayType wayType;
    /** Вариант перечисления, характеризующий тип места, на котором оборудован токопроводящий стык. */
    private final ConductingJointPlacingType placingType;

    /**
     * Конструктор мини-DTO, принимающий в качестве параметров все данные, необходимые для создания протокола отказа.
     *
     * @param joint DTO с минимальной информацией о токопроводящем стыке
     * @param wayType тип пути (перегон, станционный главный, станционный), на котором находится выбранный стык
     * @param placingType тип места, на котором оборудован токопроводящий стык
     * @param metaData метаданные, характерные для любого создаваемого документа
     * @param protMetaData метаданные, характерные для создания протокола любого типа
     * @param failureInfo объект, содержащий информацию об отказе
     */
    public ConductingJointFailureProtMiniDto(JointMiniDto joint,
                                             WayType wayType,
                                             ConductingJointPlacingType placingType,
                                             DocMetaDataDto metaData,
                                             ProtMetaDataDto protMetaData,
                                             ComplexFailureInfoDto failureInfo) {
        super(metaData, protMetaData, failureInfo, PProtFailureObjectType.P_FAIL_CONDUCTING_JOINT);
        this.joint = joint;
        this.wayType = wayType;
        this.placingType = placingType;
    }

    /**
     * Геттер минимальной информации о токопроводящем стыке.
     *
     * @return DTO с минимальной информацией о токопроводящем стыке
     */
    public JointMiniDto getJoint() {
        return joint;
    }

    /**
     * Геттер варианта перечисления типа пути, на котором находится выбранный стык.
     *
     * @return тип пути (перегон, главный на станции, станционный), на котором находится выбранный стык
     */
    public WayType getWayType() {
        return wayType;
    }

    /**
     * Геттер варианта перечисления типа места, на котором оборудован токопроводящий стык.
     *
     * @return тип места, на котором оборудован токопроводящий стык
     */
    public ConductingJointPlacingType getPlacingType() {
        return placingType;
    }

}