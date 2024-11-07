package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import lombok.*;
import jakarta.persistence.*;

/** Абстрактный класс, содержащий основные поля сущности сотрудника ОАО "РЖД" в базе данных.
 * Имеет поле с идентификатором работника в ЕК АСУТР для возможности получения данных о стаже и образовании работника. */
@Data
@Entity
@Table(name = "employees", schema = "empl")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EmployeeEntity {
    /** Идентификатор сотрудника ОАО "РЖД". Первичный ключ в таблице БД (тип данных INTEGER). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    protected Long id;
     /** Идентификатор сотрудника в ЕК АСУТР, необходим для получения данных о стаже и образовании работника. */
    @Column(name = "asutr_personnel_number")
    protected Long asutrPersonnelNumber;
    /** Фамилия сотрудника. */
    @Column(name = "employee_surname")
    protected String surname;
    /** Имя сотрудника. */
    @Column(name = "employee_name")
    protected String name;
    /** Отчество сотрудника. */
    @Column(name = "employee_patronymic")
    protected String patronymic;
    /** Тип должности сотрудника (руководитель, специалист, рабочий). */
    @Column(name = "employee_type")
    protected String employeeType;
    /** Сокращенное название должности сотрудника. */
    @Column(name = "job_title_abbreviation")
    protected String jobTitleAbbreviation;
    /** Полное название должности сотрудника. */
    @Column(name = "job_title")
    protected String fullTitle;

    /** Метод, возвращающий строковое представление сущности сотрудника ОАО "РЖД"
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество"
    */
    @Override
    public String toString() {
        return jobTitleAbbreviation + " " + surname + " " + name + " " + patronymic;
    }

}