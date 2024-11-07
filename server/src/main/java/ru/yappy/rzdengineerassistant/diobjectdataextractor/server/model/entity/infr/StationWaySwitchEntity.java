package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущности стрелочных переводов, расположенных на станционных путях железнодорожных станций. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "station_way_switches", schema = "infr")
public class StationWaySwitchEntity extends SwitchEntity {
    /** Парк, к которому относится стрелочный перевод. В большинстве случаев значение null */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_park_id")
    private StationParkEntity stationPark;
    /** Место станционного пути, на котором расположен стрелочный перевод. */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "frame_rail_joint_place_id")
    private StationWayPlaceEntity frameRailStationWayPlace;
    /** Станционный путь, на который ведет стрелочный перевод. */
    @Column(name = "way_name_switch_goes_on")
    private String wayNameSwitchGoesOn;

    /** Метод, возвращающий строковое представление сущности стрелочного перевода главного пути железнодорожной станции
     * в случае, если указано место станционного пути со всеми атрибутами.
     *
     * @return строка вида "стр. №  + (номер(название) стрелочного перевода) станции (название станции)", например
     * "стр. № 10 станции КОЕ-ГДЕНЬ".
     * @throws IllegalStateException - если не указано место станционного пути со всеми атрибутами*/
    @Override
    public String toString() {
        if (frameRailStationWayPlace == null || frameRailStationWayPlace.getStationWay() == null ||
                frameRailStationWayPlace.getStationWay().getStation() == null) {
            throw new IllegalStateException("Ошибка чтения информации о станционном пути или о месте " +
                    "станционного пути, на котором расположен стрелочный перевод, из базы данных.");
        }
        return super.toString() + " станции " + frameRailStationWayPlace.getStationWay().getStation();
    }

}