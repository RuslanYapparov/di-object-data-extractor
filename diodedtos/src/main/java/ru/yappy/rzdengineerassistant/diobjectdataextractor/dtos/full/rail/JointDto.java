package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import com.fasterxml.jackson.annotation.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LineSide;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Абстрактный класс DTO, содержащий поля, характерные для любого рельсового стыка.*
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MainWayConductingJointDto.class, name = "MAIN_COND"),
        @JsonSubTypes.Type(value = StationWayConductingJointDto.class, name = "STATION_COND"),
        @JsonSubTypes.Type(value = MainWaySwitchIsolatingJointDto.class, name = "MAIN_SWITCH"),
        @JsonSubTypes.Type(value = StationWaySwitchIsolatingJointDto.class, name = "STATION_SWITCH"),
        @JsonSubTypes.Type(value = MainWaySignalIsolatingJointDto.class, name = "MAIN_SIGNAL"),
        @JsonSubTypes.Type(value = StationWaySignalIsolatingJointDto.class, name = "STATION_SIGNAL")
})
public abstract class JointDto {
    /** Идентификатор рельсового стыка. */
    private final Long id;
    /** Сторонность рельсовой нити, на которой расположен стык ("ЛЕВАЯ" или "ПРАВАЯ"). */
    private final LineSide lineSide;
    /** Статус рельсового стыка (в приложении используется только значение по умолчанию - "АКТИВЕН"). */
    private final String status;
    /** Тип накладок в рельсовом стыке ("ДВУХГОЛОВЫЕ", "КБ", "АПАТЭК", "ПЛАСТРОН", "СБОРНЫЕ", "ИИП-65" и т.д.). */
    private final String padsType;
    /** Количество болтовых отверстий в рельсовой накладке (может быть только 4 или 6). */
    private final Integer padAmountOfHoles;
    /** Значение величины стыкового зазора. */
    private final Integer gap;
    /** Значение величины вертикальной ступеньки в рельсовом стыке. */
    private final Float verticalStep;
    /** Значение величины горизонтальной ступеньки в рельсовом стыке. */
    private final Float horizontalStep;
    /** Дата последнего измерения характеристик рельсового стыка. */
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDate lastMeasureDate;
    /** Температура рельса в момент последнего измерения характеристик рельсового стыка. */
    private final Integer lastMeasureRailTemp;
    /** Дата последней переборки изолирующего стыка. */
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDate lastPadsRemovalDate;

    /**
     * Конструктор суперкласса, принимающий значения всех полей для создания объекта-наследника.
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
     * @param lastPadsRemovalDate дата последней переборки/вскрытия рельсового стыка (снятие накладок)
     */
    public JointDto(Long id,
                    LineSide lineSide,
                    String status,
                    String padsType,
                    Integer padAmountOfHoles,
                    Integer gap,
                    Float verticalStep,
                    Float horizontalStep,
                    LocalDate lastMeasureDate,
                    Integer lastMeasureRailTemp,
                    LocalDate lastPadsRemovalDate) {
        this.id = id;
        this.lineSide = lineSide;
        this.status = status;
        this.padsType = padsType;
        this.padAmountOfHoles = padAmountOfHoles;
        this.gap = gap;
        this.verticalStep = verticalStep;
        this.horizontalStep = horizontalStep;
        this.lastMeasureDate = lastMeasureDate;
        this.lastMeasureRailTemp = lastMeasureRailTemp;
        this.lastPadsRemovalDate = lastPadsRemovalDate;
    }

    /**
     * Геттер идентификатора рельсового стыка.
     *
     * @return идентификатор рельсового стыка
     */
    public Long getId() {
        return id;
    }

    /**
     * Геттер стороны рельсовой нити, на которой расположен стык.
     *
     * @return сторона рельсовой нити
     */
    public LineSide getLineSide() {
        return lineSide;
    }

    /**
     * Геттер статуса рельсового стыка.
     *
     * @return статус
     */
    public String getStatus() {
        return status;
    }

    /**
     * Геттер типа накладок.
     *
     * @return тип накладок
     */
    public String getPadsType() {
        return padsType;
    }

    /**
     * Геттер количества болтовых отверстий в рельсовой накладке.
     *
     * @return количество болтовых отверстий
     */
    public Integer getPadAmountOfHoles() {
        return padAmountOfHoles;
    }

    /**
     * Геттер величины стыкового зазора в рельсовом стыке.
     *
     * @return величина стыкового зазора
     */
    public Integer getGap() {
        return gap;
    }

    /**
     * Геттер величины зазора в рельсовом стыке.
     *
     * @return зазор
     */
    public Float getVerticalStep() {
        return verticalStep;
    }

    /**
     * Геттер величины горизонтальной ступеньки в рельсовом стыке.
     *
     * @return горизонтальная ступенька
     */
    public Float getHorizontalStep() {
        return horizontalStep;
    }

    /**
     * Геттер даты последнего измерения характеристик рельсового стыка.
     *
     * @return дата последнего измерения
     */
    public LocalDate getLastMeasureDate() {
        return lastMeasureDate;
    }

    /**
     * Геттер температуры рельса в момент последнего измерения характеристик.
     *
     * @return температура рельса в момент последнего измерения
     */
    public Integer getLastMeasureRailTemp() {
        return lastMeasureRailTemp;
    }

    /**
     * Геттер даты последнего снятия накладок токопроводящего стыка.
     *
     * @return дата последнего снятия накладок токопроводящего стыка
     */
    public LocalDate getLastPadsRemovalDate() {
        return lastPadsRemovalDate;
    }

    /** Сравнение двух объектов, используя их состояние (значения всех полей).
     *
     * @param o другой объект для сравнения
     * @return булевое значение, определяющее равенство объектов
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JointDto jointDto)) return false;
        return Objects.equals(id, jointDto.id) &&
                lineSide == jointDto.lineSide &&
                Objects.equals(status, jointDto.status) &&
                Objects.equals(padsType, jointDto.padsType) &&
                Objects.equals(padAmountOfHoles, jointDto.padAmountOfHoles) &&
                Objects.equals(gap, jointDto.gap) &&
                Objects.equals(verticalStep, jointDto.verticalStep) &&
                Objects.equals(horizontalStep, jointDto.horizontalStep) &&
                Objects.equals(lastMeasureDate, jointDto.lastMeasureDate) &&
                Objects.equals(lastMeasureRailTemp, jointDto.lastMeasureRailTemp);
    }

    /** Получение хэш-кода объекта.
     *
     * @return числовое представление объекта на основании его состояния (значений всех полей)
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, lineSide, status, padsType, padAmountOfHoles, gap, verticalStep, horizontalStep,
                lastMeasureDate, lastMeasureRailTemp);
    }

}