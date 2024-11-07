package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import org.mapstruct.Mapper;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.MainWaySectionDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationWaySectionDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.TransportDirectionDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationNameDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWaySectionEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationWaySectionEntity;

/**
 * Интерфейс-мэппер для сущностей участков главных или станционных путей, содержит готовые реализации методов.
 */
@Mapper(componentModel = "spring", uses = { StationWayMiniMapper.class })
interface WaySectionMapper extends DiObjectEntityToDtoMapper<MainWaySectionEntity, MainWaySectionDto> {

    /**
     * Реализованный по умолчанию переопределенный метод суперинтерфейса {@link DiObjectEntityToDtoMapper},
     * описывающий логику мэппинга сущностей участков главного пути в DTO.
     *
     * @param section объект-entity участка главного пути, полученный из SpringJpa-репозитотрия
     * @return DTO участка главного пути
     */
    @Override
    default MainWaySectionDto toDto(MainWaySectionEntity section) {
        Long transportDirectionId = section.getStartKm().getTransportDirection().getId();
        String transportDirectionName = section.getStartKm().getTransportDirection().getName();
        Integer startKm = section.getStartKm().getKm();
        Integer startMeter = section.getStartMeter();
        Integer endKm = section.getEndKm();
        Integer endMeter = section.getEndMeter();

        return new MainWaySectionDto(
                new TransportDirectionDto(transportDirectionId, transportDirectionName),
                startKm,
                startMeter,
                endKm,
                endMeter
        );
    }

    /**
     * Реализованный по умолчанию метод мэппинга сущностей участков станционного пути в DTO.
     *
     * @param section объект-entity участка станционного пути, полученный из SpringJpa-репозитотрия
     * @return DTO участка станционного пути
     */
    default StationWaySectionDto toDto(StationWaySectionEntity section) {
        Long stationId = section.getStationWay().getStation().getId();
        String stationName = section.getStationWay().getStation().getName();
        Long stationWayId = section.getStationWay().getId();
        String stationWayName = section.getStationWay().getName();
        Integer startMeter = section.getStartMeter();
        Integer endMeter = section.getEndMeter();

        return new StationWaySectionDto(
                new StationWayMiniDto(stationWayId,
                        stationWayName,
                        new StationNameDto(stationId,
                                stationName)),
                startMeter,
                endMeter
        );
    }

}