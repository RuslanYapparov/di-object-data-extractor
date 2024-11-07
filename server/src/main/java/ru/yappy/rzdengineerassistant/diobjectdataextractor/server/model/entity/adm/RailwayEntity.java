package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущности железных дорог (управлений железных дорог) в базе данных. */
@Data
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = false)
@Entity
@Table(name = "railways", schema = "adm")
public class RailwayEntity {
    /** Аббревиатура железной дороги, первичный ключ в БД. */
    @ToString.Include
    @Id
    @Column(name = "railway_abbreviation")
    private String abbreviation;
    /** Полное наименование железной дороги с заглавной буквы (остальные буквы - в lowercase). */
    @Column(name = "railway_name")
    private String name;

}