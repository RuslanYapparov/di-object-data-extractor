package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.RailDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.RailMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.rail.RailRepository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper.RailMapper;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.util.ParameterValidator;

import java.util.List;

/**
 * Реализация сервиса для получения информации о сущностях рельсов из базы данных.
 */
@Service
@Slf4j
public class RailServiceImpl implements RailService {
    /** Репозиторий, извлекающий entity-объекты рельсов из базы данных. */
    private final RailRepository railRepository;
    /** Сервис для работы с сущностями рельсовых плетей. */
    private final LashService lashService;
    /** Сервис для работы с сущностями стрелочных пееводов. */
    private final SwitchService switchService;
    /** Сервис для работы с сущностями местоположений, применяется для проверки правильности пользовательских данных. */
    private final LocationService locationService;
    /** Мэппер entity-объектов рельсов в DTO/ */
    private final RailMapper railMapper;

    /**
     * Конструктор объекта сервиса, получающий необходимые зависимости из Spring-контекста.
     *
     * @param railRepository репозиторий сущностей рельсов
     * @param lashService сервис работы с рельсовыми плетями
     * @param switchService сервис работы со стрелочными переводами
     * @param railMapper мэппер entity-объектов рельсов в DTO
     */
    @Autowired
    public RailServiceImpl(RailRepository railRepository,
                           LashService lashService,
                           SwitchService switchService,
                           LocationService locationService,
                           RailMapper railMapper) {
        this.railRepository = railRepository;
        this.lashService = lashService;
        this.switchService = switchService;
        this.locationService = locationService;
        this.railMapper = railMapper;
    }

    /**
     * Получает из БД и мэппит в DTO информацию о рельсе по его id и признаку типа пути (wayType).
     *
     * @param railId идентификатор рельса в базе данных
     * @param wayType параметр, указывающий на тип пути, на котором лежит рельс
     * @return DTO, содержащий исчерпывающую информацию о рельсе
     */
    @Override
    public RailDto getFullRailInfoByIdAndWayType(Long railId, WayType wayType) {
        if (wayType == null) {
            throw new IllegalStateException("Не удалось получить полную информацию о рельсе, так как при " +
                    "вызове метода не был указан тип пути (wayType=null).");
        }
        log.debug("Начало выполнения операции получения полной информации о рельсе с ид={} и признаком WayType={}.",
                railId, wayType.getRuTitle());
        ParameterValidator.validate(railId, "'рельс'");
        RailEntity railEntity;
        if (WayType.STATION_WAY.equals(wayType)) {
            railEntity = railRepository.findFullStationWayRailInfoById(railId).orElseThrow(() ->
                    new ObjectNotFoundException("рельс станционного пути", railId));
        } else {
            railEntity = railRepository.findFullMainWayRailInfoById(railId).orElseThrow(() ->
                    new ObjectNotFoundException(String.format("рельс главного пути, wayType='%s'",
                            wayType.getRuTitle()), railId));
        }
        log.debug("Полная информация о рельсе с ид={} найдена, тип рельса = {}.",
                railEntity.getId(), railEntity.getRailKind());
        switch (railEntity.getRailKind()) {
            case "ПЛЕТЬ" -> railEntity.setRailLash(lashService.getLashEntityByRailEntity(railEntity));
            case "СТРЕЛОЧНЫЙ ПЕРЕВОД" -> railEntity.setRailSwitch(
                    switchService.getSwitchEntityByRailEntityAndWayType(railEntity, wayType));
        }
        RailDto railDto = railMapper.toDto(railEntity);
        log.debug("Необходимая информация о рельсе с ид={} получена", railId);
        return railDto;
    }

    /**
     * Получает из БД и мэппит в DTO минимальную информацию о рельсах, находящихся вблизи определенного места, заданного
     * объектом {@link LocationDto}
     *
     * @param location объект, содержащий информацию о месте, вблизи которого будет выгружена информация о рельсах
     * @return список мини-DTO, содержащих минимальную информацию о рельсах, находящихся вблизи заданного места
     */
    @Override
    public List<RailMiniDto> getRailMiniInfoVariantsByLocationDto(LocationDto location) {
        log.debug("Начало выполнения операции получения вариантов рельсов для конкретного места {}.", location);
        ParameterValidator.validate(location);
        if (!locationService.canGetLocationProjectionByLocationDto(location)) {
            throw new ObjectNotFoundException("массив вариантов рельсов для конкретного места", location.toString());
        }
        List<RailMiniDto> railMiniDtos;
        if (WayType.STATION_WAY.equals(location.wayType())) {
            railMiniDtos = railMapper.toListOfStationWayRailMiniDto(getStationWayRailMiniProjectionsByLocation(location));
        } else {
            railMiniDtos = railMapper.toListOfMainWayRailMiniDto(getMainWayRailMiniProjectionsByLocation(location));
        }
        log.debug("Данные о {} вариантах(е) рельсов, находящихся вблизи места {}, получены", railMiniDtos.size(), location);
        return railMiniDtos;
    }

    /**
     * Получает из базы данных список объектов-проекций рельсов, лежащих в главном пути и находящихся
     * вблизи определенного места.
     *
     * @param location место главного пути, для которого будет осуществлено получение близлежащих рельсов
     * @return список объектов-проекций рельсов
     */
    private List<MainWayRailProjection> getMainWayRailMiniProjectionsByLocation(LocationDto location) {
        Long transportDirectionId = location.locationId();
        String lineSide = LineSide.RIGHT.equals(location.lineSide()) ? "ПРАВАЯ" : "ЛЕВАЯ";
        Integer mainWayNumber = Integer.parseInt(location.wayNumber());
        int coordinate = (location.km() * 1000) + ((location.picket() - 1) * 100) + location.meter();

        return railRepository.findRailMiniInfoByLocationData(
                        transportDirectionId,
                        mainWayNumber,
                        coordinate,
                        lineSide);
    }

    /**
     * Получает из базы данных список объектов-проекций рельсов, лежащих в станционном пути и находящихся вблизи
     * определенного места.
     *
     * @param location место станционного пути, для которого будет осуществлено получение близлежащих рельсов
     * @return список объектов-проекций рельсов
     */
    private List<StationWayRailProjection> getStationWayRailMiniProjectionsByLocation(LocationDto location) {
        Long stationId = location.locationId();
        String lineSide = LineSide.RIGHT.equals(location.lineSide()) ? "ПРАВАЯ" : "ЛЕВАЯ";
        String stationWayNumber = location.wayNumber();
        stationWayNumber = stationWayNumber.contains(" станции") ?
                stationWayNumber.substring(0, location.wayNumber().indexOf(" станции")) : stationWayNumber;
        int stationWayMeter = (location.km() * 1000) + ((location.picket() - 1) * 100) + location.meter();

        return railRepository.findRailMiniInfoByLocationData(
                stationId,
                stationWayNumber,
                stationWayMeter,
                lineSide);
    }

}