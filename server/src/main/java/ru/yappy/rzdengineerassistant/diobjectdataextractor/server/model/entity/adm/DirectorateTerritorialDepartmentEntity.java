package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/** Класс, описывающий сущности территориальных отделов (центров, участков) региональных дирекций в базе данных. */
@Data
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = false)
@Entity
@Table(name = "directorate_territorial_departments", schema = "adm")
public class DirectorateTerritorialDepartmentEntity {
    /** Идентификатор территориального отдела (центра, участка), первичный ключ в БД (тип данных SMALLINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "directorate_territorial_department_id")
    private Long id;
    /** Аббревиатура территориального отдела (центра, участка). */
    @Column(name = "directorate_territorial_department_abbreviation")
    private String abbreviation;
    /** Полное наименование территориального отдела с заглавной буквы (остальные буквы - в lowercase). */
    @ToString.Include
    @Column(name = "directorate_territorial_department_name")
    private String name;
    /** Поле для связи с таблицей сущностей региональных дирекций. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regional_directorate")
    private RegionalDirectorateEntity regionalDirectorate;
    /** Поле для связи с таблицей сущностей территориальных управлений железных дорог. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "railway_territorial_department")
    private RailwayTerritorialDepartmentEntity railwayTerritorialDepartmentEntity;
    /** Поле для связи с таблицей сущностей структурных подразделений (дистанций, станций) региональных дирекций. */
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "dir_ter_departments_enterprises", schema = "adm",
            joinColumns = @JoinColumn(name = "directorate_territorial_department_id"),
            inverseJoinColumns = @JoinColumn(name = "structural_enterprise_id"))
    private Set<StructuralEnterpriseEntity> structuralEnterprises;

}