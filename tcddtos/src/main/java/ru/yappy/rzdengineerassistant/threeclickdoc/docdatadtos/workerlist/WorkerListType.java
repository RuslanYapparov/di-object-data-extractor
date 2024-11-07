package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.workerlist;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.TcdEnum;

/**
 * Класс-перечисление, содержащий поддерживаемые типы списков работников.
 */
public enum WorkerListType implements TcdEnum {
    /** Тип списка присутствовавших работников. */
    WORKERS_VISITED,
    /** Тип списка ознакомления работников. */
    WORKERS_INFORMED

}