package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.handler;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.*;

/**
 * Интерфейс, определяющий тип и поведение всех сервисов, обрабатывающих поступающие в контроллер запросы
 * на получение информации для создания документов-характеристик объектов путевого хозяйства.
 */
public interface DiodePObjInfoHandler {

    /**
     * Обрабатывает запрос на получение необходимой информации для создания характеристики рельса по его идентификатору.
     *
     * @param railId идентификатор рельса в базе данных, для которого нужно получить информацию
     * @param wayType тип пути
     * @return DTO-объект для создания характеристики рельса
     */
    RailInfoDto handleRailInfoRequest(Long railId, WayType wayType);

    /**
     * Обрабатывает запрос на получение необходимой информации для создания характеристики рельсового стыка
     * по его идентификатору, типу (jointType) и типу пути (wayType).
     *
     * @param jointId идентификатор рельсового стыка в базе данных, для которого нужно получить информацию
     * @param wayType тип пути
     * @param jointType тип рельсового стыка
     * @return DTO-объект с необходимой информацией для создания характеристики рельсового стыка
     */
    JointInfoDto handleJointInfoRequest(Long jointId, WayType wayType, JointType jointType);

}