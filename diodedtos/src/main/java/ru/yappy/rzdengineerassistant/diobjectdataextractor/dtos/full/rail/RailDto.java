package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.SwitchDto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Абстрактный класс, содержащий поля, характерные для всех DTO сущностей рельсов (основная информация о рельсе,
 * не касающаяся его размещения).
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "wayType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MainWayRailDto.class, name = "ГЛАВ"),
        @JsonSubTypes.Type(value = StationWayRailDto.class, name = "СТАНЦ")
})
public abstract class RailDto {
    /** Идентификатор рельса. */
    private final Long id;
    /** Длина рельса. */
    private final Float length;
    /** Сторонность рельсовой нити, на которой лежит рельс. */
    private final String lineSide;
    /** Тип верхнего строения пути, к которому относится рельс. */
    private final String railKind;
    /** Тип рельса. */
    private final String type;
    /** Категория рельса. */
    private final String category;
    /** Вид термоупрочнения рельса. */
    private final String thermalHardening;
    /** Завод изготовитель рельса. */
    private final String factory;
    /** Дата прокатки рельса на заводе. */
    private final String rollingDate;
    /** Номер плавки рельса. */
    private final String fuseNumber;
    /** Дата укладки рельса в путь. */
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDate installationDate;
    /** Тип укладки рельса в путь. */
    private final String installationType;
    /** Стыковой зазор в переднем торце рельса. */
    private final Integer gapBefore;
    /** Стыковой зазор в заднем торце рельса. */
    private final Integer gapAfter;
    /** Пропущенный тоннаж до укладки рельса в путь. */
    private final Float passedTonnageBeforeInstall;
    /** Пропущенный тоннаж после укладки рельса в путь. */
    private final Float passedTonnageAfterInstall;
    /** Вертикальный износ рельса. */
    private final Integer verticalWear;
    /** Горизонтальный износ рельса. */
    private final Integer horizontalWear;
    /** Дата последней проверки характеристик рельса. */
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDate lastMeasureDate;
    /** Температура рельса при последней проверке характеристик. */
    private final Integer lastMeasureRailTemp;
    /** Опциональное поле, содержащее DTO плети для рельса вваренного в соответствующую рельсовую плеть. */
    private final LashDto railLash;
    /** Опциональное поле, содержащее DTO стрелочного перевода для рельса, расположенного на стрелочном переводе. */
    private final SwitchDto railSwitch;

    /**
     * Конструктор суперкласса, принимающий в качестве параметров значения всех полей.
     *
     * @param id идентификатор рельса
     * @param length длина рельса
     * @param lineSide сторонность рельсовой нити, на которой лежит рельс
     * @param railKind тип верхнего строения пути, к которому относится рельс
     * @param type тип рельса
     * @param category категория рельса
     * @param thermalHardening вид термоупрочнения рельса
     * @param factory завод-изготовитель рельса
     * @param rollingDate дата прокатки рельса на заводе
     * @param fuseNumber номер плавки рельса
     * @param installationDate дата укладки рельса в путь
     * @param installationType тип укладки рельса в путь
     * @param gapBefore стыковой зазор в переднем торце рельса
     * @param gapAfter стыковой зазор в заднем торце рельса
     * @param passedTonnageBeforeInstall пропущенный тоннаж до укладки рельса в путь
     * @param passedTonnageAfterInstall пропущенный тоннаж после укладки рельса в путь
     * @param verticalWear вертикальный износ рельса
     * @param horizontalWear горизонтальный износ рельса
     * @param lastMeasureDate дата последней проверки характеристик рельса
     * @param lastMeasureRailTemp температура рельса при последней проверке характеристик
     * @param railLash объект {@link LashDto}, если рельс с типом ВСП "ПЛЕТЬ"
     * @param railSwitch объект {@link SwitchDto}, если рельс с типом ВСП "СТРЕЛОЧНЫЙ ПЕРЕВОД"
     */
    public RailDto(Long id,
                   Float length,
                   String lineSide,
                   String railKind,
                   String type,
                   String category,
                   String thermalHardening,
                   String factory,
                   String rollingDate,
                   String fuseNumber,
                   LocalDate installationDate,
                   String installationType,
                   Integer gapBefore,
                   Integer gapAfter,
                   Float passedTonnageBeforeInstall,
                   Float passedTonnageAfterInstall,
                   Integer verticalWear,
                   Integer horizontalWear,
                   LocalDate lastMeasureDate,
                   Integer lastMeasureRailTemp,
                   LashDto railLash,
                   SwitchDto railSwitch) {
        this.id = id;
        this.length = length;
        this.lineSide = lineSide;
        this.railKind = railKind;
        this.type = type;
        this.category = category;
        this.thermalHardening = thermalHardening;
        this.factory = factory;
        this.rollingDate = rollingDate;
        this.fuseNumber = fuseNumber;
        this.installationDate = installationDate;
        this.installationType = installationType;
        this.gapBefore = gapBefore;
        this.gapAfter = gapAfter;
        this.passedTonnageBeforeInstall = passedTonnageBeforeInstall;
        this.passedTonnageAfterInstall = passedTonnageAfterInstall;
        this.verticalWear = verticalWear;
        this.horizontalWear = horizontalWear;
        this.lastMeasureDate = lastMeasureDate;
        this.lastMeasureRailTemp = lastMeasureRailTemp;
        this.railSwitch = railSwitch;
        this.railLash = railLash;
    }

