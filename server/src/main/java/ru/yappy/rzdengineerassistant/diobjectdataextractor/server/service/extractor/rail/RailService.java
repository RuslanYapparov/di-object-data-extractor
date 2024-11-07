package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.RailMiniDto;

import java.util.List;

/**
 * Интерфейс, определяющий тип и поведение всех объектов-сервисов, работающих с сущностями рельсов.
 */
public interface RailService {

    /**
     * Получает из БД и мэппит в DTO информацию о рельсе по его id и признаку типа пути (wayType).
     *
     * @param railId идентификатор рельса в базе данных
     * @param wayType параметр, указывающий на тип пути, на котором лежит рельс
     * @return DTO, содержащий исчерпывающую информацию о рельсе
     */
    RailDto getFullRailInfoByIdAndWayType(Long railId, WayType wayType);

    /**
     * Получает из БД и мэппит в DTO минимальную информацию о рельсах, находящихся вблизи определенного места, заданного
     * объектом {@link LocationDto}
     *
     * @param location объект, содержащий информацию о месте, вблизи которого будет выгружена информация о рельсах
     * @return список мини-DTO, содержащих минимальную информацию о рельсах, находящихся вблизи заданного места
     */
    List<RailMiniDto> getRailMiniInfoVariantsByLocationDto(LocationDto location);

}
