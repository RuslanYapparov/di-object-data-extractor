package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/** Класс, описывающий сущность участка рельсовых характеристик главных путей железнодорожных направлений. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "main_rail_sections", schema = "wayc")
public class MainWayRailSectionEntity extends MainWayCharacteristicSectionEntity {
    /** Тип рельсов. По умолчанию значение "Р65". */
    @Column(name = "rail_type")
    private String railType;
    /** Категория рельсов. Обозначает определенные характеристики рельсов. ГОСТ Р 51685-2013. */
    @Column(name = "rail_category")
    private String railCategory;
    /** Тип термпоупрочнения рельсов. В БД содержится как несокращенное описание в UPPERCASE. */
    @Column(name = "thermal_hardening")
    private String thermalHardening;
    /** Тип пути, к которому принадлежит рельсовой участок ("ЗВЕНЬЕВОЙ", "БЕССТЫКОВОЙ", "СТРЕЛОЧНЫЕ"). */
    @Column(name = "way_type")
    private String wayType;
    /** Завод-изготовитель (хранится в виде буквы, обозначающий завод: "К" - Кузнецк, "Т" - Тагил, "М" - Муром). */
    @Column(name = "factory")
    private String factory;
    /** Год производства (в БД хранится строковое значение года, например "2007"). */
    @Column(name = "factory_year")
    private String factoryYear;
    /** Дата укладки рельсов в путь. */
    @Column(name = "installation_date")
    private LocalDate installationDate;

    /** Метод, возвращающий строковое представление участка рельсовых характеристик главного пути железнодорожного
     * направления.
     *
     * @return строка вида "(тип пути) / (завод)-(год производства) / (категория рельса), (номер пути) путь,
     * (направление), (км.метр начала - км.метр конца)",
     * например, "БЕССТЫКОВОЙ / К-2007 / В, 1 путь, ТАМ - СЯМСК, 1249.115 - 1256.570". */
    @Override
    public String toString() {
        return wayType + " / " + factory + "-" + factoryYear + " / " + railCategory + ", " + super.toString();
    }

}