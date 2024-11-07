package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.rail;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.rail.*;

import java.util.*;

/**
 * SpringJpa-репозиторий, извлекающий из базы данных объекты, касающиеся сущностей рельсовых стыков.
 */
@Repository
public interface JointRepository extends JpaRepository<JointEntity, Long> {

    /**
     * Извлекает полную информацию о токопроводящем рельсовом стыке, лежащем в главном ходу, по его идентификатору.
     *
     * @param jointId идентификатор токопроводящего рельсового стыка в базе данных
     * @return Optional-объект, содержащий полную информацию о рельсовом стыке, если он найден в базе данных
     */
    @Query("""
            SELECT mwcj FROM MainWayConductingJointEntity AS mwcj
            JOIN FETCH mwcj.mainWayPlace AS mwcjp
            JOIN FETCH mwcjp.km AS mwcjpkm
            JOIN FETCH mwcjp.km.transportDirection AS mwcjptd
            JOIN FETCH mwcj.mainWay AS mwcjw
            JOIN FETCH mwcjw.transportDirection AS mwcjwtd
            WHERE
            mwcj.id = :jointId
            """)
    Optional<MainWayConductingJointEntity> findFullMainWayConductingJointInfoById(Long jointId);

    /**
     * Извлекает полную информацию об изолирующем рельсовом стыке у сигнала, лежащем в главном ходу,
     * по его идентификатору.
     *
     * @param jointId идентификатор изолирующего рельсового стыка у сигнала в базе данных
     * @return Optional-объект, содержащий полную информацию о рельсовом стыке, если он найден в базе данных
     */
    @Query("""
            SELECT mwij FROM MainWaySignalIsolatingJointEntity AS mwij
            JOIN FETCH mwij.mainWayPlace AS mwijp
            JOIN FETCH mwijp.km AS mwijpkm
            JOIN FETCH mwijp.km.transportDirection AS mwijptd
            JOIN FETCH mwij.mainWay AS mwijw
            JOIN FETCH mwijw.transportDirection AS mwijwtd
            WHERE
            mwij.id = :jointId
            """)
    Optional<MainWaySignalIsolatingJointEntity> findFullMainWaySignalIsolatingJointInfoById(Long jointId);

    /**
     * Извлекает полную информацию о стрелочном изолирующем рельсовом стыке, лежащем в главном ходу, по его
     * идентификатору.
     *
     * @param jointId идентификатор стрелочного изолирующего рельсового стыка в базе данных
     * @return Optional-объект, содержащий полную информацию о рельсовом стыке, если он найден в базе данных
     */
    @Query("""
            SELECT mwij FROM MainWaySwitchIsolatingJointEntity AS mwij
            JOIN FETCH mwij.mainWayPlace AS mwijp
            JOIN FETCH mwijp.km AS mwijpkm
            JOIN FETCH mwijp.km.transportDirection AS mwijptd
            JOIN FETCH mwij.mainWay AS mwijw
            JOIN FETCH mwijw.transportDirection AS mwijwtd
            JOIN FETCH mwij.switchEntity AS mwijseid
            WHERE
            mwij.id = :jointId
            """)
    Optional<MainWaySwitchIsolatingJointEntity> findFullMainWaySwitchIsolatingJointInfoById(Long jointId);

    /**
     * Извлекает полную информацию о токопроводящем рельсовом стыке, лежащем на станционном пути, по его идентификатору.
     *
     * @param jointId идентификатор токопроводящего рельсового стыка в базе данных
     * @return Optional-объект, содержащий полную информацию о рельсовом, если он найден в базе данных
     */
    @Query("""
            SELECT swcj FROM StationWayConductingJointEntity AS swcj
            JOIN FETCH swcj.stationWayPlace AS swcjp
            JOIN FETCH swcjp.stationWay AS swcjpsw
            JOIN FETCH swcjpsw.station AS swcjpsws
            WHERE
            swcj.id = :jointId
            """)
    Optional<StationWayConductingJointEntity> findFullStationWayConductingJointInfoById(Long jointId);

    /**
     * Извлекает полную информацию об изолирующем рельсовом стыке у сигнала, лежащем на станционном пути, по его
     * идентификатору.
     *
     * @param jointId идентификатор изолирующего рельсового стыка у сигнала станционного пути в базе данных
     * @return Optional-объект, содержащий полную информацию о рельсовом стыке, если он найден в базе данных
     */
    @Query("""
            SELECT swij FROM StationWaySignalIsolatingJointEntity AS swij
            JOIN FETCH swij.stationWayPlace AS swijp
            JOIN FETCH swijp.stationWay AS swijpsw
            JOIN FETCH swijpsw.station AS swijpsws
            WHERE
            swij.id = :jointId
            """)
    Optional<StationWaySignalIsolatingJointEntity> findFullStationWaySignalIsolatingJointInfoById(Long jointId);

