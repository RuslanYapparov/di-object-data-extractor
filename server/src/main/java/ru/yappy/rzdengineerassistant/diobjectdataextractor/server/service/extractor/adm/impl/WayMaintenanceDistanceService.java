package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.adm.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm.WayMaintenanceDistanceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm.WayMaintenanceDistanceMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.WayMaintenanceDistanceEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.adm.WayMaintenanceDistanceProjection;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.adm.WayMaintenanceDistanceRepository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.adm.StructuralEnterpriseService;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper.WayMaintenanceDistanceMapper;

import java.util.List;

/**
 * Имплементация сервиса для работы с сущностями дистанций пути.
 */
@Service
@Qualifier("way")
@Slf4j
public class WayMaintenanceDistanceService implements StructuralEnterpriseService {
    /** Репозиторий извлекающий сущности, касающиеся дистанций пути, из БД. */
    private final WayMaintenanceDistanceRepository wayMaintenanceDistanceRepository;
    /** Мэппер объектов Entity, касающихся дистанции пути, в DTO. */
    private final WayMaintenanceDistanceMapper wayMaintenanceDistanceMapper;

    /**
     * Spring-конструктор объекта сервиса, принимающий параметры для обоих полей класса.
     *
     * @param wayMaintenanceDistanceRepository репозиторий сущностей дистанции пути
     * @param wayMaintenanceDistanceMapper мэппер сущностей дистанции пути
     */
    @Autowired
    public WayMaintenanceDistanceService(WayMaintenanceDistanceRepository wayMaintenanceDistanceRepository,
                                         WayMaintenanceDistanceMapper wayMaintenanceDistanceMapper) {
        this.wayMaintenanceDistanceRepository = wayMaintenanceDistanceRepository;
        this.wayMaintenanceDistanceMapper = wayMaintenanceDistanceMapper;
    }

    /**
     * Получает из БД и мэппит в DTO-объект полную информацию о дистанции пути по ее идентификатору.
     *
     * @param distanceId идентификатор дистанции пути
     * @return DTO-объект содержащий исчерпывающую информацию о дистанции пути
     */
    @Override
    public WayMaintenanceDistanceDto getFullStructuralEnterpriseInfoById(Long distanceId) {
        log.debug("Начало выполнения операции получения полной информации о дистанции пути с идентификатором {}", distanceId);
        WayMaintenanceDistanceEntity wayMaintenanceDistanceEntity =
                wayMaintenanceDistanceRepository.findRequiredWayMaintenanceDistanceInfoById(distanceId).orElseThrow(
                        () -> new ObjectNotFoundException("дистанция пути", distanceId));
        WayMaintenanceDistanceDto distanceDto = wayMaintenanceDistanceMapper.toDto(wayMaintenanceDistanceEntity);
        log.debug("Полная информация о дистанции пути с идентификатором {} получена", distanceId);
        return distanceDto;
    }

    /**
     * Получает из БД и мэппит в список DTO-объектов минимальную информацию о дистанциях пути по аббревиатуре
     * региональной дирекции инфраструктуры.
     *
     * @param directorateAbbreviation аббревиатура региональной дирекции, ее идентификатор в БД
     * @return список DTO-объектов минимальной информации о дистанциях пути
     */
    @Override
    public List<WayMaintenanceDistanceMiniDto> getAllStructuralEnterprisesMiniInfoByDirectorateAbbreviation(
            String directorateAbbreviation) {
        log.debug("Начало выполнения операции получения минимальной информации о дистанциях пути, " +
                "находящихся в границах дирекции инфраструктуры '{}'", directorateAbbreviation);
        List<WayMaintenanceDistanceProjection> miniDistances = wayMaintenanceDistanceRepository
                .findAllByRegionalDirectorateAbbreviationOrderByNumber(directorateAbbreviation);
        List<WayMaintenanceDistanceMiniDto> miniDistancesDtos =
                wayMaintenanceDistanceMapper.mapListOfDistanceEntitiesToListOfDto(miniDistances);
        log.debug("Минимальная информация о дистанциях пути получена, количество представленных вариантов - {}",
                miniDistancesDtos.size());
        return miniDistancesDtos;
    }

}