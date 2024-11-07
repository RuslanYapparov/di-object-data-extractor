package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import lombok.*;
import jakarta.persistence.*;

/** Абстрактный класс, содержащий поля, характерные для всех сущностей железнодорожных переездов в базе данных. */
@Data
@Entity
@Table(name = "road_crossings", schema = "infr")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RoadCrossingEntity {
    /** Идентификатор железнодорожного переезда. Первичный ключ (тип данных в БД - INTEGER). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "road_crossing_id")
    protected Long id;
    /** Категория железнодорожного переезда. Устанавливается в зависимости от интенсивности движения
     * автомобильного и железнодорожного транспорта через переезд. Обозначается римской цифрой от I до V. */
    @Column(name = "road_crossing_category")
    protected String category;
    /** Поле-флаг, указывающее на то, переезд общего пользования или нет. */
    @Column(name = "common_use")
    protected Boolean commonUse;
    /** Поле-флаг, указывающий на то, регулируемый (оснащен сигнализацией о приближении поезда) переезд или нет. */
    @Column(name = "traffic_control")
    protected Boolean trafficControl;
    /** Поле-флаг, указывающий на то, охраняемый (с дежурным работником) переезд или нет. */
    @Column(name = "with_attendant")
    protected Boolean withAttendant;

    /** Метод, возвращающий строковое представление сущности железнодорожного переезда в случае, если указаны
     * атрибуты "регулируемый" и "охраняемый".
     *
     * @return строка вида "(регулируемый/нерегулируемый) (охраняемый/неохраняемый) переезд (категория)-ой категории",
     * например "нерегулируемый охраняемый переезд V-ой категории".
     * @throws IllegalStateException если не указаны атрибуты "регулируемый" и "охраняемый". */
    @Override
    public String toString() {
        if (trafficControl == null || withAttendant == null) {
            throw new IllegalStateException("Ошибка чтения информации о железнодорожном переезде из базы данных.");
        }
        String controllable = trafficControl ? "регулируемый " : "нерегулируемый ";
        String attendance = withAttendant ? "охраняемый " : "неохраняемый ";
        return controllable + attendance + "переезд " + category + "-ой категории";
    }

}