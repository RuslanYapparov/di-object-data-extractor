package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.WayMaintenanceDistanceEntity;

/** Класс, описывающий сущности работников руководительского состава дистанций пути в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "p_distance_adm_employees", schema = "empl")
public class WayDistanceAdmEmployeeEntity extends WayMaintenanceDistanceEmployeeEntity {
    /** Поле для связи с таблицей сущностей дистанций пути. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_distance_id")
    private WayMaintenanceDistanceEntity wayDistance;

    /** Метод, возвращающий строковое представление руководителей дистанций пути.
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество" для работника с должностью без разряда,
     * строка вида "(Аббревиатура должности) (разряд)-го разряда Фамилия Имя Отчество Разряд"
     * для работника с должностью с разрядом. */
    @Override
    public String toString() {
        return super.toString();
    }

}