package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Класс минимизированного DTO сущности дистанции пути. */
public class WayMaintenanceDistanceMiniDto extends StructuralEnterpriseMiniDto {
    /** Номер дистанции пути. */
    private final Integer number;
    /** Поле-флаг, определяющее, является ли дистанция пути ИЧ (дистанцией инфраструктуры). */
    @JsonProperty("ich")
    private final Boolean isIch;

    /** Конструктор объекта класса, принимающий все параметры.
     *
     * @param id идентификатор дистанции пути
     * @param name название дистанции пути
     * @param number номер дистанции пути
     * @param isIch является ли дистанция дистанцией инфраструктуры
     */
    public WayMaintenanceDistanceMiniDto(Long id, String name, Integer number, Boolean isIch) {
        super(id, name);
        this.number = number;
        this.isIch = isIch;
    }

    /** Геттер номера дистанции пути.
     *
     * @return номер дистанции пути.
     */
    public Integer getNumber() {
        return number;
    }

    /** Геттер поля-флага, определяющего, является ли дистанция пути ИЧ (дистанцией инфраструктуры).
     *
     * @return булевое значение, определяющее, является ли дистанция пути ИЧ (дистанцией инфраструктуры).
     */
    public Boolean isIch() {
        return isIch;
    }

    /** Получение строкового представления объекта.
     *
     * @return строка с названием класса объекта и описанием значений всех полей
     */
    @Override
    public String toString() {
        return "WayMaintenanceDistanceMiniDto{" +
                "number=" + number +
                ", isIch=" + isIch +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}