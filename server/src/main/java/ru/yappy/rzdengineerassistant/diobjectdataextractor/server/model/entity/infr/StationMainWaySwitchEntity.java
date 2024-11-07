package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import lombok.*;
import jakarta.persistence.*;

/** Класс, описывающий сущности стрелочных переводов, расположенных на главных путях железнодорожных станций. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "station_main_way_switches", schema = "infr")
public class StationMainWaySwitchEntity extends SwitchEntity {
    /** Парк, к которому относится стрелочный перевод. В большинстве случаев значение null */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_park_id")
    private StationParkEntity stationPark;
    /** Главный путь станции, на котором находится стрелочный перевод. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "frame_rail_joint_station_main_way_id")
    private StationMainWayEntity stationMainWay;
    /** Место железнодорожного направления (главного пути), на котором расположен стрелочный перевод. */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "frame_rail_joint_place_id")
    private MainWayPlaceEntity frameRailJointPlace;
    /** Строковый номер (название) станционного пути, на который ведет стрелочный перевод. Null значение предполагает,
     * что стрелочный перевод является сбрасывающим устройством (сбрасывающая стрелка или остряк). */
    @Column(name = "way_name_switch_goes_on")
    private String wayNameSwitchGoesOn;

    /** Метод, возвращающий строковое представление сущности стрелочного перевода главного пути железнодорожной станции
     * в случае, если указан станционный путь со всеми атрибутами.
     *
     * @return строка вида "стр. №  + (номер(название) стрелочного перевода) станции (название станции)", например
     * "стр. № 10 станции КОЕ-ГДЕНЬ".
     * @throws IllegalStateException если нет станционного пути, на котором расположен стрелочный перевод,
     * или названия станции. */
    @Override
    public String toString() {
        if (stationMainWay == null || stationMainWay.getStation() == null) {
            throw new IllegalStateException("Ошибка чтения информации о станционном пути, на котором расположен " +
                    "стрелочный перевод, из базы данных.");
        }
        return super.toString() + " станции " + stationMainWay.getStation();
    }

}