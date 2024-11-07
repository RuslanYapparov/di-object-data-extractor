package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.infr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.infr.InterstationTrackProjection;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.infr.StationProjection;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.infr.StationWayProjection;

import java.util.Optional;
import java.util.Set;

/** SpringJPA-репозиторий, извлекающий из базы данных объекты, касающиеся сущностей локаций. */
@Repository
public interface LocationRepository extends JpaRepository<StationEntity, Long> {

    /** Извлекает минимально необходимую информацию
     * {@link ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.infr.InterstationTrackProjection}
     * о перегоне на основании данных конкретного места, расположенного на нем.
     *
     * @param transportDirectionId идентификатор железнодорожного направления, на котором находится перегон
     * @param coordinate координата места, расположенного на перегоне
     * @return объект, содержащий минимально необходимую информацию о перегоне
      */
    @Query("""
            SELECT it FROM InterstationTrackEntity AS it
            JOIN FETCH it.startStation AS itss
            JOIN FETCH it.endStation AS ites
            WHERE it.endStation.section.startKm.transportDirection.id = :transportDirectionId AND
            (((it.startStation.section.endKm * 1000) + it.startStation.section.endMeter) <= :coordinate OR
            (it.startStation.section.endKm > 1234 AND it.endStation.section.startKm.km < 20)) AND
            ((it.endStation.section.startKm.km * 1000) + it.endStation.section.startMeter) >= :coordinate AND
            :coordinate > 0
            """)
    Optional<InterstationTrackProjection> findInterstationTrackProjectionByLocationParameters(Long transportDirectionId,
                                                                                              int coordinate);

    /** Извлекает минимально необходимую информацию
     * {@link ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.infr.StationProjection}
     * о станции на основании данных конкретного места, расположенного на ее главных путях.
     *
     * @param transportDirectionId идентификатор железнодорожного направления, на котором находится станция
     * @param coordinate координата места, расположенного на главных путях станции
     * @return объект, содержащий минимально необходимую информацию о перегоне
     */
    @Query("""
            SELECT s FROM StationEntity AS s
            WHERE s.section.startKm.transportDirection.id = :transportDirectionId AND
            (s.section.startKm.km * 1000 + s.section.startMeter) <= :coordinate AND
            (s.section.endKm * 1000 + s.section.endMeter) >= :coordinate
            """)
    Optional<StationProjection> findStationProjectionByLocationParameters(Long transportDirectionId, int coordinate);

    /**
     * Извлекает минимальную информацию о всех станционных путях на основании идентификатора станции.
     *
     * @param stationId идентификатор станции, на которой находятся станционные пути
     * @return сет с минимальными данными о станционных путях
     */
    @Query("""
            SELECT sw FROM StationWayEntity AS sw
            JOIN FETCH sw.station AS s
            WHERE s.id = :stationId
            """)
    Set<StationWayProjection> findAllStationWayProjectionsByStationId(Long stationId);

}