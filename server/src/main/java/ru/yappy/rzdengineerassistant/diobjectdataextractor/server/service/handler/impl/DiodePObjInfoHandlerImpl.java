package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.wayc.WayCharacteristicsDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr.LocationService;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.wayc.WayCharacteristicService;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.handler.DiodePObjInfoHandler;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.*;

/**
 * Реализация интерфейса {@link DiodePObjInfoHandler}, содержащая методы для получения информации, необходимой для
 * создания характеристик объектов путевого хозяйства.
 */
@Service
@Slf4j
public class DiodePObjInfoHandlerImpl implements DiodePObjInfoHandler {
    /** Сервис для получения информации о рельсах. */
    private final RailService railService;
    /** Сервис для получения информации о рельсовых стыках. */
    private final JointService jointService;
    /** Сервис для получения для получения информации о характеристиках пути. */
    private final WayCharacteristicService wayCharacteristicService;
    /** Сервис для получения информации о локациях, на которых расположены объекты. */
    private final LocationService locationService;

    /**
     * Конструктор объекта сервиса, получающий зависимости из Spring-контекста.
     *
     * @param railService сервис для получения информации о рельсах
     * @param jointService сервис для получения информации о рельсовых стыках
     * @param wayCharacteristicService сервис для получения для получения ифнормации о характеристиках пути
     * @param locationService сервис для получения информации о локациях, на которых расположены объекты
     */
    @Autowired
    public DiodePObjInfoHandlerImpl(RailService railService,
                                    JointService jointService,
                                    WayCharacteristicService wayCharacteristicService,
                                    LocationService locationService) {
        this.railService = railService;
        this.jointService = jointService;
        this.wayCharacteristicService = wayCharacteristicService;
        this.locationService = locationService;
    }

    /**
     * Метод, обрабатывающий запрос на получение необходимой информации для создания характеристики рельса и
     * возвращающий неполный DTO-объект, с данными о рельсе, наименовании станции/перегона и характеристиках пути.
     *
     * @param railId идентификатор рельса в базе данных, для которого нужно получить информацию
     * @param wayType признак типа пути, указанный пользователем в заполняемой во фронтенде форме
     * @return неполный {@link RailInfoDto} с информацией для создания документа, которая извлекается из базы данных
     */
    @Override
    public RailInfoDto handleRailInfoRequest(Long railId, WayType wayType) {
        RailDto railDto = railService.getFullRailInfoByIdAndWayType(railId, wayType);
        LocationDto locationDto = getRailStartJointLocationFromRailDto(railDto, wayType);
        WayCharacteristicsDto wayCharacteristicsDto =
                wayCharacteristicService.getWayCharacteristicsByLocationDto(locationDto);
        String locationDescription = getLocationDescription(wayType, locationDto);
        return new RailInfoDto(locationDescription,
                wayCharacteristicsDto,
                railDto,
                null,
                null);
    }

    /**
     * Метод, обрабатывающий запрос на получение необходимой информации для создания характеристики рельсового стыка и
     * возвращающий неполный DTO-объект, с данными о стыке, наименовании станции/перегона и характеристиках пути.
     *
     * @param jointId идентификатор рельсого стыка в базе данных, для которого нужно получить информацию
     * @param jointType тип рельсового стыка
     * @param wayType признак типа пути, указанный пользователем в заполняемой во фронтенде форме
     * @return неполный {@link JointInfoDto} с информацией для создания документа, которая извлекается из базы данных
     */
    @Override
    public JointInfoDto handleJointInfoRequest(Long jointId, WayType wayType, JointType jointType) {
        JointDto jointDto = jointService.getFullJointInfo(jointId, wayType, jointType);
        LocationDto locationDto = getJointLocationFromJointDto(jointDto, wayType);
        WayCharacteristicsDto wayCharacteristicsDto =
                wayCharacteristicService.getWayCharacteristicsByLocationDto(locationDto);
        String locationDescription = getLocationDescription(wayType, locationDto);
        return new JointInfoDto(locationDescription,
                locationDto,
                wayCharacteristicsDto,
                jointDto,
                null);
    }

    /**
     * Получает из сервиса работы с локациями короткое описание местоположения (название станции или перегона) объекта.
     *
     * @param wayType тип пути, для которого нужно получить описание
     * @param locationDto объект, хранящий исчерпывающую информацию о локации
     * @return строка с названием станции или перегона
     */
    private String getLocationDescription(WayType wayType, LocationDto locationDto) {
        return switch (wayType) {
            case INTERSTATION_TRACK -> locationService.getInterstationTrackNameByLocationDto(locationDto);
            case STATION_MAIN_WAY -> locationService.getStationNameByLocationDto(locationDto);
            default -> null;
        };
    }

