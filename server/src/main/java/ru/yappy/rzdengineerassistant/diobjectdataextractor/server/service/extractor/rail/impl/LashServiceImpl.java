package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.rail.LashRepository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.LashService;

/**
 * Реализация сервиса для получения информации о сущностях рельсовых плетей из базы данных.
 */
@Service
@Slf4j
public class LashServiceImpl implements LashService {
    /** Репозиторий, извлекающий entity-объекты рельсовых плетей из базы данных. */
    private final LashRepository lashRepository;

    /**
     * Конструктор объекта сервиса, получающий зависимости из Spring-контекста.
     *
     * @param lashRepository репозиторий сущностей рельсовых плетей
     */
    @Autowired
    public LashServiceImpl(LashRepository lashRepository) {
        this.lashRepository = lashRepository;
    }

    /**
     * Получает entity-объект рельсовой плети по entity-объекту рельса, имеющим признак вида ВСП railKind="ПЛЕТЬ" и
     * являющемуся частью рельсовой плети.
     *
     * @param railEntity entity-объект рельса в составе рельсовой плети
     * @return entity-объект рельсовой плети
     */
    @Override
    public LashEntity getLashEntityByRailEntity(RailEntity railEntity) {
        checkMethodParameters(railEntity);
        log.debug("Начало выполнения операции получения полной информации о рельсовой плети по парматерам " +
                "расположения рельса с ид={} (class рельса={})",
                railEntity.getId(),
                railEntity.getClass().getSimpleName());

        LashEntity lashEntity = findLashEntityInDbByRailEntity(railEntity);
        if (lashEntity != null) {
            log.debug("Информация о рельсовой плети, на которой находится рельс c ид='{}' получена. " +
                    "Идентификатор плети:'{}'", railEntity.getId(), lashEntity.getId());
        } else {
            log.debug("Попытка получения информации о рельсовой плети, на которой находится рельс c ид='{}' " +
                    "не увенчалась успехом. Значение поля railLash будет null", railEntity.getId());
        }
        return lashEntity;
    }

    /**
     * Проверяет используемые свойства объекта railEntity на предмет возможности выполнения логики основного метода
     * сервиса.
     *
      * @param railEntity объект {@link RailEntity} в соответствии с которым производится поиск сущности связанной плети
     */
    private void checkMethodParameters(RailEntity railEntity) {
        if (railEntity == null) {
            throw new IllegalStateException("Для выполнения операции поиска рельсовой плети по сущности рельса " +
                    "в ее составе получен null");
        }
        if (!"ПЛЕТЬ".equals(railEntity.getRailKind())) {
            throw new IllegalStateException("Для выполнения операции поиска рельсовой плети по сущности рельса " +
                    "в ее составе получен объект railEntity с признаком railKind не 'ПЛЕТЬ'");
        }
    }

    /**
     * Вынесенная в отдельный метод логика получения entity-объекта рельсовой плети по информации из railEntity.
     * <p>
     *     Предусмотрена обработка исключения {@link ObjectNotFoundException} при не удавшейся попытке получения
     *     entity-объекта рельсовой плети.
     *
     * @param railEntity entity-объект рельса в составе рельсовой плети, по которому осуществляется поиск
     * @return entity-объект рельсовой плети, если он найден, иначе null
     */
    private LashEntity findLashEntityInDbByRailEntity(RailEntity railEntity) {
        LashEntity lashEntity = null;
        try {
            if (railEntity instanceof MainWayRailEntity mainWayRailEntity) {
                lashEntity = getMainWayLashEntityByMainWayRailLocationParameters(mainWayRailEntity);
            } else if (railEntity instanceof StationWayRailEntity stationWayRailEntity) {
                lashEntity = getStationWayLashEntityByStationWayRailLocationParameters(stationWayRailEntity);
            }
        } catch (ObjectNotFoundException objectNotFoundException) {
            log.warn("ObjectNotFoundException: {}", objectNotFoundException.getMessage());
        }
        return lashEntity;
    }

    /**
     * Получает entity-объект рельсовой плети, лежащей в главном пути, по entity-объекту рельса, относящемуся
     * к этой плети.
     *
     * @param mainWayRailEntity рельс главного хода, относящийся к рельсовой плети
     * @return объект с информацией о рельсовой плети, лежащей в главном пути
     */
    private MainWayLashEntity getMainWayLashEntityByMainWayRailLocationParameters(MainWayRailEntity mainWayRailEntity) {
        Long transportDirectionId = mainWayRailEntity.getMainWaySection()
                .getStartKm()
                .getTransportDirection()
                .getId();
        Long mainWayId = mainWayRailEntity.getMainWay().getId();
        String lineSide = mainWayRailEntity.getLineSide();
        Integer railStartCoordinate = (mainWayRailEntity.getMainWaySection().getStartKm().getKm() * 1000) +
                mainWayRailEntity.getMainWaySection().getStartMeter();

        return lashRepository
                .findFullMainWayLashInfoByLocationParameters(
                        transportDirectionId,
                        mainWayId,
                        lineSide,
                        railStartCoordinate)
                .orElseThrow(() ->
                        new ObjectNotFoundException("рельсовая плеть на главном пути",
                                String.format("Поиск по расположению рельса с ид=%d, ид направления=%d, " +
                                                "ид главного пути=%d, сторонность=%s, координата переднего торца = %f",
                                        mainWayRailEntity.getId(),
                                        transportDirectionId,
                                        mainWayId,
                                        lineSide,
                                        (railStartCoordinate.floatValue() / 1000)
                                )
                        )
                );
    }

    /**
     * Получает entity-объект рельсовой плети, лежащей в станционном пути, по entity-объекту рельса, относящемуся
     * к этой плети.
     *
     * @param stationWayRailEntity рельс станционного пути, относящийся к рельсовой плети
     * @return объект с информацией о рельсовой плети, лежащей на станционном пути
     */
    private StationWayLashEntity getStationWayLashEntityByStationWayRailLocationParameters(
            StationWayRailEntity stationWayRailEntity) {
        Long stationWayId = stationWayRailEntity.getStationWaySection()
                .getStationWay()
                .getId();
        String lineSide = stationWayRailEntity.getLineSide();
        Integer startMeter = stationWayRailEntity.getStationWaySection().getStartMeter();

        return lashRepository
                .findFullStationWayLashInfoByLocationParameters(stationWayId,
                        lineSide,
                        startMeter)
                .orElseThrow(() ->
                        new ObjectNotFoundException("рельсовая плеть на станционном пути",
                                String.format("Поиск по расположению рельса с ид=%d, ид станционного пути=%d, " +
                                                "сторонность=%s, метр начала=%d",
                                        stationWayRailEntity.getId(),
                                        stationWayId,
                                        lineSide,
                                        startMeter
                                )
                        )
                );
    }

}