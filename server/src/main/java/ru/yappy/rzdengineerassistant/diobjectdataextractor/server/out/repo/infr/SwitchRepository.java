package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.infr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.InterstationTrackSwitchEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationMainWaySwitchEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationWaySwitchEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.SwitchEntity;

import java.util.Optional;

/**
 * SpringJpa-репозиторий, извлекающий из базы данных объекты, касающиеся сущностей стрелочных переводов.
 */
@Repository
public interface SwitchRepository extends JpaRepository<SwitchEntity, Long> {

    /**
     * Извлекает полную информацию о стрелочном переводе, расположенном на перегоне, по его идентификатору.
     *
     * @param id идентификатор стрелочного перевода
     * @return Optional-объект, содержащий полную информацию о стрелочном переводе, если он найден в базе данных
     */
    @Query("""
            SELECT its FROM InterstationTrackSwitchEntity AS its
            JOIN FETCH its.frameRailJointPlace AS itsfr
            JOIN FETCH itsfr.km AS itsfrkm
            JOIN FETCH itsfrkm.transportDirection AS itsfrkmtd
            JOIN FETCH its.mainWay AS itsmw
            JOIN FETCH itsmw.transportDirection AS itsmwtd
            JOIN FETCH its.controlStation AS itscs
            WHERE its.id = :id
            """)
    Optional<InterstationTrackSwitchEntity> findInterstationTrackSwitchById(Long id);

    /**
     * Извлекает полную информацию о стрелочном переводе, расположенном на главном пути станции, по его идентификатору.
     *
     * @param id идентификатор стрелочного перевода
     * @return Optional-объект, содержащий полную информацию о стрелочном переводе, если он найден в базе данных
     */
    @Query("""
            SELECT smws FROM StationMainWaySwitchEntity AS smws
            JOIN FETCH smws.frameRailJointPlace AS smwsfr
            JOIN FETCH smwsfr.km AS smwsfrkm
            JOIN FETCH smwsfrkm.transportDirection AS smwsfrkmtd
            JOIN FETCH smws.stationMainWay AS smwssmw
            JOIN FETCH smwssmw.mainWay AS smwssmwmw
            JOIN FETCH smwssmw.station AS smwssmws
            WHERE smws.id = :id
            """)
    Optional<StationMainWaySwitchEntity> findStationMainWaySwitchById(Long id);

    /**
     * Извлекает полную информацию о стрелочном переводе, расположенном на станционном пути, по его идентификатору.
     *
     * @param id идентификатор стрелочного перевода
     * @return Optional-объект, содержащий полную информацию о стрелочном переводе, если он найден в базе данных
     */
    @Query("""
            SELECT sws FROM StationWaySwitchEntity AS sws
            JOIN FETCH sws.frameRailStationWayPlace AS swsfr
            JOIN FETCH swsfr.stationWay AS swsfrsw
            JOIN FETCH swsfrsw.station AS swsfrsws
            WHERE sws.id = :id
            """)
    Optional<StationWaySwitchEntity> findStationWaySwitchById(Long id);

    /**
     * Извлекает полную информацию о стрелочном переводе, расположенном на железнодорожном перегоне, по параметрам,
     * определяющим участок пути, относящийся к рельсу (рельс уложен на стрелочном переводе).
     *
     * @param transportDirectionId идентификатор железнодорожного направления
     * @param mainWayId идентификатор главного пути железнодорожного направления
     * @param startKm километр, на котором расположен передний торец рельса
     * @param startMeter метр километра, на котором расположен передний торец рельса
     * @param endKm километр, на котором расположен задний торец рельса
     * @param endMeter метр километра, на котором расположен задний торец рельса
     * @return Optional-объект, содержащий информацию о стрелочном переводе на перегоне, если он найден в базе данных
     */
    @Query("""
            SELECT its FROM InterstationTrackSwitchEntity AS its
            JOIN FETCH its.frameRailJointPlace AS itsfr
            JOIN FETCH itsfr.km AS itsfrkm
            JOIN FETCH itsfrkm.transportDirection AS itsfrkmtd
            JOIN FETCH its.mainWay AS itsmw
            JOIN FETCH itsmw.transportDirection AS itsmwtd
            JOIN FETCH its.controlStation AS itscs
            WHERE itsfrkmtd.id = :transportDirectionId AND
            itsmw.id = :mainWayId AND
            (itsfrkm.km = :startKm OR itsfrkm.km = :endKm) AND
            (((itsfr.meter - 37) < :startMeter AND itsfr.meter >= :endMeter) OR
            (itsfr.meter <= :startMeter AND (itsfr.meter + 37) > :endMeter))
            """)
    Optional<InterstationTrackSwitchEntity> findFullInterstationTrackSwitchInfoByLocationParameters(
            Long transportDirectionId,
            Long mainWayId,
            Integer startKm,
            Integer startMeter,
            Integer endKm,
            Integer endMeter);

