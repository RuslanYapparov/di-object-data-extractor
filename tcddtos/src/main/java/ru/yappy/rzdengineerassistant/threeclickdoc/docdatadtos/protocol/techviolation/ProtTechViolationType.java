package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.techviolation;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.TcdEnum;

/**
 * Класс-перечисление, содержащий поддерживаемые типы протоколов расследования технологического нарушения.
 */
public enum ProtTechViolationType implements TcdEnum {
    /** Тип технологического нарушения - неверное отнесение. */
    INCORRECT_ASSIGNMENT,
    /** Тип технологического нарушения - проведение восстановительных работ. */
    RESTORATION_WORK,
    /** Тип технологического нарушения - предупреждения на ограничение скорости. */
    SPEED_LIMIT_WARNING,
    /** Тип технологического нарушения - несогласованность работы со смежными службами. */
    INCONSISTENCY_OF_WORK_PRODUCTION,
    /** Тип технологического нарушения - передержка "окна". */
    TRAFFIC_BREAK_TIME_EXCEEDING,
    /** Тип технологического нарушения - превышение времени остановки для выхода рабочих. */
    WORKER_EXITING_STOP_TIME_EXCEEDING,
    /** Тип технологического нарушения - неверная заявка на остановку для выхода рабочих. */
    WORKER_EXITING_WRONG_REQUEST,
    /** Тип технологического нарушения - неперевод стрелочного перевода. */
    SWITCH_DID_NOT_SWITCH,
    /** Тип технологического нарушения - включение заградительной сигнализации на переезде. */
    ROAD_CROSSING_SIGNALIZATION_ACTIVATION,
    /** Тип технологического нарушения - ДТП на переезде. */
    ROAD_CROSSING_ROAD_ACCIDENT,
    /** Тип технологического нарушения - предотвращение наезда на человека. */
    HUMAN_POUNDING_PREVENTING,
    /** Тип технологического нарушения - предотвращение наезда на животное. */
    ANIMAL_POUNDING_PREVENTING,
    /** Тип технологического нарушения - предотвращение наезда на объект. */
    OBJECT_POUNDING_PREVENTING,
    /** Тип технологического нарушения - несанкционированное вмешательство в работу ЖД транспорта. */
    ILLEGITIMATE_INTERVENTION

}
