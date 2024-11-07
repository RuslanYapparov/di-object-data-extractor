package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.TcdEnum;

/**
 * Класс-перечисление, содержащий поддерживаемые типы протоколов.
 */
public enum ProtocolType implements TcdEnum {
    /** Тип протокола расследования отказа технического средства. */
    PROT_TECHNICAL_FAILURE,
    /** Тип протокола расследования технологического нарушения. */
    PROT_TECHNOLIGICAL_VIOLATION,
    /** Тип протокола расследования задержки поезда. */
    PROT_TRAIN_DELAY,
    /** Тип протокола расследования нарушения правил эксплуатации железнодорожного транспорта и безопасности движения
     *  поездов. */
    PROT_TRAFFIC_SAFETY_VIOLATION,
    /** Тип протокола проведенного дня культуры безопасности. */
    PROT_TRAFFIC_SAFETY_CULTURE_DAY,
    /** Тип протокола разбора результатов прохода вагона-путеизмерителя. */
    PROT_WAY_MEASURE_CAR_RESULTS,
    /** Тип протокола разбора подведения итогов работы подразделения за месяц. */
    PROT_MONTHLY_SUMMARY,
    /** Тип протокола, касающегося выполнения нормативов руководителями подразделения. */
    PROT_COMPILANCE_OF_MANAGERS_STANDARDS,
    /** Тип протокола разбора результатов сезонного осмотра пути и сооружений. */
    PROT_INSPECTION_OF_WAY_AND_STRUCTURES

}