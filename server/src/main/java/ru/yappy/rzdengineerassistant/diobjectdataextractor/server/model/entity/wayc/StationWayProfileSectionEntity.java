package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущность участка продольного профиля станционного пути железнодорожной станции. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "station_longitudinal_profile_sections", schema = "wayc")
public class StationWayProfileSectionEntity extends StationWayCharacteristicSectionEntity {
    /** Уклон продольного профиля станционного пути. Отрицательное значение указывает на спуск,
     * положительное - на подъем, 0.0 - площадка. */
    @Column(name = "slope")
    private Float slope;

    /** Метод, возвращающий строковое представление участка продольного профиля станционного пути.
     *
     * @return строка вида "(тип продольного профиля) (уклон), путь № (номер ст. пути) станции (название станции),
     * (метр начала)-(метр конца)", например "спуск -3.7, путь № 4 станции ГДЕ-ЛИБО, 507-532". */
    @Override
    public String toString() {
        return WayCharacteristicToStringConverter.getProfileTypeBySlope(slope) + ", " + super.toString();
    }

}