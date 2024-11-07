package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import jakarta.persistence.*;
import lombok.*;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationWaySectionEntity;

/** Класс, описывающий сущности рельсов, лежащих в станционных путях железнодорожных станций, в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "rails_in_station_ways", schema = "rail")
public class StationWayRailEntity extends RailEntity {
    /** Участок железнодорожного направления, на котором расположен рельс. */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rail_sw_section_id")
    private StationWaySectionEntity stationWaySection;

    /** Метод, возвращающий строковое представление рельса, лежащего в станционных путях железнодорожных станций.
     *
     * @return строка вида "рельс (длина) м, (сторонность) нить, (вид рельсового элемента), путь № (номер ст. пути)
     * станции (название станции), (метр начала)-(метр конца)",
     * например "рельс 25.0 м, ЛЕВАЯ нить, ЗВЕНЬЕВОЙ ПУТЬ, путь № 6 станции ГДЕ-НИБУДЬСК, 507-532". */
    @Override
    public String toString() {
        return super.toString() + ", " + stationWaySection;
    }

}