package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/** Класс, описывающий сущности станций в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "d_stations", schema = "adm")
public class AdmStationEntity extends StructuralEnterpriseEntity {
    /** Поле для связи с сущностями дистанций пути, обслуживающих пути станции. */
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "d_stations_p_distances", schema = "adm",
            joinColumns = @JoinColumn(name = "d_station_id"),
            inverseJoinColumns = @JoinColumn(name = "p_distance_id"))
    private Set<WayMaintenanceDistanceEntity> wayMaintenanceDistances;
    /** Поле для связи с сущностями дистанций сигнализации, централизации и блокировки,
     * обслуживающих объекты станции. */
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "d_stations_sh_distances", schema = "adm",
            joinColumns = @JoinColumn(name = "d_station_id"),
            inverseJoinColumns = @JoinColumn(name = "sh_distance_id"))
    private Set<TelemechanicsMaintenanceDistanceEntity> telemechanicsMaintenanceDistances;

    /** Метод, возвращающий строковое представление сущности станции
     *
     * @return строка вида "ст. (название)", например "ст. Где-нибудьская"
    */
    @Override
    public String toString() {
        return "ст. " + name;
    }

}