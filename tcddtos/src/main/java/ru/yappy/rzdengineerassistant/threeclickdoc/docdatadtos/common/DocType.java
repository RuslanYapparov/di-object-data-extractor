package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common;

/** Класс-перечисление, содержащий поддерживаемые типы документов. */
public enum DocType implements TcdEnum {
    /** Тип документа - протокол. */
    PROTOCOL,
    /** Тип документа - письмо. */
    LETTER,
    /** Тип документа - справка (характеристика объекта). */
    OBJ_INFO,
    /** Тип документа - акт. */
    ACT,
    /** Тип документа - список работников. */
    WORKER_LIST

}