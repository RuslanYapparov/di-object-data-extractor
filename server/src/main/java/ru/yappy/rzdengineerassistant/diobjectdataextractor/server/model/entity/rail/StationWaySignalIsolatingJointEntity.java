package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import jakarta.persistence.*;
import lombok.*;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationWayPlaceEntity;

/** Класс, описывающий сущности изолирующих стыков сигналов (светофоров) станционных путей железнодорожных станций. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "signal_isolating_joints_in_station_ways", schema = "rail")
public class StationWaySignalIsolatingJointEntity extends SignalIsolatingJointEntity {
    /** Место станционного пути, на котором расположен изолирующий стык светофора. */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "joint_sw_place_id")
    private StationWayPlaceEntity stationWayPlace;

    /** Метод, возвращающий строковое представление изолирующего стыка светофора станционного пути.
     *
     * @return строка вида "изолирующий стык сигнала (литер сигнала), путь № (номер ст. пути)
     * станции (название станции), (метр места) (сторонность рельсовой нити) нить",
     * например "изолирующий стык сигнала М14, путь № 3 станции КОЕ-ГДЕНЬСК, 215 ЛЕВАЯ НИТЬ".
     * @throws IllegalStateException если не удалось выгрузить информацию о стыкующихся рельсах из базы данных. */
    @Override
    public String toString() {
        return super.toString() + ", " + stationWayPlace + " " + lineSide + " нить";
    }

}