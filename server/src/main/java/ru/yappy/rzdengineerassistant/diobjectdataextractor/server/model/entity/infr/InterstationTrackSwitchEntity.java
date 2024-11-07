package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import lombok.*;
import jakarta.persistence.*;

/** Класс, описывающий сущность стрелочного перевода, расположенного на перегоне. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "interstation_track_switches", schema = "infr")
public class InterstationTrackSwitchEntity extends SwitchEntity {
    /** Поле для связи с таблицей сущностей главных путей железнодорожных направлений. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_way_id")
    private MainWayEntity mainWay;
    /** Местоположение стыка рамного рельса стрелочного перевода на перегоне (сущность MainWayPlaceEntity). */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "frame_rail_joint_place_id")
    private MainWayPlaceEntity frameRailJointPlace;
    /** Станция управления стрелочного перевода на перегоне (сущность StationEntity). */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "control_station_id")
    private StationEntity controlStation;

    /** Метод, возвращающий строковое представление стрелочного перевода, расположенного на перегоне.
     *
     * @return строка вида "стр. № (номер стрелочного перевода), (железнодорожное направление),
     * (км.метр стыка рамного рельса)", например "стр. № 8, ТАМ - СЯМСК, 1262.737". */
    @Override
    public String toString() {
        return "стр. " + name + ", " + frameRailJointPlace;
    }

}