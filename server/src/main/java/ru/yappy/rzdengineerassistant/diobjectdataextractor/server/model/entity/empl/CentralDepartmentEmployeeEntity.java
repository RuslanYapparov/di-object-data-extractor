package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.CentralDepartmentEntity;

/** Класс, описывающий сущности работников департаментов (управлений) центральных дирекций в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "central_department_employees", schema = "empl")
public class CentralDepartmentEmployeeEntity extends EmployeeEntity {
    /** Поле для связи с таблицей сущностей департаментов (управлений) центральных дирекций. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "central_department")
    private CentralDepartmentEntity centralDepartment;

    /** Метод, возвращающий строковое представление работника департамента центральной дирекции
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество"
    */
    @Override
    public String toString() {
        return super.toString();
    }

}