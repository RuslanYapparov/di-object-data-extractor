package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import org.mapstruct.Mapper;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl.EmployeeDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.empl.ManagerMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl.EmployeeEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Интерфейс-мэппер для сущностей сотрудников ОАО "РЖД", на основании которого Mapstruct создает реализацию.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper extends DiObjectEntityToDtoMapper<EmployeeEntity, EmployeeDto> {

    /**
     * Мэппит абстрактную сущность сотрудника в минимизированный DTO-объект, соответствующий руководителю.
     *
     * @param employeeEntity - абстрактная сущность сотрудника
     * @return DTO-объект, соответствующий руководителю
     */
    default ManagerMiniDto mapEmployeeEntityToManagerMiniDto(EmployeeEntity employeeEntity) {
        return new ManagerMiniDto(employeeEntity.getId(),
                employeeEntity.getSurname() + " " +
                        employeeEntity.getName() + " " +
                        employeeEntity.getPatronymic(),
                employeeEntity.getJobTitleAbbreviation());
    }

    /**
     * Мэппит список сущностей сотрудников разного класса в список обычных DTO с данными сотрудников.
     *
     * @param entities список сущностей сотрудников
     * @return список DTO с данными сотрудников
     */
    default List<EmployeeDto> mapEmployeeEntityListToDtoList(List<? extends EmployeeEntity> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}