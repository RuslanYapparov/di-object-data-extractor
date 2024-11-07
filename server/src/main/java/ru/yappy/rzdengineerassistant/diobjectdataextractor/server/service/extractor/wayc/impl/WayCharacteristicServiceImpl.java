package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.wayc.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.wayc.WayCharacteristicsDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.wayc.WayCharacteristicSectionRepository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.wayc.WayCharacteristicService;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper.WayCharacteristicsMapper;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.util.ParameterValidator;

import java.util.Set;

/** Реализация сервиса для получения информации о сущностях характеристик пути из базы данных. */
@Service
@Slf4j
public class WayCharacteristicServiceImpl implements WayCharacteristicService {
    /** Репозиторий, предоставляющий сущности характеристик пути. */
    private final WayCharacteristicSectionRepository wayCharacteristicSectionRepository;
    /** Мэппер entity-объектов характеристик пути в DTO. */
    private final WayCharacteristicsMapper wayCharacteristicsMapper;

    /**
     * Конструктор объекта сервиса, получающий необходимые зависимости из Spring-контекста.
     *
     * @param wayCharacteristicSectionRepository репозиторий, предоставляющий сущности характеристик пути
     * @param wayCharacteristicsMapper мэппер entity-объектов характеристик пути в DTO
     */
    @Autowired
    public WayCharacteristicServiceImpl(WayCharacteristicSectionRepository wayCharacteristicSectionRepository,
                                        WayCharacteristicsMapper wayCharacteristicsMapper) {
        this.wayCharacteristicSectionRepository = wayCharacteristicSectionRepository;
        this.wayCharacteristicsMapper = wayCharacteristicsMapper;
    }

    /**
     * Получает из БД и мэппит в DTO характеристики пути по данным конкретного места, введенным пользователем.
     *
     * @param locationDto данные о конкретном месте, для которого производится поиск характеристик пути
     * @return DTO с информацией о всех характеристиках пути для указанного места
     */
    @Override
    public WayCharacteristicsDto getWayCharacteristicsByLocationDto(LocationDto locationDto) {
        log.debug("Начало выполнения операции получения характеристик пути в соответствии с данными: {}", locationDto);
        ParameterValidator.validate(locationDto);
        int absoluteCoordinate = (locationDto.km() * 1000) + ((locationDto.picket() - 1) * 100) + locationDto.meter();
        WayCharacteristicsDto wayCharacteristicsDto;
        if (WayType.STATION_WAY.equals(locationDto.wayType())) {
            String wayNumber = locationDto.wayNumber();
            wayNumber = !(wayNumber.contains(" станции ")) ? wayNumber :
                    wayNumber.substring(0, wayNumber.indexOf(" станции "));
            Set<StationWayCharacteristicSectionEntity> characteristics =
                    wayCharacteristicSectionRepository.findAllForStationWayByLocationParameters(locationDto.locationId(),
                            wayNumber, absoluteCoordinate);
            wayCharacteristicsDto = wayCharacteristicsMapper.mapStationWayCharacteristicsToDto(characteristics);
            log.debug("Данные о характеристиках станционного пути в конкретном месте получены");
        } else {
            Set<MainWayCharacteristicSectionEntity> characteristics =
                    wayCharacteristicSectionRepository.findAllForMainWayByLocationParameters(locationDto.locationId(),
                            Integer.parseInt(locationDto.wayNumber()), absoluteCoordinate);
            wayCharacteristicsDto = wayCharacteristicsMapper.toDto(characteristics);
            log.debug("Данные о характеристиках главного пути в конкретном месте получены");
        }
        return wayCharacteristicsDto;
    }

}