    /**
     * Геттер идентификатора рельса.
     *
     * @return идентификатор рельса
     */
    public Long getId() {
        return id;
    }

    /**
     * Геттер длины рельса.
     *
     * @return длина рельса
     */
    public Float getLength() {
        return length;
    }

    /**
     * Геттер сторонности рельсовой нити, на которой лежит рельс.
     *
     * @return сторона рельсовой нити
     */
    public String getLineSide() {
        return lineSide;
    }

    /**
     * Геттер типа верхнего строения пути, к которому относится рельс.
     *
     * @return тип верхнего строения пути
     */
    public String getRailKind() {
        return railKind;
    }

    /**
     * Геттер типа рельса.
     *
     * @return тип рельса
     */
    public String getType() {
        return type;
    }

    /**
     * Геттер категории рельса.
     *
     * @return категория рельса
     */
    public String getCategory() {
        return category;
    }

    /**
     * Геттер вида термоупрочнения рельса.
     *
     * @return вид термоупрочнения рельса
     */
    public String getThermalHardening() {
        return thermalHardening;
    }

    /**
     * Геттер завода-изготовителя рельса.
     *
     * @return обозначение завода-изготовителя рельса
     */
    public String getFactory() {
        return factory;
    }

    /**
     * Геттер даты прокатки рельса на заводе.
     *
     * @return строка с обозначением даты прокатки рельса на заводе (месяц описывается римской цифрой)
     */
    public String getRollingDate() {
        return rollingDate;
    }

    /**
     * Геттер номера плавки рельса.
     *
     * @return номер плавки рельса
     */
    public String getFuseNumber() {
        return fuseNumber;
    }

    /**
     * Геттер даты укладки рельса в путь.
     *
     * @return дата укладки рельса в путь
     */
    public LocalDate getInstallationDate() {
        return installationDate;
    }

    /**
     * Геттер типа укладки рельса в путь.
     *
     * @return тип укладки рельса в путь
     */
    public String getInstallationType() {
        return installationType;
    }

    /**
     * Геттер значения величины стыкового зазора в переднем торце рельса.
     *
     * @return значение стыкового зазора в переднем торце рельса
     */
    public Integer getGapBefore() {
        return gapBefore;
    }

    /**
     * Геттер значения величины стыкового зазора в заднем торце рельса.
     *
     * @return значение стыкового зазора в заднем торце рельса
     */
    public Integer getGapAfter() {
        return gapAfter;
    }

