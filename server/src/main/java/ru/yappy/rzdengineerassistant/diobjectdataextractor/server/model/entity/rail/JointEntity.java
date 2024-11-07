package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;

/** Абстрактный класс, содержащий все поля, характерные для сущности рельсового стыка в базе данных (таблица rail.joints). */
@Data
@Entity
@Table(name = "joints", schema = "rail")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class JointEntity {
    /** Идентификатор рельсового стыка. Первичный ключ в БД (тип данных BIGINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "joint_id")
    protected Long id;
    /** Сторонность рельсовой нити, на которой расположен стык ("ЛЕВАЯ" или "ПРАВАЯ"). */
    @Column(name = "rail_line_side")
    protected String lineSide;
    /** Идентификатор рельса, расположенного до стыка. */
    @Column(name = "rail_id_before")
    protected Long railBeforeId;
    /** Рельс, расположенный после стыка. */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rail_id_after")
    protected RailEntity railAfter;
    /** Статус рельсового стыка (в приложении используется только значение по умолчанию - "АКТИВЕН"). */
    @Column(name = "joint_status")
    protected String status;
    /** Тип рельсового стыка ("ТОКОПРОВОДЯЩИЙ" или "ИЗОЛИРУЮЩИЙ"). */
    @Column(name = "joint_type")
    protected String type;
    /** Тип накладок в рельсовом стыке ("ДВУХГОЛОВЫЕ", "КБ", "АПАТЭК", "ПЛАСТРОН", "СБОРНЫЕ", "ИИП-65" и т.д.). */
    @Column(name = "pads_type")
    protected String padsType;
    /** Количество болтовых отверстий в рельсовой накладке (может быть только 4 или 6). */
    @Column(name = "pad_amount_of_holes")
    protected Integer padAmountOfHoles;
    /** Значение величины вертикальной ступеньки в рельсовом стыке. */
    @Column(name = "joint_vertical_step")
    protected Float verticalStep;
    /** Значение величины горизонтальной ступеньки в рельсовом стыке. */
    @Column(name = "joint_horizontal_step")
    protected Float horizontalStep;
    /** Дата последнего измерения характеристик рельсового стыка. */
    @Column(name = "last_measure_date")
    protected LocalDate lastMeasureDate;
    /** Температура рельса в момент последнего измерения характеристик рельсового стыка. */
    @Column(name = "last_measure_rail_temp")
    protected Integer lastMeasureRailTemp;
    /** Величина зазора в стыке в миллиметрах. */
    @Formula("""
            (SELECT r.gap_before FROM rail.rails AS r
            WHERE r.rail_id = rail_id_after)
            """)
    protected Integer gap;

    /** Метод, возвращающий строковое представление стыка в случае, если указаны идентификаторы рельсов до и после.
     *
     * @return слово "стык".
     * @throws IllegalStateException если не удалось выгрузить информацию об одном из рельсов (или об обоих рельсах)
     * из базы данных */
    @Override
    public String toString() {
        if (railBeforeId == null || railAfter == null) {
            throw new IllegalStateException("Невозможно установить, между какими рельсами находится стык, так как " +
                    "не удалось выгрузить информацию об одном из рельсов (или об обоих рельсах) из базы данных.");
        }
        return "стык";
    }

}