package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm;

import lombok.*;
import jakarta.persistence.*;

/** Класс, описывающий сущности отделов дистанции сигнализации, централизации и блокировки в базе данных. */
@Data
@Entity
@Table(name = "sh_distance_departments", schema = "adm")
public class TelemechanicsDistanceDepartmentEntity {
    /** Идентификатор отдела дистанции сигнализации, централизации и блокировки,
     * первичный ключ в БД (тип данных - SMALLINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sh_distance_department_id")
    private Long id;
    /** Тип отдела дистанции сигнализации, централизации и блокировки (с заглавной буквы, остальные - в lowercase). */
    @Column(name = "sh_distance_department_type")
    private String type;
    /** Примечание к отделу дистанции сигнализации, централизации и блокировки, по умолчанию null. */
    @Column(name = "sh_distance_department_note")
    private String note;
    /** Поле для связи с таблицей сущностей дистанции сигнализации, централизации и блокировки. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sh_distance_id")
    private TelemechanicsMaintenanceDistanceEntity telemechanicsMaintenanceDistance;

    /** Метод, возвращающий строковое представление сущности отдела дистанции сигнализации, централизации и блокировки
     *
     * @return строка вида "ШЧ-(номер) (название) (тип отдела) (примечание)",
     * например "ШЧ-1 Кое-гденькская Производственно-технический отдел"
    */
    @Override
    public String toString() {
        String fullName = (note == null) ? type : type + " (" + note + ")";
        return (telemechanicsMaintenanceDistance == null) ? fullName : telemechanicsMaintenanceDistance +
                " " + fullName;
    }

}