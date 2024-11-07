package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.culprit.CulpritDto;

/**
 * Класс DTO, состоящего из отдельных DTO элементов для хранения и передачи полной информации об отказе в работе
 * технических средств.
 *
 * @param basis основная информация об отказе
 * @param recovery информация об оповещении и восстановлении после отказа
 * @param reasoning информация о расследовании и заключениях о причинах отказа
 * @param trainDelays информация о задержках поездов, вызванных отказом технических средств
 * @param culprits работники, виновные в возникновении отказа
 */
public record ComplexFailureInfoDto(FailureBasicInfoDto basis,
                                    FailureRecoveryInfoDto recovery,
                                    FailureReasoningInfoDto reasoning,
                                    TrainDelaysInfoDto trainDelays,
                                    CulpritDto[] culprits) {}