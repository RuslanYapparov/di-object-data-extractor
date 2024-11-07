package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.empl;

/** Класс минимизированного DTO сущности сотрудника ОАО "РЖД" с типом "Специалист".
 *
 * @param id идентификатор сотрудника в базе данных
 * @param fullName полное имя сотрудника (Фамилия Имя Отчество)
 * @param jobTitle должность сотрудника (полное наименование)
 */
public record SpecialistMiniDto(Long id,
                                String fullName,
                                String jobTitle) {}