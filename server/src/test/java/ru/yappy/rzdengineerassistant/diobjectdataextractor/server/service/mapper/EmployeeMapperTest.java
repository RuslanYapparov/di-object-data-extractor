package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl.EmployeeDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.empl.EmplSchemaTestDataCreator;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class EmployeeMapperTest {
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeMapperTest(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Test
    void toDto_whenGetCorrectEmployeeEntity_thenReturnDto() {
        EmployeeDto employeeDto =
                employeeMapper.toDto(EmplSchemaTestDataCreator.createTestWayMaintenanceDistanceEmployeeEntity());

        assertThat(employeeDto).isNotNull();

        assertThat(employeeDto.getId()).isEqualTo(777);
        assertThat(employeeDto.getName()).isEqualTo("TestName");
        assertThat(employeeDto.getFullTitle()).isEqualTo("TestFullTitle");
    }

    @Test
    void toDto_whenGetNull_thenReturnNull() {
        EmployeeDto employeeDto = employeeMapper.toDto(null);

        assertThat(employeeDto).isNull();
    }

    @Test
    void mapEmployeeEntityListToDtoList_whenGetListWith2Employees_thenReturnDtoListWith2EmployeeDtos() {
        List<EmployeeDto> employees = employeeMapper.mapEmployeeEntityListToDtoList(List.of(
                EmplSchemaTestDataCreator.createTestWayMaintenanceDistanceEmployeeEntity(),
                EmplSchemaTestDataCreator.createTestWayMaintenanceDistanceEmployeeEntity()
        ));

        assertThat(employees).isInstanceOf(ArrayList.class);
        assertThat(employees).hasSize(2);
        assertThat(employees.getFirst().getId()).isEqualTo(777);
        assertThat(employees.get(1).getName()).isEqualTo("TestName");
    }

    @Test
    void mapEmployeeEntityListToDtoList_whenGetEmptyList_thenReturnEmptyArrayList() {
        List<EmployeeDto> employees = employeeMapper.mapEmployeeEntityListToDtoList(List.of());

        assertThat(employees).isEmpty();
    }

    @Test
    void mapEmployeeEntityListToDtoList_whenGetNull_thenThrowNPE() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> employeeMapper.mapEmployeeEntityListToDtoList(null));

        assertThat(exception.getMessage()).isEqualTo("Cannot invoke \"java.util.List.stream()\" " +
                "because \"entities\" is null");
    }

}