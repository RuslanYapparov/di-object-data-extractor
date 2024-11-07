package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущности конкретных мест железнодорожных направлений (главных путей) в базе данных. */
@Data
@Entity
@Table(name = "transport_direction_places", schema = "infr")
public class MainWayPlaceEntity {
    /** Идентификатор места железнодорожного направления. Первичный ключ (тип данных в БД - BIGINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;
    /** Километр, на котором расположено конкретное место. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "transport_direction_id", referencedColumnName = "transport_direction_id"),
                  @JoinColumn(name = "place_km", referencedColumnName = "km")})
    private KilometerEntity km;
    /** Метр километра, на котором расположено конкретное место. */
    @Column(name = "place_meter_of_km")
    private Integer meter;

    /** Метод, возвращающий строковое представление конкретного места железнодорожного направления.
     *
     * @return строка вида "(направление), (км.метр места)", например "ТАМ - СЯМСК, 1234.514"
    */
    @Override
    public String toString() {
        return (km == null) ? null : km + "." + meter;
    }

}