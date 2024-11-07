package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.delay;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.TcdEnum;

/**
 * Класс-перечисление, содержащий поддерживаемые типы протоколов расследования задержки поезда.
 */
public enum ProtTrainDelayType implements TcdEnum {
    /** Тип протокола расследования задержки пассажирского поезда. */
    PASSENGER_TRAIN_DELAY,
    /** Тип протокола расследования задержки пригородного поезда. */
    SUBURBAN_TRAIN_DELAY,
    /** Тип протокола расследования задержки доставки груза. */
    FREIGHT_DELIVERY_DELAY,
    /** Тип протокола расследования особых вариантов пропуска поездов. */
    SPECIAL_TRAIN_PASS

}