package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import jakarta.persistence.*;
import lombok.*;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWayEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWayPlaceEntity;

/** Класс, описывающий сущности изолирующих рельсовых стыков сигналов (светофоров), лежащих в главных путях
 * железнодорожного направления */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "signal_isolating_joints_in_main_ways", schema = "rail")
public class MainWaySignalIsolatingJointEntity extends SignalIsolatingJointEntity {
    /** Место железнодорожного направления, на котором расположен изолирующий стык сигнала (светофора). */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "joint_main_place_id")
    private MainWayPlaceEntity mainWayPlace;
    /** Сущность главного пути железнодорожного направления, на котором расположен изолирующий стык светофора. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_way_id")
    private MainWayEntity mainWay;

    /** Метод, возвращающий строковое представление изолирующего стыка сигнала (светофора), лежащего в главных путях.
     *
     * @return строка вида "изолирующий стык сигнала (литер сигнала), (номер пути) путь, (направление), (км.метр)
     * (сторонность рельсовой нити) нить",
     * например, "изолирующий стык сигнала 5, 1 путь, ТАМ - СЯМСК, 1240.315 ПРАВАЯ НИТЬ".
     * @throws IllegalStateException если не удалось выгрузить информацию об одном из рельсов (или об обоих рельсах)
     * из базы данных. */
    @Override
    public String toString() {
        return super.toString() + " " + mainWay.getNumber() + " путь, " + mainWayPlace + " " + lineSide + " нить";
    }

}