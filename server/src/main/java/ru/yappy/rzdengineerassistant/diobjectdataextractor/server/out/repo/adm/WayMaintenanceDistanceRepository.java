package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.adm;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.WayMaintenanceDistanceEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.adm.WayMaintenanceDistanceProjection;

import java.util.*;

/**
 * SpringJpa-репозиторий, извлекающий из базы данных объекты, касающиеся сущностей дистанций пути.
 */
@Repository
public interface WayMaintenanceDistanceRepository extends JpaRepository<WayMaintenanceDistanceEntity, Long> {

    /**
     * Извлекает полноценную информацию о дистанции пути по ее идентификатору, необходимую для мэппинга в
     * {@link ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm.WayMaintenanceDistanceDto}
     *
     * @param id идентификатор дистанции пути
     * @return полноценная информация о дистанции пути, если передан верный идентификатор
     */
    @Query("""
            SELECT wmd FROM WayMaintenanceDistanceEntity AS wmd
            JOIN FETCH wmd.managers AS mans
            JOIN FETCH wmd.stations AS sts
            JOIN FETCH wmd.mainWays AS mws
            JOIN FETCH mws.transportDirection AS mwstd
            WHERE wmd.id = :id
            """)
    Optional<WayMaintenanceDistanceEntity> findRequiredWayMaintenanceDistanceInfoById(Long id);

    /**
     * Извлекает минимальную информацию о всех дистанциях пути в границах региональной дирекции инфраструктуры.
     *
     * @param directorateAbbreviation аббревиатура региональной дирекции инфраструктуры
     * @return список из объектов, содержащих минимальную информацию о дистанциях пути в границах дирекции инфраструктуры
     */
    List<WayMaintenanceDistanceProjection> findAllByRegionalDirectorateAbbreviationOrderByNumber(
            String directorateAbbreviation);

}