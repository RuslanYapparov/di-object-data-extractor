package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import org.mapstruct.Mapper;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationNameDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.AbstractStationWayEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.infr.StationWayProjection;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Интерфейс-мэппер для сущностей станционных путей, на основании которого Mapstruct создает реализацию.
 */
@Mapper(componentModel = "spring")
public interface StationWayMiniMapper extends DiObjectEntityToDtoMapper<AbstractStationWayEntity, StationWayMiniDto> {

    /**
     * Переопределенная реализация метода суперинтерфейса {@link DiObjectEntityToDtoMapper}, описывающий логику
     * мэппинга сущностей станционных путей в DTO.
     *
     * @param stationWay объект-entity станционного пути, полученный из SpringJpa-репозитотрия
     * @return мини-DTO с минимальной информацией о станционном пути
     */
    @Override
    default StationWayMiniDto toDto(AbstractStationWayEntity stationWay) {
        Long stationId = stationWay.getStation().getId();
        Long stationWayId = stationWay.getId();
        String stationName = stationWay.getStation().getName();
        String stationWayName = stationWay.getName();

        return new StationWayMiniDto(stationWayId,
                stationWayName,
                new StationNameDto(stationId,
                        stationName));
    }

    /**
     * Перегруженный дефолтный метод мэппинга сета проекций сущностей станционных путей в сет DTO.
     *
     * @param stationWays сет проекций сущностей станционных путей
     * @return сет DTO с минимальной информацией о станционных путях
     */
    default Set<StationWayMiniDto> toDto(Set<StationWayProjection> stationWays) {
        return stationWays.stream()
                .map(swproj -> new StationWayMiniDto(swproj.getId(),
                        swproj.getName(),
                        new StationNameDto(
                                swproj.getStation().getId(),
                                swproj.getStation().getName()
                        ))
                )
                .collect(Collectors.toSet());
    }

}