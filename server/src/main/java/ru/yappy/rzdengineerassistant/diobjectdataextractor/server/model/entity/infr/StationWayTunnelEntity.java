package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущности туннелей, расположенных на станционных путях, в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "station_tunnels", schema = "infr")
public class StationWayTunnelEntity extends TunnelEntity {
    /** Участок станционного пути, на котором расположен туннель. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_way_section_id")
    private StationWaySectionEntity section;

    /** Метод, возвращающий строковое представление туннеля, расположенного на станционных путях.
     *
     * @return строка вида "(размер туннеля) (название туннеля), путь № (номер пути) станции (название станции),
     * (метр начала)-(метр конца)",
     * например "МАЛЫЙ ТОННЕЛЬ ЧЕРЕЗ Р. БАЛБЕСКА, путь № 8 станции СЯМСК, 330-339".
     */
    @Override
    public String toString() {
        return super.toString() + ", " + section;
    }

}