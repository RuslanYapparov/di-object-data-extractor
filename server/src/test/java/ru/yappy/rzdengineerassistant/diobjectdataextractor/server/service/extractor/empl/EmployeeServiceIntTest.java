package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.empl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl.EmployeeDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class EmployeeServiceIntTest {
    private final EmployeeService emplService;

    @Autowired
    public EmployeeServiceIntTest(EmployeeService emplService) {
        this.emplService = emplService;
    }

    @ParameterizedTest
    @ValueSource(ints = {1271, 1255})
    void getAllWayDistanceEmployeesServingTheLocationWithoutValidation_whenGetPD4MainWayLocation_thenReturnEmplDtoList(
            int km) {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                1L,
                "1",
                km,
                7,
                7,
                LineSide.RIGHT
        );

        List<EmployeeDto> employees = emplService.getAllWayDistanceEmployeesServingTheLocationWithoutValidation(location);
        if (km == 1271) {
            assertThat(employees).hasSize(28);
            assertThat(employees.getFirst().getJobTitleAbbreviation()).isEqualTo("ИЧ");
            assertThat(employees.get(1).getJobTitleAbbreviation()).isEqualTo("ИЧЗ");
            assertThat(employees.get(9).getJobTitleAbbreviation()).isEqualTo("ПД-2");
            assertThat(employees.getLast().getJobTitleAbbreviation()).isEqualTo("ПЧБ");
        } else {
            assertThat(employees).hasSize(22);
            assertThat(employees.getFirst().getJobTitleAbbreviation()).isEqualTo("ПЧ");
            assertThat(employees.get(1).getJobTitleAbbreviation()).isEqualTo("ПЧЗ");
            assertThat(employees.get(8).getJobTitleAbbreviation()).isEqualTo("ПД-2");
            assertThat(employees.getLast().getJobTitleAbbreviation()).isEqualTo("ПЧБ");
        }
    }

    @Test
    void getAllWayDistanceEmployeesServingTheLocationWithoutValidation_whenGetPD1MainWayLocation_thenReturnEmplDtoList() {
        LocationDto location = new LocationDto(
                WayType.STATION_MAIN_WAY,
                1L,
                "2",
                1235,
                1,
                7,
                LineSide.RIGHT
        );

        List<EmployeeDto> employees = emplService.getAllWayDistanceEmployeesServingTheLocationWithoutValidation(location);
        assertThat(employees).hasSize(22);
        assertThat(employees.getFirst().getJobTitleAbbreviation()).isEqualTo("ПЧ");
        assertThat(employees.get(1).getJobTitleAbbreviation()).isEqualTo("ПЧЗ");
        assertThat(employees.get(7).getJobTitleAbbreviation()).isEqualTo("ПЧУ-1");
        assertThat(employees.getLast().getJobTitleAbbreviation()).isEqualTo("ПЧБ");
    }

    @ParameterizedTest
    @ValueSource(strings = { "4", "4 станции Пюре" })
    void getAllWayDistanceEmployeesServingTheLocationWithoutValidation_whenGetPD3StationWayLocation_thenReturnEmplDtoList(
            String wayNumber) {
        LocationDto location = new LocationDto(
                WayType.STATION_WAY,
                7L,
                wayNumber,
                1,
                1,
                7,
                LineSide.RIGHT
        );

        List<EmployeeDto> employees = emplService.getAllWayDistanceEmployeesServingTheLocationWithoutValidation(location);
        assertThat(employees).hasSize(23);
        assertThat(employees.getFirst().getJobTitleAbbreviation()).isEqualTo("ИЧ");
        assertThat(employees.get(1).getJobTitleAbbreviation()).isEqualTo("ИЧЗ");
        assertThat(employees.get(2).getJobTitleAbbreviation()).isEqualTo("ИЧЗШ");
        assertThat(employees.get(10).getJobTitleAbbreviation()).isEqualTo("ПДБ");
        assertThat(employees.getLast().getJobTitleAbbreviation()).isEqualTo("ПЧБ");
    }

    @Test
    void getAllWayDistanceEmployeesServingTheLocationWithoutValidation_whenGetIncorrectLocDto_throwObjectNotFoundException() {
        LocationDto location = new LocationDto(
                WayType.STATION_WAY,
                1L,
                "4",
                1,
                1,
                7,
                LineSide.RIGHT
        );

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> emplService.getAllWayDistanceEmployeesServingTheLocationWithoutValidation(location));
        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект 'массив данных о " +
                "работниках дистанции пути для конкретного места' со следующими критериями выборки: LocationDto" +
                "[wayType=STATION_WAY, locationId=1, wayNumber=4, km=1, picket=1, meter=7, lineSide=RIGHT]");
    }

    @Test
    void getAllWayDistanceEmployeesServingTheLocationWithoutValidation_whenGetNullLocDto_thenThrowNPE() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> emplService.getAllWayDistanceEmployeesServingTheLocationWithoutValidation(null));
        assertThat(exception.getMessage()).isEqualTo("Cannot invoke \"ru.yappy.rzdengineerassistant." +
                "diobjectdataextractor.dtos.common.location.LocationDto.wayType()\" because \"location\" is null");
    }

}