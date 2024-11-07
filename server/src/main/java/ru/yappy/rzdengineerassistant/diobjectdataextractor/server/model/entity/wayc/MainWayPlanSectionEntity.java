package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущность участка плана главных путей железнодорожных направлений. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "main_plan_sections", schema = "wayc")
public class MainWayPlanSectionEntity extends MainWayCharacteristicSectionEntity {
    /** Поле-флаг, определяющее тип участка плана (прямой участок (true, остальные поля - null)
     * или кривая (false, остальные поля имеют значения) главных путей железнодорожного направления. */
    @Column(name = "straight")
    private Boolean straight;
    /** Сторонность кривого участка пути ("ЛЕВАЯ" или "ПРАВАЯ"). */
    @Column(name = "line_side")
    private String curveLineSide;
    /** Радиус кривого участка пути (от 100 до 4000 метров). */
    @Column(name = "radius")
    private Integer radius;
    /** Возвышение наружного рельса в кривом участке пути (от 0 до 150 миллиметров). */
    @Column(name = "rail_elevation")
    private Integer railElevation;
    /** Отметка конца первой переходной кривой (от начала кривого участка пути). */
    @Column(name = "end_of_first_transition_curve_meter")
    private Integer endOfFirstTransitionCurveMeter;
    /** Отметка конца второй переходной кривой (от начала кривого участка пути). */
    @Column(name = "end_of_second_transition_curve_meter")
    private Integer endOfSecondTransitionCurveMeter;

    /** Метод, возвращающий строковое представление участка плана железнодорожного направления.
     *
     * @return строка вида "(тип плана) (номер пути) путь, (направление), (км.метр начала - км.метр конца)",
     *      * например "ЛЕВАЯ кривая радиусом 1000 м, 1 путь, ТАМ - СЯМСК, 1234.514 - 1234.521". */
    @Override
    public String toString() {
        return WayCharacteristicToStringConverter.getPlanType(straight, curveLineSide, radius) + ", " + super.toString();
    }

}