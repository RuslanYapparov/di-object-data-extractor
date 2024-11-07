package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущности участков станционных путей в базе данных. */
@Data
@Entity
@Table(name = "station_way_sections", schema = "infr")
public class StationWaySectionEntity {
    /** Идентификатор участка станционного пути. Первичный ключ (тип данных в БД - BIGINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sw_section_id")
    private Long id;
    /** Станционный путь, на котором расположен данный участок. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_way_id")
    private StationWayEntity stationWay;
    /** Метр станционного пути, на котором расположено начало участка. */
    @Column(name = "sw_section_start_meter")
    private Integer startMeter;
    /** Метр станционного пути, на котором расположен конец участка. */
    @Column(name = "sw_section_end_meter")
    private Integer endMeter;
    /** Длина участка. */
    @Column(name = "sw_section_length")
    private Integer length;

    /** Метод, возвращающий строковое представление участка станционного пути в случае, если указан станционный путь.
     *
     * @return строка вида "путь № (номер ст. пути) станции (название станции), (метр начала)-(метр конца)",
     * например "путь № 4 станции ГДЕ-ЛИБО, 507-532".
     * @throws IllegalStateException если не указан станционный путь при выгрузке из базы данных. */
    @Override
    public String toString() {
        if (stationWay == null) {
            throw new IllegalStateException("Ошибка чтения информации о станционном пути, на котором расположен " +
                    "рассматриваемый участок, из базы данных.");
        }
        return String.format("%s, %d-%d", stationWay, startMeter, endMeter);
    }

}