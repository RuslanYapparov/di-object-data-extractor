package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Абстрактный класс, содержащий поля, характерные для всех DTO сущностей стрелочных переводов (основная информация
 * о стрелочном переводе, не касающаяся его размещения)
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "wayType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InterstationTrackSwitchDto.class, name = "ПЕРЕГОН"),
        @JsonSubTypes.Type(value = StationMainWaySwitchDto.class, name = "СТАНЦГЛАВ"),
        @JsonSubTypes.Type(value = StationWaySwitchDto.class, name = "СТАНЦ"),
})
public abstract class SwitchDto {
    /** Идентификатор стрелочного перевода. */
    private final Long id;
    /** Номер (наименование) стрелочного перевода, строковое значение, т.к. в номере (наименовании) могут быть буквы. */
    private final String name;
    /** Полное наименование стрелочного перевода (если в названии стрелочного перевода есть название заводов и т.п.). */
    private final String fullName;
    /** Проект стрелочного перевода. */
    private final String project;
    /** Тип рельса стрелочного перевода. */
    private final String railType;
    /** Материал брусьев стрелочного перевода. */
    private final String beamsMaterial;
    /** Тип балласта стрелочного перевода. */
    private final String ballastType;
    /** Марка крестовины стрелочного перевода. */
    private final String crossMarking;
    /** Тип управления стрелочного перевода. */
    private final String controlType;
    /** Сторонность стрелочного перевода (ведет налево или направо относительно стыка рамного рельса). */
    private final String lineSide;
    /** Ширина колеи, на которую зашит стрелочный перевод (в миллиметрах). */
    private final Integer gauge;
    /** Дата укладки стрелочного перевода в путь. */
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDate installationDate;
    /** Пропущенный тоннаж на момент укладки стрелочного перевода. */
    private final Float passedTonnageBeforeInstall;
    /** Пропущенный тоннаж после укладки стрелочного перевода. */
    private final Float passedTonnageAfterInstall;
    /** Длина закрестовинной кривой (в метрах). */
    private final Integer outcrossCurveLength;

    /**
     * Конструктор суперкласса DTO сущности стрелочного перевода, принимающий параметрами значения всех полей.
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
     */
    public SwitchDto(Long id,
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
                     LocalDate installationDate,
                     Float passedTonnageBeforeInstall,
                     Float passedTonnageAfterInstall,
                     Integer outcrossCurveLength) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.project = project;
        this.railType = railType;
        this.beamsMaterial = beamsMaterial;
        this.ballastType = ballastType;
        this.crossMarking = crossMarking;
        this.controlType = controlType;
        this.lineSide = lineSide;
        this.gauge = gauge;
        this.installationDate = installationDate;
        this.passedTonnageBeforeInstall = passedTonnageBeforeInstall;
        this.passedTonnageAfterInstall = passedTonnageAfterInstall;
        this.outcrossCurveLength = outcrossCurveLength;
    }

    /**
     * Геттер идентификатора стрелочного перевода.
     *
     * @return идентификатор стрелочного перевода
     */
    public Long getId() {
        return id;
    }

    /**
     * Геттер номера (наименования) стрелочного перевода.
     *
     * @return номер (наименование) стрелочного перевода
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер полного наименования стрелочного перевода.
     *
     * @return полное наименование стрелочного перевода
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Геттер проекта стрелочного перевода.
     *
     * @return проект стрелочного перевода
     */
    public String getProject() {
        return project;
    }

    /**
     * Геттер типа рельса стрелочного перевода.
     *
     * @return тип рельса стрелочного перевода
     */
    public String getRailType() {
        return railType;
    }

    /**
     * Геттер типа материала брусьев стрелочного перевода.
     *
     * @return тип материала брусьев стрелочного перевода
     */
    public String getBeamsMaterial() {
        return beamsMaterial;
    }

    /**
     * Геттер типа балласта стрелочного перевода.
     *
     * @return тип балласта стрелочного перевода
     */
    public String getBallastType() {
        return ballastType;
    }

    /**
     * Геттер марки крестовины стрелочного перевода.
     *
     * @return марка крестовины стрелочного перевода
     */
    public String getCrossMarking() {
        return crossMarking;
    }

    /**
     * Геттер типа управления стрелочного перевода.
     *
     * @return тип управления стрелочного перевода
     */
    public String getControlType() {
        return controlType;
    }

    /**
     * Геттер сторонности стрелочного перевода.
     *
     * @return сторонность стрелочного перевода
     */
    public String getLineSide() {
        return lineSide;
    }

    /**
     * Геттер ширины колеи, на которую зашит стрелочный перевод (в миллиметрах).
     *
     * @return ширина колеи, на которую зашит стрелочный перевод (в миллиметрах)
     */
    public Integer getGauge() {
        return gauge;
    }

    /**
     * Геттер даты укладки стрелочного перевода.
     *
     * @return дата укладки стрелочного перевода
     */
    public LocalDate getInstallationDate() {
        return installationDate;
    }

    /**
     * Геттер пропущенного тоннажа на момент укладки стрелочного перевода.
     *
     * @return пропущенный тоннаж на момент укладки стрелочного перевода
     */
    public Float getPassedTonnageBeforeInstall() {
        return passedTonnageBeforeInstall;
    }

    /**
     * Геттер пропущенного тоннажа после укладки стрелочного перевода.
     *
     * @return пропущеный тоннаж после укладки стрелочного перевода
     */
    public Float getPassedTonnageAfterInstall() {
        return passedTonnageAfterInstall;
    }

    /**
     * Геттер длины закрестовинной кривой стрелочного перевода
     *
     * @return длина закрестовинной кривой стрелочного перевода
     */
    public Integer getOutcrossCurveLength() {
        return outcrossCurveLength;
    }

    /** Сравнение двух объектов, используя их состояние (значения всех полей).
     *
     * @param o другой объект для сравнения
     * @return булевое значение, определяющее равенство объектов
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SwitchDto switchDto)) return false;
        return Objects.equals(id, switchDto.id) &&
                Objects.equals(name, switchDto.name) &&
                Objects.equals(fullName, switchDto.fullName) &&
                Objects.equals(project, switchDto.project) &&
                Objects.equals(railType, switchDto.railType) &&
                Objects.equals(beamsMaterial, switchDto.beamsMaterial) &&
                Objects.equals(ballastType, switchDto.ballastType) &&
                Objects.equals(crossMarking, switchDto.crossMarking) &&
                Objects.equals(controlType, switchDto.controlType) &&
                Objects.equals(lineSide, switchDto.lineSide) &&
                Objects.equals(gauge, switchDto.gauge) &&
                Objects.equals(installationDate, switchDto.installationDate) &&
                Objects.equals(passedTonnageBeforeInstall, switchDto.passedTonnageBeforeInstall) &&
                Objects.equals(passedTonnageAfterInstall, switchDto.passedTonnageAfterInstall) &&
                Objects.equals(outcrossCurveLength, switchDto.outcrossCurveLength);
    }

    /** Получение хэш-кода объекта.
     *
     * @return числовое представление объекта на основании его состояния (значений всех полей)
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, fullName, project, railType, beamsMaterial, ballastType, crossMarking, controlType, lineSide, gauge, installationDate, passedTonnageBeforeInstall, passedTonnageAfterInstall, outcrossCurveLength);
    }

}