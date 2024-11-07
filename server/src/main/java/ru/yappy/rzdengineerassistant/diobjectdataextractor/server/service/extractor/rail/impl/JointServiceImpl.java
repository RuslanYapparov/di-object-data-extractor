package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.infr.SwitchRepository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.rail.JointRepository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.JointService;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper.JointMapper;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.util.ParameterValidator;

import java.util.List;

/**
 * Реализация сервиса для получения сущностей с информацией о рельсовых стыках из базы данных.
 */
@Service
@Slf4j
public class JointServiceImpl implements JointService {
    /** JPA-репозиторий, извлекающий данные о рельсовых стыках. */
    private final JointRepository jointRepository;
    /** JPA-репозиторий, извлекающий данные о стрелочных переводах. */
    private final SwitchRepository switchRepository;
    /** Сервис для работы с обозначениями локаций (перегонов/станций). */
    private final LocationService locationService;
    /** Мэппер, преобразующий объекты сущностей рельсовых стыков в их DTO. */
    private final JointMapper jointMapper;

    /**
     * Конструктор объекта сервиса, получающий зависимости из Spring-контекста.
     *
     * @param jointRepository JPA-репозиторий, извлекающий данные о рельсовых стыках
     * @param switchRepository JPA-репозиторий, извлекающий данные о стрелочных переводах
     * @param locationService сервис для работы с обозначениями локаций (перегонов/станций)
     * @param jointMapper мэппер, преобразующий объекты сущностей рельсовых стыков в их DTO
     */
    @Autowired
    public JointServiceImpl(JointRepository jointRepository,
                            SwitchRepository switchRepository,
                            LocationService locationService,
                            JointMapper jointMapper) {
        this.jointRepository = jointRepository;
        this.switchRepository = switchRepository;
        this.locationService = locationService;
        this.jointMapper = jointMapper;
    }

    /**
     * Получает из БД и мэппит в DTO полную информацию о рельсовом стыке по его id и признаку типа пути (wayType).
     *
     * @param jointId идентификатор рельсового стыка в базе данных
     * @param wayType параметр, указывающий на тип пути, на котором лежит рельсовый стык
     * @return DTO, содержащий исчерпывающую информацию о рельсовом стыке
     */
    @Override
    public JointDto getFullJointInfo(Long jointId, WayType wayType, JointType jointType) {
        if (wayType == null || jointType == null) {
            throw new IllegalStateException("Не удалось получить полную информацию о рельсом стыке, так как при " +
                    "вызове метода не был указан тип пути (wayType=null) или тип стыка (jointType=null).");
        }
        log.debug("Начало выполнения операции получения полной информации о '{}' рельсовом стыке с ид={} и признаком " +
                        "WayType={}.", jointType, jointId, wayType.getRuTitle());
        ParameterValidator.validate(jointId, "'рельсовый стык'");
        JointEntity jointEntity;
        if (WayType.STATION_WAY.equals(wayType)) {
            jointEntity = switch (jointType) {
                case CONDUCTING -> jointRepository.findFullStationWayConductingJointInfoById(jointId)
                        .orElseThrow(() -> new ObjectNotFoundException("токопроводящий рельсовый стык " +
                                "на станционном пути", jointId));
                case ISOLATING_SIGNAL -> jointRepository.findFullStationWaySignalIsolatingJointInfoById(jointId)
                        .orElseThrow(() ->
                                new ObjectNotFoundException("изолирующий стык сигнала станционного пути", jointId));
                case ISOLATING_SWITCH -> getFullInfoSwitchIsolatingJointEntity(jointId, wayType);
            };
        } else {
            jointEntity = switch (jointType) {
                case CONDUCTING -> jointRepository.findFullMainWayConductingJointInfoById(jointId)
                        .orElseThrow(() -> new ObjectNotFoundException("токопроводящий рельсовый стык " +
                                "на главном пути", jointId));
                case ISOLATING_SIGNAL -> jointRepository.findFullMainWaySignalIsolatingJointInfoById(jointId)
                        .orElseThrow(() ->
                                new ObjectNotFoundException("изолирующий стык сигнала главного пути", jointId));
                case ISOLATING_SWITCH -> getFullInfoSwitchIsolatingJointEntity(jointId, wayType);
            };
        }
        JointDto jointDto = jointMapper.toDto(jointEntity);
        log.debug("Полная информация о '{}' рельсовом стыке с ид={} найдена.", jointType, jointEntity.getId());
        return jointDto;
    }

    /**
     * Получает из БД и мэппит в список DTO минимальную информацию о рельсовых стыках, находящихся вблизи определенного
     * места, заданного объектом {@link LocationDto}
     *
     * @param location объект, содержащий информацию о месте, вблизи которого будет выгружена информация о стыках
     * @return список мини-DTO, содержащих минимальную информацию о рельсовых стыках, находящихся вблизи заданного места
     */
    @Override
    public List<JointMiniDto> getJointMiniInfoVariantsByLocationDto(LocationDto location, JointType jointType) {
        log.debug("Начало выполнения операции получения вариантов '{}' рельсовых стыков для конкретного места {}.",
                jointType, location);
        ParameterValidator.validate(location);
        if (!locationService.canGetLocationProjectionByLocationDto(location)) {
            throw new ObjectNotFoundException("массив вариантов рельсовых стыков для конкретного места",
                    location.toString());
        }
        List<JointMiniDto> jointMiniDtos;
        if (WayType.STATION_WAY.equals(location.wayType())) {
            jointMiniDtos = jointMapper.mapStationWayJointEntityListToMiniDtoList(
                    getStationWayJointProjections(location, jointType));
        } else {
            jointMiniDtos = jointMapper.mapMainWayJointEntityListToMiniDtoList(
                    getMainWayJointProjections(location, jointType));
        }
        log.debug("Данные о {} вариантах(е) '{}' рельсовых стыков, находящихся вблизи места {}, получены",
                jointMiniDtos.size(), jointType, location);
        return jointMiniDtos;
    }

