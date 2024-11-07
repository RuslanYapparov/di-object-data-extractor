package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm;

import jakarta.persistence.*;
import lombok.*;

/**
 * Класс, описывающий сущности центральных дирекций в базе данных.
 */
@Data
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = false)
@Entity
@Table(name = "central_directorates", schema = "adm")
public class CentralDirectorateEntity {
    /** Аббревиатура центральной дирекции, первичный ключ в БД. */
    @ToString.Include
    @Id
    @Column(name = "central_directorate_abbreviation")
    private String abbreviation;
    /** Полное наименование центральной дирекции с заглавной буквы (остальные буквы - в lowercase). */
    @Column(name = "central_directorate_name")
    private String name;

}