package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.empl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl.*;

import java.util.List;

/** SpringJPA-репозиторий, извлекающий из базы данных объекты, касающиеся сущностей сотрудников подразделений
 * ОАО "РЖД". */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    /**
     * Извлекает информацию о всех работниках линейного участка по данным конкретного места - идентификатору
     * транспортного направления и километру на его протяжении.
     *
     * @param transportDirectionId идентификатор железнодорожного транспортного направления
     * @param km километр на протяжении транспортного направления
     * @return список объектов-сущностей с информацией о работниках линейного участка
     */
    @Query("""
            SELECT linearEmpl FROM WayLinearSectionEmployeeEntity AS linearEmpl
            JOIN FETCH linearEmpl.wayMaintenanceDistance AS dist
            JOIN linearEmpl.wayLinearSection AS linearSection
            JOIN linearSection.kilometers AS km
            JOIN km.transportDirection AS td
            WHERE td.id = :transportDirectionId AND km.km = :km
            """)
    List<WayMaintenanceDistanceEmployeeEntity> findAllLinearSectionWorkersByMainWayLocationIdAndKm(
            Long transportDirectionId, Integer km);

    /**
     * Извлекает информацию о всех работниках линейного участка по идентификатору станции и наименованию станционного
     * пути.
     *
     * @param stationId идентификатор станции
     * @param stationWayNumber наименование станционного пути
     * @return список объектов-сущностей с информацией о работниках линейного участка
     */
    @Query("""
            SELECT linearEmpl FROM WayLinearSectionEmployeeEntity AS linearEmpl
            JOIN FETCH linearEmpl.wayMaintenanceDistance AS dist
            JOIN linearEmpl.wayLinearSection AS linearSection
            JOIN linearSection.stationWays AS stationWays
            JOIN stationWays.station AS station
            WHERE station.id = :stationId AND stationWays.name = :stationWayNumber
            """)
    List<WayMaintenanceDistanceEmployeeEntity> findAllLinearSectionWorkersByStationWayLocationIdAndKm(
            Long stationId, String stationWayNumber);

    /**
     * Извлекает информацию о всех работниках дистанции пути, причастных к осуществлению производственных и
     * технических функций предприятия (участники совещаний, руководители процессов и т.п.).
     *
     * @param wayDistanceId идентификатор дистанции пути
     * @return список объектов-сущностей с информацией о причастных работниках дистанции пути
     */
    @Query("""
            SELECT pDistEmpl FROM WayMaintenanceDistanceEmployeeEntity AS pDistEmpl
            JOIN pDistEmpl.wayMaintenanceDistance AS dist
            WHERE dist.id = :wayDistanceId AND (TYPE(pDistEmpl) IN
            (WayDistanceAdmEmployeeEntity, WayDistanceDepartmentEmployeeEntity, WayExploitativeSectionEmployeeEntity) AND
            (LOWER(pDistEmpl.fullTitle) LIKE '%начальник%' OR LOWER(pDistEmpl.fullTitle) LIKE '%инженер%' OR
            LOWER(pDistEmpl.fullTitle) LIKE '%техник%'))
            """)
    List<WayMaintenanceDistanceEmployeeEntity> findAllInvolvedWayDistanceEmployeesByDistanceId(Long wayDistanceId);

}