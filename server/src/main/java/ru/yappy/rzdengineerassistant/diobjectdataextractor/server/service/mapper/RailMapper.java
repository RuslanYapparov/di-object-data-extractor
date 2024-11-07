package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import org.mapstruct.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.RailMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.rail.*;

import java.util.List;

/**
 * Интерфейс-мэппер для сущностей рельсов, на основании которого Mapstruct создает реализацию.
 */
@Mapper(componentModel = "spring", uses = {
        WaySectionMapper.class,
        LashMapper.class,
        SwitchMapper.class })
public interface RailMapper extends DiObjectEntityToDtoMapper<RailEntity, RailDto> {

    /**
     * Переопределенный метод суперинтерфейса {@link DiObjectEntityToDtoMapper}, описывающий логику мэппинга сущностей
     * рельсов в DTO.
     *
     * @param railEntity объект-entity рельса (главного или станционного пути), полученный из SpringJpa-репозитория
     * @return DTO рельса (главного или станционного пути)
     */
    @Override
    default RailDto toDto(RailEntity railEntity) {
        return switch (railEntity) {
            case null -> null;
            case MainWayRailEntity mainWayRailEntity -> mapMainWayRailEntityToDto(mainWayRailEntity);
            case StationWayRailEntity stationWayRailEntity -> mapStationWayRailEntityToDto(stationWayRailEntity);
            default -> throw new IllegalStateException("Не реализована логика мэппинга для типа рельса '" +
                    railEntity.getClass().getSimpleName() + "' в DTO");
        };
    }

    /**
     * Дополнительный перегруженный основной метод мэппинга, преобразует entity-объект рельса главного пути в DTO.
     *
     * @param mainWayRailEntity объект-entity рельса главного пути, полученный из SpringJpa-репозитория
     * @return DTO рельса главного пути
     */
    @Mapping(target = "mainWayNumber", source = "mainWay.number")
    MainWayRailDto mapMainWayRailEntityToDto(MainWayRailEntity mainWayRailEntity);

    /**
     * Дополнительный перегруженный основной метод мэппинга, преобразует entity-объект рельса станционного пути в DTO.
     *
     * @param stationWayRailEntity объект-entity рельса станционного пути, полученный из SpringJpa-репозитория
     * @return DTO рельса станционного пути
     */
    StationWayRailDto mapStationWayRailEntityToDto(StationWayRailEntity stationWayRailEntity);

    /**
     * Преобразует список объектов-проекций рельсов главного пути в список DTO рельсов главного пути.
     *
     * @param railProjections список объектов-проекций рельсов главного пути, полученный из SpringJpa-репозитория
     * @return список мини-DTO рельсов
     */
    List<RailMiniDto> toListOfMainWayRailMiniDto(List<MainWayRailProjection> railProjections);

    /**
     * Преобразует список объектов-проекций рельсов станционного пути в список DTO рельсов станционного пути.
     *
     * @param railProjections список объектов-проекций рельсов станционного пути, полученный из SpringJpa-репозитория
     * @return список мини-DTO рельсов
     */
    List<RailMiniDto> toListOfStationWayRailMiniDto(List<StationWayRailProjection> railProjections);

    /**
     * Реализованный по умолчанию метод для мэппинга одного объекта-проекции рельса главного пути в мини-DTO.
     *
     * @param railProjection объект-проекция рельса главного пути, полученный из SpringJpa-репозитория
     * @return минимизированный DTO рельса
     */
    default RailMiniDto mapMainWayRailProjectionToMiniDto(MainWayRailProjection railProjection) {
        MainWaySectionEntity section = railProjection.getMainWaySection();
        float startCoordinate = section.getStartKm().getKm().floatValue() +
                (section.getStartMeter().floatValue() / 1000);
        float endCoordinate = section.getEndKm().floatValue() +
                (section.getEndMeter().floatValue() / 1000);
        startCoordinate = ((endCoordinate - startCoordinate) < 0) ? startCoordinate - 1 : startCoordinate;

        return new RailMiniDto(
                railProjection.getId(),
                railProjection.getLineSide(),
                railProjection.getRailKind(),
                startCoordinate,
                endCoordinate
        );
    }

    /**
     * Реализованный по умолчанию метод для мэппинга одного объекта-проекции рельса станционного пути в мини-DTO.
     *
     * @param railProjection объект-проекция рельса станционного пути, полученный из SpringJpa-репозитория
     * @return минимизированный DTO рельса
     */
    default RailMiniDto mapStationWayRailProjectionToMiniDto(StationWayRailProjection railProjection) {
        StationWaySectionEntity section = railProjection.getStationWaySection();
        int startKm = section.getStartMeter() / 1000;
        int startMeter = section.getStartMeter() % 1000;
        int endKm = section.getEndMeter() / 1000;
        int endMeter = section.getEndMeter() % 1000;

        return new RailMiniDto(
                railProjection.getId(),
                railProjection.getLineSide(),
                railProjection.getRailKind(),
                (float) startKm + ((float) startMeter / 1000f),
                (float) endKm + ((float) endMeter / 1000f)
        );
    }

}