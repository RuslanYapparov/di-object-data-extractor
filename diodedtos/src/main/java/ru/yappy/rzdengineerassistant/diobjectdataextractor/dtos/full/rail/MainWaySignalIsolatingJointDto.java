package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.MainWayPlaceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LineSide;

import java.time.LocalDate;

/**
 * Класс DTO для хранения и передачи данных об изолирующих стыках сигналов (светофоров), расположенных в главном пути.
 */
public final class MainWaySignalIsolatingJointDto extends SignalIsolatingJointDto implements MainWayJoint {
    /** Номер главного пути. */
    private final Integer mainWayNumber;
    /** DTO конкретного места главного пути. */
    private final MainWayPlaceDto mainWayPlace;

    /**
     * Конструктор класса DTO изолирующих стыков сигналов (светофоров), расположенных в главном пути.
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
     * @param signalName название сигнала
     * @param mainWayNumber номер главного пути
     * @param mainWayPlace DTO конкретного места главного пути
     */
    public MainWaySignalIsolatingJointDto(Long id,
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
                                          String signalName,
                                          Integer mainWayNumber,
                                          MainWayPlaceDto mainWayPlace) {
        super(id, lineSide, status, padsType, padAmountOfHoles, gap, verticalStep, horizontalStep, lastMeasureDate,
                lastMeasureRailTemp, lastPadsRemovalDate, lastDemagnetizationDate, lastDemagnetizationMethod,
                magnetization, lastMagnetizationMeasureDate, signalName);
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