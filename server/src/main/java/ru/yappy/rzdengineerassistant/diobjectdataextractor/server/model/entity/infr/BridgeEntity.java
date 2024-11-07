package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import lombok.*;
import jakarta.persistence.*;

/** Абстрактный класс, содержащий поля, характерные для всех мостов в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bridges", schema = "infr")
public abstract class BridgeEntity extends ArtificialConstructionEntity {
    /** Название моста в UPPER_CASE. */
    @Column(name = "bridge_name")
    protected String name;
    /** Тип моста в зависимости от его размера ("БОЛЬШОЙ", "СРЕДНИЙ", "МАЛЫЙ"). */
    @Column(name = "size_type")
    protected String sizeType;
    /** Тип моста в зависимости от его назначения ("МОСТ", "ПЕШ. МОСТ", "ВИАДУК" и др.). */
    @Column(name = "appointment")
    protected String appointment;
    /** Тип моста в зависимости от реализации его статической схемы ("БАЛОЧНЫЙ",
     * "ФЕРМЫ Н" (ферменный с ездой по низу), "ФЕРМЫ В" (ферменный с ездой по верху), "ВАНТОВЫЙ" и др.). */
    @Column(name = "static_schema_type")
    protected String staticSchemaType;

    /** Метод, возвращающий строковое представление сущности железнодорожного моста.
     *
     * @return строка вида "(размер моста) (название моста)", например "СРЕДНИЙ МОСТ ЧЕРЕЗ Р. ЧУВИХА 2 ПУТЬ"
     */
    @Override
    public String toString() {
        return (sizeType == null) ? "" : sizeType + " " + name;
    }

}