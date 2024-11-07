package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.TelemechanicsMaintenanceDistanceEntity;

/** Класс, описывающий сущности работников управления дистанций сигнализации, централизации и блокировки в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sh_distance_employees", schema = "empl")
public class TelemechanicsMaintenanceDistanceEmployeeEntity extends EmployeeEntity {
    /** Поле для связи с таблицей сущностей дистанций сигнализации, централизации и блокировки. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sh_distance_id")
    private TelemechanicsMaintenanceDistanceEntity telemechanicsMaintenanceDistance;

    /** Метод, возвращающий строковое представление работника управления дистанции сигнализации, централизации и блокировки
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество"
    */
    @Override
    public String toString() {
        return super.toString();
    }

}