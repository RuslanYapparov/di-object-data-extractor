package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.AdmStationEntity;

/** Класс, описывающий сущности работников железнодорожных станций в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "d_station_employees", schema = "empl")
public class StationEmployeeEntity extends EmployeeEntity {
    /** Поле для связи с таблицей сущностей железнодорожных станций. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d_station_id")
    private AdmStationEntity station;

    /** Метод, возвращающий строковое представление работника железнодорожной станции
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество"
    */
    @Override
    public String toString() {
        return super.toString();
    }

}