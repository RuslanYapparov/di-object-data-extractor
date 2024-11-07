package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import jakarta.persistence.*;
import lombok.*;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationWaySectionEntity;

/** Класс, описывающий сущности рельсовых плетей, лежащих в станционных путях железнодорожных станций, в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lashes_in_station_ways", schema = "rail")
public class StationWayLashEntity extends LashEntity {
    /** Участок железнодорожного направления, на котором расположена рельсовая плеть. */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lash_sw_section_id")
    private StationWaySectionEntity stationWaySection;

    /** Метод, возвращающий строковое представление рельсовой плети, лежащей в станционных путях железнодорожных станций.
     *
     * @return строка вида "плеть № (номер плети) (длина плети) м, (сторонность рельсовой нити) + нить,
     * путь № (номер ст. пути) станции (название станции), (метр начала)-(метр конца)",
     * например "плеть № 1ЛА 558.02 м, ЛЕВАЯ нить, путь № 3 станции КОЕ-ГДЕНЬСК, 121 - 579". */
    @Override
    public String toString() {
        return super.toString() + ", " + stationWaySection;
    }

}