    /**
     * Получает из базы данных список объектов-проекций, содержащих минимальную информацию о рельсовых стыках в
     * главном пути определенного типа, находящихся вблизи определенного места.
     *
     * @param location DTO с данными о конкретном месте, вблизи которого будет выгружена информация о стыках
     * @param jointType тип рельсового стыка
     * @return список объектов-проекций с минимальной информацией о рельсовых стыках
     */
    private List<MainWayJointProjection> getMainWayJointProjections(LocationDto location, JointType jointType) {
        Long transportDirectionId = location.locationId();
        String lineSide = LineSide.RIGHT.equals(location.lineSide()) ? "ПРАВАЯ" : "ЛЕВАЯ";
        Integer mainWayNumber = Integer.parseInt(location.wayNumber());
        int coordinate = (location.km() * 1000) + ((location.picket() - 1) * 100) + location.meter();

        return switch (jointType) {
            case CONDUCTING -> jointRepository.findMiniMainWayConductingJointInfoByLocationData(transportDirectionId,
                    mainWayNumber, coordinate, lineSide);
            case ISOLATING_SIGNAL -> jointRepository.findMiniMainWaySignalIsolatingJointInfoByLocationData(
                    transportDirectionId, mainWayNumber, coordinate, lineSide);
            case ISOLATING_SWITCH -> jointRepository.findMiniMainWaySwitchIsolatingJointInfoByLocationData(
                    transportDirectionId, mainWayNumber, coordinate, lineSide);
        };
    }

    /**
     * Получает из базы данных список объектов-проекций, содержащих минимальную информацию о рельсовых стыках на
     * станционном пути определенного типа, находящихся вблизи определенного места.
     *
     * @param location DTO с данными о конкретном месте, вблизи которого будет выгружена информация о стыках
     * @param jointType тип рельсового стыка
     * @return список объектов-проекций с минимальной информацией о рельсовых стыках
     */
    private List<StationWayJointProjection> getStationWayJointProjections(LocationDto location, JointType jointType) {
        Long stationId = location.locationId();
        String lineSide = LineSide.RIGHT.equals(location.lineSide()) ? "ПРАВАЯ" : "ЛЕВАЯ";
        String stationWayNumber = location.wayNumber();
        stationWayNumber = stationWayNumber.contains(" станции") ?
                stationWayNumber.substring(0, location.wayNumber().indexOf(" станции")) : stationWayNumber;
        int stationWayMeter = (location.km() * 1000) + ((location.picket() - 1) * 100) + location.meter();

        return switch (jointType) {
            case CONDUCTING -> jointRepository.findMiniStationWayConductingJointInfoByLocationData(stationId,
                    stationWayNumber, stationWayMeter, lineSide);
            case ISOLATING_SIGNAL -> jointRepository.findMiniStationWaySignalIsolatingJointInfoByLocationData(
                    stationId, stationWayNumber, stationWayMeter, lineSide);
            case ISOLATING_SWITCH -> jointRepository.findMiniStationWaySwitchIsolatingJointInfoByLocationData(
                    stationId, stationWayNumber, stationWayMeter, lineSide);
        };
    }

    /**
     * Получает из базы данных Entity-объект с полной информацией о стрелочном изолирующем стыке и делает
     * дополнительный запрос, чтобы добавить полную информацию о стрелочном переводе, которую сеттит в Entity-объект.
     *
     * @param jointId идентификатор стрелочного изолирующего стыка
     * @param wayType тип пути
     * @return Entity-объект с полной информацией о стрелочном изолирующем стыке и стрелочном переводе
     */
    private SwitchIsolatingJointEntity getFullInfoSwitchIsolatingJointEntity(Long jointId, WayType wayType) {
        SwitchIsolatingJointEntity jointEntity;
        if (WayType.STATION_WAY.equals(wayType)) {
            jointEntity = jointRepository.findFullStationWaySwitchIsolatingJointInfoById(jointId)
                    .orElseThrow(() ->
                            new ObjectNotFoundException("стрелочный изолирующий стык станционного пути", jointId));
            StationWaySwitchEntity switchEntity = switchRepository.findStationWaySwitchById(
                    jointEntity.getSwitchEntity().getId()).orElse(null);
            jointEntity.setSwitchEntity(switchEntity);
            return jointEntity;
        }
        jointEntity = jointRepository.findFullMainWaySwitchIsolatingJointInfoById(jointId)
                .orElseThrow(() ->
                        new ObjectNotFoundException("стрелочный изолирующий стык главного пути", jointId));
        SwitchEntity switchEntity;
        if (WayType.INTERSTATION_TRACK.equals(wayType)) {
            switchEntity = switchRepository.findInterstationTrackSwitchById(jointEntity.getSwitchEntity().getId())
                    .orElse(null);
        } else {
            switchEntity = switchRepository.findStationMainWaySwitchById(jointEntity.getSwitchEntity().getId())
                    .orElse(null);
        }
        jointEntity.setSwitchEntity(switchEntity);
        return jointEntity;
    }

}