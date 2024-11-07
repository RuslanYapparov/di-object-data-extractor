package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocMetaDataDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocType;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocumentDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.TcdEnum;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.FailureProtDto;

/**
 * Родительский DTO-класс для протоколов всех типов и всех хозяйств.
 */
public sealed class ProtocolDto extends DocumentDto permits FailureProtDto {
    /**
     * Объект, содержащий данные, характерные и нужные для протокола любого типа.
     */
    protected final ProtMetaDataDto protMetaData;

    /**
     * Конструктор DTO-суперкласса, определяющий свойства, характерные для всех типов протоколов.
     *
     * @param metaData метаданные, характерные для любого создаваемого документа
     * @param protMetaData объект, содержащий данные, характерные для создаваемого протокола любого типа и
     *                    любого хозяйства
     */
    public ProtocolDto(DocMetaDataDto metaData, ProtMetaDataDto protMetaData) {
        super(DocType.PROTOCOL, metaData);
        this.protMetaData = protMetaData;
    }

    /**
     * Переопределенный геттер {@link ProtocolType}-значения, определяющий тип создаваемого протокола.
     *
     * @return тип создаваемого протокола
     */
    @Override
    public TcdEnum getDocSubType() {
        return protMetaData.type();
    }

    /**
     * Геттер метаданных создаваемого протокола.
     *
     * @return DTO c метаданными создаваемого протокола
     */
    public ProtMetaDataDto getProtMetaData() {
        return protMetaData;
    }

}