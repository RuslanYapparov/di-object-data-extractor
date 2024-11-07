package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import org.mapstruct.Mapper;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm.WayMaintenanceDistanceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm.WayMaintenanceDistanceMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.WayMaintenanceDistanceEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.adm.WayMaintenanceDistanceProjection;

import java.util.List;

/**
 * Интерфейс-маппер для сущности дистанции пути, на основании которого Mapstruct создает реализацию.
 */
@Mapper(componentModel = "spring", uses = { EmployeeMapper.class })
public interface WayMaintenanceDistanceMapper
        extends DiObjectEntityToDtoMapper<WayMaintenanceDistanceEntity, WayMaintenanceDistanceDto> {

    /**
     * Мэппит список объектов минимизированных сущностей дистанций в список DTO.
     *
     * @param projections hibernate-проекции сущностей дистанции пути, содержащие минимальную информацию из БД
     * @return список минимизированных DTO-объекты сущностей дистанции пути
     */
    List<WayMaintenanceDistanceMiniDto> mapListOfDistanceEntitiesToListOfDto(
            List<WayMaintenanceDistanceProjection> projections);

}