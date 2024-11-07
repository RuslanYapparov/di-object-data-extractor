package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.InterstationTrackSwitchDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.StationMainWaySwitchDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.StationWaySwitchDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.SwitchDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.InterstationTrackSwitchEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationMainWaySwitchEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationWaySwitchEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.SwitchEntity;

/**
 * Интерфейс-мэппер для сущностей стрелочных переводов, на основании которого Mapstruct создает реализацию.
 */
@Mapper(componentModel = "spring", uses = {
        StationWayMiniMapper.class,
        WayPlaceMapper.class,
        WaySectionMapper.class
        })
public interface SwitchMapper extends DiObjectEntityToDtoMapper<SwitchEntity, SwitchDto> {

    /**
     * Переопределенный метод суперинтерфейса {@link DiObjectEntityToDtoMapper}, описывающий логику мэппинга сущностей
     * стрелочных переводов в DTO.
     *
     * @param switchEntity объект-entity стрелочного перевода (перегонный, главного пути станции или станционного пути),
     *                    полученный из SpringJpa-репозитотрия
     * @return DTO стрелочного перевода (перегонного, главного пути станции или станционного пути)
     */
    @Override
    default SwitchDto toDto(SwitchEntity switchEntity) {
        return switch (switchEntity) {
            case null ->  null;
            case InterstationTrackSwitchEntity interstationTrackSwitchEntity -> toDto(interstationTrackSwitchEntity);
            case StationMainWaySwitchEntity stationMainWaySwitchEntity -> toDto(stationMainWaySwitchEntity);
            case StationWaySwitchEntity stationWaySwitchEntity -> toDto(stationWaySwitchEntity);
            default -> throw new IllegalStateException("Не реализована логика мэппинга для типа стрелочного " +
                    "перевода '" + switchEntity.getClass().getSimpleName() + "' в DTO");
        };
    }

    /**
     * Дополнительный перегруженный основной метод мэппинга, преобразует entity-объект стрелочного перегона на перегоне
     * в DTO.
     *
     * @param interstationTrackSwitchEntity объект-entity стрелочного перегона на перегоне
     * @return DTO стрелочного перевода на перегоне
     */
    @Mapping(target = "controlStation", source = "controlStation.name")
    @Mapping(target = "mainWayNumber", source = "mainWay.number")
    InterstationTrackSwitchDto toDto(InterstationTrackSwitchEntity interstationTrackSwitchEntity);

    /**
     * Дополнительный перегруженный основной метод мэппинга, преобразует entity-объект стрелочного перегона на главном
     * пути станции в DTO.
     *
     * @param stationMainWaySwitchEntity объект-entity стрелочного перегона на главном пути станции
     * @return DTO стрелочного перевода на главном пути станции
     */
    @Mapping(target = "stationParkName", source = "stationPark.name")
    StationMainWaySwitchDto toDto(StationMainWaySwitchEntity stationMainWaySwitchEntity);

    /**
     * Дополнительный перегруженный основной метод мэппинга, преобразует entity-объект стрелочного перегона на
     * станционном пути в DTO.
     *
     * @param stationWaySwitchEntity объект-entity стрелочного перегона на станционном пути
     * @return DTO стрелочного перевода на станционном пути
     */
    @Mapping(target = "stationParkName", source = "stationPark.name")
    StationWaySwitchDto toDto(StationWaySwitchEntity stationWaySwitchEntity);

}