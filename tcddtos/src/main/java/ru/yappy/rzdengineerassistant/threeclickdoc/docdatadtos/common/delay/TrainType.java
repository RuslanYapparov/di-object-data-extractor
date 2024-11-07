package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.delay;

/**
 * Класс-перечисление, содержащий варианты для выбора типа поезда.
 */
public enum TrainType {
    /** Пассажирский поезд. */
    PASSENGER("пассажирский поезд", "пассажирского поезда", 21_515.05f, 10),
    /** Пригородный поезд. */
    SUBURBAN("пригородный поезд", "пригородного поезда", 14_035.21f, 15),
    /** Грузовой поезд. */
    FREIGHT("грузовой поезд", "грузового поезда", 34_015.78f, 20),
    /** Хозяйственный поезд на электротяге. */
    SERVICE_ELECTRIC("хозяйственный поезд на электротяге",
            "хозяйственного поезда на электротяге", 5_611.68f, 25),
    /** Хозяйственный поезд на дизельной тяге. */
    SERVICE_DIESEL("хозяйственный поезд на дизельной тяге",
            "хозяйственного поезда на дизельной тяге", 5_330.69f, 30),
    /** Хозяйственный поезд - автомотриса на электротяге. */
    ELECTRIC_MOTORCAR("мотовоз (автомотриса) на электротяге",
            "мотовоза (автомотрисы) на электротяге", 5_611.68f, 35),
    /** Хозяйственный поезд - автомотриса на дизельной тяге. */
    DIESEL_MOTORCAR("мотовоз (автомотриса) на дизельной тяге",
            "мотовоза (автомотрисы) на дизельной тяге", 4_462.06f, 40),
    /** Хозяйственный поезд - рельсовый автобус. */
    RAIL_BUS("рельсовый автобус", "рельсового автобуса", 2_273.77f, 45),
    /** Одиночный пассажирский локомотив на электротяге. */
    SINGLE_ELECTRIC_PASSENGER("пассажирский локомотив на электротяге",
            "пассажирского локомотива на электротяге", 6_618.43f, 50),
    /** Одиночный пассажирский локомотив на дизельной тяге. */
    SINGLE_DIESEL_PASSENGER("пассажирский локомотив на дизельной тяге",
            "пассажирского локомотива на дизельной тяге", 7_324.68f, 55),
    /** Одиночный грузовой локомотив на электротяге. */
    SINGLE_ELECTRIC_FREIGHT("грузовой локомотив на электротяге",
            "грузового локомотива на электротяге", 3_658.70f, 60),
    /** Одиночный грузовой локомотив на дизельной тяге. */
    SINGLE_DIESEL_FREIGHT("грузовой локомотив на дизельной тяге",
            "грузового локомотива на дизельной тяге", 7_464.94f, 65),
    /** Маневровый локомотив. */
    SHUNTING("маневровый локомотив", "маневрового локомотива", 5_755.90f, 70);

    /** Поле элемента перечисления, хранящее обозначение типа поезда на русском языке в именительном падеже. */
    private final String ruNominative;
    /** Поле элемента перечисления, хранящее обозначение типа поезда на русском языке в родительном падеже. */
    private final String ruGenitive;
    /** Поле элемента перечисления, хранящее стоимость поездо-часа задержки. */
    private final Float stopHourCost;
    /** Целочисленное обозначение условного приоритета типа поезда. */
    private final int priority;

    /**
     * Приватный конструктор, принимающий обозначение типа поезда на русском языке
     *
     * @param ruNominative обозначение типа поезда на русском языке
     */
    TrainType(String ruNominative, String ruGenitive, Float stopHourCost, int priority) {
        this.ruNominative = ruNominative;
        this.ruGenitive = ruGenitive;
        this.stopHourCost = stopHourCost;
        this.priority = priority;
    }

    /**
     * Геттер русского обозначения типа поезда в именительном падеже.
     *
      * @return русское обозначение типа поезда в именительном падеже
     */
    public String getRuNominative() {
        return ruNominative;
    }

    /**
     * Геттер русского обозначения типа поезда в родительном падеже.
     *
     * @return русское обозначение типа поезда в родительном падеже
     */
    public String getRuGenitive() {
        return ruGenitive;
    }

    /**
     * Геттер стоимости поездо-часа задержки.
     *
     * @return стоимость поездо-часа задержки
     */
    public Float getStopHourCost() {
        return stopHourCost;
    }

    /**
     * Геттер целочисленного обозначения условного приоритета типа поезда.
     *
      * @return целочисленное обозначение условного приоритета типа поезда
     */
    public int getPriority() {
        return priority;
    }

}