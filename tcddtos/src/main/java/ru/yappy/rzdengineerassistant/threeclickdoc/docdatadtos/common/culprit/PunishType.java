package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.culprit;

/**
 * Класс-перечисление, содержащий возможные варианты мер наказания работников за проступки.
 */
public enum PunishType {
    /** Нет примененного наказания. */
    NOTHING("ограничиться заслушиванием объяснения на разборе"),
    /** Предупреждение о недопущении в дальнейшей работе. */
    WARNING("предупредить о недопущении подобных проступков"),
    /** Возмещение ущерба от понесенных компанией убытков. */
    COST_COMPENSATION("назначить возмещение материального ущерба"),
    /** Снижение размера премии. */
    AWARD_REDUCTION("снизить размер премии по итогам работы за месяц"),
    /** Дисциплинарное наказание - замечание. */
    REPROACH("объявить замечание"),
    /** Дисциплинарное наказание - выговор. */
    REPRIMAND("объявить выговор"),
    /** Отстранение от выполнения рабочих обязанностей. */
    SUSPENSION_FROM_WORK("отстранить от выполнения рабочих обязанностей"),
    /** Дисциплинарное наказание - увольнение. */
    DISMISSAL("инициировать процедуру увольнения работника по соответствующим основаниям");

    /** Описание варианта наказания на русском языке. */
    private final String ruDescription;

    /**
     * Приватный конструктор класса-перечисления, добавляющий русское описание каждому варианту типа наказания.
     *
     * @param ruDescription описание варианта наказания на русском языке
     */
    PunishType(String ruDescription) {
        this.ruDescription = ruDescription;
    }

    /**
     * Геттер описания варианта наказания на русском языке.
     *
     * @return описание варианта наказания на русском языке
     */
    public String getRuDescription() {
        return ruDescription;
    }

}