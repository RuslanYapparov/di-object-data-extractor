package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.MainWaySectionDto;

import java.time.LocalDate;

/**
 * Класс DTO сущности рельсовой плети, лежащей на главных путях железнодорожного направления.
 */
public class MainWayLashDto extends LashDto {
    /** Номер главного пути, на котором лежит плеть. */
    private final Integer mainWayNumber;
    /** Участок главного пути, на котором лежит плеть. */
    private final MainWaySectionDto mainWaySection;

    /**
     * Конструктор класса DTO плети главного пути, принимающий в качестве параметров значения всех полей.
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
     * @param mainWayNumber номер главного пути, на котором лежит плеть
     * @param mainWaySection участок главного пути, на котором лежит плеть
     */
    public MainWayLashDto(Long id,
                          String name,
                          String lineSide,
                          Float length,
                          Integer startCentimeterOnMeter,
                          Integer endCentimeterOnMeter,
                          String weldingCompany,
                          @JsonFormat(pattern = "dd-MM-yyyy")
                          LocalDate installationDate,
                          String installationType,
                          String installedBy,
                          Float passedTonnageBeforeInstall,
                          Float passedTonnageAfterInstall,
                          @JsonFormat(pattern = "dd-MM-yyyy")
                          LocalDate lastTemperatureTensionDischargingDate,
                          Integer lastTemperatureTensionDischargingTemperature,
                          String lastTemperatureTensionDischargingReason,
                          Integer mainWayNumber,
                          MainWaySectionDto mainWaySection) {
        super(id, name, lineSide, length, startCentimeterOnMeter, endCentimeterOnMeter, weldingCompany,
                installationDate, installationType, installedBy, passedTonnageBeforeInstall, passedTonnageAfterInstall,
                lastTemperatureTensionDischargingDate, lastTemperatureTensionDischargingTemperature,
                lastTemperatureTensionDischargingReason);
        this.mainWayNumber = mainWayNumber;
        this.mainWaySection = mainWaySection;
    }

    /**
     * Геттер номера главного пути, на котором лежит плеть.
     *
     * @return номер главного пути, на котором лежит плеть
     */
    public Integer getMainWayNumber() {
        return mainWayNumber;
    }

    /**
     * Геттер участка главного пути, на котором лежит плеть.
     *
     * @return участок главного пути, на котором лежит плеть
     */
    public MainWaySectionDto getMainWaySection() {
        return mainWaySection;
    }

}