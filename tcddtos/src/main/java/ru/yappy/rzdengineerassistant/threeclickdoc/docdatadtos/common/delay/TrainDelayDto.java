package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.delay;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Класс DTO для хранения и передачи минимальной информации о задержке поезда.
 *
 * @param trainType тип поезда (пассажирский, пригородный, грузовой, хозяйственный и т.д.)
 * @param trainNumber номер поезда
 * @param delayStart начало задержки
 * @param delayEnd конец задержки
 */
public record TrainDelayDto(TrainType trainType,
                            String trainNumber,
                            @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
                            LocalDateTime delayStart,
                            @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
                            LocalDateTime delayEnd) {}