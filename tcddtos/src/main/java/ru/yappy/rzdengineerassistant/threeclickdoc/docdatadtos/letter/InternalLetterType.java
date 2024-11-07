package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.letter;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.TcdEnum;

/**
 * Класс-перечисление, содержащий поддерживаемые типы внутренних писем.
 */
public enum InternalLetterType implements TcdEnum {
    /** Тип внутреннего письма. */
    INTERNAL_LETTER,
    /** Тип телеграммы. */
    TELEGRAM,
    /** Тип рапорта. */
    REPORT

}