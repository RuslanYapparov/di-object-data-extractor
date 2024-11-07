package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.MainWayPlaceDto;

import java.time.LocalDate;

/**
 * Класс DTO сущности стрелочного перевода на перегоне между железнодорожными станциями.
 */
public class InterstationTrackSwitchDto extends SwitchDto {
    /** Номер главного пути, на котором расположен стрелочный перевод. */
    private final Integer mainWayNumber;
    /** Место главного пути, на котором расположен стык рамного рельса стрелочного перевода. */
    private final MainWayPlaceDto frameRailJointPlace;
    /** Наименование станции, управляющая положением стрелочного перевода. */
    private final String controlStation;

    /**
     * Конструктор DTO сущности перегонного стрелочного перевода принимающий в качестве параметров значения всех полей.
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
     * @param mainWayNumber номер главного пути, на котором расположен стрелочный перевод
     * @param frameRailJointPlace место главного пути, на котором расположен стык рамного рельса стрелочного перевода
     * @param controlStation название станции, управляющей положением стрелочного перевода
     */
    public InterstationTrackSwitchDto(Long id,
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
                                      Integer mainWayNumber,
                                      MainWayPlaceDto frameRailJointPlace,
                                      String controlStation) {
        super(id, name, fullName, project, railType, beamsMaterial, ballastType, crossMarking, controlType, lineSide,
                gauge, installationDate, passedTonnageBeforeInstall, passedTonnageAfterInstall, outcrossCurveLength);
        this.mainWayNumber = mainWayNumber;
        this.frameRailJointPlace = frameRailJointPlace;
        this.controlStation = controlStation;
    }

    /**
     * Геттер номера главного пути, на котором расположен стрелочный перевод.
     *
     * @return номер главного пути, на котором расположен стрелочный перевод
     */
    public Integer getMainWayNumber() {
        return mainWayNumber;
    }

    /**
     * Геттер места главного пути, на котором расположен стык рамного рельса стрелочного перевода.
     *
     * @return место главного пути, на котором расположен стык рамного рельса стрелочного перевода
     */
    public MainWayPlaceDto getFrameRailJointPlace() {
        return frameRailJointPlace;
    }

    /**
     * Геттер названия станции, управляющей положением стрелочного перевода
     *
     * @return название станции, управляющей положением стрелочного перевода
     */
    public String getControlStation() {
        return controlStation;
    }

}