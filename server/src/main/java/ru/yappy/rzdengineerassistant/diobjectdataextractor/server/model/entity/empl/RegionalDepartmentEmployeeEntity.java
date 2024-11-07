package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.RegionalDepartmentEntity;

/** Класс, описывающий сущности работников служб, центров, отделов и участков региональных дирекций в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "regional_department_employees", schema = "empl")
public class RegionalDepartmentEmployeeEntity extends EmployeeEntity {
    /** Поле для связи с таблицей сущностей служб, центров, отделов и участков региональных дирекций. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regional_department")
    private RegionalDepartmentEntity regionalDepartment;

    /** Метод, возвращающий строковое представление работника служб, центров, отделов и участков региональных дирекций
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество"
    */
    @Override
    public String toString() {
        return super.toString();
    }

}