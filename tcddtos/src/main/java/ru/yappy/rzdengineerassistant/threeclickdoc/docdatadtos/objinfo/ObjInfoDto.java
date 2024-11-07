package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocMetaDataDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocType;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocumentDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.PObjInfoDto;

/**
 * Родительский DTO-класс для всех типов документов-характеристик (характеристик объектов) всех хозяйств.
 */
public sealed class ObjInfoDto extends DocumentDto permits PObjInfoDto {

    /**
     * Конструктор DTO-суперкласса, определяющий свойства, характерные для всех типов
     * документов-характеристик (объектов).
     *
     * @param metaData метаданные будущего документа
     */
    public ObjInfoDto(DocMetaDataDto metaData) {
        super(DocType.OBJ_INFO, metaData);
    }

}