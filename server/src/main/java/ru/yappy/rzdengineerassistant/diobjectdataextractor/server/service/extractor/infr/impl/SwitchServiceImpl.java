package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.SwitchDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.SwitchMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.infr.SwitchRepository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr.SwitchService;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper.SwitchMapper;

import java.util.Collections;
import java.util.Set;

/**
 * Реализация сервиса для получения информации о сущностях стрелочных переводов из базы данных.
 */
@Service
@Slf4j
public class SwitchServiceImpl implements SwitchService {
    /** Репозиторий, извлекающий entity-объекты стрелочных переводов из базы данных. */
    private final SwitchRepository switchRepository;
    /** Мэппер entity-объектов стрелочных переводов в DTO. */
    private final SwitchMapper switchMapper;

    /**
     * Конструктор объекта сервиса, получающий зависимости из Spring-контекста.
     *
     * @param switchRepository репозиторий сущностей стрелочных переводов
     * @param switchMapper мэппер сущностей стрелочных переводов в DTO
     */
    @Autowired
    public SwitchServiceImpl(SwitchRepository switchRepository, SwitchMapper switchMapper) {
        this.switchRepository = switchRepository;
        this.switchMapper = switchMapper;
    }

    /**
     * Получает из БД и мэппит в DTO информацию о стрелочном переводе по его идентификатору.
     *
     * @param switchId идентификатор стрелочного перевода в базе данных
     * @return DTO, содержащий полную информацию о стрелочном переводе
     */
    @Override
    public SwitchDto getFullSwitchInfoByIdAndWayType(Long switchId, WayType wayType) {
        log.debug("Начало выполнения операции получения полной информации о стрелочном переводе с ид={}", switchId);
        SwitchEntity switchEntity = switch (wayType) {
            case INTERSTATION_TRACK -> switchRepository.findInterstationTrackSwitchById(switchId)
                    .orElseThrow(() -> new ObjectNotFoundException("стрелочный перевод на перегоне", switchId));
            case STATION_MAIN_WAY -> switchRepository.findStationMainWaySwitchById(switchId)
                    .orElseThrow(() ->
                            new ObjectNotFoundException("стрелочный перевод на главном пути станции", switchId));
            case STATION_WAY -> switchRepository.findStationWaySwitchById(switchId)
                    .orElseThrow(() -> new ObjectNotFoundException("стрелочный перевод на станционном пути", switchId));
            case null -> throw new IllegalArgumentException("При выполнении операции поиска стрелочного " +
                    "перевода по его идентификатору получен признак wayType=null");
        };
        SwitchDto switchDto = switchMapper.toDto(switchEntity);
        log.debug("Полная информация о стрелочном переводе с ид={} получена", switchId);
        return switchDto;
    }

    /**
     * Получает из БД и мэппит в сет DTO информацию о стрелочных переводах, расположенных вблизи места, определенного
     * в {@link LocationDto}, переданном в парметре к методу.
     *
     * @param location объект, описывающий конкретное место, относительно которого будет произведен поиск подходящих
     *                стрелочных переводов
     * @return сет из DTO, содержащих минимально необходимую информацию о стрелочных переводах
     */
    @Override
    public Set<SwitchMiniDto> getSwitchMiniDtoVariantsByLocationDto(LocationDto location) {
        log.debug("Начало выполнения операции получения минимальной информации обо всех стрелочных переводах, " +
                "расположенных вблизи места: {}.\nВНИМАНИЕ!!! МЕТОД НЕ РЕАЛИЗОВАН И ВОЗВРАЩАЕТ ПУСТОЙ СЕТ!", location);
        return Collections.emptySet();
    }

    /**
     * Получает из БД entity-объект с полной информацией о стрелочном переводе, в границах которого находится
     * переданный в параметре entity-объект рельса.
     *
     * @param railEntity entity-объект рельса, относительно которого будет производится поиск нужной информации о
     *                   стрелочном переводе
     * @param wayType тип пути, значение участвует в логике поиска информации о стрелочном переводе
     * @return entity-объект с полной информацией о стрелочном переводе
     */
    @Override
    public SwitchEntity getSwitchEntityByRailEntityAndWayType(RailEntity railEntity, WayType wayType) {
        checkMethodParameters(railEntity, wayType);
        log.debug("Начало выполнения операции получения информации о стрелочном переводе по параметрам " +
                        "расположения рельса с ид={} (class рельса='{}') и признаком wayType={}",
                railEntity.getId(),
                railEntity.getClass().getSimpleName(),
                wayType.getRuTitle());

        SwitchEntity switchEntity = findSwitchEntityInDbByRailEntityAndWayType(railEntity, wayType);
        if (switchEntity != null) {
            log.debug("Информация о стрелочном переводе, на котором находится рельс c ид='{}' получена. " +
                    "Идентификатор стрелочного перевода:'{}'", railEntity.getId(), switchEntity.getId());
        } else {
            log.debug("Попытка получения информации о стрелочном переводе, на котором находится рельс c ид='{}', " +
                    "не увенчалась успехом. Значение поля railSwitch будет null", railEntity.getId());
        }
        return switchEntity;
    }

