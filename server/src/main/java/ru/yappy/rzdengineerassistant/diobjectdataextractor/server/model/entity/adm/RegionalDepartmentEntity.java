package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущности служб, центров, отделов и участков региональных дирекций в базе данных. */
@Data
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = false)
@Entity
@Table(name = "regional_departments", schema = "adm")
public class RegionalDepartmentEntity {
    /** Аббревиатура служб, центров, отделов и участков региональных дирекций, первичный ключ в БД. */
    @ToString.Include
    @Id
    @Column(name = "regional_department_abbreviation")
    private String abbreviation;
    /** Поле для связи с таблицей сущностей региональных дирекции*/
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regional_directorate_abbreviation")
    private RegionalDirectorateEntity regionalDirectorate;
    /** Поле для связи с таблицей сущностей управлений (департаментов) центральных дирекций. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "central_department_abbreviation")
    private CentralDepartmentEntity centralDepartment;

}