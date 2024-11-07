package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.empl;

/** Класс минимизированного DTO сущности сотрудника ОАО "РЖД" с типом "Рабочий".
 *
 * @param id идентификатор сотрудника в базе данных
 * @param fullName полное имя сотрудника (Фамилия Имя Отчество)
 * @param jobTitleAbbreviation аббревиатура должности сотрудника
 * @param jobRank разряд должности сотрудника
 */
public record WorkerMiniDto(Long id,
                            String fullName,
                            String jobTitleAbbreviation,
                            Integer jobRank) {}