    /**
     * Проверяет используемые свойства объектов railEntity и wayType на предмет возможности выполнения логики
     * основного метода сервиса.
     *
     * @param railEntity объект {@link RailEntity} в соответствии с которым производится поиск сущности связанного
     *                  стрелочного перевода
     * @param wayType тип пути, значение участвует в логике поиска информации о стрелочном переводе
     */
    private void checkMethodParameters(RailEntity railEntity, WayType wayType) {
        if (railEntity == null) {
            throw new IllegalStateException("Для выполнения операции поиска стрелочного перевода по сущности рельса " +
                    "в его составе не получен сам объект сущности рельса (railEntity=null");
        }
        if (wayType == null) {
            throw new IllegalStateException("Для выполнения операции поиска стрелочного перевода по сущности рельса " +
                    "в его составе не получен признак типа пути (wayType=null)");
        }
        if (!"СТРЕЛОЧНЫЙ ПЕРЕВОД".equals(railEntity.getRailKind())) {
            throw new IllegalStateException("Для выполнения операции поиска стрелочного перевода по сущности рельса " +
                    "в его составе получен объект railEntity с признаком railKind не 'СТРЕЛОЧНЫЙ ПЕРВЕОД'");
        }
    }

    /**
     * Вынесенная в отдельный метод логика получения entity-объекта стрелочного перевода по информации из railEntity в
     * зависимости от признака wayType.
     * <p>
     *     Предусмотрена обработка исключения {@link ObjectNotFoundException} при не удавшейся попытке получения
     *     entity-объекта стрелочного перевода.
     *
     * @param railEntity entity-объект рельса стрелочного перевода, по которому осуществляется поиск
     * @param wayType признак типа пути, в зависимости от которого будет произведен поиск конкретного типа switchEntity
     * @return entity-объект стрелочного перевода, если он найден, иначе null
     */
    private SwitchEntity findSwitchEntityInDbByRailEntityAndWayType(RailEntity railEntity, WayType wayType) {
        SwitchEntity switchEntity = null;
        try {
            if (railEntity instanceof MainWayRailEntity mainWayRailEntity) {
                switchEntity = switch (wayType) {
                    case INTERSTATION_TRACK ->
                            getInterstationTrackSwitchEntityByMainWayRailEntityLocationParameters(mainWayRailEntity);
                    case STATION_MAIN_WAY ->
                            getStationMainWaySwitchEntityByMainWayRailEntityLocationParameters(mainWayRailEntity);
                    default -> throw new IllegalStateException("Для выполнения операции поиска " +
                            "стрелочного перевода, расположенного на главном пути (перегона или станции), был получен " +
                            "недопустимый признак wayType=" + wayType);
                };
            } else if (railEntity instanceof StationWayRailEntity stationWayRailEntity) {
                switchEntity = getStationWaySwitchEntityByStationWayRailEntityLocationParameters(stationWayRailEntity);
            }
        } catch (ObjectNotFoundException objectNotFoundException) {
            log.warn(objectNotFoundException.getMessage());
        }
        return switchEntity;
    }

