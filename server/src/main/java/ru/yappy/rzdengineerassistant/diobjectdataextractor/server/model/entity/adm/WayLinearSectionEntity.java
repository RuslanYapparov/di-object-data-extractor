package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm;

import lombok.*;
import jakarta.persistence.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.KilometerEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationWayEntity;

import java.util.Set;

/** Класс, описывающий сущности линейных участков дистанций пути в базе данных. */
@Data
@Entity
@Table(name = "p_linear_sections", schema = "adm")
public class WayLinearSectionEntity {
    /** Идентификатор линейного участка, первичный ключ в БД (тип данных - SMALLINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_linear_section_id")
    private Long id;
    /** Номер линейного участка (тип данных в БД - SMALLINT). */
    @Column(name = "p_linear_section_number")
    private Integer number;
    /** Поле для связи с таблицей сущностей эксплуатационных участков дистанции пути. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_exploitative_section_id")
    private WayExploitativeSectionEntity wayExploitativeSection;
    /** Поле для связи с сущностями километров железнодорожных транспортных направлений. */
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "wayLinearSection")
    private Set<KilometerEntity> kilometers;
    /** Поле для связи с сущностями станционных путей, которые обслуживает данный линейный участок. */
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "wayLinearSection")
    private Set<StationWayEntity> stationWays;

    /** Метод, возвращающий строковое представление сущности линейного участка дистанции пути
     *
     * @return строка вида "ПЧ-(номер) (название) ПЧУ-(номер) ПД-(номер)", например "ПЧ-1 Кое-гденьская ПЧУ-1 ПД-2"
    */
    @Override
    public String toString() {
        return (wayExploitativeSection == null) ? "ПД-" + number : wayExploitativeSection + " ПД-" + number;
    }

}