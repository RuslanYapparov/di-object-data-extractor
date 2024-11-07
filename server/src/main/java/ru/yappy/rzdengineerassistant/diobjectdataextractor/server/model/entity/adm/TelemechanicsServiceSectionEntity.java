package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm;

import lombok.*;
import jakarta.persistence.*;

/** Класс, описывающий сущности участков телемеханики дистанций сигнализации,
 * централизации и блокировки в базе данных. */
@Data
@Entity
@Table(name = "sh_service_sections", schema = "adm")
public class TelemechanicsServiceSectionEntity {
    /** Идентификатор участка телемеханики, первичный ключ в БД (тип данных - SMALLINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sh_service_section_id")
    private Long id;
    /** Номер участка телемеханики (тип данных в БД - SMALLINT). */
    @Column(name = "sh_service_section_number")
    private Integer number;
    /** Поле для связи с таблицей сущностей эксплуатационных участков дистанции сигнализации,
     * централизации и блокировки. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sh_exploitative_section_id")
    private TelemechanicsExploitativeSectionEntity telemechanicsExploitativeSection;

    /** Метод, возвращающий строковое представление сущности участка телемеханики дистанции сигнализации, централизации и блокировки
     *
     * @return строка вида "ШЧ-(номер) (название) ШЧУ-(номер) ШН-(номер)", например "ШЧ-1 Кое-гденьская ШЧУ-1 ШН-1"
    */
    @Override
    public String toString() {
        return (telemechanicsExploitativeSection == null) ? "ШН-" + number :
                telemechanicsExploitativeSection + " ШН-" + number;
    }

}