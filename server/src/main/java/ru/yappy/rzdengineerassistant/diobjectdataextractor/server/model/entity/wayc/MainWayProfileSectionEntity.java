package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import lombok.*;
import jakarta.persistence.*;

/** Класс, описывающий сущность участка продольного профиля главных путей железнодорожных направлений. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "main_longitudinal_profile_sections", schema = "wayc")
public class MainWayProfileSectionEntity extends MainWayCharacteristicSectionEntity {
    /** Уклон продольного профиля главных путей. Отрицательное значение указывает на спуск,
     * положительное - на подъем, 0.0 - площадка. */
    @Column(name = "slope")
    private Float slope;

    /** Метод, возвращающий строковое представление участка продольного профиля железнодорожного направления.
     *
     * @return строка вида "(тип продольного профиля) (уклон), (номер пути) путь, (направление),
     * (км.метр начала - км.метр конца)", например "подъем +0.5, 2 путь, ТАМ - СЯМСК, 1234.514 - 1234.521". */
    @Override
    public String toString() {
        return WayCharacteristicToStringConverter.getProfileTypeBySlope(slope) + ", " + super.toString();
    }

}