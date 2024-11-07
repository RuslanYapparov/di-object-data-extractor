package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущность участка характеристик подрельсового основания главных путей железнодорожных направлений. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "main_underrail_base_sections", schema = "wayc")
public class MainWayUnderrailSectionEntity extends MainWayCharacteristicSectionEntity {
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

    /** Метод, возвращающий строковое представление участка характеристик подрельсового основания главного пути
     * железнодорожного направления.
     *
     * @return строка вида "(тип шпал) / (тип скрепления) / (тип балласта), (номер пути) путь, (направление),
     * (км.метр начала - км.метр конца)", например, "Ж/Б / КБ / ЩЕБЕНЬ, 1 путь, ТАМ - СЯМСК, 1249.115 - 1256.570". */
    @Override
    public String toString() {
        return sleeperMaterial + " / " + fasteningType + " / " + ballastType + ", " + super.toString();
    }

}