    /**
     * Извлекает полную информацию о стрелочном переводе, расположенном на главном пути станции, по параметрам,
     * определяющим участок пути, относящийся к рельсу (рельс уложен на стрелочном переводе).
     *
     * @param transportDirectionId идентификатор железнодорожного направления
     * @param mainWayId идентификатор главного пути железнодорожного направления
     * @param startKm километр, на котором расположен передний торец рельса
     * @param startMeter метр километра, на котором расположен передний торец рельса
     * @param endKm километр, на котором расположен задний торец рельса
     * @param endMeter метр километра, на котором расположен задний торец рельса
     * @return Optional-объект, содержащий информацию о стрелочном переводе на главном пути станции, если он найден
     * в базе данных
     */
    @Query("""
            SELECT smws FROM StationMainWaySwitchEntity AS smws
            JOIN FETCH smws.frameRailJointPlace AS smwsfr
            JOIN FETCH smwsfr.km AS smwsfrkm
            JOIN FETCH smwsfrkm.transportDirection AS smwsfrkmtd
            JOIN FETCH smws.stationMainWay AS smwssmw
            JOIN FETCH smwssmw.mainWay AS smwssmwmw
            JOIN FETCH smwssmw.station AS smwssmws
            WHERE smwsfrkmtd.id = :transportDirectionId AND
            smwssmwmw.id = :mainWayId AND
            (smwsfrkm.km = :startKm OR smwsfrkm.km = :endKm) AND (smwsfr.meter = :startMeter OR smwsfr.meter = :endMeter)
            """)
    Optional<StationMainWaySwitchEntity> findFullStationMainWaySwitchInfoByLocationParameters(
            Long transportDirectionId,
            Long mainWayId,
            Integer startKm,
            Integer startMeter,
            Integer endKm,
            Integer endMeter);

    /**
     * Извлекает полную информацию о стрелочном переводе, расположенном на станционном пути, по параметрам, определяющим
     * участок пути, относящийся к рельсу (рельс уложен на стрелочном переводе).
     *
     * @param stationWayId идентификатор станционного пути
     * @param startMeter метр, на котором расположен передний торец рельса
     * @param endMeter метр, на котором расположен задний торец рельса
     * @return Optional-объект, содержащий информацию о стрелочном переводе на станционном пути,
     * если он найден в базе данных
     */
    @Query("""
            SELECT sws FROM StationWaySwitchEntity AS sws
            JOIN FETCH sws.frameRailStationWayPlace AS swsfr
            JOIN FETCH swsfr.stationWay AS swsfrsw
            JOIN FETCH swsfrsw.station AS swsfrsws
            WHERE swsfrsw.id = :stationWayId AND
            (((swsfr.placeMeter - 37) < :startMeter AND swsfr.placeMeter >= :endMeter) OR
            (swsfr.placeMeter <= :startMeter AND (swsfr.placeMeter + 37) > :endMeter))
            """)
    Optional<StationWaySwitchEntity> findFullStationWaySwitchInfoByLocationParameters(
            Long stationWayId,
            Integer startMeter,
            Integer endMeter);

}