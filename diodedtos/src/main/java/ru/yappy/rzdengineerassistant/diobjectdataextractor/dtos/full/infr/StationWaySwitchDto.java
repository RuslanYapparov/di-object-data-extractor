package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationWayPlaceDto;

import java.time.LocalDate;

/**
 * Класс DTO сущности стрелочного перевода, расположенного на станционном пути.
 */
public class StationWaySwitchDto extends SwitchDto {
    /** Место станционного пути, на котором расположен стык рамного рельса стрелочного перевода. */
    private final StationWayPlaceDto frameRailStationWayPlace;
    /** Наименование парка станции, к которому относится стрелочный перевод. */
    private final String stationParkName;
    /** Наименование пути, на котороый ведет стрелочный перевод. */
    private final String wayNameSwitchGoesOn;

    /**
     * Конструктор DTO сущности стрелочного перевода, расположенного на станционном пути, принимающий в качестве
     * параметров значения всех полей.
     *
     * @param id идентификатор стрелочного перевода
     * @param name номер (наименование) стрелочного перевода
     * @param fullName полное наименование стрелочного перевода
     * @param project проект стрелочного перевода
     * @param railType тип рельса стрелочного перевода
     * @param beamsMaterial материал брусьев стрелочного перевода
     * @param ballastType тип балласта стрелочного перевода
     * @param crossMarking марка крестовины стрелочного перевода
     * @param controlType тип управления стрелочного перевода
     * @param lineSide сторонность стрелочного перевода
     * @param gauge ширина колеи, на которую зашит стрелочный перевод (в миллиметрах)
     * @param installationDate дата установки стрелочного перевода
     * @param passedTonnageBeforeInstall пропущенный тоннаж на момент укладки стрелочного перевода
     * @param passedTonnageAfterInstall пропущенный тоннаж после укладки стрелочного перевода
     * @param outcrossCurveLength длина закрестовинной кривой (в метрах)
     * @param stationParkName наименование парка станции, к которому относится стрелочный перевод
     * @param frameRailStationWayPlace место станционного пути, на котором расположен стык рамного рельса
     * @param wayNameSwitchGoesOn наименование пути, на который ведет стрелочный перевод
     */
    public StationWaySwitchDto(Long id,
                               String name,
                               String fullName,
                               String project,
                               String railType,
                               String beamsMaterial,
                               String ballastType,
                               String crossMarking,
                               String controlType,
                               String lineSide,
                               Integer gauge,
                               @JsonFormat(pattern = "dd-MM-yyyy")
                               LocalDate installationDate,
                               Float passedTonnageBeforeInstall,
                               Float passedTonnageAfterInstall,
                               Integer outcrossCurveLength,
                               StationWayPlaceDto frameRailStationWayPlace,
                               String stationParkName,
                               String wayNameSwitchGoesOn) {
        super(id, name, fullName, project, railType, beamsMaterial, ballastType, crossMarking, controlType, lineSide,
                gauge, installationDate, passedTonnageBeforeInstall, passedTonnageAfterInstall, outcrossCurveLength);
        this.frameRailStationWayPlace = frameRailStationWayPlace;
        this.stationParkName = stationParkName;
        this.wayNameSwitchGoesOn = wayNameSwitchGoesOn;
    }

    /**
     * Геттер места станционного пути, на котором расположен стык рамного рельса стрелочного перевода.
     *
     * @return место станционного пути, на котором расположен стык рамного рельса стрелочного перевода
     */
    public StationWayPlaceDto getFrameRailStationWayPlace() {
        return frameRailStationWayPlace;
    }

    /**
     * Геттер наименования парка станции, к которому относится стрелочный перевод.
     *
     * @return наименование парка станции, к которому относится стрелочный перевод
     */
    public String getStationParkName() {
        return stationParkName;
    }

    /**
     * Геттер наименования пути, на который ведет стрелочный перевод.
     *
     * @return наименование пути, на который ведет стрелочный перевод
     */
    public String getWayNameSwitchGoesOn() {
        return wayNameSwitchGoesOn;
    }

}