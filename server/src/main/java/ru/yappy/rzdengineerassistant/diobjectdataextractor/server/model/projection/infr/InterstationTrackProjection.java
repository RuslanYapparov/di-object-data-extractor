package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.infr;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationEntity;

/**
 * Объект-проекция для получения основной (минимальной) информации о железнодорожном перегоне.
 */
public interface InterstationTrackProjection {

    /**
     * Предоставляет доступ к объекту сущности станции, с которой начинается перегон.
     *
     * @return объект сущности начальной станции
     */
    StationEntity getStartStation();

    /**
     * Предоставляет доступ к объекту сущности станции, которой заканчивается перегон.
     *
     * @return объект сущности конечной станции
     */
    StationEntity getEndStation();

    /**
     * Реализованный по умолчанию метод, возвращающий обозначение перегона исходя из данных ограничивающих его станций.
     *
     * @return строковое обозначение перегона типа "(начальная станция) - (конечная станция)"
     */
    default String getInterstationTrackName() {
        return getStartStation().getName() + " - " + getEndStation().getName();
    }

}