package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.wayc;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.wayc.WayCharacteristicsDto;

/** Интерфейс, определяющий тип и поведение всех объектов-сервисов, работающих с сущностями характеристик пути. */
public interface WayCharacteristicService {

    /** Получает из БД и мэппит в DTO характеристики пути по данным конкретного места, введенным пользователем. */
    WayCharacteristicsDto getWayCharacteristicsByLocationDto(LocationDto locationDto);

}