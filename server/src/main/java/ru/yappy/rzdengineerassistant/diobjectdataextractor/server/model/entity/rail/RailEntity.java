package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.SwitchEntity;

import java.time.LocalDate;

/** Абстрактный класс, содержащий все поля, характерные для сущности рельса в базе данных (таблица rail.rails). */
@Data
@Entity
@Table(name = "rails", schema = "rail")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RailEntity {
    /** Идентификатор рельса. Первичный ключ в базе данных (тип данных - BIGINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rail_id")
    protected Long id;
    /** Длина рельса. */
    @Column(name = "rail_length")
    protected Float length;
    /** Сторонность рельсовой нити, на которой находится рельс ("ЛЕВАЯ", "ПРАВАЯ"). */
    @Column(name = "rail_line_side")
    protected String lineSide;
    /** Вид рельсового элемента ("ПЛЕТЬ, "СТРЕЛОЧНЫЙ ПЕРЕВОД", "ЗВЕНЬЕВОЙ ПУТЬ", "УРАВНИТЕЛЬНАЯ РУБКА"). */
    @Column(name = "rail_kind")
    protected String railKind;
    /** Тип рельса. По умолчанию значение "Р65". */
    @Column(name = "rail_type")
    protected String type;
    /** Категория рельса ("В", "Т1", "Т2", "НТ"). */
    @Column(name = "rail_category")
    protected String category;
    /** Вид термоупрочнения рельса ("НЕТЕРМОУПРОЧНЕННЫЙ", "ОБЪЕМНО-ТЕРМОУПРОЧНЕННЫЙ",
     *  "ДИФФЕРЕНЦИАЛЬНО-ТЕРМОУПРОЧНЕННЫЙ"). */
    @Column(name = "thermal_hardening")
    protected String thermalHardening;
    /** Завод-изготовитель рельса ("К" - Кузнецк, "Т" - Тагил, "М" - Муром (для стрелочных рельсов)". */
    @Column(name = "factory")
    protected String factory;
    /** Дата прокатки рельса, месяц в виде римской цифры и год через тире, например "VIII-2020". */
    @Column(name = "rolling_date")
    protected String rollingDate;
    /** Номер плавки рельса, кодовая цифро-буквенная комбинация,
     * характерная для завода и условий производства и позволяющая идентифицировать рельс. */
    @Column(name = "fuse_number")
    protected String fuseNumber;
    /** Дата укладки рельса в путь. */
    @Column(name = "installation_date")
    protected LocalDate installationDate;
    /** Тип установки рельса ("НОВЫЙ", "СЕЗОННАЯ СМЕНА", "СТАРОГОДНЫЙ", "СМЕНА КАНТА"). */
    @Column(name = "installation_type")
    protected String installationType;
    /** Зазор в стыке перед рельсом в миллиметрах. */
    @Column(name = "gap_before")
    protected Integer gapBefore;
    /** Зазор в стыке после рельса в миллиметрах. */
    @Column(name = "gap_after")
    protected Integer gapAfter;
    /** Пропущенный тоннаж по рельсу перед укладкой в путь. */
    @Column(name = "passed_tonnage_before_install")
    protected Float passedTonnageBeforeInstall;
    /** Пропущенный тоннаж по рельсу после укладки в путь. */
    @Column(name = "passed_tonnage_after_install")
    protected Float passedTonnageAfterInstall;
    /** Вертикальный износ рельса в миллиметрах. */
    @Column(name = "vertical_wear")
    protected Integer verticalWear;
    /** Горизонтальный износ рельса в миллиметрах. */
    @Column(name = "horizontal_wear")
    protected Integer horizontalWear;
    /** Дата последнего измерения характеристик рельса. */
    @Column(name = "last_measure_date")
    protected LocalDate lastMeasureDate;
    /** Температура рельса на момент последнего измерения в градусах Цельсия. */
    @Column(name = "last_measure_rail_temp")
    protected Integer lastMeasureRailTemp;
    /** Объект плети, на которой расположен рельс вида "ПЛЕТЬ". Извлекается из БД и присваивается отдельно. */
    @Transient
    protected LashEntity railLash;
    /** Объект стрелочного перевода, на которой расположен рельс вида "СТРЕЛОЧНЫЙ ПЕРЕВОД".
     * Извлекается из БД и присваивается отдельно. */
    @Transient
    protected SwitchEntity railSwitch;

    /** Метод, возвращающий строковое краткое представление сущности рельса.
     *
     * @return строка вида "рельс (длина) м, (сторонность) нить, (вид рельсового элемента)",
     * например "рельс 12.52 м, ЛЕВАЯ нить, УРАВНИТЕЛЬНАЯ РУБКА". */
    @Override
    public String toString() {
        return "рельс " + length + " м, " + lineSide + " нить, " + railKind;
    }

}