package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.WayLinearSectionEntity;

/** Класс, описывающий сущности работников линейных участков пути в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "p_linear_section_employees", schema = "empl")
public class WayLinearSectionEmployeeEntity extends WayMaintenanceDistanceEmployeeEntity {
    /** Поле для связи с таблицей сущностей линейных участков пути. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_linear_section_id")
    private WayLinearSectionEntity wayLinearSection;

    /** Метод, возвращающий строковое представление работника линейного участка пути
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество" для работника с должностью без разряда,
     * строка вида "(Аббревиатура должности) (разряд)-го разряда Фамилия Имя Отчество Разряд"
     * для работника с должностью с разрядом. */
    @Override
    public String toString() {
        return super.toString();
    }

}