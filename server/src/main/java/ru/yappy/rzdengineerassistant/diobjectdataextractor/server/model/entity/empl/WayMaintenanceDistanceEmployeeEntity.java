package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import jakarta.persistence.*;
import lombok.*;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.WayMaintenanceDistanceEntity;

/** Абстрактный класс, описывающий сущности всех разновидностей работников дистанций пути в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "p_distance_employees", schema = "empl")
public abstract class WayMaintenanceDistanceEmployeeEntity extends StructuralEnterpriseEmployeeEntity {
    /** Поле для связи с таблицей сущностей дистанций пути. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_distance_id")
    private WayMaintenanceDistanceEntity wayMaintenanceDistance;

    /** Метод, возвращающий строковое представление любого работника дистанции пути.
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество"
    */
    @Override
    public String toString() {
        return super.toString();
    }

}