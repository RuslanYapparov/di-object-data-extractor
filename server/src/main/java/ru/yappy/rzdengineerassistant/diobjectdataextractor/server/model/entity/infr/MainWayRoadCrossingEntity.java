package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import lombok.*;
import jakarta.persistence.*;

/** Класс, описывающий сущности железнодорожных переездов, расположенных на главных путях. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "main_road_crossings", schema = "infr")
public class MainWayRoadCrossingEntity extends RoadCrossingEntity {
    /** Конкретное место главного пути, на котором расположена ось железнодорожного переезда. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private MainWayPlaceEntity place;

    /** Метод, возвращающий строковое представление сущности железнодорожного переезда.
     *
     * @return строка вида "(регулируемый/нерегулируемый) (охраняемый/неохраняемый) переезд (категория)-ой категории,
     * (направление), (км.метр места)",
     * например "нерегулируемый охраняемый переезд V-ой категории, ТАМ - СЯМСК, 1270.115". */
    @Override
    public String toString() {
        return super.toString() + ", " + place;
    }

}