package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/** Класс, описывающий сущность участка рельсовых характеристик станционных путей железнодорожных станций. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "station_rail_sections", schema = "wayc")
public class StationWayRailSectionEntity extends StationWayCharacteristicSectionEntity {
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

    /** Метод, возвращающий строковое представление участка рельсовых характеристик станционного пути.
     *
     * @return строка вида "(тип пути) / (завод)-(год производства) / (категория рельса), путь № (номер ст. пути)
     * станции (название станции), (метр начала)-(метр конца)",
     * например "ЗВЕНЬЕВОЙ / Т-2010 / НТ, путь № 4 станции ГДЕ-ЛИБО, 507-532". */
    @Override
    public String toString() {
        return wayType + " / " + factory + "-" + factoryYear + " / " + railCategory + ", " + super.toString();
    }

}