package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.RegionalDirectorateEntity;

/** Класс, описывающий сущности работников управления региональных дирекций в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "regional_directorate_employees", schema = "empl")
public class RegionalDirectorateEmployeeEntity extends EmployeeEntity {
    /** Поле для связи с таблицей сущностей региональных дирекций. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regional_directorate")
    private RegionalDirectorateEntity regionalDirectorate;

    /** Метод, возвращающий строковое представление работника управления региональной дирекции
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество"
    */
    @Override
    public String toString() {
        return super.toString();
    }

}