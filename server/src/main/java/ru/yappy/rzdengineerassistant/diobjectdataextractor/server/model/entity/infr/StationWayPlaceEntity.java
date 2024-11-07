package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущности конкретных мест станционных путей в базе данных. */
@Data
@Entity
@Table(name = "station_way_places", schema = "infr")
public class StationWayPlaceEntity {
    /** Идентификатор места станционного пути. Первичный ключ (тип данных в БД - BIGINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sw_place_id")
    private Long id;
    /** Станционный путь, на котором расположено данное место. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_way_id")
    private StationWayEntity stationWay;
    /** Метр станционного пути, на котором расположено данное место. */
    @Column(name = "sw_place_meter")
    private Integer placeMeter;

    /** Метод, возвращающий строковое представление места станционного пути в случае, если указан станционный путь.
     *
     * @return строка вида "путь № (номер ст. пути) станции (название станции), (метр места)",
     * например "путь № 4 станции ГДЕ-ЛИБО, 532".
     * @throws IllegalStateException если не удалось выгрузить информацию о станционном пути из базы данных */
    @Override
    public String toString() {
        if (stationWay == null) {
            throw new IllegalStateException("Ошибка чтения информации о станционном пути, на котором расположено " +
                    "рассматриваемое место, из базы данных.");
        }
        return String.format("%s, %d", stationWay, placeMeter);
    }

}