    /**
     * Извлекает из БД entity-объект с полной информацией о стрелочном переводе, находящемся на перегоне, по объекту
     * рельса главного пути, относящемуся к этому стрелочному переводу.
     *
     * @param mainWayRailEntity entity-объект рельса главного пути, который относится к стрелочному переводу,
     *                          который необходимо найти в БД
     * @return entity-объект с полной информацией о стрелочном переводе на перегоне
     * @throws ObjectNotFoundException если стрелочный перевод не был найден в базе данных по заданным параметрам
     */
    private InterstationTrackSwitchEntity getInterstationTrackSwitchEntityByMainWayRailEntityLocationParameters(
            MainWayRailEntity mainWayRailEntity) throws ObjectNotFoundException {
        Long transportDirectionId = mainWayRailEntity.getMainWaySection()
                .getStartKm()
                .getTransportDirection()
                .getId();
        Long mainWayId = mainWayRailEntity.getMainWay().getId();
        Integer startKm = mainWayRailEntity.getMainWaySection().getStartKm().getKm();
        Integer startMeter = mainWayRailEntity.getMainWaySection().getStartMeter();
        Integer endKm = mainWayRailEntity.getMainWaySection().getEndKm();
        Integer endMeter = mainWayRailEntity.getMainWaySection().getEndMeter();

        return switchRepository
                .findFullInterstationTrackSwitchInfoByLocationParameters(
                        transportDirectionId,
                        mainWayId,
                        startKm,
                        startMeter,
                        endKm,
                        endMeter)
                .orElseThrow(() ->
                        new ObjectNotFoundException("стрелочный перевод на перегоне",
                                String.format("Поиск по расположению рельса с ид=%d, ид направления=%d, ид главного " +
                                                "пути=%d, км начала=%d, метр начала=%d, км конца=%d, метр конца=%d",
                                        mainWayRailEntity.getId(),
                                        transportDirectionId,
                                        mainWayId,
                                        startKm,
                                        startMeter,
                                        endKm,
                                        endMeter
                                )
                        )
                );
    }

    /**
     * Извлекает из БД entity-объект с полной информацией о стрелочном переводе, находящемся на главном пути станции,
     * по объекту рельса главного пути, относящемуся к этому стрелочному переводу.
     *
     * @param mainWayRailEntity entity-объект рельса главного пути, который относится к стрелочному переводу,
     *                          который необходимо найти в БД
     * @return entity-объект с полной информацией о стрелочном переводе на главном пути станции
     * @throws ObjectNotFoundException если стрелочный перевод не был найден в базе данных по заданным параметрам
     */
    private StationMainWaySwitchEntity getStationMainWaySwitchEntityByMainWayRailEntityLocationParameters(
            MainWayRailEntity mainWayRailEntity) throws ObjectNotFoundException {
        Long transportDirectionId = mainWayRailEntity.getMainWaySection()
                .getStartKm()
                .getTransportDirection()
                .getId();
        Long mainWayId = mainWayRailEntity.getMainWay().getId();
        Integer startKm = mainWayRailEntity.getMainWaySection().getStartKm().getKm();
        Integer startMeter = mainWayRailEntity.getMainWaySection().getStartMeter();
        Integer endKm = mainWayRailEntity.getMainWaySection().getEndKm();
        Integer endMeter = mainWayRailEntity.getMainWaySection().getEndMeter();

        return switchRepository
                .findFullStationMainWaySwitchInfoByLocationParameters(
                        transportDirectionId,
                        mainWayId,
                        startKm,
                        startMeter,
                        endKm,
                        endMeter)
                .orElseThrow(() ->
                        new ObjectNotFoundException("стрелочный перевод на главном пути станции",
                                String.format("Поиск по расположению рельса с ид=%d, ид направления=%d, ид главного " +
                                                "пути=%d, км начала=%d, метр начала=%d, км конца=%d, метр конца=%d",
                                        mainWayRailEntity.getId(),
                                        transportDirectionId,
                                        mainWayId,
                                        startKm,
                                        startMeter,
                                        endKm,
                                        endMeter
                                )
                        )
                );
    }

    /**
     * Извлекает из БД entity-объект с полной информацией о стрелочном переводе, находящемся на станционном пути,
     * по объекту рельса станционного пути, относящемуся к этому стрелочному переводу.
     *
     * @param stationWayRailEntity entity-объект рельса станционного пути, который относится к стрелочному переводу
     * @return entity-объект с полной информацией о стрелочном переводе на станционном пути
     * @throws ObjectNotFoundException если стрелочный перевод не был найден в базе данных по заданным параметрам
     */
    private StationWaySwitchEntity getStationWaySwitchEntityByStationWayRailEntityLocationParameters(
            StationWayRailEntity stationWayRailEntity) throws ObjectNotFoundException {
        Long stationWayId = stationWayRailEntity.getStationWaySection()
                .getStationWay()
                .getId();
        Integer startMeter = stationWayRailEntity.getStationWaySection().getStartMeter();
        Integer endMeter = stationWayRailEntity.getStationWaySection().getEndMeter();

        return switchRepository
                .findFullStationWaySwitchInfoByLocationParameters(stationWayId,
                        startMeter,
                        endMeter)
                .orElseThrow(() ->
                        new ObjectNotFoundException("стрелочный перевод на станционном пути",
                                String.format("Поиск по расположению рельса с ид=%d, ид станционного пути=%d, " +
                                                "метр начала=%d, метр конца=%d",
                                        stationWayRailEntity.getId(),
                                        stationWayId,
                                        startMeter,
                                        endMeter
                                )
                        )
                );
    }

}