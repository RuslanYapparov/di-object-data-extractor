package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.MainWayPlaceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LineSide;

import java.time.LocalDate;

/**
 * Класс DTO для хранения и передачи данных о токопроводящем стыке, расположенном в главном пути.
 */
public final class MainWayConductingJointDto extends ConductingJointDto implements MainWayJoint {
    /** Номер главного пути. */
    private final Integer mainWayNumber;
    /** DTO конкретного места главного пути. */
    private final MainWayPlaceDto mainWayPlace;

    /**
     * Конструктор класса в иерархии наследования DTO токопроводящих стыков главного хода, принимающий значения всех
     * полей для создания объекта-наследника.
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
     * @param mainWayNumber номер главного пути
     * @param mainWayPlace DTO конкретного места главного пути
     */
    public MainWayConductingJointDto(Long id,
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
                                     Integer contactResistance,
                                     @JsonFormat(pattern = "dd-MM-yyyy")
                                     LocalDate lastResistanceMeasureDate,
                                     String connectorTypes,
                                     @JsonFormat(pattern = "dd-MM-yyyy")
                                     LocalDate connectorInstallationDate,
                                     Integer mainWayNumber,
                                     MainWayPlaceDto mainWayPlace) {
        super(id, lineSide, status, padsType, padAmountOfHoles, gap, verticalStep, horizontalStep, lastMeasureDate,
                lastMeasureRailTemp, lastPadsRemovalDate,  contactResistance, lastResistanceMeasureDate, connectorTypes,
                connectorInstallationDate);
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