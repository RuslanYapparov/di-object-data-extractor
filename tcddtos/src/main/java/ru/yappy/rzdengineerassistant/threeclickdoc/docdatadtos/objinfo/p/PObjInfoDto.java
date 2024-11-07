package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocMetaDataDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.ObjInfoDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.mini.JointInfoMiniDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.mini.RailInfoMiniDto;

/**
 * Родительский DTO-класс, содержащий свойства, характерные для всех типов документов-характеристик (объектов)
 * хозяйства пути. С помощью аннотаций определяет логику сериализации/десериализации классов наследников.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "pObjInfoDtoType")
@JsonSubTypes({ @JsonSubTypes.Type(value = RailInfoMiniDto.class, name = "RAIL_MINI"),
        @JsonSubTypes.Type(value = RailInfoDto.class, name = "RAIL_FULL"),
        @JsonSubTypes.Type(value = JointInfoMiniDto.class, name = "JOINT_MINI"),
        @JsonSubTypes.Type(value = JointInfoDto.class, name = "JOINT_FULL")
})
public sealed class PObjInfoDto extends ObjInfoDto permits RailInfoMiniDto, RailInfoDto,
        JointInfoMiniDto, JointInfoDto {
    /** Поле, содержащее тип объекта хозяйства пути, характеристика которого будет создана. */
    protected final PObjectInfoType objectInfoType;

    /**
     * Конструктор DTO-суперкласса, определяющий свойства, характерные для всех типов
     * документов-характеристик (объектов хозяйства пути).
     *
     * @param objectInfoType тип объекта, характеристика которого будет создана
     * @param metaData метаданные будущего документа
     */
    public PObjInfoDto(PObjectInfoType objectInfoType, DocMetaDataDto metaData) {
        super(metaData);
        this.objectInfoType = objectInfoType;
    }

    /**
     * Переопределенный геттер {@link PObjectInfoType}-значения, характерного для конкретного вида характеристики
     * объекта.
     *
     * @return тип объекта, характеристика которого будет создана
     */
    @Override
    public PObjectInfoType getDocSubType() {
        return objectInfoType;
    }

    /** Получение строкового представления объекта.
     *
     * @return строка с названием объекта и описанием значений всех полей
     */
    @Override
    public String toString() {
        return "PObjInfoDto{" +
                "objectInfoType=" + objectInfoType +
                ", docType=" + docType +
                ", metaData=" + metaData +
                '}' +
                " ВНИМАНИЕ! Для класса '" + this.getClass().getSimpleName() + "' не переопределен метод toString()";
    }

}