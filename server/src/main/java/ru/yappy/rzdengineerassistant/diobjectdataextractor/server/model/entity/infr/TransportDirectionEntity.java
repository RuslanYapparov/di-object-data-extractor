package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущности железнодорожных направлений в базе данных. */
@Data
@Entity
@Table(name = "transport_directions", schema = "infr")
public class TransportDirectionEntity {
    /** Идентификатор железнодорожного направления. Первичный ключ (тип данных в БД - SMALLINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transport_direction_id")
    private Long id;
    /** Наименование железнодорожного направления. */
    @Column(name = "transport_direction_name")
    private String name;
    /** Уровень железнодорожного направления, имеет 2 разновидности: ГЛОБАЛЬНОЕ и ЛОКАЛЬНОЕ. */
    @Column(name = "transport_direction_level")
    private String level;

    /** Метод, возвращающий строковое представление железнодорожного направления.
     *
     * @return строка вида "Направление (наименование)", например "Направление ТАМ - СЯМСК". */
    @Override
    public String toString() {
        return "Направление " + name;
    }

}