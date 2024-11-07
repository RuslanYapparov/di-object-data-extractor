package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr;

import lombok.experimental.UtilityClass;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.adm.impl.AdmSchemaTestDataCreator;

import java.time.LocalDate;
import java.util.Set;

@UtilityClass
public class InfrSchemaTestDataCreator {

    public static TransportDirectionEntity createTestTransportDirectionEntity() {
        TransportDirectionEntity transportDirectionEntity = new TransportDirectionEntity();
        transportDirectionEntity.setId(777L);
        transportDirectionEntity.setName("ТЕСТ - ТЕСТ");
        return transportDirectionEntity;
    }

    public static StationEntity createTestStationEntity() {
        StationEntity stationEntity = new StationEntity();
        stationEntity.setId(777L);
        stationEntity.setName("StationName");
        stationEntity.setSection(createTestMainWaySectionEntity());
        stationEntity.setStationClass("StationClass");
        stationEntity.setAppointment("StationAppointment");
        stationEntity.setTechnologicalUse("StationTechnologicalUse");
        stationEntity.setWayMaintenanceDistances(
                Set.of(AdmSchemaTestDataCreator.createTestWayMaintenanceDistanceEntity()));
        stationEntity.setRegionalDirectorateAbbreviation("D");
        return stationEntity;
    }

    public static MainWayEntity createTestMainWayEntity() {
        MainWayEntity mainWayEntity = new MainWayEntity();
        mainWayEntity.setId(777L);
        mainWayEntity.setNumber(1);
        mainWayEntity.setTransportDirection(createTestTransportDirectionEntity());
        return mainWayEntity;
    }

    public static MainWaySectionEntity createTestMainWaySectionEntity() {
        KilometerEntity kilometerEntity = new KilometerEntity();
        kilometerEntity.setTransportDirection(createTestTransportDirectionEntity());
        kilometerEntity.setKm(7777);

        MainWaySectionEntity mainWaySectionEntity = new MainWaySectionEntity();
        mainWaySectionEntity.setId(777L);
        mainWaySectionEntity.setLength(77);
        mainWaySectionEntity.setStartKm(kilometerEntity);
        mainWaySectionEntity.setStartMeter(777);
        mainWaySectionEntity.setEndKm(7777);
        mainWaySectionEntity.setEndMeter(777);
        return mainWaySectionEntity;
    }

    public static StationWaySectionEntity createTestStationWaySectionEntity() {
        StationEntity stationEntity = createTestStationEntity();

        StationWayEntity stationWayEntity = new StationWayEntity();
        stationWayEntity.setId(777L);
        stationWayEntity.setStation(stationEntity);
        stationWayEntity.setName("77");

        StationWaySectionEntity stationWaySectionEntity = new StationWaySectionEntity();
        stationWaySectionEntity.setId(777L);
        stationWaySectionEntity.setLength(77);
        stationWaySectionEntity.setStartMeter(777);
        stationWaySectionEntity.setEndMeter(777);
        stationWaySectionEntity.setStationWay(stationWayEntity);
        return stationWaySectionEntity;
    }

    public static StationMainWayEntity createTestStationMainWayEntity() {
        StationMainWayEntity stationMainWayEntity = new StationMainWayEntity();
        stationMainWayEntity.setStation(createTestStationEntity());
        stationMainWayEntity.setMainWay(createTestMainWayEntity());
        stationMainWayEntity.setSection(createTestMainWaySectionEntity());
        return stationMainWayEntity;
    }

    public static MainWayPlaceEntity createTestMainWayPlaceEntity() {
        KilometerEntity kilometerEntity = new KilometerEntity();
        kilometerEntity.setTransportDirection(createTestTransportDirectionEntity());
        kilometerEntity.setKm(7777);

        MainWayPlaceEntity mainWayPlaceEntity = new MainWayPlaceEntity();
        mainWayPlaceEntity.setId(777L);
        mainWayPlaceEntity.setKm(kilometerEntity);
        mainWayPlaceEntity.setMeter(777);
        return mainWayPlaceEntity;
    }

    public static StationMainWaySwitchEntity createTestStationMainWaySwitchEntity() {
        StationMainWaySwitchEntity stationMainWaySwitchEntity = new StationMainWaySwitchEntity();
        stationMainWaySwitchEntity.setId(777L);
        stationMainWaySwitchEntity.setName("ТЕСТ");
        stationMainWaySwitchEntity.setBallastType("ЩЕБЕНЬ");
        stationMainWaySwitchEntity.setBeamsMaterial("Ж/Б");
        stationMainWaySwitchEntity.setControlType("ТЕСТ");
        stationMainWaySwitchEntity.setCrossMarking("1/11");
        stationMainWaySwitchEntity.setFullName("ТЕСТ ТЕСТ");
        stationMainWaySwitchEntity.setInstallationDate(LocalDate.of(2024, 1, 1));
        stationMainWaySwitchEntity.setRailType("Р65");
        stationMainWaySwitchEntity.setProject("2750");
        stationMainWaySwitchEntity.setLineSide("ПРАВЫЙ");
        stationMainWaySwitchEntity.setGauge(7777);
        stationMainWaySwitchEntity.setPassedTonnageBeforeInstall(7777.77f);
        stationMainWaySwitchEntity.setPassedTonnageAfterInstall(7777.77f);
        stationMainWaySwitchEntity.setOutcrossCurveLength(7777);
        stationMainWaySwitchEntity.setWayNameSwitchGoesOn("ТЕСТ");
        stationMainWaySwitchEntity.setStationPark(new StationParkEntity());
        stationMainWaySwitchEntity.setStationMainWay(createTestStationMainWayEntity());
        stationMainWaySwitchEntity.setFrameRailJointPlace(createTestMainWayPlaceEntity());
        return stationMainWaySwitchEntity;
    }

    public static StationWayEntity createTestStationWayEntity() {
        StationWayEntity stationWayEntity = new StationWayEntity();
        stationWayEntity.setId(777L);
        stationWayEntity.setStation(createTestStationEntity());
        stationWayEntity.setName("7а");
        stationWayEntity.setAppointment("Тестовое назначение");
        stationWayEntity.setStartSwitch("15");
        stationWayEntity.setEndSwitch("17");
        stationWayEntity.setFreightTrainSpeed(40);
        stationWayEntity.setPassengerTrainSpeed(50);
        stationWayEntity.setUsefulLength(1777);
        stationWayEntity.setFullLength(1888);
        return stationWayEntity;
    }

    public static StationWayPlaceEntity createTestStationWayPlaceEntity() {
        StationWayPlaceEntity stationWayPlaceEntity = new StationWayPlaceEntity();
        stationWayPlaceEntity.setStationWay(createTestStationWayEntity());
        stationWayPlaceEntity.setPlaceMeter(777);
        return stationWayPlaceEntity;
    }

}