package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/** Абстрактный класс для обозначения сущностей структурных предприятий (дистанций, станций) региональных дирекций. */
@Data
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = false)
@Entity
@Table(name = "structural_enterprises", schema = "adm")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class StructuralEnterpriseEntity {
    /** Идентификатор структурного предприятия, первичный ключ в БД (тип данных SMALLINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "structural_enterprise_id")
    protected Long id;
    /** Наименование структурного предприятия (дистанции - с заглавной буквы и в lowercase; станции - в uppercase). */
    @ToString.Include
    @Column(name = "structural_enterprise_name")
    protected String name;
    /** Поле содержащее аббревиатуру наименования региональной дирекции. */
    @EqualsAndHashCode.Exclude
    @Column(name = "regional_directorate_abbreviation")
    protected String regionalDirectorateAbbreviation;
    /** Поле для связи с таблицей сущностей территориальных отделов региональных дирекций. */
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "dir_ter_departments_enterprises", schema = "adm",
            joinColumns = @JoinColumn(name = "structural_enterprise_id"),
            inverseJoinColumns = @JoinColumn(name = "directorate_territorial_department_id"))
    protected Set<DirectorateTerritorialDepartmentEntity> directorateTerritorialDepartments;

}