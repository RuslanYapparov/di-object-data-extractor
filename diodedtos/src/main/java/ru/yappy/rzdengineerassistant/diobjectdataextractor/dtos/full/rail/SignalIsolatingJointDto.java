package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LineSide;

import java.time.LocalDate;

/**
 * Абстрактный DTO класс в иерархии наследования изолирующих стыков, содержащий поле с названием сигнала,
 * к которому он относится.
 */
public abstract class SignalIsolatingJointDto extends IsolatingJointDto {
    /** Название сигнала, к которому относится изолирующий рельсовый стык. */
    private final String signalName;

    /**
     * Конструктор класса в иерархии наследования DTO изолирующих стыков, относящихся к сигналам, принимающий значения
     * всех полей для создания объекта-наследника.
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
     */
    public SignalIsolatingJointDto(Long id,
                                   LineSide lineSide,
                                   String status,
                                   String padsType,
                                   Integer padAmountOfHoles,
                                   Integer gap,
                                   Float verticalStep,
                                   Float horizontalStep,
                                   LocalDate lastMeasureDate,
                                   Integer lastMeasureRailTemp,
                                   LocalDate lastPadsRemovalDate,
                                   LocalDate lastDemagnetizationDate,
                                   String lastDemagnetizationMethod,
                                   Integer magnetization,
                                   LocalDate lastMagnetizationMeasureDate,
                                   String signalName) {
        super(id, lineSide, status, padsType, padAmountOfHoles, gap, verticalStep, horizontalStep, lastMeasureDate,
                lastMeasureRailTemp, lastPadsRemovalDate, lastDemagnetizationDate, lastDemagnetizationMethod,
                magnetization, lastMagnetizationMeasureDate);
        this.signalName = signalName;
    }

    /**
     * Геттер названия сигнала, к которому относится изолирующий стык.
     *
     * @return название сигнала
     */
    public String getSignalName() {
        return signalName;
    }

}