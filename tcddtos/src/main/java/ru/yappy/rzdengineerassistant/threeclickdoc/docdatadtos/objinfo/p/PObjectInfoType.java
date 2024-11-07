package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.TcdEnum;

/**
 * Класс-перечисление, содержащий поддерживаемые типы объектов хозяйства пути для создания их характеристик
 * (документов-характеристик).
 */
public enum PObjectInfoType implements TcdEnum {
    /** Тип объекта для создания документа-характеристики - рельс. */
    P_OBJ_INFO_RAIL,
    /** Тип объекта для создания документа-характеристики - рельсовый стык, путейские показатели. */
    P_OBJ_INFO_JOINT,
    /** Тип объекта для создания документа-характеристики - искусственное сооружение. */
    P_OBJ_INFO_ISSO,
    /** Тип объекта для создания документа-характеристики - стрелочный перевод, путейские показатели. */
    P_OBJ_INFO_SWITCH,
    /** Тип объектов для создания документа-характеристики - устройства хозяйства пути на станции. */
    P_OBJ_INFO_STATION

}