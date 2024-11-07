package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import lombok.*;
import jakarta.persistence.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.WayLinearSectionEntity;

/** Абстрактный класс, содержащий поля для всех представленных сущностей искусственных сооружений. */
@Data
@Entity
@Table(name = "artificial_constructions", schema = "infr")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ArtificialConstructionEntity {
    /** Идентификатор сущности искусственного сооружения. Первичный ключ (тип данных в БД - BIGINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "isso_id")
    protected Long id;
    /** Материал, из которого сделано искусственное сооружение. */
    @Column(name = "material_type")
    protected String materialType;
    /** Участок обслуживания искусственных сооружений (представляет собой сущность линейного участка дистанции пути
     * и хранится в таблице adm.p_linear_sections). */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_isso_linear_section_id")
    protected WayLinearSectionEntity wayLinearSection;

}