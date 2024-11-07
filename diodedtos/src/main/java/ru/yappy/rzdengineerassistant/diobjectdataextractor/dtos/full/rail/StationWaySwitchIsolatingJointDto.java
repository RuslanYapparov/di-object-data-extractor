package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;


import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationWayPlaceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LineSide;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.SwitchDto;

import java.time.LocalDate;

/**
 * Класс DTO для хранения и передачи данных об изолирующем стыке стрелочного перевода,
 * расположенного на станционном пути.
 */
public class StationWaySwitchIsolatingJointDto extends SwitchIsolatingJointDto implements StationWayJoint {
    /** DTO с информацией о конкретном месте станционного пути, на котором находится изолирующий стык. */
    private final StationWayPlaceDto stationWayPlace;

    /**
     * Конструктор класса в иерархии наследования DTO изолирующих стыков, относящихся к стрелочным переводам
     * станционных путей, принимающий значения всех полей для создания объекта-наследника.
     *
     * @param id идентификатор стыка в БД
     * @param lineSide сторона рельсовой нити, на которой расположен стык
     * @param status статус рельсового стыка
     * @param padsType тип накладок
     * @param padAmountOfHoles количество болтовых отверстий
     * @param gap значение величины стыкового зазора
     * @param verticalStep значение величины вертикальной ступеньки
     * @param horizontalStep значение величины горизонтальной ступеньки
     * @param lastMeasureDate дата последнего измерения характеристик рельсового стыка
     * @param lastMeasureRailTemp температура рельса в момент последнего измерения характеристик
     * @param lastPadsRemovalDate дата последней переборки изолирующего стыка
     * @param lastDemagnetizationDate дата последнего проведения демагнетизации
     * @param lastDemagnetizationMethod метод проведения последней демагнетизации
     * @param magnetization значение величины намагниченности
     * @param lastMagnetizationMeasureDate дата последнего измерения намагниченности
     * @param jointSwitch DTO с информацией о стрелочном переводе, на котором находится изолирующий стык
     * @param jointNumber номер изолирующего стыка в зависимости от размещения на схеме стрелочного перевода
     * @param stationWayPlace DTO с информацией о месте станционного пути, на котором находится изолирующий стык
     */
    public StationWaySwitchIsolatingJointDto(Long id,
                                             LineSide lineSide,
                                             String status,
                                             String padsType,
                                             Integer padAmountOfHoles,
                                             Integer gap,
                                             Float verticalStep,
                                             Float horizontalStep,
                                             @JsonFormat(pattern = "dd-MM-yyyy")
                                             LocalDate lastMeasureDate,
                                             Integer lastMeasureRailTemp,
                                             @JsonFormat(pattern = "dd-MM-yyyy")
                                             LocalDate lastPadsRemovalDate,
                                             @JsonFormat(pattern = "dd-MM-yyyy")
                                             LocalDate lastDemagnetizationDate,
                                             String lastDemagnetizationMethod,
                                             Integer magnetization,
                                             @JsonFormat(pattern = "dd-MM-yyyy")
                                             LocalDate lastMagnetizationMeasureDate,
                                             SwitchDto jointSwitch,
                                             Integer jointNumber,
                                             StationWayPlaceDto stationWayPlace) {
        super(id, lineSide, status, padsType, padAmountOfHoles, gap, verticalStep, horizontalStep, lastMeasureDate,
                lastMeasureRailTemp, lastPadsRemovalDate, lastDemagnetizationDate, lastDemagnetizationMethod,
                magnetization, lastMagnetizationMeasureDate, jointSwitch, jointNumber);
        this.stationWayPlace = stationWayPlace;
    }

    /**
     * Геттер DTO с информацией о месте станционного пути, на котором находится изолирующий стык.
     *
     * @return DTO с информацией о конкретном месте станционного пути
     */
    @Override
    public StationWayPlaceDto getStationWayPlace() {
        return stationWayPlace;
    }

}