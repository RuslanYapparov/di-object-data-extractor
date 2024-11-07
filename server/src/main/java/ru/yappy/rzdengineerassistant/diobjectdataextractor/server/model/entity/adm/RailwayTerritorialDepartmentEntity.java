package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущности территориальных управлений железных дорог в базе данных. */
@Data
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = false)
@Entity
@Table(name = "railway_territorial_departments", schema = "adm")
public class RailwayTerritorialDepartmentEntity {
    /** Идентификатор территориального управления железной дороги, первичный ключ в БД (тип данных SMALLINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "railway_territorial_department_id")
    private Long id;
    /** Аббревиатура территориального управления железной дороги. */
    @Column(name = "railway_territorial_department_abbreviation")
    private String abbreviation;
    /** Полное наименование территориального управления с заглавной буквы (остальные буквы - в lowercase). */
    @ToString.Include
    @Column(name = "railway_territorial_department_name")
    private String name;
    /** Поле для связи с таблицей сущностей железных дорог. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "railway")
    private RailwayEntity railway;

}