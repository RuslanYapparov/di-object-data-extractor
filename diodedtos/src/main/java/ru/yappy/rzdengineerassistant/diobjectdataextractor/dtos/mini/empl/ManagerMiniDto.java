package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.empl;

/** Класс минимизированного DTO сущности сотрудника ОАО "РЖД" с типом "Руководитель".
 *
 * @param id идентификатор сотрудника в базе данных
 * @param fullName полное имя сотрудника (Фамилия Имя Отчество)
 * @param jobTitleAbbreviation аббревиатура должности сотрудника
 *           (аббревиатура подразделения ОАО "РЖД", которым управляет сотрудник)
 */
public record ManagerMiniDto(Long id,
                             String fullName,
                             String jobTitleAbbreviation) {}