    /**
     * Извлекает полную информацию о стрелочном изолирующем рельсовом стыке, лежащем на станционном пути, по его
     * идентификатору.
     *
     * @param jointId идентификатор стрелочного изолирующего рельсового стыка станционного пути в базе данных
     * @return Optional-объект, содержащий полную информацию о рельсовом стыке, если он найден в базе данных
     */
    @Query("""
            SELECT swij FROM StationWaySwitchIsolatingJointEntity AS swij
            JOIN FETCH swij.stationWayPlace AS swijp
            JOIN FETCH swijp.stationWay AS swijpsw
            JOIN FETCH swijpsw.station AS swijpsws
            JOIN FETCH swij.switchEntity AS swijseid
            WHERE
            swij.id = :jointId
            """)
    Optional<StationWaySwitchIsolatingJointEntity> findFullStationWaySwitchIsolatingJointInfoById(Long jointId);

    /**
     * Извлекает полную информацию, преобразующуюся в объекты-проекции, о всех токопроводящих стыках главного хода,
     * лежащих вблизи места, определяемого набором параметров, передающихся в метод.
     *
     * @param transportDirectionId идентификатор железнодорожного направления в базе данных
     * @param mainWayNumber номер главного пути
     * @param coordinate координата места, относительно которой будет осуществлено получение близлежащих стыков
     * @param lineSide сторонность рельсовой нити, в соответствии с которой извлекается информация о рельсовых стыках
     * @return массив объектов-проекций, содержащих основную информацию о рельсовых стыках
     */
    @Query("""
            SELECT mwcj FROM MainWayConductingJointEntity AS mwcj
            JOIN FETCH mwcj.mainWayPlace AS mwcjp
            JOIN FETCH mwcjp.km AS mwcjpkm
            WHERE mwcj.mainWay.transportDirection.id = :transportDirectionId AND
            mwcj.mainWay.number = :mainWayNumber AND
            ((mwcjpkm.km * 1000) + mwcjp.meter) >= (:coordinate - 500) AND
            ((mwcjpkm.km * 1000) + mwcjp.meter) <= (:coordinate + 500) AND
            mwcj.lineSide ILIKE(:lineSide)
            ORDER BY ((mwcjpkm.km * 1000) + mwcjp.meter) ASC
            """)
    List<MainWayJointProjection> findMiniMainWayConductingJointInfoByLocationData(Long transportDirectionId,
                                                                                  Integer mainWayNumber,
                                                                                  int coordinate,
                                                                                  String lineSide);

    /**
     * Извлекает полную информацию, преобразующуюся в объекты-проекции, о всех изолирующих стыках сигналов главного
     * хода, лежащих вблизи места, определяемого набором параметров, передающихся в метод.
     *
     * @param transportDirectionId идентификатор железнодорожного направления в базе данных
     * @param mainWayNumber номер главного пути
     * @param coordinate координата места, относительно которой будет осуществлено получение близлежащих стыков
     * @param lineSide сторонность рельсовой нити, в соответствии с которой извлекается информация о рельсовых стыках
     * @return массив объектов-проекций, содержащих основную информацию о рельсовых стыках */
    @Query("""
            SELECT mwij FROM MainWaySignalIsolatingJointEntity AS mwij
            JOIN FETCH mwij.mainWayPlace AS mwijp
            JOIN FETCH mwijp.km AS mwijpkm
            WHERE mwij.mainWay.transportDirection.id = :transportDirectionId AND
            mwij.mainWay.number = :mainWayNumber AND
            ((mwijpkm.km * 1000) + mwijp.meter) >= (:coordinate - 500) AND
            ((mwijpkm.km * 1000) + mwijp.meter) <= (:coordinate + 500) AND
            mwij.lineSide ILIKE(:lineSide)
            ORDER BY ((mwijpkm.km * 1000) + mwijp.meter) ASC
            """)
    List<MainWayJointProjection> findMiniMainWaySignalIsolatingJointInfoByLocationData(Long transportDirectionId,
                                                                                       Integer mainWayNumber,
                                                                                       int coordinate,
                                                                                       String lineSide);

    /**
     * Извлекает полную информацию, преобразующуюся в объекты-проекции, о всех изолирующих стыках стрелочных переводов
     * главного хода, лежащих вблизи места, определяемого набором параметров, передающихся в метод.
     *
     * @param transportDirectionId идентификатор железнодорожного направления в базе данных
     * @param mainWayNumber номер главного пути
     * @param coordinate координата места, относительно которой будет осуществлено получение близлежащих стыков
     * @param lineSide сторонность рельсовой нити, в соответствии с которой извлекается информация о рельсовых стыках
     * @return массив объектов-проекций, содержащих основную информацию о рельсовых стыках
     */
    @Query("""
            SELECT mwij FROM MainWaySwitchIsolatingJointEntity AS mwij
            JOIN FETCH mwij.mainWayPlace AS mwijp
            JOIN FETCH mwijp.km AS mwijpkm
            WHERE mwij.mainWay.transportDirection.id = :transportDirectionId AND
            mwij.mainWay.number = :mainWayNumber AND
            ((mwijpkm.km * 1000) + mwijp.meter) >= (:coordinate - 500) AND
            ((mwijpkm.km * 1000) + mwijp.meter) <= (:coordinate + 500) AND
            mwij.lineSide ILIKE(:lineSide)
            ORDER BY ((mwijpkm.km * 1000) + mwijp.meter) ASC
            """)
    List<MainWayJointProjection> findMiniMainWaySwitchIsolatingJointInfoByLocationData(Long transportDirectionId,
                                                                                       Integer mainWayNumber,
                                                                                       int coordinate,
                                                                                       String lineSide);

