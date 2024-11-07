package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm;

import jakarta.persistence.*;
import lombok.*;

/**
 * Класс, описывающий сущности региональных дирекций в базе данных.
 */
@Data
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = false)
@Entity
@Table(name = "regional_directorates", schema = "adm")
public class RegionalDirectorateEntity {
    /** Аббревиатура региональной дирекции, первичный ключ в БД. */
    @ToString.Include
    @Id
    @Column(name = "regional_directorate_abbreviation")
    private String abbreviation;
    /** Полное наименование региональной дирекции с заглавной буквы (остальные буквы - в lowercase). */
    @Column(name = "regional_directorate_name")
    private String name;
    /** Поле для связи с таблицей сущностей центральных дирекций. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "central_directorate")
    private CentralDirectorateEntity centralDirectorate;

}