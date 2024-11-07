package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;

/** Абстрактный класс, содержащий все поля сущности рельсовой плети в базе данных (таблица rail.lashes). */
@Data
@Entity
@Table(name = "lashes", schema = "rail")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class LashEntity {
    /** Идентификатор рельсовой плети. Первичный ключ в базе данных (тип данных - BIGINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lash_id")
    protected Long id;
    /** Номер (название) рельсовой плети, тип данных VARCHAR учитывая использование букв в обозначении. */
    @Column(name = "lash_name")
    protected String name;
    /** Сторонность рельсовой нити, на которой находится рельсовая плеть. */
    @Column(name = "rail_line_side")
    protected String lineSide;
    /** Длина рельсовой плети в метрах с точностью до сотых (сантиметров). */
    @Column(name = "lash_length")
    protected Float length;
    /** Сантиметр на метре, на котором находится начало рельсовой плети. */
    @Column(name = "lash_start_centimeter_on_meter")
    protected Integer startCentimeterOnMeter;
    /** Сантиметр на метре, на котором находится конец рельсовой плети. */
    @Column(name = "lash_end_centimeter_on_meter")
    protected Integer endCentimeterOnMeter;
    /** Рельсосварочное предприятие, выпустившее рельсовую плеть. */
    @Column(name = "lash_welding_company")
    protected String weldingCompany;
    /** Дата укладки рельсовой плети в путь. */
    @Column(name = "installation_date")
    protected LocalDate installationDate;
    /** Тип укладки плети ("НОВАЯ УКЛАДКА", "УКЛАДКА СГ", "СМЕНА КАНТА"). */
    @Column(name = "installation_type")
    protected String installationType;
    /** Аббревиатура предприятия, производившая укладку рельсовой плети. */
    @Column(name = "installed_by")
    protected String installedBy;
    /** Пропущенный тоннаж по рельсовой плети до укладки. */
    @Column(name = "passed_tonnage_before_install")
    protected Float passedTonnageBeforeInstall;
    /** Пропущенный тоннаж по рельсовой плети после укладки. */
    @Column(name = "passed_tonnage_after_install")
    protected Float passedTonnageAfterInstall;
    /** Дата последней разрядки температурных напряжений в рельсовой плети. */
    @Column(name = "last_temperature_tension_discharging_date")
    protected LocalDate lastTemperatureTensionDischargingDate;
    /** Температуру, на которую была закреплена плеть при разрядке температурных напряжений. */
    @Column(name = "last_temperature_tension_discharging_temperature")
    protected Integer lastTemperatureTensionDischargingTemperature;
    /** Причина, по которой производилась последняя разрядка температурных напряжений ("ПОДВИЖКИ", "ППВ" и др.). */
    @Column(name = "last_temperature_tension_discharging_reason")
    protected String lastTemperatureTensionDischargingReason;

    /** Метод, возвращающий строковое краткое представление сущности рельсовой плети.
     *
     * @return строка вида "плеть № (номер плети) (длина плети) м, (сторонность рельсовой нити) + нить",
     * например, "плеть № 22Л 799,95 м, ЛЕВАЯ нить".
     */
    @Override
    public String toString() {
        return "плеть № " + name + " " + length + " м, " + lineSide + " нить";
    }

}