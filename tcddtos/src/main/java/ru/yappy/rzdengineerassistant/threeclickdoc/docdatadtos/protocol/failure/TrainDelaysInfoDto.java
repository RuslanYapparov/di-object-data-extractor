package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure;

import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.delay.TrainDelayDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.delay.TrainDelayFullDto;

/**
 * Класс DTO для хранения и передачи полной информации о задержках поездов, вызванных отказом технических средств.
 *
 * @param mainDelay DTO с исчерпывающей информацией об основной задержке
 * @param delays массив DTO с минимальной информацией о всех задержках поездов, допущенных в результате какого-либо
 *               нарушения перевозочного процесса
 */
public record TrainDelaysInfoDto(TrainDelayFullDto mainDelay,
                                 TrainDelayDto[] delays) {}