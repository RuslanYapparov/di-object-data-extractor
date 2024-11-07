package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/** Абстрактный класс, содержащий все поля, характерные для сущности токопроводящего рельсового стыка в базе данных
 * (таблица rail.conducting_joint_info). */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "conducting_joint_info", schema = "rail")
public abstract class ConductingJointEntity extends JointEntity {
    /** Величина переходного сопротивления в токопроводящем рельсовом стыке. */
    @Column(name = "joint_contact_resistance")
    protected Integer contactResistance;
    /** Дата последнего измерения переходного сопротивления в токопроводящем рельсовом стыке. */
    @Column(name = "last_resistance_measure_date")
    protected LocalDate lastResistanceMeasureDate;
    /** Дата последнего снятия накладок токопроводящего стыка. */
    @Column(name = "last_pads_removal_date")
    protected LocalDate lastPadsRemovalDate;
    /** Типы рельсовых соединителей, установленных в токопроводящем рельсовом стыке (в приложении для всех таких
     * стыков одно значение - "СРСП", но могут быть несколько, типа "ЭМСВ", "ЦМС" и т.д.). */
    @Column(name = "connector_types")
    protected String connectorTypes;
    /** Дата установки рельсового соединителя(-лей) в токопроводящем рельсовом стыке. */
    @Column(name = "connector_installation_date")
    protected LocalDate connectorInstallationDate;

    /** Метод, возвращающий строковое представление токопроводящего рельсового стыка.
     *
     * @return строка вида "токопроводящий стык".
     * @throws IllegalStateException если не удалось выгрузить информацию об одном из рельсов (или об обоих рельсах)
     * из базы данных */
    @Override
    public String toString() {
        return "токопроводящий " + super.toString();
    }

}