    /**
     * Получает объект {@link LocationDto} для переднего торца рельса на основании данных, хранящихся в DTO
     * с информацией о рельсе.
     *
     * @param railDto объект, хранящий исчерпывающую информацию о рельсе
     * @param wayType признак типа пути, указанный пользователем в заполняемой во фронтенде форме
     * @return объект {@link LocationDto} для переднего торца рельса
     */
    private LocationDto getRailStartJointLocationFromRailDto(RailDto railDto, WayType wayType) {
        Long locationId;
        String wayNumber;
        Integer km;
        int picket;
        int meter;
        LineSide lineside = "ПРАВАЯ".equals(railDto.getLineSide()) ? LineSide.RIGHT : LineSide.LEFT;
        switch (railDto) {
            case MainWayRailDto mainWayRailDto -> {
                locationId = mainWayRailDto.getMainWaySection().transportDirection().id();
                wayNumber = mainWayRailDto.getMainWayNumber().toString();
                km = mainWayRailDto.getMainWaySection().startKm();
                picket = mainWayRailDto.getMainWaySection().startMeter() / 100 + 1;
                meter = mainWayRailDto.getMainWaySection().startMeter() % 100;
            }
            case StationWayRailDto stationWayRailDto -> {
                locationId = stationWayRailDto.getStationWaySection().stationWay().station().getId();
                wayNumber = stationWayRailDto.getStationWaySection().stationWay().number();
                km = stationWayRailDto.getStationWaySection().startMeter() / 1000;
                picket = (stationWayRailDto.getStationWaySection().startMeter() % 1000) / 100 + 1;
                meter = (stationWayRailDto.getStationWaySection().startMeter() % 100);
            }
            default -> throw new IllegalStateException("При выполнении операции получения координаты начала рельса " +
                    "получен класс DTO '" + railDto.getClass().getSimpleName() + "', для которого не предусмотрена " +
                    "логика обработки.");
        }
        return new LocationDto(wayType, locationId, wayNumber, km, picket, meter, lineside);
    }

    /**
     * Получает объект {@link LocationDto} для рельсового стыка на основании данных, хранящихся в DTO
     * с информацией о стыке.
     *
     * @param jointDto объект, хранящий исчерпывающую информацию о рельсовом стыке
     * @param wayType признак типа пути, указанный пользователем в заполняемой во фронтенде форме
     * @return объект {@link LocationDto} для переднего торца рельса
     */
    private LocationDto getJointLocationFromJointDto(JointDto jointDto, WayType wayType) {
        Long locationId;
        String wayNumber;
        Integer km;
        int picket;
        int meter;
        switch (jointDto) {
            case MainWayJoint mainWayJoint -> {
                locationId = mainWayJoint.getMainWayPlace().transportDirection().id();
                wayNumber = mainWayJoint.getMainWayNumber().toString();
                km = mainWayJoint.getMainWayPlace().km();
                picket = mainWayJoint.getMainWayPlace().meterOnKm() / 100 + 1;
                meter = mainWayJoint.getMainWayPlace().meterOnKm() % 100;
            }
            case StationWayJoint stationWayJoint -> {
                String stationName = stationWayJoint.getStationWayPlace().stationWay().station().getName();
                stationName = Character.toUpperCase(stationName.charAt(0)) + (stationName.length() > 1 ?
                        stationName.substring(1).toLowerCase() : "");
                locationId = stationWayJoint.getStationWayPlace().stationWay().station().getId();
                wayNumber = stationWayJoint.getStationWayPlace().stationWay().number() + " станции " + stationName;
                km = stationWayJoint.getStationWayPlace().meterOnStationWay() / 1000;
                picket = (stationWayJoint.getStationWayPlace().meterOnStationWay() % 1000) / 100 + 1;
                meter = (stationWayJoint.getStationWayPlace().meterOnStationWay() % 100);
            }
            default -> throw new IllegalStateException("При выполнении операции получения координаты рельсового " +
                    "стыка получен класс DTO '" + jointDto.getClass().getSimpleName() + "', для которого не " +
                    "предусмотрена логика обработки.");
        }
        return new LocationDto(wayType, locationId, wayNumber, km, picket, meter, jointDto.getLineSide());
    }

}