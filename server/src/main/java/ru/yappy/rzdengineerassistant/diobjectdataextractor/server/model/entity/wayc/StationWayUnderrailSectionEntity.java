package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущность участка характеристик подрельсового основания станционных путей железнодорожных стаций */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "station_underrail_base_sections", schema = "wayc")
public class StationWayUnderrailSectionEntity extends StationWayCharacteristicSectionEntity {
    /** Материал, из которого изготовлены железнодорожные шпалы рассматриваемого участка ("Ж/Б", "ДЕРЕВННЫЕ"). */
    @Column(name = "sleeper_material")
    private String sleeperMaterial;
    /** Количество шпал на километре рассматриваемого участка. */
    @Column(name = "sleepers_per_km")
    private Integer sleepersPerKm;
    /** Тип рельсошпального скрепления рассматриваемого участка ("КБ", "ДО", "ЖБР" и др.). */
    @Column(name = "fastening_type")
    private String fasteningType;
    /** Тип материала балластной призмы рассматриваемого участка ("ЩЕБЕНЬ", "ПГС" и др.). */
    @Column(name = "ballast_type")
    private String ballastType;
    /** Высота балластной призмы рассматриваемого участка в сантиметрах. */
    @Column(name = "ballast_height")
    private Integer ballastHeight;

    /** Метод, возвращающий строковое представление участка характеристик подрельсового основания станционного пути.
     *
     * @return строка вида "(тип шпал) / (тип скрепления) / (тип балласта), путь № (номер ст. пути) станции
     * (название станции), (метр начала)-(метр конца)",
     * например "ДЕРЕВЯННЫЕ / ДО / ЩЕБЕНЬ, путь № 4 станции ГДЕ-ЛИБО, 507-532". */
    @Override
    public String toString() {
        return sleeperMaterial + " / " + fasteningType + " / " + ballastType + ", " + super.toString();
    }

}