package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущности главных станционных путей в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "station_main_ways", schema = "infr")
public class StationMainWayEntity extends AbstractStationWayEntity {
    /** Главный путь железнодорожного направления, к которому относится главный путь станции. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_way_id")
    private MainWayEntity mainWay;
    /** Участок главного пути железнодорожного направления, на котором находится главный путь станции. */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private MainWaySectionEntity section;

    /** Метод, возвращающий строковое представление главного пути станции.
     *
     * @return строка вида "главный путь №(номер ст. пути) станции (название станции)",
     * например "главный путь №1 станции КОЕ-ГДЕНЬ" */
    @Override
    public String toString() {
        return "главный " + super.toString();
    }

}