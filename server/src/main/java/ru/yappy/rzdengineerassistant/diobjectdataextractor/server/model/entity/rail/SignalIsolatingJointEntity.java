package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import jakarta.persistence.*;
import lombok.*;

/** Абстрактный класс, содержащий все поля, характерные для сущности изолирующего рельсового стыка железнодорожного
 * сигнала (светофора) в базе данных (таблица rail.signal_isolating_joints). */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "signal_isolating_joints", schema = "rail")
public abstract class SignalIsolatingJointEntity extends IsolatingJointEntity {
    /** Название сигнала, к которому относится изолирующий стык. */
    @Column(name = "signal_name")
    protected String signalName;

    /** Метод, возвращающий строковое представление изолирующего рельсового стыка железнодорожного сигнала (светофора).
     *
     * @return строка вида "изолирующий стык сигнала (литер сигнала)", например, "изолирующий стык сигнала Ч2".
     * @throws IllegalStateException если не удалось выгрузить информацию об одном из рельсов (или об обоих рельсах)
     * из базы данных */
    @Override
    public String toString() {
        return super.toString() + " сигнала " + signalName;
    }

}