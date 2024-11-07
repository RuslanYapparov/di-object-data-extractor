package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.TelemechanicsDistanceDepartmentEntity;

/** Класс, описывающий сущности работников отделов дистанций сигнализации, централизации и блокировки в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sh_distance_department_employees", schema = "empl")
public class TelemechanicsDistanceDepartmentEmployeeEntity extends StructuralEnterpriseEmployeeEntity {
    /** Поле для связи с таблицей сущностей отделов дистанций сигнализации, централизации и блокировки. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sh_distance_department_id")
    private TelemechanicsDistanceDepartmentEntity telemechanicsDistanceDepartment;

    /** Метод, возвращающий строковое представление работника отдела дистанций сигнализации, централизации и блокировки
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество" для работника с должностью без разряда,
     *      * строка вида "(Аббревиатура должности) (разряд)-го разряда Фамилия Имя Отчество Разряд"
     *      * для работника с должностью с разрядом. */
    @Override
    public String toString() {
        return super.toString();
    }

}