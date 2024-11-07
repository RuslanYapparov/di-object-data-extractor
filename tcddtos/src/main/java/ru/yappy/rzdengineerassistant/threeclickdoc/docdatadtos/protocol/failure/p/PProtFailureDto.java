package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p;

import com.fasterxml.jackson.annotation.*;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocMetaDataDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.TcdEnum;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.ProtMetaDataDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.*;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.mini.ConductingJointFailureProtMiniDto;

/**
 * Класс в иерархии DTO протоколов, являющийся родительским для всех протоколов по отказам технических средств,
 * возникающих в путевом хозяйстве ОАО "РЖД". С помощью аннотаций определяет логику сериализации/десериализации
 * финальных классов наследников.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "pProtFailType")
@JsonSubTypes({ @JsonSubTypes.Type(value = ConductingJointFailureProtMiniDto.class, name = "COND_JOINT_MINI"),
        @JsonSubTypes.Type(value = ConductingJointFailureProtDto.class, name = "COND_JOINT_FULL")
})
public sealed class PProtFailureDto extends FailureProtDto permits ConductingJointFailureProtDto,
        ConductingJointFailureProtMiniDto {
    /** Отражает тип объекта путевого хозяйства, из-за неисправности которого произошел отказ. */
    protected final PProtFailureObjectType protObjectType;

    /**
     * Конструктор DTO, принимающий в качестве параметров данные, необходимые для создания любого протокола отказа в
     * работе технического средства, относящегося к путевому хозяйству ОАО "РЖД".
     *
     * @param metaData метаданные, характерные для любого создаваемого документа
     * @param protMetaData метаданные, характерные для создания протокола любого типа
     * @param failureInfo объект, содержащий информацию об отказе
     * @param protObjectType тип отказавшего объекта путевого хозяйства
     */
    public PProtFailureDto(DocMetaDataDto metaData,
                           ProtMetaDataDto protMetaData,
                           ComplexFailureInfoDto failureInfo,
                           PProtFailureObjectType protObjectType) {
        super(metaData, protMetaData, failureInfo);
        this.protObjectType = protObjectType;
    }

    /**
     * Переопределенный геттер {@link TcdEnum}-значения, определяющий подтип протокола и содержащего информацию о типе
     * отказавшего объекта путевого хозяйства.
     *
     * @return подтип протокола с отражением типа отказавшего объекта путевого хозяйства
     */
    @Override
    public TcdEnum getDocSubType() {
        return protObjectType;
    }

}