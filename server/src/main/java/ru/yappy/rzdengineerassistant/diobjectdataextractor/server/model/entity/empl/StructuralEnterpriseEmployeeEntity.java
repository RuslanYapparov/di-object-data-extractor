package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl;

import jakarta.persistence.*;
import lombok.*;

/** Абстрактный класс, содержащий поле с разрядом для сущности сотрудника эксплуатационного структурного
 * подразделения ОАО "РЖД" (уровень StructuralEnterprise), должность которого может быть ранжирована по разряду. */
@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class StructuralEnterpriseEmployeeEntity extends EmployeeEntity {
    /** Разрядность должности работника (null, если разряд не подразумевается). */
    @Column(name = "job_rank")
    protected Integer jobRank;

    /** Метод, возвращающий строковое представление сущности работника с типом "Рабочий"
     *
     * @return строка вида "(Аббревиатура должности) Фамилия Имя Отчество" для работника с должностью без разряда,
     * строка вида "(Аббревиатура должности) (разряд)-го разряда Фамилия Имя Отчество Разряд"
     * для работника с должностью с разрядом. */
    @Override
    public String toString() {
        return (jobRank == null) ? super.toString() :
                jobTitleAbbreviation + " " + jobRank + "-го разряда " + surname + " " + name + " " + patronymic;
    }

}