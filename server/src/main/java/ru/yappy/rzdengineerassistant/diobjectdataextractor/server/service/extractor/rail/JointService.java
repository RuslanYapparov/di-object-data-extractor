package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto;

import java.util.List;

/**
 * Интерфейс, определяющий тип и поведение всех объектов-сервисов, работающих с сущностями рельсовых стыков.
 */
public interface JointService {

    /**
     * Получает из БД и мэппит в DTO информацию о рельсовом стыке по его id и признаку типа пути (wayType).
     *
     * @param jointId идентификатор рельсового стыка в базе данных
     * @param wayType параметр, указывающий на тип пути, на котором лежит рельсовый стык
     * @param jointType параметр, указывающий на тип рельсового стыка
     * @return DTO, содержащий исчерпывающую информацию о рельсовом стыке
     */
    JointDto getFullJointInfo(Long jointId, WayType wayType, JointType jointType);

    /**
     * Получает из БД и мэппит в список DTO минимальную информацию о рельсовых стыках определенного типа, находящихся
     * вблизи определенного места, заданного объектом {@link LocationDto}.
     *
     * @param location объект, содержащий информацию о месте, вблизи которого будет выгружена информация о стыках
     * @param jointType тип стыков, сущности которых будут извлечены из БД
     * @return список мини-DTO, содержащих минимальную информацию о рельсовых стыках, находящихся вблизи заданного места
     */
    List<JointMiniDto> getJointMiniInfoVariantsByLocationDto(LocationDto location, JointType jointType);

}
