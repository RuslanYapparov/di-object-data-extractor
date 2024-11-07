package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.RailwayEntity;

/** Класс, описывающий сущности работников управления железных дорог в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "railway_employees", schema = "empl")
public class RailwayEmployeeEntity extends EmployeeEntity {
    /** Поле для связи с таблицей сущностей железных дорог. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "railway")
    private RailwayEntity railway;

    /** Метод, возвращающий строковое представление работника управления железной дороги
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество"
    */
    @Override
    public String toString() {
        return super.toString();
    }

}