package ru.yappy.rzdengineerassistant.employeedataextractor.dtos;

/**
 * Класс-перечисление, содержащий варианты для обозначения типа образования, полученного работником
 */
public enum EducationType {
    /** Вариант для случая отсутствия данных. */
    N_A("[Нет сведений о типе образования]"),
    /** Начальное общее. */
    PRIMARY_EDUCATION("начальное общее"),
    /** Основное общее. */
    BASIC_EDUCATION("основное общее"),
    /** Среднее общее. */
    SECONDARY_EDUCATION("среднее общее"),
    /** Среднее профессиональное. */
    SPECIAL_EDUCATION("среднее профессиональное"),
    /** Среднее профессиональное профильное. */
    SPECIAL_PROFILE_EDUCATION("среднее профессиональное профильное"),
    /** Высшее. */
    HIGHER_EDUCATION("высшее"),
    /** Высшее неоконченное. */
    HIGHER_EDUCATION_NOT_ENDED("высшее неоконченное"),
    /** Высшее профильное. */
    HIGHER_PROFILE_EDUCATION("высшее профильное"),
    /** Аспирантура. */
    POSTGRADUATE_EDUCATION("аспирантура (магистратура)");

    /** Название типа образования на русском языке. */
    private final String ruTitle;

    /**
     * Конструктор объекта перечисления, принимающий название типа образования на русском языке.
     *
     * @param ruTitle название типа образования на русском языке
     */
    EducationType(String ruTitle) {
        this.ruTitle = ruTitle;
    }

    /**
     * Геттер названия типа образования на русском языке.
     *
     * @return название типа образования на русском языке
     */
    public String getRuTitle() {
        return ruTitle;
    }

}