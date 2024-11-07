package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущности главных путей железнодорожных направлений в базе данных. */
@Data
@Entity
@Table(name = "main_ways", schema = "infr")
public class MainWayEntity {
    /** Идентификатор главного пути. Первичный ключ (тип данных в БД - SMALLINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_way_id")
    private Long id;
    /** Поле для связи с сущностью железнодорожного направления. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_direction_id")
    private TransportDirectionEntity transportDirection;
    /** Номер главного пути. */
    @Column(name = "main_way_number")
    private Integer number;

    /** Метод, возвращающий строковое представление главного пути железнодорожного направления.
     *
     * @return строка вида "(направление), номер", например "ТАМ - СЯМСК, 1". */
    @Override
    public String toString() {
        return (transportDirection == null) ? String.valueOf(number) : transportDirection.getName() + ", " + number;
    }

}