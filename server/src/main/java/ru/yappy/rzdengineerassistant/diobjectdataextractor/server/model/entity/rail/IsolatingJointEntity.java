package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/** Абстрактный класс, содержащий все поля, характерные для сущности изолирующего рельсового стыка в базе данных
 * (таблица rail.isolating_joint_info). */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "isolating_joint_info", schema = "rail")
public abstract class IsolatingJointEntity extends JointEntity {
    /** Тип объекта изолирующего рельсового стыка ("СИГНАЛ" или "СТРЕЛОЧНЫЙ ПЕРЕВОД"). */
    @Column(name = "isolating_joint_object_type")
    protected String objectType;
    /** Дата последней переборки изолирующего стыка. */
    @Column(name = "last_pads_removal_date")
    protected LocalDate lastPadsRemovalDate;
    /** Дата последнего проведения демагнетизации изолирующего стыка (операция, характерная для изолирующих
     * стыков с типом накладок АПАТЭК, но может возникнуть необходимость и на других типах накладок). */
    @Column(name = "last_demagnetization_date")
    protected LocalDate lastDemagnetizationDate;
    /** Метод последней демагнетизации изолирующего стыка ("ДЕМ-Р ПОСТОЯННЫЙ", "ДЕМ-Р ГЕНЕРАТОР" (демагнетизатор
     * на постоянных магнитах или с питанием от генератора), "УСТАНОВКА ИИП", "УСТАНОВКА ШУНТА"). */
    @Column(name = "last_demagnetization_method")
    protected String lastDemagnetizationMethod;
    /** Значение величины намагниченности изолирующего стыка при последнем измерении. */
    @Column(name = "isolating_joint_magnetization")
    protected Integer magnetization;
    /** Дата последнего измерения намагниченности изолирующего стыка. */
    @Column(name = "last_magnetization_measure_date")
    protected LocalDate lastMagnetizationMeasureDate;

    /** Метод, возвращающий строковое представление изолирующего рельсового стыка.
     *
     * @return строка вида "изолирующий стык".
     * @throws IllegalStateException если не удалось выгрузить информацию об одном из рельсов (или об обоих рельсах)
     * из базы данных */
    @Override
    public String toString() {
        return "изолирующий " + super.toString();
    }

}