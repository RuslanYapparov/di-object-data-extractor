package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.TelemechanicsServiceSectionEntity;

/** Класс, описывающий сущности работников участков обслуживания телемеханики в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sh_service_section_employees", schema = "empl")
public class TelemechanicsServiceSectionEmployeeEntity extends StructuralEnterpriseEmployeeEntity {
    /** Поле для связи с таблицей сущностей участков обслуживания телемеханики. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sh_service_section_id")
    private TelemechanicsServiceSectionEntity telemechanicsServiceSection;

    /** Метод, возвращающий строковое представление работника участка обслуживания телемеханики
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество" для работника с должностью без разряда,
     * строка вида "(Аббревиатура должности) (разряд)-го разряда Фамилия Имя Отчество Разряд"
     * для работника с должностью с разрядом. */
    @Override
    public String toString() {
        return super.toString();
    }

}