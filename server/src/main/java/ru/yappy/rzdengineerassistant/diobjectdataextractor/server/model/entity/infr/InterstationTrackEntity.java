package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import lombok.*;
import jakarta.persistence.*;

/** Класс, описывающий сущности железнодорожных перегонов в базе данных. */
@Data
@Entity
@Table(name = "interstation_tracks", schema = "infr")
public class InterstationTrackEntity {
    /** Идентификатор железнодорожного перегона. Первичный ключ (тип данных в БД - SMALLINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interstation_track_id")
    private Long id;
    /** Начальная станция, от границы которой начинается перегон. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_station_id")
    private StationEntity startStation;
    /** Конечная станция, на границе которой заканчивается перегон. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_station_id")
    private StationEntity endStation;
    /** Поле-флаг, определяющий электрификацию перегона. */
    @Column(name = "electrified")
    private Boolean electrified;
    /** Поле-флаг, определяющий наличие автоблокировки. */
    @Column(name = "auto_block_system")
    private Boolean autoBlockSystem;
    /** Количество железнодорожных путей на перегоне. */
    @Column(name = "amount_of_ways")
    private Integer amountOfWays;

    /** Метод, возвращающий строковое представление железнодорожного перегона в случае если указаны ограничивающие станции.
     *
     * @return строка вида "(начальная станция) - (конечная станция)", например "ГДЕ-ЛИБО - ЗДЕСЯ".
     * @throws IllegalStateException если не указаны ограничивающие станции. */
    @Override
    public String toString() {
        if (startStation == null || endStation == null) {
            throw new IllegalStateException("Ошибка чтения данных о станциях, ограничивающих железнодорожный перегон, " +
                    "из базы данных.");
        }
        return startStation.getName() + " - " + endStation.getName();
    }

}