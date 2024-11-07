package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.empl.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl.EmployeeDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl.WayMaintenanceDistanceEmployeeEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.empl.EmployeeRepository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.empl.EmployeeService;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper.EmployeeMapper;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util.EmployeeComparingUtils;

import java.util.*;

/**
 * Реализация сервиса для получения сущностей с информацией о работниках подразделений из базы данных ЕК АСУИ.
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    /** Spring-JPA репозиторий, извлекающий сущности работников подразделений из БД. */
    private final EmployeeRepository emplRepository;
    /** Мэппер, преобразующий сущности с информацией о сотрудниках подразделений в DTO. */
    private final EmployeeMapper emplMapper;

    /**
     * Конструктор объекта сервиса, получающий зависимости из Spring-контекста.
     *
     * @param emplRepository Spring-JPA репозиторий, извлекающий сущности работников подразделений из БД
     * @param emplMapper мэппер, преобразующий сущности с информацией о сотрудниках подразделений в DTO.
     */
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository emplRepository,
                               EmployeeMapper emplMapper) {
        this.emplRepository = emplRepository;
        this.emplMapper = emplMapper;
    }

    /**
     * Возвращает отсортированный по иерархии должностей список DTO с информацией о сотрудниках дистанции пути,
     * причастных к обслуживанию конкретного места, заданного LocationDto, не проводя при этом валидацию поступивших
     * данных (предполагается, что они проверены ранее).
     *
     * @param location DTO c информацией о месте, для которого будет получен список работников дистанции пути
     * @return список DTO с информацией о работниках дистанции пути, причастных к обслуживанию конкретного места
     * @throws ObjectNotFoundException если при запросе в БД извлекается пустой список сущностей
     */
    @Override
    public List<EmployeeDto> getAllWayDistanceEmployeesServingTheLocationWithoutValidation(LocationDto location) {
        log.debug("Начало выполнения операции получения информации о работниках дистанции пути для " +
                        "конкретного места {} без проверки указанного местоположения.", location);
        List<WayMaintenanceDistanceEmployeeEntity> employees;
        if (WayType.STATION_WAY.equals(location.wayType())) {
            String stationWayNumber = location.wayNumber();
            stationWayNumber = stationWayNumber.contains(" станции") ?
                    stationWayNumber.substring(0, location.wayNumber().indexOf(" станции")) : stationWayNumber;
            employees = new LinkedList<>(emplRepository.findAllLinearSectionWorkersByStationWayLocationIdAndKm(
                    location.locationId(), stationWayNumber));
        } else {
            employees = new LinkedList<>(emplRepository.findAllLinearSectionWorkersByMainWayLocationIdAndKm(
                    location.locationId(), location.km()));
        }
        if (employees.isEmpty()) {
            throw new ObjectNotFoundException("массив данных о работниках дистанции пути для конкретного места",
                    location.toString());
        }
        Long wayDistanceId = employees.getFirst().getWayMaintenanceDistance().getId();
        employees.addAll(emplRepository.findAllInvolvedWayDistanceEmployeesByDistanceId(wayDistanceId));
        List<EmployeeDto> employeeDtos = emplMapper.mapEmployeeEntityListToDtoList(employees);
        employeeDtos.sort(EmployeeComparingUtils.getEmployeeDtoComparator());
        log.debug("Данные о {} сотрудниках, причастных к обслуживанию конкретного места {} получены", employeeDtos.size(),
                location);
        return employeeDtos;
    }

}