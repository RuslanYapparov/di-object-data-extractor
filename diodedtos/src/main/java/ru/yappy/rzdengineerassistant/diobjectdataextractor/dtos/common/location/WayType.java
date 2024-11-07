package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location;

/**
 * Класс-перечисление, содержащий варианты для выбора типа пути, для которого вносятся данные об объекте.
 */
public enum WayType {
    /** Главный путь на перегоне. */
    INTERSTATION_TRACK("Главный путь перегона"),
    /** Главный путь на станции. */
    STATION_MAIN_WAY("Главный путь станции"),
    /** Станционный путь. */
    STATION_WAY("Станционный путь");

    /** Поле элемента перечисления, хранящее обозначение типа пути на русском языке. */
    private final String ruTitle;

    /**
     * Приватный конструктор, принимающий обозначение типа пути на русском языке для каждого элемента перечисления.
     */
    WayType(String ruTitle) {
        this.ruTitle = ruTitle;
    }

    /**
     * Геттер для получения типа пути на русском языке.
     *
     * @return обозначение типа пути на русском языке
     */
    public String getRuTitle() {
        return ruTitle;
    }

}