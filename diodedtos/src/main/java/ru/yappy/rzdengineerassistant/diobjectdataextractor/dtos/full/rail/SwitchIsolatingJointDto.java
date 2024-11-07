package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LineSide;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.SwitchDto;

import java.time.LocalDate;

/**
 * Абстрактный DTO класс в иерархии наследования изолирующих стыков, содержащий поля с данными о стрелочном переводе,
 * к которому относится стык.
 */
public abstract class SwitchIsolatingJointDto extends IsolatingJointDto {
    /** DTO c информацией о стрелочном переводе, на котором находится изолирующий стык. */
    private final SwitchDto jointSwitch;
    /** Номер изолирующего стыка в зависимости от размещения на схеме стрелочного перевода. */
    private final Integer jointNumber;

    /**
     * Конструктор класса в иерархии наследования DTO изолирующих стыков, относящихся к стрелочным переводам,
     * принимающий значения всех полей для создания объекта-наследника.
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
     */
    public SwitchIsolatingJointDto(Long id,
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
                                   SwitchDto jointSwitch,
                                   Integer jointNumber) {
        super(id, lineSide, status, padsType, padAmountOfHoles, gap, verticalStep, horizontalStep, lastMeasureDate,
                lastMeasureRailTemp, lastPadsRemovalDate, lastDemagnetizationDate, lastDemagnetizationMethod,
                magnetization, lastMagnetizationMeasureDate);
        this.jointSwitch = jointSwitch;
        this.jointNumber = jointNumber;
    }

    /**
     * Геттер DTO с данными о стрелочном переводе, на котором находится изолирующий стык.
     *
     * @return DTO с данными о стрелочном переводе
     */
    public SwitchDto getJointSwitch() {
        return jointSwitch;
    }

    /**
     * Геттер номера изолирующего стыка в зависимости от размещения на схеме стрелочного перевода.
     *
     * @return номер изолирующего стыка
     */
    public Integer getJointNumber() {
        return jointNumber;
    }

}