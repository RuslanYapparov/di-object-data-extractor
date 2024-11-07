package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocMetaDataDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.ProtMetaDataDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.ProtocolDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.PProtFailureDto;

import java.util.Objects;

/**
 * Класс в иерархии DTO протоколов, являющийся родительским для всех протоколов по отказам технических средств с
 * распределением по хозяйствам ОАО "РЖД".
 */
public sealed class FailureProtDto extends ProtocolDto permits PProtFailureDto {
    /** Объект, содержащий информацию об отказе. */
    protected final ComplexFailureInfoDto failureInfo;

    /**
     * Конструктор DTO, принимающий в качестве параметров все данные, необходимые для создания любого протокола отказа
     * для каждого из хозяйств ОАО "РЖД".
     *
     * @param metaData метаданные, характерные для любого создаваемого документа
     * @param protMetaData метаданные, характерные для создания протокола любого типа и любого хозяйства
     * @param failureInfo объект, содержащий информацию об отказе
     */
    public FailureProtDto(DocMetaDataDto metaData,
                          ProtMetaDataDto protMetaData,
                          ComplexFailureInfoDto failureInfo) {
        super(metaData, protMetaData);
        this.failureInfo = failureInfo;
    }

    /**
     * Геттер объекта, содержащего информацию об отказе.
     *
     * @return объект, содержащий информацию об отказе
     */
    public ComplexFailureInfoDto getFailureInfo() {
        return failureInfo;
    }

    /** Проверка на равенство (идентичность) двух объектов, используя их состояние (значения всех полей).
     *
     * @param o другой объект для сравнения
     * @return булевое значение, определяющее равенство объектов
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FailureProtDto that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(failureInfo, that.failureInfo) && Objects.equals(protMetaData, that.protMetaData);
    }

    /** Получение хэш-кода объекта.
     *
     * @return числовое представление объекта на основании его состояния (значений всех полей)
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), failureInfo);
    }

}