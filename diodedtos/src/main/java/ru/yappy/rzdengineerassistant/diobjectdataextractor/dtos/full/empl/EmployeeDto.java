package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl;

import java.util.Objects;

/**
 * Класс DTO сущности сотрудника ОАО "РЖД".
 */
public class EmployeeDto {
    /** Идентификатор сотрулника ОАО "РЖД" */
    private final Long id;
    /** Идентификатор сотрудника в ЕК АСУТР, необходим для получения данных о стаже и образовании работника. */
    private final Long asutrPersonnelNumber;
    /** Фамилия сотрудника. */
    private final String surname;
    /** Имя сотрудника. */
    private final String name;
    /** Отчество сотрудника. */
    private final String patronymic;
    /** Тип должности сотрудника (руководитель, специалист, рабочий). */
    private final String employeeType;
    /** Сокращенное название должности сотрудника. */
    private final String jobTitleAbbreviation;
    /** Полное название должности сотрудника. */
    private final String fullTitle;

    /**
     * Конструктор DTO сотрудника ОАО "РЖД", принимающий в качестве параметров значения всех полей.
     *
     * @param id идентификатор в базе данных ЕК АСУИ
     * @param asutrPersonnelNumber идентификатор в базе данных АСУТР
     * @param surname фамилия сотрудника
     * @param name имя сотрудника
     * @param patronymic отчество сотрудника
     * @param employeeType тип должности сотрудника
     * @param jobTitleAbbreviation сокращенное название должности сотрудника
     * @param fullTitle полное название должности сотрудника
     */
    public EmployeeDto(Long id,
                       Long asutrPersonnelNumber,
                       String surname,
                       String name,
                       String patronymic,
                       String employeeType,
                       String jobTitleAbbreviation,
                       String fullTitle) {
        this.id = id;
        this.asutrPersonnelNumber = asutrPersonnelNumber;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.employeeType = employeeType;
        this.jobTitleAbbreviation = jobTitleAbbreviation;
        this.fullTitle = fullTitle;
    }

    /**
     * Геттер идентификатора сотрудника ОАО "РЖД" в базе данных ЕК АСУИ.
     *
     * @return идентификатор сотрудника ОАО "РЖД"
     */
    public Long getId() {
        return id;
    }

    /**
     * Геттер идентификатора сотрудника в ЕК АСУТР.
     *
     * @return идентифиактор сотрудника в ЕК АСУТР
     */
    public Long getAsutrPersonnelNumber() {
        return asutrPersonnelNumber;
    }

    /**
     * Геттер фамилии сотрудника ОАО "РЖД".
     *
     * @return фамилия сотрудника ОАО "РЖД"
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Геттер имени сотрудника ОАО "РЖД".
     *
     * @return имя сотрудника ОАО "РЖД"
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер отчества сотрудника ОАО "РЖД".
     *
     * @return отчество сотрудника ОАО "РЖД"
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Геттер типа должности сотрудника ОАО "РЖД".
     *
     * @return тип должности сотрудника ОАО "РЖД"
     */
    public String getEmployeeType() {
        return employeeType;
    }

    /**
     * Геттер сокращенного названия должности сотрудника ОАО "РЖД".
     *
     * @return сокращенное название должности сотрудника ОАО "РЖД"
     */
    public String getJobTitleAbbreviation() {
        return jobTitleAbbreviation;
    }

    /**
     * Геттер полного названия должности сотрудника ОАО "РЖД".
     *
     * @return полное название должности сотрудника ОАО "РЖД"
     */
    public String getFullTitle() {
        return fullTitle;
    }

    /** Сравнение двух объектов, используя их состояние (значения всех полей).
     *
     * @param o другой объект для сравнения
     * @return булевое значение, определяющее равенство объектов
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDto that)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(asutrPersonnelNumber, that.asutrPersonnelNumber) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(employeeType, that.employeeType) &&
                Objects.equals(jobTitleAbbreviation, that.jobTitleAbbreviation) &&
                Objects.equals(fullTitle, that.fullTitle);
    }

    /** Получение хэш-кода объекта.
     *
     * @return числовое представление объекта на основании его состояния (значений всех полей)
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, asutrPersonnelNumber, surname, name, patronymic, employeeType,
                jobTitleAbbreviation, fullTitle);
    }

    /**
     * Получение строкового представления объекта.
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", asutrPersonnelNumber=" + asutrPersonnelNumber +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", employeeType='" + employeeType + '\'' +
                ", jobTitleAbbreviation='" + jobTitleAbbreviation + '\'' +
                ", fullTitle='" + fullTitle + '\'' +
                '}';
    }
}