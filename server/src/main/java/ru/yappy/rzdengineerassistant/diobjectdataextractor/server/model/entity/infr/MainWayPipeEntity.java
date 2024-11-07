package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущности труб, расположенных на главных путях, в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "main_pipes", schema = "infr")
public class MainWayPipeEntity extends PipeEntity {
    /** Место пути железнодорожного направления, на котором расположена ось трубы. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private MainWayPlaceEntity place;

    /** Метод, возвращающий строковое представление трубы, расположенной на главных путях.
     *
     * @return строка вида "(материал трубы) (количество отверстий)-очковая труба, (направление), (км.метр)",
     * например "Ж/Б 2-очковая труба, ТАМ - СЯМСК, 1234.514". */
    @Override
    public String toString() {
        return super.toString() + ", " + place;
    }

}