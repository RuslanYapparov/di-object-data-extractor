package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common;

/** Класс DTO, содержащий данные об исполнителе.
 *
 * @param firstName имя исполнителя
 * @param lastName фамилия исполнителя
 * @param patronymic отчество исполнителя
 * @param jobTitle должность исполнителя (по умолчанию - "ведущий инженер")
 * @param telephone рабочий номер телефона
 * @param workEmail рабочий адрес электронной почты
 */
public record PerformerDto(String lastName,
                           String firstName,
                           String patronymic,
                           String jobTitle,
                           String telephone,
                           String workEmail) {}