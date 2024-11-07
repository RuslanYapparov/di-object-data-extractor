package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import jakarta.persistence.*;
import lombok.*;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationWayPlaceEntity;

/** Класс, описывающий сущности изолирующих стыков, лежащих на стрелочных переводах станционных путей. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "switch_isolating_joints_in_station_ways", schema = "rail")
public class StationWaySwitchIsolatingJointEntity extends SwitchIsolatingJointEntity {
    /** Место станционного пути, на котором расположен изолирующий стык стрелочного перевода. */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "joint_sw_place_id")
    private StationWayPlaceEntity stationWayPlace;

    /** Метод, возвращающий строковое представление изолирующего стыка, лежащего на стрелочном переводе
     * станционного пути железнодорожной станции.
     *
     * @return строка вида "изолирующий стык № (номер стыка) стр. № (номер стрелки), путь № (номер ст. пути)
     * станции (название станции), (метр места)",
     * например "изолирующий стык № 3 стр. № 14, путь № 4 станции ГДЕ-ЛИБО, 757". */
    @Override
    public String toString() {
        return super.toString() + ", " + stationWayPlace;
    }

}