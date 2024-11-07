package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import jakarta.persistence.*;
import lombok.*;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationWayPlaceEntity;

/** Класс, описывающий сущности токопроводящих рельсовых стыков, лежащих в станционных путях железнодорожных станций. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "conducting_joints_in_station_ways", schema = "rail")
public class StationWayConductingJointEntity extends ConductingJointEntity {
    /** Место станционного пути, на котором расположен токопроводящий стык. */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "joint_sw_place_id")
    private StationWayPlaceEntity stationWayPlace;

    /** Метод, возвращающий строковое представление токопроводящего рельсового стыка, лежащего в станционных путях
     * железнодорожных станций.
     *
     * @return строка вида "токопроводящий стык, путь № (номер ст. пути) станции (название станции), (метр места)",
     * например "токопроводящий стык, путь № 4 станции ГДЕ-ЛИБО, 757".
     * @throws IllegalStateException если не удалось выгрузить информацию о стыкующихся рельсах из базы данных. */
    @Override
    public String toString() {
        return super.toString() + ", " + stationWayPlace;
    }

}