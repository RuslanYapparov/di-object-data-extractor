package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl.EmployeeDto;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeComparingUtilsUnitTest {

    @ParameterizedTest
    @ValueSource(strings = { "ПЧ", "ПЧЗ", "ПЧГ", "ПЧЗК", "НПТО", "ФЭС", "ПДдеф", "ПДМ", "ПД-1", "ПЧУ-2", "ПДБ", "КСП",
            "ПЧТ", "м/п", "ПД", "ПЧУ", "Кекс", "", " ", "    ", "\r", "\n", "\t" })
    public void compare_whenGetEmployeesWithDifferentPrioritiesByJobTitleAbbreviation_shouldReturnValues(
            String jobTitleAbbreviation) {
        EmployeeDto firstEmployee = new EmployeeDto(1L, 777L, "Тестов", "Тест",
                "Тестович", "Тестировщик", jobTitleAbbreviation,
                "Тестировщик аббревиатур должностей");
        EmployeeDto secondEmployee = new EmployeeDto(1L, 777L, "Тестов", "Тест",
                "Тестович", "Тестировщик", "Тест",
                "Тестировщик аббревиатур должностей");
        int result = EmployeeComparingUtils.getEmployeeDtoComparator().compare(secondEmployee, firstEmployee);
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

}