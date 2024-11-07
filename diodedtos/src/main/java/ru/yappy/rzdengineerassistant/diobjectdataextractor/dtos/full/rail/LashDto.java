package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Абстрактный класс, содержащий поля, характерные для всех DTO сущностей рельсовых плетей (основная информация
 * о плети, не касающаяся ее размещения).
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "wayType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MainWayLashDto.class, name = "ГЛАВ"),
        @JsonSubTypes.Type(value = StationWayLashDto.class, name = "СТАНЦ")
})
public abstract class LashDto {
    /** Идентификатор рельсовой плети. */
    private final Long id;
    /** Цифробуквенное обозначение рельсовой плети. */
    private final String name;
    /** Сторонность рельсовой нити, на которой лежит плеть. */
    private final String lineSide;
    /** Длина рельсовой плети. */
    private final Float length;
    /** Сантиметр расположения начала рельсовой плети на метре ее переднего торца. */
    private final Integer startCentimeterOnMeter;
    /** Сантиметр расположения конца рельсовой плети на метре ее заднего торца. */
    private final Integer endCentimeterOnMeter;
    /** Рельсо-сварочное предприятие, на котором была изготовлена плеть. */
    private final String weldingCompany;
    /** Дата укладки рельсовой плети. */
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDate installationDate;
    /** Тип укладки. */
    private final String installationType;
    /** Предприятие, производившее укладку рельсовой плети.*/
    private final String installedBy;
    /** Пропущенный тоннаж до укладки рельсовой плети в путь. */
    private final Float passedTonnageBeforeInstall;
    /** Пропущенный тоннаж после укладки рельсовой плети в путь. */
    private final Float passedTonnageAfterInstall;
    /** Дата последней разрядки температурных напряжений в рельсовой плети. */
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDate lastTemperatureTensionDischargingDate;
    /** Температура, на которую была закреплена плеть в ходе последней разрядки температурных напряжений. */
    private final Integer lastTemperatureTensionDischargingTemperature;
    /** Причина последней разрядки температурных напряжений в рельсовой плети. */
    private final String lastTemperatureTensionDischargingReason;

    /**
     * Конструктор суперкласса, принимающий в качестве параметров значения всех полей.
     *
     * @param id идентификатор рельсовой плети
     * @param name цифробуквенное обозначение рельсовой плети
     * @param lineSide сторонность рельсовой нити, на которой лежит плеть
     * @param length длина рельсовой плети
     * @param startCentimeterOnMeter сантиметр расположения начала рельсовой плети на метре ее переднего торца
     * @param endCentimeterOnMeter сантиметр расположения конца рельсовой плети на метре ее заднего торца
     * @param weldingCompany рельсо-сварочное предприятие, на котором была изготовлена плеть
     * @param installationDate дата укладки рельсовой плети
     * @param installationType тип укладки
     * @param installedBy предприятие, производившее укладку рельсовой плети
     * @param passedTonnageBeforeInstall пропущенный тоннаж до укладки рельсовой плети в путь
     * @param passedTonnageAfterInstall пропущенный тоннаж после укладки рельсовой плети в путь
     * @param lastTemperatureTensionDischargingDate дата последней разрядки температурных напряжений в рельсовой плети
     * @param lastTemperatureTensionDischargingTemperature температура, на которую была закреплена плеть
     * @param lastTemperatureTensionDischargingReason причина последней разрядки температурных напряжений в плети
     */
    public LashDto(Long id,
                   String name,
                   String lineSide,
                   Float length,
                   Integer startCentimeterOnMeter,
                   Integer endCentimeterOnMeter,
                   String weldingCompany,
                   LocalDate installationDate,
                   String installationType,
                   String installedBy,
                   Float passedTonnageBeforeInstall,
                   Float passedTonnageAfterInstall,
                   LocalDate lastTemperatureTensionDischargingDate,
                   Integer lastTemperatureTensionDischargingTemperature,
                   String lastTemperatureTensionDischargingReason) {
        this.id = id;
        this.name = name;
        this.lineSide = lineSide;
        this.length = length;
        this.startCentimeterOnMeter = startCentimeterOnMeter;
        this.endCentimeterOnMeter = endCentimeterOnMeter;
        this.weldingCompany = weldingCompany;
        this.installationDate = installationDate;
        this.installationType = installationType;
        this.installedBy = installedBy;
        this.passedTonnageBeforeInstall = passedTonnageBeforeInstall;
        this.passedTonnageAfterInstall = passedTonnageAfterInstall;
        this.lastTemperatureTensionDischargingDate = lastTemperatureTensionDischargingDate;
        this.lastTemperatureTensionDischargingTemperature = lastTemperatureTensionDischargingTemperature;
        this.lastTemperatureTensionDischargingReason = lastTemperatureTensionDischargingReason;
    }

    /**
     * Геттер идентификатора рельсовой плети.
     *
     * @return идентификатор рельсовой плети
     */
    public Long getId() {
        return id;
    }

