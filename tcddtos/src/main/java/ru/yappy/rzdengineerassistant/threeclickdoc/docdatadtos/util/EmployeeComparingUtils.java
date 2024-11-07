package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl.EmployeeDto;

import java.util.Comparator;

/**
 * Утилитарный класс, содержащий вспомогательные методы для сравнения различных DTO объектов, касающихся сотрудников
 * компании ОАО "РЖД" в зависимости от приоритета их должности.
 */
public class EmployeeComparingUtils {

    /**
     * Возвращает реализацию функционального интерфейса Comparator для сравнения DTO сотрудников дистанций пути.
     *
     * @return компаратор сотрудников дистанций пути
     */
    public static Comparator<EmployeeDto> getEmployeeDtoComparator() {
        return (e1, e2) -> getEmployeePriority(e2.getJobTitleAbbreviation()) -
                getEmployeePriority(e1.getJobTitleAbbreviation());
    }

    /**
     * Возвращает целочисленное обозначение приоритета сотрудника в зависимости от аббревиатуры его должности.
     *
     * @param jobTitleAbbreviation строка с аббревиатурой должности сотрудника
     * @return целочисленное обозначение приоритета сотрудника
     */
    private static int getEmployeePriority(String jobTitleAbbreviation) {
        int priority = switch (jobTitleAbbreviation) {
            case "ПЧ", "ИЧ" -> 100;
            case "ПЧЗ", "ИЧЗ", "ИЧЗШ" -> 90;
            case "ПЧГ", "ИЧГ" -> 80;
            case "ПЧЗК" -> 70;
            case "НПТО" -> 60;
            case "ФЭС" -> 55;
            case "ПДдеф" -> 50;
            case "ПДМ" -> 45;
            case "ПДБ" -> 20;
            case "КСП" -> 10;
            case "ПЧТ" -> 5;
            case "м/п" -> 1;
            default -> 0;
        };
        if (priority == 0 && jobTitleAbbreviation.startsWith("ПЧУ-")) {
            return 40;
        }
        if (priority == 0 && jobTitleAbbreviation.startsWith("ПД-")) {
            return 30;
        }
        return priority;
    }

}