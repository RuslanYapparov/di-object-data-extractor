package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущность участка установленных скоростей главных путей железнодорожных направлений. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "main_speed_sections", schema = "wayc")
public class MainWaySpeedSectionEntity extends MainWayCharacteristicSectionEntity {
    /** Установленная скорость движения пассажирских поездов. */
    @Column(name = "passenger_train_speed")
    private Integer passengerTrainSpeed;
    /** Установленная скорость движения грузовых поездов. */
    @Column(name = "freight_train_speed")
    private Integer freightTrainSpeed;

    /** Метод, возвращающий строковое представление участка установленных скоростей железнодорожного направления.
     *
     * @return строка вида "скорость (пассажирских)/(грузовых), (номмер пути) путь, (направление), (км.метр начала -
     * км.метр конца)", например, "скорость 60/60, 1 путь, ГДЕ-ЛИБО - ЗДЕСЯ, 7.157 - 7.400". */
    @Override
    public String toString() {
        String passengerSpeed = (passengerTrainSpeed == null) ? "-" : passengerTrainSpeed.toString();
        return "скорость " + passengerSpeed + "/" + freightTrainSpeed + ", " + super.toString();
    }

}