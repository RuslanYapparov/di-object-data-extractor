package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationWayPlaceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LineSide;

import java.time.LocalDate;

/**
 * Класс DTO для хранения и передачи данных о токопроводящем стыке, расположенном на станционном пути.
 */
public final class StationWayConductingJointDto extends ConductingJointDto implements StationWayJoint {
    /** DTO с информацией о конкретном месте станционного пути. */
    private final StationWayPlaceDto stationWayPlace;

    /**
     * Конструктор класса в иерархии наследования DTO токопроводящих стыков станционных путей, принимающий значения всех
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
     * @param stationWayPlace DTO с информацией о конкретном месте станционного пути
     */
    public StationWayConductingJointDto(Long id,
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
                                        StationWayPlaceDto stationWayPlace) {
        super(id, lineSide, status, padsType, padAmountOfHoles, gap, verticalStep, horizontalStep, lastMeasureDate,
                lastMeasureRailTemp, lastPadsRemovalDate, contactResistance, lastResistanceMeasureDate, connectorTypes,
                connectorInstallationDate);
        this.stationWayPlace = stationWayPlace;
    }

    /**
     * Геттер для DTO с информацией о конкретном месте станционного пути.
     *
     * @return DTO с информацией о конкретном месте станционного пути
     */
    @Override
    public StationWayPlaceDto getStationWayPlace() {
        return stationWayPlace;
    }

}