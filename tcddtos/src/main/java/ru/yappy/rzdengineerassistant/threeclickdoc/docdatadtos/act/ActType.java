package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.act;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.TcdEnum;

/**
 * Класс-перечисление, содержащий поддерживаемые типы актов.
 */
public enum ActType implements TcdEnum {
    /** Тип акта осмотра изолирующего стыка. */
    ACT_ISOLATED_JOINT_INSPECTION,
    /** Тип акта объезда участка руководителем подразделения в кабине локомотива. */
    ACT_LOCOMOTIVE_CAB_DETOUR

}