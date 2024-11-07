package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.letter;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.TcdEnum;

/**
 * Класс-перечисление, содержащий поддерживаемые типы писем.
 */
public enum LetterType implements TcdEnum {
    /** Тип письма, оформляемого для отправки адресатам внутри холдинга ОАО"РЖД". */
    LETTER_INTERNAL,
    /** Тип письма, оформляемого для отправки внешним контрагентам. */
    LETTER_EXTERNAL

}