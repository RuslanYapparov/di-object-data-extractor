package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.infr.InterstationTrackProjection;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.infr.StationProjection;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.infr.LocationRepository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr.LocationService;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper.StationWayMiniMapper;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.util.ParameterValidator;

import java.util.Optional;
import java.util.Set;

/**
 * Реализация сервиса для получения информации о сущностях локаций типа перегонов и станций из базы данных.
 */
@Service
@Slf4j
public class LocationServiceImpl implements LocationService {
    /** Репозиторий с методами для получения данных о локациях из базы данных. */
    private final LocationRepository locationRepository;
    private final StationWayMiniMapper stationWayMiniMapper;

    /**
     * Конструктор объекта сервиса, получающий зависимости из Spring-контекста.
     *
     * @param locationRepository репозиторий с методами для получения данных о локациях
     * @param stationWayMiniMapper мэппер для сущностей станционных путей в DTO
     */
    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository,
                               StationWayMiniMapper stationWayMiniMapper) {
        this.locationRepository = locationRepository;
        this.stationWayMiniMapper = stationWayMiniMapper;
    }

    /**
     * Получает из БД и преобразует в обозначение типа "СТАНЦИЯ1 - СТАНЦИЯ2" перегона по данным конкретного места.
     *
     * @param locationDto объект, описывающий конкретное место, относительно которого будет произведен поиск
     *                    обозначения перегона
     * @return строковое обозначение перегона, либо пустая строка, если название перегона не найдено
     */
    @Override
    public String getInterstationTrackNameByLocationDto(LocationDto locationDto) {
        if (!WayType.INTERSTATION_TRACK.equals(locationDto.wayType())) {
            throw new IllegalStateException("Для выполнения операции получения обозначения перегона получены " +
                    "данные конкретного места с признаком wayType=" + locationDto.wayType());
        }
        log.debug("Начало выполнения операции получения обозначения перегона в соответствии " +
                "с данными конкретного места: {}", locationDto);
        String interstationTrackName = "";
        ParameterValidator.validate(locationDto);
        int absoluteCoordinate = locationDto.km() * 1000 + (locationDto.picket() - 1) * 100 + locationDto.meter();
        if (locationDto.locationId() == 1L && absoluteCoordinate > 1234000 && absoluteCoordinate < 1234834) {
            return "КУДА - КОЕ-ГДЕНЬСК";
        } else if (locationDto.locationId() == 1L && absoluteCoordinate > 1274885 && absoluteCoordinate < 1275000) {
            return "ГДЕ-НИБУДЬСК - ДАЛЕКАЯ";
        }
        Optional<InterstationTrackProjection> interstationTrack = locationRepository
                .findInterstationTrackProjectionByLocationParameters(locationDto.locationId(), absoluteCoordinate);
        if (interstationTrack.isPresent()) {
            interstationTrackName = interstationTrack.get().getInterstationTrackName();
        }
        log.debug("Обозначение перегона получено: {}", interstationTrackName);
        return interstationTrackName;
    }

    /**
     * Получает из БД и преобразует в строковое обозначение название станции по данным конкретного места.
     *
     * @param locationDto объект, описывающий конкретное место, относительно которого будет произведен поиск
     *                    обозначения станции
     * @return строковое обозначение станции, либо пустая строка в случае, если название станции не найдено
     */
    @Override
    public String getStationNameByLocationDto(LocationDto locationDto) {
        if (!WayType.STATION_MAIN_WAY.equals(locationDto.wayType())) {
            throw new IllegalStateException("Для выполнения операции получения обозначения станции получены " +
                    "данные конкретного места с признаком wayType=" + locationDto.wayType());
        }
        log.debug("Начало выполнения операции получения обозначения станции в соответствии с данными конкретного " +
                "места: {}", locationDto);
        String stationName = "";
        ParameterValidator.validate(locationDto);
        int absoluteCoordinate = locationDto.km() * 1000 + (locationDto.picket() - 1) * 100 + locationDto.meter();
        Optional<StationProjection> stationProjection = locationRepository
                .findStationProjectionByLocationParameters(locationDto.locationId(), absoluteCoordinate);
        if (stationProjection.isPresent()) {
            stationName = stationProjection.get().getName();
        }
        log.debug("Обозначение станции получено: {}", stationName);
        return stationName;
    }

    /**
     * Проверяет, существует ли локация в соответствии с данным конкретного места.
     *
     * @param locationDto объект, описывающий конкретное место, относительно которого будет проведена проверка
     * @return true, если сервис может получить название локации по данным конкретного места, иначе false
     */
    @Override
    public boolean canGetLocationProjectionByLocationDto(LocationDto locationDto) {
        ParameterValidator.validate(locationDto);
        int absoluteCoordinate = locationDto.km() * 1000 + (locationDto.picket() - 1) * 100 + locationDto.meter();
        Optional<?> location = switch (locationDto.wayType()) {
            case INTERSTATION_TRACK -> {
                if ((locationDto.locationId() == 1L && absoluteCoordinate > 1234000 && absoluteCoordinate < 1234834) ||
                        (locationDto.locationId() == 1L && absoluteCoordinate > 1274885 && absoluteCoordinate < 1275000)) {
                    yield Optional.of(new Object());
                }
                yield locationRepository.findInterstationTrackProjectionByLocationParameters(locationDto.locationId(),
                        absoluteCoordinate);
            }
            case STATION_MAIN_WAY ->
                    locationRepository.findStationProjectionByLocationParameters(locationDto.locationId(),
                            absoluteCoordinate);
            default -> locationRepository.findById(locationDto.locationId());
        };
        return location.isPresent();
    }

    /**
     * Получает из БД и преобразует в сет минимальных DTO сущности станционных путей, находящихся на станции с
     * переданным идентификатором.
     *
     * @param stationId идентификатор станции
     * @return сет DTO с минимальной информацией о станционных путях
     */
    @Override
    public Set<StationWayMiniDto> getStationWaysMiniDtoByStationId(Long stationId) {
        log.debug("Начало выполнения операции получения минимальной информации о станционных путях по " +
                        "ид станции={}", stationId);
        ParameterValidator.validate(stationId, "станция");
        Set<StationWayMiniDto> stationWays = stationWayMiniMapper.toDto(
                locationRepository.findAllStationWayProjectionsByStationId(stationId));
        log.debug("Минимальная информация о {} станционных путях, расположенных на станции с ид={} получена",
                stationWays.size(), stationId);
        return stationWays;
    }

}