package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm;

import jakarta.persistence.*;
import lombok.*;

/**
 * Класс, описывающий сущности управлений (департаментов) центральных дирекций в базе данных.
. */
@Data
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = false)
@Entity
@Table(name = "central_departments", schema = "adm")
public class CentralDepartmentEntity {
    /** Аббревиатура управления, первичный ключ в БД. */
    @ToString.Include
    @Id
    @Column(name = "central_department_abbreviation")
    private String abbreviation;
    /** Полное наименование управления с заглавной буквы (остальные буквы - в lowercase). */
    @Column(name = "central_department_name")
    private String name;
    /** Поле для связи с таблицей сущностей центральных дирекций. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "central_directorate_abbreviation")
    private CentralDirectorateEntity centralDirectorate;

}