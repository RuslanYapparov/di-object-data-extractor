package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import org.mapstruct.Mapper;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.MainWayPlaceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationWayPlaceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.TransportDirectionDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationNameDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWayPlaceEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationWayPlaceEntity;

/**
 * Интерфейс-мэппер для сущностей конкретных мест главного или станционного пути, содержит готовые реализации методов.
 */
@Mapper(componentModel = "spring", uses = { StationWayMiniMapper.class })
interface WayPlaceMapper extends DiObjectEntityToDtoMapper<MainWayPlaceEntity, MainWayPlaceDto> {

    /**
     * Реализованный по умолчанию переопределенный метод суперинтерфейса {@link DiObjectEntityToDtoMapper},
     * описывающий логику мэппинга сущностей конкретных мест главного пути в DTO.
     *
     * @param place объект-entity конкретного места главного пути, полученный из SpringJpa-репозитотрия
     * @return DTO конкретного места главного пути
     */
    @Override
    default MainWayPlaceDto toDto(MainWayPlaceEntity place) {
        Long transportDirectionId = place.getKm().getTransportDirection().getId();
        String transportDirectionName = place.getKm().getTransportDirection().getName();
        Integer km = place.getKm().getKm();
        Integer meter = place.getMeter();

        return new MainWayPlaceDto(
                new TransportDirectionDto(transportDirectionId, transportDirectionName),
                km,
                meter
        );
    }

    /**
     * Реализованный по умолчанию метод мэппинга сущностей конкретных мест станционного пути в DTO.
     *
     * @param place объект-entity конкретного места станционного пути, полученный из SpringJpa-репозитотрия
     * @return DTO конкретного места станционного пути
     */
    default StationWayPlaceDto toDto(StationWayPlaceEntity place) {
        Long stationId = place.getStationWay().getStation().getId();
        String stationName = place.getStationWay().getStation().getName();
        Long stationWayId = place.getStationWay().getId();
        String stationWayName = place.getStationWay().getName();
        Integer placeMeter = place.getPlaceMeter();

        return new StationWayPlaceDto(
                new StationWayMiniDto(stationWayId,
                        stationWayName,
                        new StationNameDto(stationId,
                                stationName)),
                placeMeter
        );
    }

}