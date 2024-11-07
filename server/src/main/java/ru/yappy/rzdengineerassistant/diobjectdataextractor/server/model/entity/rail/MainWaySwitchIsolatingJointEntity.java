package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import jakarta.persistence.*;
import lombok.*;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWayPlaceEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWayEntity;

/** Класс, описывающий сущности изолирующих стыков стрелочных переводов, лежащих в главных путях
 *  железнодорожного направления */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "switch_isolating_joints_in_main_ways", schema = "rail")
public class MainWaySwitchIsolatingJointEntity extends SwitchIsolatingJointEntity {
    /** Место железнодорожного направления, на котором расположен изолирующий стык стрелочного перевода. */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "joint_main_place_id")
    private MainWayPlaceEntity mainWayPlace;
    /** Сущность главного пути, на котором расположен изолирующий стык стрелочного перевода. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_way_id")
    private MainWayEntity mainWay;

    /** Метод, возвращающий строковое представление изолирующего стыка сигнала (светофора), лежащего в главных путях.
     *
     * @return строка вида "изолирующий стык № (номер стыка) стр. № (номер стрелки),
     * (номер пути) путь, (направление), (км.метр)",
     * например, "изолирующий стык № 7 стр. № 1, 1 путь, ТАМ - СЯМСК, 1235.915". */
    @Override
    public String toString() {
        return super.toString() + ", " + mainWay.getNumber() + " путь, " + mainWayPlace;
    }

}