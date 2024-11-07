package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/** Класс, описывающий сущности дистанций сигнализации, централизации и блокировки в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sh_distances", schema = "adm")
public class TelemechanicsMaintenanceDistanceEntity extends StructuralEnterpriseEntity {
    /** Номер дистанции (тип данных в БД - SMALLINT). */
    @Column(name = "sh_distance_number")
    private Integer number;
    /** Поле-флаг, сигнализирующий о том, что предприятие является дистанцией инфраструктуры (BOOLEAN столбец в БД). */
    @Column(name = "is_ich")
    private Boolean isIch;
    /** Поле для связи с таблицей сущностей эксплуатационных участков дистанции. */
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "telemechanicsMaintenanceDistance")
    private Set<TelemechanicsExploitativeSectionEntity> telemechanicsExploitativeSections;
    /** Поле для связи с таблицей сущностей отделов дистанции сигнализации, централизации и блокировки. */
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "telemechanicsMaintenanceDistance")
    private Set<TelemechanicsDistanceDepartmentEntity> telemechanicsDistanceDepartments;

    /** Метод, возвращающий строковое представление сущности дистанции сигнализации, централизации и блокировки
     *
     * @return строка вида "ШЧ-(номер) (название)", например "ИЧ-1 Где-нибудьская"
    */
    @Override
    public String toString() {
        String shortName = isIch ? "ИЧ-" : "ПЧ-";
        return shortName + number + " " + name;
    }

}