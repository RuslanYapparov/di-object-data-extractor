package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.seasoninspection;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.TcdEnum;

/**
 * Класс-перечисление, содержащий поддерживаемые типы протоколов сезонных осмотров пути и сооружений.
 */
public enum ProtSeasonInspectionType implements TcdEnum {
    /** Тип протокола зимнего осмотра пути и сооружений на линейном участке. */
    WINTER_LINEAR_SECTION_INSPECTION,
    /** Тип протокола зимнего осмотра пути и сооружений дистанции. */
    WINTER_DISTANCE_INSPECTION,
    /** Тип протокола весеннего осмотра пути и сооружений на линейном участке. */
    SPRING_LINEAR_SECTION_INSPECTION,
    /** Тип протокола весеннего осмотра пути и сооружений дистанции. */
    SPRING_DISTANCE_INSPECTION,
    /** Тип протокола осеннего осмотра пути и сооружений на линейном участке. */
    AUTUMN_LINEAR_SECTION_INSPECTION,
    /** Тип протокола осеннего осмотра пути и сооружений дистанции. */
    AUTUMN_DISTANCE_INSPECTION,
    /** Тип протокола зимнего объезда дистанции под председательством начальника территориального управления. */
    WINTER_DISTANCE_RAILWAY_TERRITORIAL_DEPARTMENT_BOSS_INSPECTION,
    /** Тип протокола весеннего объезда дистанции под председателем начальника железной дороги. */
    SPRING_DISTANCE_RAILWAY_BOSS_INSPECTION,
    /** Тип протокола летнего объезда дистанции под председательством начальника территориального управления. */
    SUMMER_DISTANCE_RAILWAY_TERRITORIAL_DEPARTMENT_BOSS_INSPECTION,
    /** Тип протокола осеннего объезда дистанции под председателем начальника железной дороги. */
    AUTUMN_DISTANCE_RAILWAY_BOSS_INSPECTION

}