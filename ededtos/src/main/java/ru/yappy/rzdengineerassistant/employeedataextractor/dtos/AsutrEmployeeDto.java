package ru.yappy.rzdengineerassistant.employeedataextractor.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * Класс DTO, содержащий расширенную информацию о сотрудниках ОАО "РЖД", необходимую для отражения в некоторых
 * документах, результат обращения к сервису EmployeeDataExtractor, имитирующего подключение к базе данных АСУТР.
 *
 * @param asutrPersonnelNumber табельный номер сотрудника - идентификатор в базе данных АСУТР
 * @param lastName фамилия сотрудника
 * @param firstName имя сотрудника
 * @param patronymic отчество сотрудника
 * @param employeeType тип сотрудника
 * @param jobTitleAbbreviation аббревиатура должности
 * @param fullJobTitle полное название должности
 * @param employmentDate дата трудоустройства
 * @param employmentLength продолжительность работы в компании
 * @param atPositionSince дата начала работы в текущей должности
 * @param atPositionJobLength продолжительность работы в текущей должности
 * @param edType тип образования
 * @param edInstitutionName название учебного заведения
 * @param faculty факультет, специальность
 * @param edGraduationYear год окончания учебного заведения
 */
public record AsutrEmployeeDto(
        Long asutrPersonnelNumber,
        String lastName,
        String firstName,
        String patronymic,
        String employeeType,
        String jobTitleAbbreviation,
        String fullJobTitle,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate employmentDate,
        Float employmentLength,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate atPositionSince,
        Float atPositionJobLength,
        EducationType edType,
        String edInstitutionName,
        String faculty,
        Integer edGraduationYear) {}