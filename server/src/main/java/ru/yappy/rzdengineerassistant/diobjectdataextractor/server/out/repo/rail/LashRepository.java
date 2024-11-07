package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.rail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.*;

import java.util.Optional;

/**
 * SpringJpa-репозиторий, извлекающий из базы данных объекты, касающиеся сущностей рельсовых плетей.
 */
@Repository
public interface LashRepository extends JpaRepository<LashEntity, Long> {

    /**
     * Извлекает полную информацию о рельсовой плети, лежащей в главном пути, по параметрам,
     * определяющим место пути, относящееся к переднему торцу рельса.
     *
     * @param transportDirectionId идентификатор железнодорожного направления
     * @param mainWayId идентификатор главного пути железнодорожного направления
     * @param lineSide сторонность рельсовой нити, на которой находится рельс и плеть
     * @param railStartCoordinate координата переднего торца рельса
     * @return Optional-объект, содержащий информацию о рельсовой плети главного хода, если она найдена в базе данных
     */
    @Query("""
            SELECT l FROM MainWayLashEntity AS l
            JOIN FETCH l.mainWaySection AS lms
            JOIN FETCH lms.startKm AS lmsk
            JOIN FETCH lmsk.transportDirection AS lmsktd
            JOIN FETCH l.mainWay as lmw
            JOIN FETCH lmw.transportDirection AS lmwt
            WHERE
            l.mainWaySection.startKm.transportDirection.id = :transportDirectionId AND
            lmw.id = :mainWayId AND
            l.lineSide = :lineSide AND
            ((l.mainWaySection.startKm.km * 1000) + l.mainWaySection.startMeter <= :railStartCoordinate AND
            (l.mainWaySection.endKm * 1000) + l.mainWaySection.endMeter >= :railStartCoordinate)
            """)
    Optional<MainWayLashEntity> findFullMainWayLashInfoByLocationParameters(Long transportDirectionId,
                                                                           Long mainWayId,
                                                                           String lineSide,
                                                                           Integer railStartCoordinate);

    /**
     * Извлекает полную информацию о рельсовой плети, лежащей на станционном пути, по параметрам,
     * определяющим место пути, относящееся к переднему торцу рельса.
     *
     * @param stationWayId идентификатор станционного пути
     * @param lineSide сторонность рельсовой нити, на которой находится рельс и плеть
     * @param meter метр станционного пути, на котором расположен передний торец рельса
     * @return Optional-объект, содержащий информацию о рельсовой плети станционного пути, если она найдена в базе данных
     */
    @Query("""
            SELECT l FROM StationWayLashEntity AS l
            JOIN FETCH l.stationWaySection as lsws
            JOIN FETCH lsws.stationWay AS sw
            JOIN FETCH sw.station AS sws
            WHERE
            lsws.stationWay.id = :stationWayId AND
            l.lineSide = :lineSide AND
            lsws.startMeter <= :meter AND lsws.endMeter >= :meter
            """)
    Optional<StationWayLashEntity> findFullStationWayLashInfoByLocationParameters(Long stationWayId,
                                                                                  String lineSide,
                                                                                  Integer meter);

}