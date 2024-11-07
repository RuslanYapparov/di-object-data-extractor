package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm;

import lombok.*;
import jakarta.persistence.*;

/** Класс, описывающий сущности отделов дистанции пути в базе данных. */
@Data
@Entity
@Table(name = "p_distance_departments", schema = "adm")
public class WayDistanceDepartmentEntity {
    /** Идентификатор отдела дистанции пути, первичный ключ в БД (тип данных - SMALLINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_distance_department_id")
    private Long id;
    /** Тип отдела дистанции пути (с заглавной буквы, остальные - в lowercase). */
    @Column(name = "p_distance_department_type")
    private String type;
    /** Примечание к отделу дистанции пути, по умолчанию null. */
    @Column(name = "p_distance_department_note")
    private String note;
    /** Поле для связи с таблицей сущностей дистанции пути. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_distance_id")
    private WayMaintenanceDistanceEntity wayMaintenanceDistance;

    /** Метод, возвращающий строковое представление сущности отдела дистанции пути
     *
     * @return строка вида "ПЧ-(номер) (название) (тип отдела) (примечание)",
     * например "ИЧ-1 Где-нибудьская Производственно-технический отдел"
    */
    @Override
    public String toString() {
        String fullName = (note == null) ? type : type + " (" + note + ")";
        return (wayMaintenanceDistance == null) ? fullName : wayMaintenanceDistance + " " + fullName;
    }

}