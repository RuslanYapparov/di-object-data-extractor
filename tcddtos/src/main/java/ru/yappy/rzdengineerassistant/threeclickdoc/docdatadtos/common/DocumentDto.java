package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.ObjInfoDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.ProtocolDto;

import java.util.Objects;

/** Родительский DTO-класс, содержащий информацию, характерную для документов всех видов, оформляемых на предприятии.
 * С помощью аннотаций определяет логику сериализации/десериализации классов наследников. */
public sealed class DocumentDto permits ObjInfoDto, ProtocolDto {
    /**
     * Поле, содержащее тип документа {@link DocType}
     */
    protected final DocType docType;
    /** Содержит информацию о предприятии, подписавшем руководителе, дате подписания,
     * исполнителе документа {@link DocMetaDataDto} */
    protected final DocMetaDataDto metaData;

    /**
     * Конструктор суперкласса для создания документа любого типа
     *
     * @param docType тип документа
     * @param metaData объект DTO метаданных документа {@link DocMetaDataDto}
     */
    public DocumentDto(DocType docType, DocMetaDataDto metaData) {
        this.docType = docType;
        this.metaData = metaData;
    }

    /** Геттер подтипа документа - возвращает значение enum-класса, имплементирующего
     * интерфейс-пометку {@link TcdEnum}
     *
     * @return подтип документа - значение enum-класса, имплементирующего интерфейс-пометку {@link TcdEnum}
     * @throws UnsupportedOperationException если в классе-наследнике не переопределен данный метод.
     * <p>
     *     Было отдано предпочтению использованию sealed-классов, не согласующихся с абстрактными классами и методами,
     *     поэтому применено такое решение
     */
    public TcdEnum getDocSubType() {
        throw new UnsupportedOperationException("Для класса '" + this.getClass().getSimpleName() +
                "' не переопределен метод getDocSubType()");
    }

    /** Геттер типа документа
     *
     * @return тип документа (значение {@link DocType})
     */
    public DocType getDocType() {
        return docType;
    }

    /** Геттер мета-информации о документе {@link DocMetaDataDto}
     *
     * @return мета-информация о документе
     */
    public DocMetaDataDto getMetaData() {
        return metaData;
    }

    /** Проверка на равенство (идентичность) двух объектов, используя их состояние (значения всех полей).
     *
     * @param o другой объект для сравнения
     * @return булевое значение, определяющее равенство объектов
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentDto that)) return false;
        return docType == that.docType && Objects.equals(metaData, that.metaData);
    }

    /** Получение хэш-кода объекта.
     *
     * @return числовое представление объекта на основании его состояния (значений всех полей)
     */
    @Override
    public int hashCode() {
        return Objects.hash(docType, metaData);
    }

    /** Получение строкового представления объекта.
     *
     * @return строка с названием объекта и описанием значений всех полей
     */
    @Override
    public String toString() {
        return "DocumentDto{" +
                "type=" + docType +
                ", metaData=" + metaData +
                '}';
    }

}