    /**
     * Геттер цифробуквенного обозначения рельсовой плети.
     *
     * @return название рельсовой плети
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер сторонности рельсовой нити, на которой лежит плеть.
     *
     * @return сторонность рельсовой нити, на которой лежит плеть
     */
    public String getLineSide() {
        return lineSide;
    }

    /**
     * Геттер длины рельсовой плети.
     *
     * @return длина рельсовой плети
     */
    public Float getLength() {
        return length;
    }

    /**
     * Геттер сантиметра расположения начала рельсовой плети на метре ее переднего торца.
     *
     * @return сантиметр начала рельсовой плети
     */
    public Integer getStartCentimeterOnMeter() {
        return startCentimeterOnMeter;
    }

    /**
     * Геттер сантиметра расположения конца рельсовой плети на метре ее заднего торца.
     *
     * @return сантиметр конца рельсовой плети
     */
    public Integer getEndCentimeterOnMeter() {
        return endCentimeterOnMeter;
    }

    /**
     * Геттер рельсо-сварочного предприятия, на котором была изготовлена плеть.
     *
     * @return название рельсо-сварочного предприятия
     */
    public String getWeldingCompany() {
        return weldingCompany;
    }

    /**
     * Геттер даты укладки рельсовой плети.
     *
     * @return дата укладки рельсовой плети
     */
    public LocalDate getInstallationDate() {
        return installationDate;
    }

    /**
     * Геттер типа укладки рельсовой плети.
     *
     * @return тип укладки рельсовой плети
     */
    public String getInstallationType() {
        return installationType;
    }

    /**
     * Геттер предприятия, производившего укладку рельсовой плети.
     *
     * @return наименование предприятия, производящего укладку рельсовой плети
     */
    public String getInstalledBy() {
        return installedBy;
    }

    /**
     * Геттер пропущенного тоннажа перед укладкой рельсовой плети.
     *
     * @return пропущенный тоннаж перед укладкой рельсовой плети
     */
    public Float getPassedTonnageBeforeInstall() {
        return passedTonnageBeforeInstall;
    }

    /**
     * Геттер пропущенного тоннажа после укладки рельсовой плети.
     *
     * @return пропущенный тоннаж после укладки рельсовой плети
     */
    public Float getPassedTonnageAfterInstall() {
        return passedTonnageAfterInstall;
    }

    /**
     * Геттер даты последней разрядки температурных напряжений в рельсовой плети.
     *
     * @return дата последней разрядки температурных напряжений в рельсовой плети
     */
    public LocalDate getLastTemperatureTensionDischargingDate() {
        return lastTemperatureTensionDischargingDate;
    }

    /**
     * Геттер температуры рельса, на которую была закреплена плеть в ходе последней разрядки.
     *
     * @return температура рельса, на которую была закреплена плеть в ходе последней разрядки
     */
    public Integer getLastTemperatureTensionDischargingTemperature() {
        return lastTemperatureTensionDischargingTemperature;
    }

    /**
     * Геттер причины последней разрядки температурных напряжений в рельсовой плети.
     *
     * @return причина последней разрядки температурных напряжений в рельсовой плети
     */
    public String getLastTemperatureTensionDischargingReason() {
        return lastTemperatureTensionDischargingReason;
    }

    /** Сравнение двух объектов, используя их состояние (значения всех полей).
     *
     * @param o другой объект для сравнения
     * @return булевое значение, определяющее равенство объектов
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LashDto lashDto)) return false;
        return Objects.equals(id, lashDto.id) &&
                Objects.equals(name, lashDto.name) &&
                Objects.equals(lineSide, lashDto.lineSide) &&
                Objects.equals(length, lashDto.length) &&
                Objects.equals(startCentimeterOnMeter, lashDto.startCentimeterOnMeter) &&
                Objects.equals(endCentimeterOnMeter, lashDto.endCentimeterOnMeter) &&
                Objects.equals(weldingCompany, lashDto.weldingCompany) &&
                Objects.equals(installationDate, lashDto.installationDate) &&
                Objects.equals(installationType, lashDto.installationType) &&
                Objects.equals(installedBy, lashDto.installedBy) &&
                Objects.equals(passedTonnageBeforeInstall, lashDto.passedTonnageBeforeInstall) &&
                Objects.equals(passedTonnageAfterInstall, lashDto.passedTonnageAfterInstall) &&
                Objects.equals(lastTemperatureTensionDischargingDate, lashDto.lastTemperatureTensionDischargingDate) &&
                Objects.equals(lastTemperatureTensionDischargingTemperature,
                        lashDto.lastTemperatureTensionDischargingTemperature) &&
                Objects.equals(lastTemperatureTensionDischargingReason,
                        lashDto.lastTemperatureTensionDischargingReason);
    }

    /** Получение хэш-кода объекта.
     *
     * @return числовое представление объекта на основании его состояния (значений всех полей)
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, lineSide, length, startCentimeterOnMeter, endCentimeterOnMeter, weldingCompany,
                installationDate, installationType, installedBy, passedTonnageBeforeInstall, passedTonnageAfterInstall,
                lastTemperatureTensionDischargingDate, lastTemperatureTensionDischargingTemperature,
                lastTemperatureTensionDischargingReason);
    }

}