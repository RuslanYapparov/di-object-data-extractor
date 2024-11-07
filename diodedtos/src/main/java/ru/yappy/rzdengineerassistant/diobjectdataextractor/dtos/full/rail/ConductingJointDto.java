package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LineSide;

import java.time.LocalDate;

/**
 * Абстрактный класс DTO, содержащий поля, характерные для любого токопроводящего стыка.
 */
public abstract class ConductingJointDto extends JointDto {
    /** Величина переходного сопротивления в токопроводящем рельсовом стыке. */
    private final Integer contactResistance;
    /** Дата последнего измерения переходного сопротивления в токопроводящем рельсовом стыке. */
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDate lastResistanceMeasureDate;
    /** Типы рельсовых соединителей, установленных в токопроводящем рельсовом стыке (в приложении для всех таких
     * стыков одно значение - "СРСП", но могут быть несколько, типа "ЭМСВ", "ЦМС" и т.д.). */
    private final String connectorTypes;
    /** Дата установки рельсового соединителя(-лей) в токопроводящем рельсовом стыке. */
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDate connectorInstallationDate;

    /**
     * Конструктор класса в иерархии наследования DTO токопроводящих стыков, принимающий значения всех полей для
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
     * @param contactResistance величина переходного сопротивления в токопроводящем рельсовом стыке
     * @param lastResistanceMeasureDate дата последнего измерения переходного сопротивления в токопроводящем стыке
     * @param lastPadsRemovalDate дата последнего снятия накладок токопроводящего стыка
     * @param connectorTypes типы рельсовых соединителей, установленных в токопроводящем рельсовом стыке
     * @param connectorInstallationDate дата установки рельсового соединителя(-лей) в токопроводящем рельсовом стыке
     */
    public ConductingJointDto(Long id,
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
                              Integer contactResistance,
                              LocalDate lastResistanceMeasureDate,
                              String connectorTypes,
                              LocalDate connectorInstallationDate) {
        super(id, lineSide, status, padsType, padAmountOfHoles, gap, verticalStep, horizontalStep, lastMeasureDate,
                lastMeasureRailTemp, lastPadsRemovalDate);
        this.contactResistance = contactResistance;
        this.lastResistanceMeasureDate = lastResistanceMeasureDate;
        this.connectorTypes = connectorTypes;
        this.connectorInstallationDate = connectorInstallationDate;
    }

    /**
     * Геттер величины переходного сопротивления в токопроводящем рельсовом стыке.
     *
     * @return величина переходного сопротивления в токопроводящем рельсовом стыке
     */
    public Integer getContactResistance() {
        return contactResistance;
    }

    /**
     * Геттер даты последнего измерения переходного сопротивления в токопроводящем рельсовом стыке.
     *
     * @return дата последнего измерения переходного сопротивления в токопроводящем рельсовом стыке
     */
    public LocalDate getLastResistanceMeasureDate() {
        return lastResistanceMeasureDate;
    }

    /**
     * Геттер типов рельсовых соединителей, установленных в токопроводящем рельсовом стыке.
     *
     * @return типы рельсовых соединителей
     */
    public String getConnectorTypes() {
        return connectorTypes;
    }

    /**
     * Геттер даты установки рельсового соединителя(-лей) в токопроводящем рельсовом стыке.
     *
     * @return дата установки рельсового соединителя(-лей)
     */
    public LocalDate getConnectorInstallationDate() {
        return connectorInstallationDate;
    }

}