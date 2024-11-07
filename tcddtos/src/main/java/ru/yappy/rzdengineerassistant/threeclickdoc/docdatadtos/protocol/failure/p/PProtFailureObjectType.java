package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.TcdEnum;

/**
 * Класс-перечисление, содержащий поддерживаемые типы отказавших объектов в протоколе расследования отказа.*
 */
public enum PProtFailureObjectType implements TcdEnum {
    /** Тип отказавшего объекта - путь в целом. */
    P_FAIL_WAY_COMPLEX,
    /** Тип отказавшего объекта - изолирующий стык. */
    P_FAIL_ISOLATING_JOINT,
    /** Тип отказавшего объекта - токопроводящий стык. */
    P_FAIL_CONDUCTING_JOINT,
    /** Тип отказавшего объекта - рельс. */
    P_FAIL_RAIL,
    /** Тип отказавшего объекта - шпала. */
    P_FAIL_SLEEPER,
    /** Тип отказавшего объекта - рельсовое скрепление. */
    P_FAIL_RAIL_FASTENING,
    /** Тип отказавшего объекта - балласт. */
    P_FAIL_BALLAST,
    /** Тип отказавшего объекта - стрелочный перевод. */
    P_FAIL_SWITCH

}