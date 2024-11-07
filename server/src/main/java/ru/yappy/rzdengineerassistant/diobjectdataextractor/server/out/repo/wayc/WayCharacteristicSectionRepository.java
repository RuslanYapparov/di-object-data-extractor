package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.wayc;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc.*;

import java.util.Set;

/**
 * SpringJpa-репозиторий, извлекающий из базы данных объекты сущностей участков характеристик главных и
 * станционных путей.
 */
@Repository
public interface WayCharacteristicSectionRepository extends JpaRepository<MainWayCharacteristicSectionEntity, Long> {

    /**
     * Возвращает список сущностей участков характеристик, характерных для конкретного места определенного главного пути.
     *
     * @param transportDirectionId идентификатор железнодорожного направления
     * @param mainWayNumber номер главного пути, для которого будут получены характеристики
     * @param coordinate абсолютная координата места, относительно которого будут получены характеристики
     * @return список объектов, содержащих характеристики участка главного пути
     */
    @Query("""
            SELECT mwcs FROM MainWayCharacteristicSectionEntity AS mwcs
            JOIN FETCH mwcs.mainWaySection AS m
            JOIN FETCH m.startKm AS mkm
            JOIN FETCH m.startKm.transportDirection AS mkmtd
            JOIN FETCH mwcs.mainWay AS mw
            JOIN FETCH mw.transportDirection AS mwt
            WHERE mwt.id = :transportDirectionId AND
            mw.number = :mainWayNumber AND
            ((mkm.km * 1000) + m.startMeter) <= :coordinate AND
            ((m.endKm * 1000) + m.endMeter) >= :coordinate
            """)
    Set<MainWayCharacteristicSectionEntity> findAllForMainWayByLocationParameters(Long transportDirectionId,
                                                                                  Integer mainWayNumber,
                                                                                  int coordinate);

    /**
     * Возвращает список сущностей участков характеристик, характерных для конкретного места определенного
     * станционного пути.
     *
     * @param stationId идентификатор станции, для станционного пути которой будут получены характеристики
     * @param stationWayName номер (название) станционного пути, для которого производится поиск характеристик
     * @param coordinate абсолютная координата места, относительно которого будут получены характеристики
     * @return список объектов, содержащих характеристики участка станционного пути
     */
    @Query("""
            SELECT swc FROM StationWayCharacteristicSectionEntity AS swc
            JOIN FETCH swc.stationWaySection AS s
            JOIN FETCH swc.stationWaySection.stationWay AS sw
            WHERE
            s.stationWay.station.id = :stationId AND
            s.stationWay.name = :stationWayName AND
            s.startMeter <= :coordinate AND s.endMeter >= :coordinate
            """)
    Set<StationWayCharacteristicSectionEntity> findAllForStationWayByLocationParameters(Long stationId,
                                                                                        String stationWayName,
                                                                                        int coordinate);

}