    /**
     * Геттер значения пропущенного тоннажа до укладки рельса в путь.
     *
     * @return значение пропущенного тоннажа до укладки рельса в путь
     */
    public Float getPassedTonnageBeforeInstall() {
        return passedTonnageBeforeInstall;
    }

    /**
     * Геттер значения пропущенного тоннажа после укладки рельса в путь.
     *
     * @return значение пропущенного тоннажа после укладки рельса в путь
     */
    public Float getPassedTonnageAfterInstall() {
        return passedTonnageAfterInstall;
    }

    /**
     * Геттер значения вертикального износа рельса.
     *
     * @return значение вертикального износа рельса
     */
    public Integer getVerticalWear() {
        return verticalWear;
    }

    /**
     * Геттер значения горизонтального износа рельса.
     *
     * @return значение горизонтального износа рельса
     */
    public Integer getHorizontalWear() {
        return horizontalWear;
    }

    /**
     * Геттер даты последнего измерения характеристик рельса.
     *
     * @return дата последнего измерения характеристик рельса
     */
    public LocalDate getLastMeasureDate() {
        return lastMeasureDate;
    }

    /**
     * Геттер значения температуры рельса на последнем измерении характеристик рельса.
     *
     * @return зрначение температуры рельса на последнем измерении характеристик рельса
     */
    public Integer getLastMeasureRailTemp() {
        return lastMeasureRailTemp;
    }

    /**
     * Геттер DTO рельсовой плети для сущности рельса, вваренного в рельсвого плеть.
     *
     * @return DTO рельсовой плети
     */
    public LashDto getRailLash() {
        return railLash;
    }

    /**
     * Геттер DTO стрелочного перевода для сущности рельса, лежащем на стрелочном переводе.
     *
     * @return DTO стрелочного перевода
     */
    public SwitchDto getRailSwitch() {
        return railSwitch;
    }

    /** Сравнение двух объектов, используя их состояние (значения всех полей).
     *
     * @param o другой объект для сравнения
     * @return булевое значение, определяющее равенство объектов
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RailDto railDto)) return false;
        return Objects.equals(id, railDto.id) &&
                Objects.equals(length, railDto.length) &&
                Objects.equals(lineSide, railDto.lineSide) &&
                Objects.equals(railKind, railDto.railKind) &&
                Objects.equals(type, railDto.type) &&
                Objects.equals(category, railDto.category) &&
                Objects.equals(thermalHardening, railDto.thermalHardening) &&
                Objects.equals(factory, railDto.factory) &&
                Objects.equals(rollingDate, railDto.rollingDate) &&
                Objects.equals(fuseNumber, railDto.fuseNumber) &&
                Objects.equals(installationDate, railDto.installationDate) &&
                Objects.equals(installationType, railDto.installationType) &&
                Objects.equals(gapBefore, railDto.gapBefore) &&
                Objects.equals(gapAfter, railDto.gapAfter) &&
                Objects.equals(passedTonnageBeforeInstall, railDto.passedTonnageBeforeInstall) &&
                Objects.equals(passedTonnageAfterInstall, railDto.passedTonnageAfterInstall) &&
                Objects.equals(verticalWear, railDto.verticalWear) &&
                Objects.equals(horizontalWear, railDto.horizontalWear) &&
                Objects.equals(lastMeasureDate, railDto.lastMeasureDate) &&
                Objects.equals(lastMeasureRailTemp, railDto.lastMeasureRailTemp) &&
                Objects.equals(railLash, railDto.railLash) &&
                Objects.equals(railSwitch, railDto.railSwitch);
    }

    /** Получение хэш-кода объекта.
     *
     * @return числовое представление объекта на основании его состояния (значений всех полей)
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, length, lineSide, railKind, type, category, thermalHardening, factory, rollingDate,
                fuseNumber, installationDate, installationType, gapBefore, gapAfter, passedTonnageBeforeInstall,
                passedTonnageAfterInstall, verticalWear, horizontalWear, lastMeasureDate, lastMeasureRailTemp,
                railLash, railSwitch);
    }

}