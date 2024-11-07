package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.handler;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.mini.JointFailureProtCreatingDataDto;

/**
 * Интерфейс, определяющий тип и поведение всех сервисов, обрабатывающих поступающие в контроллер запросы
 * на получение информации для создания протоколов отказов технических средств путевого хозяйства.
 */
public interface DiodePProtFailureHandler {

    /**
     * Обрабатывает запрос на получение информации, необходимой для создания протокола отказа из-за неисправности
     * рельсового стыка.
     *
     * @param location DTO с данными о конкретном месте, введенными пользователем в UI
     * @return DTO с вариантами для выбора работников дистанции пути, вариантов рельсовых стыков и описанием локации
     */
    JointFailureProtCreatingDataDto getJointFailureProtCreatingDataDtoByLocationDto(LocationDto location, JointType jointType);

}