package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.MainWayDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationNameDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.empl.ManagerMiniDto;

import java.util.Set;

/**
 * Класс DTO сущности дистанции пути.
 */
public class WayMaintenanceDistanceDto extends StructuralEnterpriseDto {
    /** Номер дистанции пути. */
    private final Integer number;
    /** Поле-флаг, определяющее, является ли дистанция пути ИЧ (дистанцией инфраструктуры). */
    private final Boolean isIch;
    /** Сет, содержащий информацию обо всех главных путях железнодорожных направлений, обслуживаемых дистанцией пути. */
    private final Set<MainWayDto> mainWays;
    /** Сет, содержащий информацию обо всех станциях, обслуживаемых дистанцией пути. */
    private final Set<StationNameDto> stations;

    /**
     * Конструктор DTO-объекта сущности дистанции пути, принимающий в качестве параметров значения всех полей.
     *
     * @param id идентификатор дистанции пути
     * @param name название дистанции пути
     * @param regionalDirectorateAbbreviation аббревиатура дирекции инфраструктуры, в состав которой входит дистанция
     * @param managers сет DTO-объектов, содержащий информацию обо всех руководителях дистанции пути
     * @param number номер дистанции пути
     * @param isIch является ли дистанция ИЧ (дистанцией инфраструктуры)
     * @param mainWays сет DTO-объектов, содержащий информацию о главных путях железнодорожных направлений дистанции
     * @param stations сет DTO-объектов, содержащий информацию о станциях в границах дистанции
     */
    public WayMaintenanceDistanceDto(Long id,
                                     String name,
                                     String regionalDirectorateAbbreviation,
                                     Set<ManagerMiniDto> managers,
                                     Integer number,
                                     Boolean isIch,
                                     Set<MainWayDto> mainWays,
                                     Set<StationNameDto> stations) {
        super(id, name, regionalDirectorateAbbreviation, managers);
        this.number = number;
        this.isIch = isIch;
        this.mainWays = mainWays;
        this.stations = stations;
    }

    /**
     * Геттер номера дистанции пути.
     *
     * @return номер дистанции
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Геттер значения поля-флага, определяющего, является ли дистанция пути ИЧ (дистанцией инфраструктуры).
     *
     * @return является ли дистанция пути ИЧ
     */
    public Boolean getIsIch() {
        return isIch;
    }

    /**
     * Геттер сета DTO-объектов, содержащего информацию о главных путях железнодорожных направлений дистанции.
     *
     * @return сет DTO-объектов главных путей железнодорожных направлений
     */
    public Set<MainWayDto> getMainWays() {
        return mainWays;
    }

    /**
     * Геттер сета DTO-объектов, содержащего информацию о станциях в границах дистанции.
     *
     * @return сет DTO-объектов станций
     */
    public Set<StationNameDto> getStations() {
        return stations;
    }

}