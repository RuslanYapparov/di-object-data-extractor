package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.empl;

import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl.EmployeeDto;

import java.util.List;

/**
 * Интерфейс, определяющий тип и поведение всех объектов-сервисов, работающих с сущностями сотрудников компании.
 */
public interface EmployeeService {

    /**
     * Возвращает отсортированный по иерархии должностей список DTO с информацией о сотрудниках дистанции пути,
     * причастных к обслуживанию конкретного места, заданного LocationDto, не проводя при этом валидацию поступивших
     * данных (предполагается, что они проверены ранее).
     *
     * @param location DTO c информацией о месте, для которого будет получен список работников дистанции пути
     * @return список DTO с информацией о работниках дистанции пути, причастных к обслуживанию конкретного места
     * @throws ObjectNotFoundException если при запросе в БД извлекается пустой список сущностей
     */
    List<EmployeeDto> getAllWayDistanceEmployeesServingTheLocationWithoutValidation(LocationDto location);

}