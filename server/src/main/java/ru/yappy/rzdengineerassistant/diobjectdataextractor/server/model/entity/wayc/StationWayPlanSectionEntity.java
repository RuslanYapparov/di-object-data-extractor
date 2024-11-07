package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущность участка плана станционного пути железнодорожной станции. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "station_plan_sections", schema = "wayc")
public class StationWayPlanSectionEntity extends StationWayCharacteristicSectionEntity {
    /** Поле-флаг, определяющее тип участка плана (прямой участок (true, остальные поля - null)
     * или кривая (false, остальные поля имеют значения) главных путей железнодорожного направления. */
    @Column(name = "straight")
    private Boolean straight;
    /** Сторонность кривого участка пути ("ЛЕВАЯ" или "ПРАВАЯ"). */
    @Column(name = "line_side")
    private String lineSide;
    /** Радиус кривого участка пути (от 100 до 4000 метров). */
    @Column(name = "radius")
    private Integer radius;

    /** Метод, возвращающий строковое представление участка плана станционного пути железнодорожной станции.
     *
     * @return строка вида "(тип плана), путь № (номер ст. пути) станции (название станции), (метр начала)-(метр конца)",
     * например "ЛЕВАЯ кривая радиусом 1000 м, путь № 4 станции ГДЕ-ЛИБО, 507-532". */
    @Override
    public String toString() {
        return WayCharacteristicToStringConverter.getPlanType(straight, lineSide, radius) + ", " + super.toString();
    }

}