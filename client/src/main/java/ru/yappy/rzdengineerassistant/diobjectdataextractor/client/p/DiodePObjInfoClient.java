package ru.yappy.rzdengineerassistant.diobjectdataextractor.client.p;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.*;

/**
 * Интерфейс, определяющий тип и поведение всех объектов-клиентов, запрашивающих у сервера DiObjectDataExtractor
 * данные для создания документов-характеристик объектов хозяйства пути.
 */
public interface DiodePObjInfoClient {

    /**
     * Отправляет запрос и обрабатывает ответ от сервера с информацией о рельсе по его id и признаку типа пути (wayType)
     * для создания характеристики рельса или места излома.
     *
     * @param railId идентификатор рельса
     * @param wayType признак типа пути
     * @return DTO c информацией о рельсе или об ошибке (поле locationDescription) объекта {@link RailInfoDto})
     */
    RailInfoDto getRailInfoDtoByIdAndWayType(Long railId, WayType wayType);

    /**
     * Отправляет запрос и обрабатывает ответ от сервера с информацией о рельсовом стыке определенного типа по его id,
     * признаку типа пути (wayType) и признаку типа стыка (jointType) для создания характеристики рельсового стыка.
     *
     * @param jointId идентификатор рельсового стыка в базе данных
     * @param wayType признак типа пути
     * @param jointType признак типа стыка
     * @return DTO с информацией о рельсовом стыке или об ошибке (поле locationDescription) объекта {@link JointInfoDto})
     */
    JointInfoDto getJointInfoDtoByIdAndWayTypeAndJointType(Long jointId, WayType wayType, JointType jointType);

}