package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.RailwayTerritorialDepartmentEntity;

/** Класс, описывающий сущности работников территориальных отделов железных дорог в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "railway_territorial_department_employees", schema = "empl")
public class RailwayTerritorialDepartmentEmployeeEntity extends EmployeeEntity {
    /** Поле для связи с таблицей сущностей территориальных отделов железных дорог. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "railway_territorial_department_id")
    private RailwayTerritorialDepartmentEntity railwayTerritorialDepartment;

    /** Метод, возвращающий строковое представление работника территориального отдела железной дороги
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество"
    */
    @Override
    public String toString() {
        return super.toString();
    }

}