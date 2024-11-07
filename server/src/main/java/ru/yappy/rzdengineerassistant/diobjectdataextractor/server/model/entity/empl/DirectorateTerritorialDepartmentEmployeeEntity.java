package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.DirectorateTerritorialDepartmentEntity;

/** Класс, описывающий сущности работников территориальных отделов региональных дирекций в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "directorate_territorial_department_employees", schema = "empl")
public class DirectorateTerritorialDepartmentEmployeeEntity extends EmployeeEntity {
    /** Поле для связи с таблицей сущностей территориальных отделов региональных дирекций. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directorate_territorial_department_id")
    private DirectorateTerritorialDepartmentEntity directorateTerritorialDepartment;

    /** Метод, возвращающий строковое представление работника территориального отдела региональной дирекции
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество"
    */
    @Override
    public String toString() {
        return super.toString();
    }

}