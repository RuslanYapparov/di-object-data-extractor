package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.LashDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.MainWayLashDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.StationWayLashDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.LashEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.MainWayLashEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.StationWayLashEntity;

/**
 * Интерфейс-мэппер для сущностей рельсовых плетей, на основании которого Mapstruct создает реализацию.
 */
@Mapper(componentModel = "spring", uses = { WaySectionMapper.class })
public interface LashMapper extends DiObjectEntityToDtoMapper<LashEntity, LashDto> {

    /**
     * Переопределенный метод суперинтерфейса {@link DiObjectEntityToDtoMapper}? описывающий логику мэппинга сущностей
     * рельсовых плетей в DTO.
     *
     * @param lashEntity объект-entity рельсовой плети (главного или станционного пути), полученный из
     *                   SpringJpa-репозитотрия
     * @return DTO рельсовой плети (главного или станционного пути)
     */
    @Override
    default LashDto toDto(LashEntity lashEntity) {
        return switch (lashEntity) {
            case null -> null;
            case MainWayLashEntity mainWayLashEntity -> toDto(mainWayLashEntity);
            case StationWayLashEntity stationWayLashEntity -> toDto(stationWayLashEntity);
            default -> throw new IllegalStateException("Не реализована логика мэппинга для типа плети '" +
                    lashEntity.getClass().getSimpleName() + "' в DTO");
        };
    }

    /**
     * Дополнительный перегруженный основной метод мэппинга, преобразует entity-объект рельсовой плети главного
     * пути в DTO.
     *
     * @param mainWayLashEntity entity-объект рельсовой плети главного пути
     * @return DTO рельсовой плети главного пути
     */
    @Mapping(target = "mainWayNumber", source = "mainWay.number")
    MainWayLashDto toDto(MainWayLashEntity mainWayLashEntity);

    /**
     * Дополнительный перегруженный основной метод мэппинга, преобразует entity-объект рельсовой плети станционного
     * пути в DTO.
     *
     * @param stationWayLashEntity entity-объект рельсовой плети станционного пути
     * @return DTO рельсовой плети станционного пути
     */
    StationWayLashDto toDto(StationWayLashEntity stationWayLashEntity);

}