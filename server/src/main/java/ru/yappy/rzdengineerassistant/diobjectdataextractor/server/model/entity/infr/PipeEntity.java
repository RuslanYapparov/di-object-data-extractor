package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;

/** Абстрактный класс, содержащий поля, характерные для всех сущностей труб в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "pipes", schema = "infr")
public abstract class PipeEntity extends ArtificialConstructionEntity {
    /** Длина трубы. */
    @Column(name = "pipe_length")
    protected Integer length;
    /** Количество водопропускных отверстий. */
    @Column(name = "amount_of_holes")
    protected Integer amountOfHoles;
    /** Форма отверстий ("КРУГЛАЯ", "КВАДРАТНАЯ", "ПРЯМОУГОЛЬНАЯ", "ОВАЛЬНАЯ"). */
    @Column(name = "hole_shape")
    protected String holeShape;
    /** Максимальное измерение отверстий в миллиметрах.
     * Значение равно holeMinDimension для круглых и квадратных форм отверстий труб */
    @Column(name = "hole_max_dimension")
    protected Integer holeMaxDimension;
    /** Минимальное измерение отверстий в миллиметрах. */
    @Column(name = "hole_min_dimension")
    protected Integer holeMinDimension;

    /** Метод, возвращающий строковое представление сущности железнодорожной трубы.
     *
     * @return строка вида "(материал трубы) (количество отверстий)-очковая труба", например "Ж/Б 2-хочковая труба".
     */
    @Override
    public String toString() {
        String pipeWithHoles = (amountOfHoles == null) ? "труба" : amountOfHoles + "-очковая труба";
        return (materialType == null) ? pipeWithHoles : materialType + " " + amountOfHoles + "-очковая труба";
    }

}