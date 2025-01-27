package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.MainWaySectionDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.SwitchDto;

import java.time.LocalDate;

/**
 * Класс DTO сущности рельса, лежащего в главном пути железнодорожного направления.
 */
public class MainWayRailDto extends RailDto {
    /** Номер главного пути. */
    private final Integer mainWayNumber;
    /** Участок главного пути железнодорожного направления. */
    private final MainWaySectionDto mainWaySection;

    /**
     * Конструктор класса DTO сущности рельса, лежащего в главном пути, принимающмй параметрами занчения всех полей.
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
     * @param mainWaySection участок главного пути, обозначающий лежащий в нем рельс
     * @param mainWayNumber номер главного пути
     */
    public MainWayRailDto(Long id,
                          Float length,
                          String lineSide,
                          String railKind,
                          String type,
                          String category,
                          String thermalHardening,
                          String factory,
                          String rollingDate,
                          String fuseNumber,
                          @JsonFormat(pattern = "dd-MM-yyyy")
                          LocalDate installationDate,
                          String installationType,
                          Integer gapBefore,
                          Integer gapAfter,
                          Float passedTonnageBeforeInstall,
                          Float passedTonnageAfterInstall,
                          Integer verticalWear,
                          Integer horizontalWear,
                          @JsonFormat(pattern = "dd-MM-yyyy")
                          LocalDate lastMeasureDate,
                          Integer lastMeasureRailTemp,
                          LashDto railLash,
                          SwitchDto railSwitch,
                          Integer mainWayNumber,
                          MainWaySectionDto mainWaySection) {
        super(id, length, lineSide, railKind, type, category, thermalHardening, factory, rollingDate, fuseNumber,
                installationDate, installationType, gapBefore, gapAfter, passedTonnageBeforeInstall,
                passedTonnageAfterInstall, verticalWear, horizontalWear, lastMeasureDate, lastMeasureRailTemp,
                railLash, railSwitch);
        this.mainWayNumber = mainWayNumber;
        this.mainWaySection = mainWaySection;
    }

    /**
     * Геттер номера главного пути.
     *
     * @return номер главного пути
     */
    public Integer getMainWayNumber() {
        return mainWayNumber;
    }

    /**
     * Геттер участка главного пути, определяющий лежащий в нем рельс.
     *
     * @return объект {@link MainWaySectionDto} участка главного пути
     */
    public MainWaySectionDto getMainWaySection() {
        return mainWaySection;
    }

}