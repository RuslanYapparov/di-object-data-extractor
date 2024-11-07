package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import lombok.*;
import jakarta.persistence.*;

/** Класс, описывающий сущности мостов, расположенных на станционных путях, в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "station_bridges", schema = "infr")
public class StationWayBridgeEntity extends BridgeEntity {
    /** Участок станционного пути, на котором расположен мост. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_way_section_id")
    private StationWaySectionEntity section;

    /** Метод, возвращающий строковое представление моста, расположенного на станционных путях.
     *
     * @return строка вида "(размер моста) (название моста), путь № (номер пути) станции (название станции),
     * (метр начала)-(метр конца)",
     * например "МАЛЫЙ МОСТ ЧЕРЕЗ Р. ГДЕ-НИБУДЬСКИЙ 6 ПУТЬ, путь № 6 станции ГДЕ-НИБУДЬСК, 210-229".
     */
    @Override
    public String toString() {
        return super.toString() + ", " + section;
    }

}