package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущности железнодорожных переездов, расположенных на станционных путях. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "station_road_crossings", schema = "infr")
public class StationWayRoadCrossingEntity extends RoadCrossingEntity {
    /** Конкретное место станционного пути, на котором расположена ось железнодорожного переезда. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_way_place_id")
    private StationWayPlaceEntity place;

    /** Метод, возвращающий строковое представление сущности железнодорожного переезда.
     *
     * @return строка вида "(регулируемый/нерегулируемый) (охраняемый/неохраняемый) переезд (категория)-ой категории,
     * путь № (номер станционного пути) станции (название станции), (метр)",
     * например "нерегулируемый охраняемый переезд V-ой категории, путь № 3 станции КОЕ-ГДЕНЬ ГРУЗОВАЯ, 115". */
    @Override
    public String toString() {
        return super.toString() + ", " + place;
    }

}