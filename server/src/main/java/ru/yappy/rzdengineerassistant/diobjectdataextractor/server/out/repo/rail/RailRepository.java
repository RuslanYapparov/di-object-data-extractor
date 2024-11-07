package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.rail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.MainWayRailEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.RailEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.StationWayRailEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.rail.MainWayRailProjection;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.rail.StationWayRailProjection;

import java.util.Optional;
import java.util.List;

/**
 * SpringJpa-репозиторий, извлекающий из базы данных объекты, касающиеся сущностей рельсов (рельсы в главных путях и
 * в станционных путях)
 */
@Repository
public interface RailRepository extends JpaRepository<RailEntity, Long> {

    /**
     * Извлекает полную информацию о рельсе, лежащем в главном ходу, по его идентификатору.
     *
     * @param railId идентификатор рельса в базе данных
     * @return Optional-объект, содержащий полную информацию о рельсе, если он найден в базе данных
     */
    @Query("""
            SELECT r FROM RailEntity AS r
            JOIN FETCH r.mainWaySection AS s
            JOIN FETCH s.startKm AS sk
            JOIN FETCH s.startKm.transportDirection AS ts
            JOIN FETCH r.mainWay AS w
            JOIN FETCH w.transportDirection AS tw
            WHERE
            r.id = :railId
            """)
    Optional<MainWayRailEntity> findFullMainWayRailInfoById(Long railId);

    /**
     * Извлекает полную информацию о рельсе, лежащем на станционном пути, по его идентификатору.
     *
     * @param railId идентификатор рельса в базе данных
     * @return Optional-объект, содержащий полную информацию о рельсе, если он найден в базе данных
     */
    @Query("""
            SELECT r FROM RailEntity AS r
            JOIN FETCH r.stationWaySection AS s
            JOIN FETCH s.stationWay AS sw
            JOIN FETCH s.stationWay.station AS sws
            WHERE
            r.id = :railId
            """)
    Optional<StationWayRailEntity> findFullStationWayRailInfoById(Long railId);

    /**
     * Извлекает полную информацию, преобразующуюся в объекты-проекции, о всех рельсах главного хода, лежащих вблизи
     * места, определяемого набором параметров, передающихся в метод.
     *
     * @param transportDirectionId идентификатор железнодорожного направления в базе данных
     * @param mainWayNumber номер главного пути
     * @param coordinate координата места, относительно которой будет осуществлено получение близлежащих рельсов
     * @param lineSide сторонность рельсовой нити, в соответствии с которой извлекается информация о рельсах
     * @return список с объектами-проекциями, содержащими основную информацию о рельсах
     */
    @Query("""
            SELECT mwr FROM MainWayRailEntity AS mwr
            JOIN FETCH mwr.mainWaySection AS mwrs
            JOIN FETCH mwrs.startKm AS mwrsskm
            WHERE mwr.mainWay.transportDirection.id = :transportDirectionId AND
            mwr.mainWay.number = :mainWayNumber AND
            ((mwrsskm.km * 1000) + mwrs.startMeter) >= (:coordinate - 101) AND
            ((mwrsskm.km * 1000) + mwrs.startMeter) <= (:coordinate + 101) AND
            mwr.lineSide ilike(:lineSide)
            ORDER BY ((mwrsskm.km * 1000) + mwrs.startMeter)
            """)
    List<MainWayRailProjection> findRailMiniInfoByLocationData(Long transportDirectionId,
                                                              Integer mainWayNumber,
                                                              int coordinate,
                                                              String lineSide);

    /**
     * Извлекает полную информацию, преобразующующуюся в объекты-проекции, о всех рельсах станционного пути, лежащих
     * вблизи места, определяемого набором параметров, передающихся в метод.
     *
     * @param stationId идентификатор станции в базе данных
     * @param stationWayNumber номер станционного пути, на котором расположено конкретное место
     * @param stationWayMeter цифровое обозначение метра, на котором расположено конкретное место
     * @param lineSide сторонность рельсовой нити, в соответствии с которой извлекается информация о рельсах
     * @return список с объектами-проекциями, содержащими основную информацию о рельсах
     */
    @Query("""
            SELECT swr FROM StationWayRailEntity AS swr
            JOIN FETCH swr.stationWaySection AS s
            WHERE
            s.stationWay.station.id = :stationId AND
            s.stationWay.name = :stationWayNumber AND
            s.startMeter >= (:stationWayMeter - 101) AND s.endMeter <= (:stationWayMeter + 101) AND
            swr.lineSide ilike(:lineSide)
            ORDER BY s.startMeter
            """)
    List<StationWayRailProjection> findRailMiniInfoByLocationData(Long stationId,
                                                                 String stationWayNumber,
                                                                 Integer stationWayMeter,
                                                                 String lineSide);

}