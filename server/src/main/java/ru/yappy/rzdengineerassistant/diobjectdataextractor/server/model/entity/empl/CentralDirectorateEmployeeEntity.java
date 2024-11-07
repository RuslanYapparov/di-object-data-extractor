package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.CentralDirectorateEntity;

/** Класс, описывающий сущности работников управления центральных дирекций в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "central_directorate_employees", schema = "empl")
public class CentralDirectorateEmployeeEntity extends EmployeeEntity {
    /** Поле для связи с таблицей сущностей центральных дирекций. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "central_directorate")
    private CentralDirectorateEntity centralDirectorate;

    /** Метод, возвращающий строковое представление работника управления центральных дирекций
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество"
    */
    @Override
    public String toString() {
        return super.toString();
    }

}