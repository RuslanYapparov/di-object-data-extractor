package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.TelemechanicsServiceSectionEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.WayLinearSectionEntity;

/** Класс, описывающий сущности километров железнодорожных направлений в базе данных. */
@Data
@Entity
@Table(name = "kilometers", schema = "infr")
public class KilometerEntity {
    /** Идентификатор железнодорожного направления. Вместе с числовым значением километра составляют первичный ключ. */
    @EqualsAndHashCode.Exclude
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_direction_id")
    private TransportDirectionEntity transportDirection;
    /** Числовое значение километра. Вместе с идентификатором железнодорожного направления составляют первичный ключ. */
    @Column(name = "km")
    private Integer km;
    /** Поле-флаг, означающий, что километр не является обычным, то есть его длина отличается от 1000 метров. */
    @Column(name = "non_standard")
    private Boolean nonStandard;
    /** Количество метров в километре (по умолчанию - 1000). В нестандартном километре отличается от 1000 метров. */
    @Column(name = "km_length")
    private Integer kmLength;
    /** Линейный участок дистанции пути, обслуживающий данный километр. Поле связано с таблицей "way_linear_sections". */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_linear_section_id")
    private WayLinearSectionEntity wayLinearSection;
    /** Участок обслуживания телемеханики дистанции сигнализации, централизации и блокировки.
     * Поле связано с таблицей "telemechanics_service_sections". */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sh_service_section_id")
    private TelemechanicsServiceSectionEntity telemechanicsServiceSection;

    /** Метод, возвращающий строковое представление километра.
     *
     * @return строка вида "(направление), (км)", например "КОЕ-ГДЕНЬ - КОЕ-ГДЕНЬ ГРУЗОВАЯ, 4"
    */
    @Override
    public String toString() {
        return (transportDirection == null) ? String.valueOf(km) : transportDirection.getName() + ", " + km;
    }

}