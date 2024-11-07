package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.*;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocMetaDataDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.JointInfoDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.ProtMetaDataDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.ComplexFailureInfoDto;

import java.util.Objects;

/**
 * Класс DTO, содержащий все данные, необходимые для создания протокола отказа из-за неисправности токопроводящего стыка.
 */
public final class ConductingJointFailureProtDto extends PProtFailureDto {
    /** DTO с исчерпывающей информацией о токопроводящем рельсовом стыке. */
    private final JointInfoDto jointInfoDto;
    /** Тип расположения токопроводящего стыка. */
    private final ConductingJointPlacingType placingType;

    /**
     * Конструктор мини-DTO, принимающий в качестве параметров все данные, необходимые для создания протокола отказа,
     * вызванного неисправностью токопроводящего стыка.
     *
     * @param jointInfoDto DTO с исчерпывающей информацией о рельсовом стыке
     * @param placingType тип расположения токопроводящего стыка
     * @param metaData метаданные, характерные для любого создаваемого документа
     * @param protMetaData метаданные, характерные для создания протокола любого типа
     * @param failureInfo объект, содержащий информацию об отказе
     */
    public ConductingJointFailureProtDto(JointInfoDto jointInfoDto,
                                         ConductingJointPlacingType placingType,
                                         DocMetaDataDto metaData,
                                         ProtMetaDataDto protMetaData,
                                         ComplexFailureInfoDto failureInfo) {
        super(metaData, protMetaData, failureInfo, PProtFailureObjectType.P_FAIL_CONDUCTING_JOINT);
        this.jointInfoDto = jointInfoDto;
        this.placingType = placingType;
    }

    /**
     * Геттер DTO с исчерпывающей информацией о рельсовом стыке.
     *
     * @return DTO с исчерпывающей информацией о рельсовом стыке
     */
    public JointInfoDto getJointInfoDto() {
        return jointInfoDto;
    }

    /**
     * Геттер типа расположения токопроводящего стыка.
     *
     * @return тип расположения токопроводящего стыка
     */
    public ConductingJointPlacingType getPlacingType() {
        return placingType;
    }

    /**
     *  Проверка на равенство (идентичность) двух объектов, используя их состояние (значения всех полей).
     *
     * @param o другой объект для сравнения
     * @return булевое значение, определяющее равенство объектов
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConductingJointFailureProtDto that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(jointInfoDto, that.jointInfoDto) && placingType == that.placingType;
    }

    /** Получение хэш-кода объекта.
     *
     * @return числовое представление объекта на основании его состояния (значений всех полей)
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), jointInfoDto, placingType);
    }

}