package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.SwitchMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.SwitchEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.RailEntity;

import java.util.Set;

/**
 * Интерфейс, определяющий тип и поведение всех объектов-сервисов, работающих с сущностями стрелочных переводов.
 */
public interface SwitchService {

    /**
     * Получает из БД и мэппит в DTO информацию о стрелочном переводе по его идентификатору.
     *
     * @param switchId идентификатор стрелочного перевода в базе данных
     * @return DTO, содержащий полную информацию о стрелочном переводе
     */
    SwitchDto getFullSwitchInfoByIdAndWayType(Long switchId, WayType wayType);

    /**
     * Получает из БД и мэппит в сет DTO информацию о стрелочных переводах, расположенных вблизи места, определенного
     * в {@link LocationDto}, переданном в парметре к методу.
     *
     * @param location объект, описывающий конкретное место, относительно которого будет произведен поиск подходящих
     *                стрелочных переводов
     * @return сет из DTO, содержащих минимально необходимую информацию о стрелочных переводах
     */
    Set<SwitchMiniDto> getSwitchMiniDtoVariantsByLocationDto(LocationDto location);

    /**
     * Получает из БД entity-объект с полной информацией о стрелочном переводе, в границах которого находится
     * переданный в параметре entity-объект рельса.
     *
     * @param railEntity entity-объект рельса, относительно которого будет производится поиск нужной информации о
     *                   стрелочном переводе
     * @param wayType тип пути, значение участвует в логике поиска информации о стрелочном переводе
     * @return entity-объект с полной информацией о стрелочном переводе
     */
    SwitchEntity getSwitchEntityByRailEntityAndWayType(RailEntity railEntity, WayType wayType);

}