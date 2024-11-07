package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущности труб, расположенных на станционных путях, в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "station_pipes", schema = "infr")
public class StationWayPipeEntity extends PipeEntity {
    /** Место станционного пути, на котором расположена труба. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_way_place_id")
    private StationWayPlaceEntity place;

    /** Метод, возвращающий строковое представление трубы, расположенной на станционных путях.
     *
     * @return строка вида "(материал трубы) (количество отверстий)-очковая труба,
     * путь № (номер пути) станции (название станции), (метр)",
     * например "Ж/Б 1-очковая труба, путь № 6 станции ГДЕ-НИБУДЬСК, 990". */
    @Override
    public String toString() {
        return super.toString() + ", " + place;
    }

}