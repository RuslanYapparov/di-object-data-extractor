package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import lombok.*;
import jakarta.persistence.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.AdmStationEntity;

/** Класс, описывающий сущность железнодорожной станции в базе данных, отражающий ее характеристики. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "stations", schema = "infr")
public class StationEntity extends AdmStationEntity {
    /** Участок железнодорожного направления (главных путей), на котором расположена станция
     * (начальная граница, конечная граница, протяженность). */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private MainWaySectionEntity section;
    /** Класс станции, отражается римскими цифрами. */
    @Column(name = "station_class")
    private String stationClass;
    /** Назначение станции ("ПАССАЖИРСКАЯ", "ГРУЗОВАЯ", "ПАССАЖИРСКО-ГРУЗОВАЯ"). */
    @Column(name = "appointment")
    private String appointment;
    /** Вид станции с точки зрения технологического процесса железной дороги
     * ("ПРОМЕЖУТОЧНАЯ", "СОРТИРОВОЧНАЯ", "УЗЛОВАЯ") */
    @Column(name = "technological_use")
    private String technologicalUse;

    /** Метод, возвращающий строковое представление железнодорожной станции.
     *
     * @return строка вида "ст. (название станции)", например "ст. ГДЕ-НИБУДЬСК".
     */
    @Override
    public String toString() {
        return super.toString();
    }

}