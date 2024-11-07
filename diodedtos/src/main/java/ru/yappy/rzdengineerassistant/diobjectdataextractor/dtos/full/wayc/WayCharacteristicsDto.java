package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.wayc;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * Класс DTO, содержащий информацию о характеристиках пути.
 *
 * @param slope уклон
 * @param straight флаг, определяющий, прямой участок или кривая
 * @param curveSide сторонность кривой (null для прямого участка)
 * @param radius радиус кривой (null для прямого участка)
 * @param railElevation возвышение рельса (null для прямого участка)
 * @param passengerTrainSpeed скорость движения пассажирских поездов
 * @param freightTrainSpeed скорость движения грузовых поездов
 * @param passedTonnageBeforeInstall пропущенный тоннаж до укладки
 * @param passedTonnageAfterInstall пропущенный тоннаж после укладки
 * @param freightIntensity грузонапряженность
 * @param lineClassSpecialization класс и специализация линии
 * @param groupClassCode код группы пути
 * @param railType тип рельсов
 * @param railCategory категория рельсов
 * @param thermalHardening тип термоупрочнения рельсов
 * @param wayType тип пути
 * @param factory завод-изготовитель рельсов
 * @param factoryYear год производства рельсов
 * @param installationDate дата укладки рельсов в путь
 * @param sleeperMaterial материал шпал
 * @param sleepersPerKm количество шпал на км
 * @param fasteningType тип рельсовых скреплений
 * @param ballastType тип балласта
 * @param ballastHeight высота балласта
 * @param capitalRepairDate год (и месяц) капитального ремонта
 * @param capitalRepairType тип капитального ремонта
 * @param intermediateRepairDate год (и месяц) промежуточного ремонта
 * @param intermediateRepairType тип промежуточного ремонта
 */
public record WayCharacteristicsDto(Float slope,
                                    Boolean straight,
                                    String curveSide,
                                    Integer radius,
                                    Integer railElevation,
                                    Integer passengerTrainSpeed,
                                    Integer freightTrainSpeed,
                                    Float passedTonnageBeforeInstall,
                                    Float passedTonnageAfterInstall,
                                    Float freightIntensity,
                                    String lineClassSpecialization,
                                    String groupClassCode,
                                    String railType,
                                    String railCategory,
                                    String thermalHardening,
                                    String wayType,
                                    String factory,
                                    String factoryYear,
                                    @JsonFormat(pattern = "dd-MM-yyyy")
                                    LocalDate installationDate,
                                    String sleeperMaterial,
                                    Integer sleepersPerKm,
                                    String fasteningType,
                                    String ballastType,
                                    Integer ballastHeight,
                                    @JsonFormat(pattern = "dd-MM-yyyy")
                                    LocalDate capitalRepairDate,
                                    String capitalRepairType,
                                    @JsonFormat(pattern = "dd-MM-yyyy")
                                    LocalDate intermediateRepairDate,
                                    String intermediateRepairType) {}