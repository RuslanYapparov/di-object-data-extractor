package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl.WayDistanceAdmEmployeeEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWayEntity;

import java.util.Set;

/** Класс, описывающий сущности дистанций пути в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "p_distances", schema = "adm")
public class WayMaintenanceDistanceEntity extends StructuralEnterpriseEntity {
    /** Номер дистанции (тип данных в БД - SMALLINT). */
    @Column(name = "p_distance_number")
    private Integer number;
    /** Поле-флаг, сигнализирующий о том, что предприятие является дистанцией инфраструктуры (BOOLEAN столбец в БД). */
    @Column(name = "is_ich")
    private Boolean isIch;
    /** Поле для связи с таблицей сущностей эксплуатационных участков дистанции. */
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "wayMaintenanceDistance")
    private Set<WayExploitativeSectionEntity> wayExploitativeSections;
    /** Поле для связи с таблицей сущностей отделов дистанции пути. */
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "wayMaintenanceDistance")
    private Set<WayDistanceDepartmentEntity> wayDistanceDepartments;
    /** Поле для связи с таблицей сущностей сотрудников административного блока дистанции пути. */
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "wayDistance")
    private Set<WayDistanceAdmEmployeeEntity> managers;
    /** Поле для связи с сущностями главных путей железнодорожных направлений, километры которых обслуживаются
     * дистанцией пути. */
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "main_ways_p_distances", schema = "infr",
            joinColumns = @JoinColumn(name = "p_distance_id"),
            inverseJoinColumns = @JoinColumn(name = "main_way_id"))
    private Set<MainWayEntity> mainWays;
    /** Поле для связи с сущностями станций, находящихся в границах дистанций пути. */
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "d_stations_p_distances", schema = "adm",
            joinColumns = @JoinColumn(name = "p_distance_id"),
            inverseJoinColumns = @JoinColumn(name = "d_station_id"))
    private Set<AdmStationEntity> stations;

    /** Метод, возвращающий строковое представление сущности дистанции пути
     *
     * @return строка вида "ПЧ-(номер) (название)", например "ИЧ-1 Где-нибудьская"
    */
    @Override
    public String toString() {
        String shortName = isIch ? "ИЧ-" : "ПЧ-";
        return shortName + number + " " + name;
    }

}