package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Класс DTO для хранения и передачи базовой информации об отказе (основная информация из приложения КАСАНТ).
 *
 * @param kasantNumber номер отказа в приложении КАСАНТ
 * @param category категория отказа
 * @param failureDateTime дата и время начала отказа
 * @param recoveryDateTime дата и время окончания (восстановления после) отказа
 * @param character характер отказа
 * @param characterSubCause подпричина характера отказа
 * @param appearance вариант перечисления, отражающий как проявился отказ
 * @param appearanceInfo дополнительная информация, касающаяся проявления отказа
 */
public record FailureBasicInfoDto(Long kasantNumber,
                                  Integer category,
                                  @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
                                  LocalDateTime failureDateTime,
                                  @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
                                  LocalDateTime recoveryDateTime,
                                  FailureCharacter character,
                                  String characterSubCause,
                                  FailureAppearance appearance,
                                  String appearanceInfo) {}