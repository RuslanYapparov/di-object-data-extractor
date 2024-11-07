package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.MainWayPlaceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto;

import java.time.LocalDate;

/**
 * Класс DTO сущности стрелочного перевода, расположенного на главном пути железнодорожной станции.
 */
public class StationMainWaySwitchDto extends SwitchDto {
    /** Наименование парка станции, к которому относится стрелочный перевод. */
    private final String stationParkName;
    /** Мини-DTO главного пути, на котором находится стрелочный перевод. */
    private final StationWayMiniDto stationMainWay;
    /** Место железнодорожного направления, на котором находится стык рамного рельса стрелочного перевода. */
    private final MainWayPlaceDto frameRailJointPlace;
    /** Наименование пути, на который ведет стрелочный перевод. */
    private final String wayNameSwitchGoesOn;

    /**
     * Конструктор DTO сущности стрелочного перевода, расположенного на главном пути жерелодорожной станции,
     * принимающий в качестве параметров значения всех полей.
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
     * @param stationMainWay мини-DTO главного пути, на котором находится стрелочный перевод
     * @param frameRailJointPlace место, на котором находится стык рамного рельса стрелочного перевода
     * @param wayNameSwitchGoesOn наименование пути, на который ведет стрелочный перевод
     */
    public StationMainWaySwitchDto(Long id,
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
                                   String stationParkName,
                                   StationWayMiniDto stationMainWay,
                                   MainWayPlaceDto frameRailJointPlace,
                                   String wayNameSwitchGoesOn) {
        super(id, name, fullName, project, railType, beamsMaterial, ballastType, crossMarking, controlType, lineSide,
                gauge, installationDate, passedTonnageBeforeInstall, passedTonnageAfterInstall, outcrossCurveLength);
        this.stationParkName = stationParkName;
        this.stationMainWay = stationMainWay;
        this.frameRailJointPlace = frameRailJointPlace;
        this.wayNameSwitchGoesOn = wayNameSwitchGoesOn;
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
     * Геттер мини-DTO главного пути, на котором находится стрелочный перевод.
     *
     * @return мини-DTO главного пути, на котором находится стрелочный перевод
     */
    public StationWayMiniDto getStationMainWay() {
        return stationMainWay;
    }

    /**
     * Геттер места, на котором находится стык рамного рельса стрелочного перевода.
     *
     * @return место, на котором находится стык рамного рельса стрелочного перевода
     */
    public MainWayPlaceDto getFrameRailJointPlace() {
        return frameRailJointPlace;
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