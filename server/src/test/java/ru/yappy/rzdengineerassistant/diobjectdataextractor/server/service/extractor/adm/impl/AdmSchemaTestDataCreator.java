package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.adm.impl;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.WayExploitativeSectionEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.WayMaintenanceDistanceEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.empl.EmplSchemaTestDataCreator;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr.InfrSchemaTestDataCreator;

import java.util.Set;

public class AdmSchemaTestDataCreator {

    public static WayMaintenanceDistanceEntity createTestWayMaintenanceDistanceEntity() {
        WayMaintenanceDistanceEntity wayMaintenanceDistanceEntity = new WayMaintenanceDistanceEntity();
        wayMaintenanceDistanceEntity.setId(777L);
        wayMaintenanceDistanceEntity.setName("DistanceName");
        wayMaintenanceDistanceEntity.setNumber(7);
        wayMaintenanceDistanceEntity.setRegionalDirectorateAbbreviation("DI");
        wayMaintenanceDistanceEntity.setIsIch(false);
        wayMaintenanceDistanceEntity.setManagers(Set.of(
                EmplSchemaTestDataCreator.createTestWayMaintenanceDistanceEmployeeEntity()));
        wayMaintenanceDistanceEntity.setMainWays(Set.of(
                InfrSchemaTestDataCreator.createTestMainWayEntity()));
        wayMaintenanceDistanceEntity.setStations(Set.of());
        return wayMaintenanceDistanceEntity;
    }

    public static WayExploitativeSectionEntity createTestWayExploitativeSectionEntity() {
        WayExploitativeSectionEntity wayExploitativeSectionEntity = new WayExploitativeSectionEntity();
        wayExploitativeSectionEntity.setId(777L);
        wayExploitativeSectionEntity.setNumber(7);
        wayExploitativeSectionEntity.setWayMaintenanceDistance(createTestWayMaintenanceDistanceEntity());
        return wayExploitativeSectionEntity;
    }

}