package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl.EmployeeDto;

import java.time.LocalDateTime;

/**
 * Класс DTO, содержащий информацию об оповещении об отказе и восстановлении работоспособности технических средств.
 *
 * @param notifier кто сообщил о нарушении нормальной работы технических средств
 * @param notifyDateTime время поступления оповещения об отказе
 * @param whoNotified кто был оповещен об отказе
 * @param employeeOnPlace работник, прибывший на место отказа для восстановления работоспособности
 * @param arrivedAt время прибытия работника на место отказа
 * @param causeFoundAt время обнаружения причины отказа
 * @param recoveryMethod способ устранения неисправности, вызвавшей отказ
 * @param investigationParticipants список работников-руководителей, участвующих в расследовании отказа
 * @param estimatedMinutes расчетное время устранения отказа в минутах
 */
public record FailureRecoveryInfoDto(String notifier,
                                     @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
                                     LocalDateTime notifyDateTime,
                                     String whoNotified,
                                     EmployeeDto employeeOnPlace,
                                     @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
                                     LocalDateTime arrivedAt,
                                     @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
                                     LocalDateTime causeFoundAt,
                                     String recoveryMethod,
                                     String investigationParticipants,
                                     Integer estimatedMinutes) {}