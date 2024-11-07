package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;

/** Абстрактный класс, содержащий поля, характерные для всех сущностей туннелей в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tunnels", schema = "infr")
public abstract class TunnelEntity extends ArtificialConstructionEntity {
    /** Название туннеля в UPPER_CASE. */
    @Column(name = "tunnel_name")
    protected String name;
    /** Тип туннеля в зависимости от его размера ("БОЛЬШОЙ", "СРЕДНИЙ", "МАЛЫЙ"). */
    @Column(name = "size_type")
    protected String sizeType;
    /** Тип туннеля в зависимости от его назначения ("МОСТ", "ПЕШ. МОСТ", "ВИАДУК" и др.). */
    @Column(name = "appointment")
    protected String appointment;

    /** Метод, возвращающий строковое представление сущности железнодорожного туннеля.
     *
     * @return строка вида "(размер туннеля) (название туннеля)", например "СРЕДНИЙ ТОННЕЛЬ ЧЕРЕЗ Г. БАЛДА 1 ПУТЬ"
     */
    @Override
    public String toString() {
        return (sizeType == null) ? "" : sizeType + " " + name;
    }

}