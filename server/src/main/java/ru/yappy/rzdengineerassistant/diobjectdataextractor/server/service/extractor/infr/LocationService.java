package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto;

import java.util.Set;

/**
 * Интерфейс, определяющий тип и поведение всех объектов-сервисов, работающих с сущностями локаций типа станции,
 * перегоны, станционные пути.
 */
public interface LocationService {

    /**
     * Получает из БД и преобразует в обозначение типа "СТАНЦИЯ1 - СТАНЦИЯ2" перегона по данным конкретного места,
     * введенным пользователем.
     *
     * @param locationDto объект, описывающий конкретное место, относительно которого будет произведен поиск
     *                    обозначения перегона
     * @return строковое обозначение перегона, либо пустая строка, если название перегона не найдено
     */
    String getInterstationTrackNameByLocationDto(LocationDto locationDto);

    /**
     * Получает из БД и преобразует в строковое обозначение название станции по данным объекта конкретного места.
     *
     * @param locationDto объект, описывающий конкретное место, относительно которого будет произведен поиск
     *                    обозначения станции
     * @return строковое обозначение станции, либо пустая строка, если название станции не найдено
     */
    String getStationNameByLocationDto(LocationDto locationDto);

    /**
     * Проверяет, существует ли локация в соответствии с данным конкретного места.
     *
     * @param locationDto объект, описывающий конкретное место, относительно которого будет проведена проверка
     * @return true, если сервис может получить название локации по данным конкретного места, иначе false
     */
    boolean canGetLocationProjectionByLocationDto(LocationDto locationDto);

    /**
     * Получает из БД и преобразует в сет минимальных DTO сущности станционных путей, находящихся на станции с
     * переданным идентификатором.
     *
     * @param stationId идентификатор станции
     * @return сет DTO с минимальной информацией о станционных путях
     */
    Set<StationWayMiniDto> getStationWaysMiniDtoByStationId(Long stationId);

}