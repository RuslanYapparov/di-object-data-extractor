package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LineSide;

import java.time.LocalDate;

/**
 * Абстрактный класс DTO, содержащий поля, характерные для любого изолирующего стыка.
 */
public abstract class IsolatingJointDto extends JointDto {
    /** Дата последнего проведения демагнетизации изолирующего стыка (операция, характерная для изолирующих
     * стыков с типом накладок АПАТЭК, но может возникнуть необходимость и на других типах накладок). */
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDate lastDemagnetizationDate;
    /** Метод последней демагнетизации изолирующего стыка ("ДЕМ-Р ПОСТОЯННЫЙ", "ДЕМ-Р ГЕНЕРАТОР" (демагнетизатор
     * на постоянных магнитах или с питанием от генератора), "УСТАНОВКА ИИП", "УСТАНОВКА ШУНТА"). */
    private final String lastDemagnetizationMethod;
    /** Значение величины намагниченности изолирующего стыка при последнем измерении. */
    private final Integer magnetization;
    /** Дата последнего измерения намагниченности изолирующего стыка. */
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDate lastMagnetizationMeasureDate;

    /**
     * Конструктор класса в иерархии наследования DTO изолирующих стыков, принимающий значения всех полей для
     * создания объекта-наследника.
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
     */
    public IsolatingJointDto(Long id,
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
                             LocalDate lastMagnetizationMeasureDate) {
        super(id, lineSide, status, padsType, padAmountOfHoles, gap, verticalStep, horizontalStep, lastMeasureDate,
                lastMeasureRailTemp, lastPadsRemovalDate);
        this.lastDemagnetizationDate = lastDemagnetizationDate;
        this.lastDemagnetizationMethod = lastDemagnetizationMethod;
        this.magnetization = magnetization;
        this.lastMagnetizationMeasureDate = lastMagnetizationMeasureDate;
    }

    /**
     * Геттер даты последнего проведения демагнетизации.
     *
     * @return дата последнего проведения демагнетизации
     */
    public LocalDate getLastDemagnetizationDate() {
        return lastDemagnetizationDate;
    }

    /**
     * Геттер метода проведения последней демагнетизации.
     *
     * @return метод проведения последней демагнетизации
     */
    public String getLastDemagnetizationMethod() {
        return lastDemagnetizationMethod;
    }

    /**
     * Геттер величины значения намагниченности в изолирующем стыке.
     *
     * @return величина намагниченности
     */
    public Integer getMagnetization() {
        return magnetization;
    }

    /**
     * Геттер даты последнего измерения намагниченности в изолирующем стыке.
     *
     * @return дата последнего измерения намагниченности в изолирующем стыке
     */
    public LocalDate getLastMagnetizationMeasureDate() {
        return lastMagnetizationMeasureDate;
    }

}