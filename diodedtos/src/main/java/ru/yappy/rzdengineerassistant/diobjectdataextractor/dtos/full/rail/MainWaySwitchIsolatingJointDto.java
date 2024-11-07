package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.MainWayPlaceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LineSide;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.SwitchDto;

import java.time.LocalDate;

/**
 * Класс DTO для хранения и передачи данных об изолирующем стыке стрелочного перевода, расположенного в главном пути.
 */
public final class MainWaySwitchIsolatingJointDto extends SwitchIsolatingJointDto implements MainWayJoint {
    /** Номер главного пути. */
    private final Integer mainWayNumber;
    /** DTO конкретного места главного пути. */
    private final MainWayPlaceDto mainWayPlace;

    /**
     * Конструктор класса в иерархии наследования DTO изолирующих стыков, относящихся к стрелочным переводам главных
     * путей, принимающий значения всех полей для создания объекта-наследника.
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
     * @param mainWayNumber номер главного пути
     * @param mainWayPlace DTO конкретного места главного пути
     */
    public MainWaySwitchIsolatingJointDto(Long id,
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
                                          Integer mainWayNumber,
                                          MainWayPlaceDto mainWayPlace) {
        super(id, lineSide, status, padsType, padAmountOfHoles, gap, verticalStep, horizontalStep, lastMeasureDate,
                lastMeasureRailTemp, lastPadsRemovalDate, lastDemagnetizationDate, lastDemagnetizationMethod,
                magnetization, lastMagnetizationMeasureDate, jointSwitch, jointNumber);
        this.mainWayNumber = mainWayNumber;
        this.mainWayPlace = mainWayPlace;
    }

    /**
     * Геттер номера главного пути.
     *
     * @return номер главного пути
     */
    @Override
    public Integer getMainWayNumber() {
        return mainWayNumber;
    }

    /**
     * Геттер DTO конкретного места главного пути.
     *
     * @return DTO конкретного места главного пути
     */
    @Override
    public MainWayPlaceDto getMainWayPlace() {
        return mainWayPlace;
    }

}