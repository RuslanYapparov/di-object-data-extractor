package ru.yappy.rzdengineerassistant.employeedataextractor.dtos;

import java.time.LocalDate;

/**
 * Утилитарный класс для создания тестовых данных сервиса EmployeeDataExtractor.
 */
public class EdeTestDataCreator {

    /**
     * Создает тестовый объект AsutrEmployeeDto.
     *
     * @return тестовый объект AsutrEmployeeDto
     */
    public static AsutrEmployeeDto createAsutrEmployeeDto() {
        return new AsutrEmployeeDto(
                1234567L,
                "Тестов",
                "Тест",
                "Тестович",
                "РАБОЧИЙ",
                "Т",
                "Тестировщик путей сообщения",
                LocalDate.of(2022, 1, 1),
                2.8f,
                LocalDate.of(2023, 1, 1),
                1.8f,
                EducationType.HIGHER_EDUCATION,
                "Кусайкинская высшая тестировочная академия",
                "Факультет прикладного тестирования",
                2022);
    }

}