package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import jakarta.persistence.*;
import lombok.*;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWayEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWayPlaceEntity;

/** Класс, описывающий сущности токопроводящих рельсовых стыков, лежащих в главных путях железнодорожного направления */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "conducting_joints_in_main_ways", schema = "rail")
public class MainWayConductingJointEntity extends ConductingJointEntity {
    /** Место железнодорожного направления, на котором расположен токопроводящий стык. */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "joint_main_place_id")
    private MainWayPlaceEntity mainWayPlace;
    /** Сущность главного пути железнодорожного направления, на котором расположен токопроводящий стык. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_way_id")
    private MainWayEntity mainWay;

    /** Метод, возвращающий строковое представление токопроводящего рельсового стыка, лежащего в главных путях.
     *
     * @return строка вида "токопроводящий стык, (номер пути) путь, (направление), (км.метр) (сторонность) нить",
     * например, "стык 1 путь, КОЕ-ГДЕНЬ - КОЕ-ГДЕНЬ ГРУЗОВАЯ, 3.757 ПРАВАЯ нить".
     * @throws IllegalStateException если не удалось выгрузить информацию об одном из рельсов (или об обоих рельсах)
     * из базы данных. */
    @Override
    public String toString() {
        return super.toString() + " " + mainWay.getNumber() + " путь, " + mainWayPlace + " " + lineSide + " нить";
    }

}