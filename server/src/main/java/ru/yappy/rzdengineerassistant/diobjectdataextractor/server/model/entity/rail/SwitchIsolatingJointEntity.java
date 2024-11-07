package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import jakarta.persistence.*;
import lombok.*;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.SwitchEntity;

/** Абстрактный класс, содержащий все поля, характерные для сущности изолирующего рельсового стыка стрелочного
 * перевода в базе данных (таблица rail.signal_isolating_joints). */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "switch_isolating_joints", schema = "rail")
public abstract class SwitchIsolatingJointEntity extends IsolatingJointEntity {
    /** Сущность стрелочного перевода, на котором находится изолирующий стык. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "switch_id")
    protected SwitchEntity switchEntity;
    /** Номер изолирующего стыка в зависимости от размещения на схеме стрелочного перевода. */
    @Column(name = "joint_number")
    protected Integer jointNumber;

    /** Метод, возвращающий строковое представление изолирующего рельсового стыка стрелочного перевода.
     * <p>
     * Не выбрасывает исключение в отличие от других видов стыков, потому что допускается отсутствие информации
     * о стыкующихся рельсах.
     *
     * @return строка вида "изолирующий стык № (номер стыка) стр. № (номер стрелки)",
     * например, "изолирующий стык № 5 стр. № 5". */
    @Override
    public String toString() {
        return "изолирующий стык № " + jointNumber + " стр. № " + switchEntity.getName();
    }

}