    /**
     * Извлекает полную информацию, преобразующующуюся в объекты-проекции, о всех токопроводящих рельсовых стыках
     * станционного пути, лежащих вблизи места, определяемого набором параметров, передающихся в метод.
     *
     * @param stationId идентификатор станции в базе данных
     * @param stationWayNumber номер станционного пути, на котором расположено конкретное место
     * @param stationWayMeter цифровое обозначение метра, на котором расположено конкретное место
     * @param lineSide сторонность рельсовой нити, в соответствии с которой извлекается информация о рельсовых стыках
     * @return массив объектов-проекций, содержащих основную информацию о рельсовых стыках
     */
    @Query("""
            SELECT swcj FROM StationWayConductingJointEntity AS swcj
            JOIN FETCH swcj.stationWayPlace AS swcjp
            WHERE
            swcjp.stationWay.station.id = :stationId AND
            swcjp.stationWay.name = :stationWayNumber AND
            swcjp.placeMeter >= (:stationWayMeter - 500) AND swcjp.placeMeter <= (:stationWayMeter + 500) AND
            swcj.lineSide ILIKE(:lineSide)
            ORDER BY swcjp.placeMeter ASC
            """)
    List<StationWayJointProjection> findMiniStationWayConductingJointInfoByLocationData(Long stationId,
                                                                                        String stationWayNumber,
                                                                                        Integer stationWayMeter,
                                                                                        String lineSide);

    /**
     * Извлекает полную информацию, преобразующующуюся в объекты-проекции, о всех изолирующих рельсовых стыках сигналов
     * станционного пути, лежащих вблизи места, определяемого набором параметров, передающихся в метод.
     *
     * @param stationId идентификатор станции в базе данных
     * @param stationWayNumber номер станционного пути, на котором расположено конкретное место
     * @param stationWayMeter цифровое обозначение метра, на котором расположено конкретное место
     * @param lineSide сторонность рельсовой нити, в соответствии с которой извлекается информация о рельсовых стыках
     * @return массив объектов-проекций, содержащих основную информацию о рельсовых стыках
     */
    @Query("""
            SELECT swij FROM StationWaySignalIsolatingJointEntity AS swij
            JOIN FETCH swij.stationWayPlace AS swijp
            WHERE
            swijp.stationWay.station.id = :stationId AND
            swijp.stationWay.name = :stationWayNumber AND
            swijp.placeMeter >= (:stationWayMeter - 500) AND swijp.placeMeter <= (:stationWayMeter + 500) AND
            swij.lineSide ILIKE(:lineSide)
            ORDER BY swijp.placeMeter ASC
            """)
    List<StationWayJointProjection> findMiniStationWaySignalIsolatingJointInfoByLocationData(Long stationId,
                                                                                         String stationWayNumber,
                                                                                         Integer stationWayMeter,
                                                                                         String lineSide);

    /**
     * Извлекает полную информацию, преобразующующуюся в объекты-проекции, о всех изолирующих рельсовых стыках
     * стрелочных переводов станционного пути, лежащих вблизи места, определяемого набором параметров,
     * передающихся в метод.
     *
     * @param stationId идентификатор станции в базе данных
     * @param stationWayNumber номер станционного пути, на котором расположено конкретное место
     * @param stationWayMeter цифровое обозначение метра, на котором расположено конкретное место
     * @param lineSide сторонность рельсовой нити, в соответствии с которой извлекается информация о рельсовых стыках
     * @return массив объектов-проекций, содержащих основную информацию о рельсовых стыках
     */
    @Query("""
            SELECT swij FROM StationWaySwitchIsolatingJointEntity AS swij
            JOIN FETCH swij.stationWayPlace AS swijp
            WHERE
            swijp.stationWay.station.id = :stationId AND
            swijp.stationWay.name = :stationWayNumber AND
            swijp.placeMeter >= (:stationWayMeter - 500) AND swijp.placeMeter <= (:stationWayMeter + 500) AND
            swij.lineSide ILIKE(:lineSide)
            ORDER BY swijp.placeMeter ASC
            """)
    List<StationWayJointProjection> findMiniStationWaySwitchIsolatingJointInfoByLocationData(Long stationId,
                                                                                         String stationWayNumber,
                                                                                         Integer stationWayMeter,
                                                                                         String lineSide);

}