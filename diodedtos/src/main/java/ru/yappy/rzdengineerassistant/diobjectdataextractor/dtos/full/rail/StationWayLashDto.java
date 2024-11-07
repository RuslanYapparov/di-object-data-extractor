package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationWaySectionDto;

import java.time.LocalDate;

/**
 * Класс DTO сущности рельсовой плети, лежащей на станционном пути.
 */
public class StationWayLashDto extends LashDto {
    /** Участок станционного пути, на котором лежит плеть. */
    private final StationWaySectionDto stationWaySection;

    /**
     * Конструктор класса DTO плети станционного пути, принимающий в качестве параметров значения всех полей.
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
     * @param stationWaySection участок станционного пути, на котором лежит плеть
     */
    public StationWayLashDto(Long id,
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
                             StationWaySectionDto stationWaySection) {
        super(id, name, lineSide, length, startCentimeterOnMeter, endCentimeterOnMeter, weldingCompany,
                installationDate, installationType, installedBy, passedTonnageBeforeInstall, passedTonnageAfterInstall,
                lastTemperatureTensionDischargingDate, lastTemperatureTensionDischargingTemperature,
                lastTemperatureTensionDischargingReason);
        this.stationWaySection = stationWaySection;
    }

    /**
     * Геттер участка станционного пути, на котором лежит плеть.
     *
     * @return участок станционного пути, на котором лежит плеть
     */
    public StationWaySectionDto getStationWaySection() {
        return stationWaySection;
    }

}