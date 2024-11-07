package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail;

import lombok.experimental.UtilityClass;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr.InfrSchemaTestDataCreator;

import java.time.LocalDate;

@UtilityClass
public class RailSchemaTestDataCreator {

    public static MainWayRailEntity createTestMainWayRailEntity() {
        MainWaySectionEntity mainWaySectionEntity =
                InfrSchemaTestDataCreator.createTestMainWaySectionEntity();
        MainWayEntity mainWayEntity =
                InfrSchemaTestDataCreator.createTestMainWayEntity();

        MainWayRailEntity mainWayRailEntity = new MainWayRailEntity();
        mainWayRailEntity.setId(777L);
        mainWayRailEntity.setRailKind("ПЛЕТЬ");
        mainWayRailEntity.setLineSide("ЛЕВАЯ");
        mainWayRailEntity.setMainWaySection(mainWaySectionEntity);
        mainWayRailEntity.setMainWay(mainWayEntity);
        return mainWayRailEntity;
    }

    public static StationWayRailEntity createTestStationWayRailEntity() {
        StationWaySectionEntity stationWaySectionEntity =
                InfrSchemaTestDataCreator.createTestStationWaySectionEntity();
        StationWayEntity stationWayEntity = new StationWayEntity();
        stationWayEntity.setId(777L);
        stationWayEntity.setName("ТЕСТ");
        StationWayRailEntity stationWayRailEntity = new StationWayRailEntity();
        stationWayRailEntity.setId(777L);
        stationWayRailEntity.setRailKind("ПЛЕТЬ");
        stationWayRailEntity.setLineSide("ПРАВАЯ");
        stationWayRailEntity.setStationWaySection(stationWaySectionEntity);
        return stationWayRailEntity;
    }

    public static MainWayLashEntity createTestMainWayLashEntity() {
        MainWayLashEntity mainWayLashEntity = new MainWayLashEntity();
        mainWayLashEntity.setId(777L);
        mainWayLashEntity.setName("ТЕСТ");
        mainWayLashEntity.setLength(7777.77f);
        mainWayLashEntity.setLineSide("ЛЕВАЯ");
        mainWayLashEntity.setWeldingCompany("ТЕСТ");
        mainWayLashEntity.setStartCentimeterOnMeter(77);
        mainWayLashEntity.setEndCentimeterOnMeter(77);
        mainWayLashEntity.setInstallationDate(LocalDate.of(2024, 1, 1));
        mainWayLashEntity.setInstalledBy("ТЕСТ");
        mainWayLashEntity.setInstallationType("НОВАЯ");
        mainWayLashEntity.setPassedTonnageBeforeInstall(7777.77f);
        mainWayLashEntity.setPassedTonnageAfterInstall(7777.77f);
        mainWayLashEntity.setLastTemperatureTensionDischargingDate(LocalDate.of(2024, 1, 1));
        mainWayLashEntity.setLastTemperatureTensionDischargingTemperature(77);
        mainWayLashEntity.setLastTemperatureTensionDischargingReason("ТЕСТ");
        mainWayLashEntity.setMainWay(InfrSchemaTestDataCreator.createTestMainWayEntity());
        mainWayLashEntity.setMainWaySection(InfrSchemaTestDataCreator
                .createTestMainWaySectionEntity());
        return mainWayLashEntity;
    }

    public static StationWayLashEntity createTestStationWayLashEntity() {
        StationWayLashEntity stationWayLashEntity = new StationWayLashEntity();
        stationWayLashEntity.setId(777L);
        stationWayLashEntity.setName("ТЕСТ");
        stationWayLashEntity.setLength(7777.77f);
        stationWayLashEntity.setLineSide("ЛЕВАЯ");
        stationWayLashEntity.setWeldingCompany("ТЕСТ");
        stationWayLashEntity.setStartCentimeterOnMeter(77);
        stationWayLashEntity.setEndCentimeterOnMeter(77);
        stationWayLashEntity.setInstallationDate(LocalDate.of(2024, 1, 1));
        stationWayLashEntity.setInstalledBy("ТЕСТ");
        stationWayLashEntity.setInstallationType("НОВАЯ");
        stationWayLashEntity.setPassedTonnageBeforeInstall(7777.77f);
        stationWayLashEntity.setPassedTonnageAfterInstall(7777.77f);
        stationWayLashEntity.setLastTemperatureTensionDischargingDate(LocalDate.of(2024, 1, 1));
        stationWayLashEntity.setLastTemperatureTensionDischargingTemperature(77);
        stationWayLashEntity.setLastTemperatureTensionDischargingReason("ТЕСТ");
        stationWayLashEntity.setStationWaySection(InfrSchemaTestDataCreator.createTestStationWaySectionEntity());
        return stationWayLashEntity;
    }

    public static JointEntity createTestJointEntity(JointType type, boolean onMainWay) {
        JointEntity jointEntity;
        if (onMainWay) {
            jointEntity = createMainWayJointEntity(type);
        } else {
            jointEntity = createStationWayJointEntity(type);
        }
        jointEntity.setId(777L);
        jointEntity.setLineSide("ЛЕВАЯ");
        jointEntity.setRailBeforeId(7777L);
        jointEntity.setRailAfter(createTestMainWayRailEntity());
        jointEntity.setStatus("ТЕСТ");
        jointEntity.setType("ТЕСТОВЫЙ");
        jointEntity.setPadsType("ТЕСТОВЫЕ");
        jointEntity.setPadAmountOfHoles(4);
        jointEntity.setVerticalStep(7.7f);
        jointEntity.setHorizontalStep(7.7f);
        jointEntity.setGap(10);
        jointEntity.setLastMeasureDate(LocalDate.of(2024, 1, 1));
        jointEntity.setLastMeasureRailTemp(7);
        return jointEntity;
    }

    private static JointEntity createMainWayJointEntity(JointType type) {
        JointEntity jointEntity;
        switch (type) {
            case ISOLATING_SIGNAL -> {
                jointEntity = new MainWaySignalIsolatingJointEntity();
                ((MainWaySignalIsolatingJointEntity) jointEntity)
                        .setMainWay(InfrSchemaTestDataCreator.createTestMainWayEntity());
                ((MainWaySignalIsolatingJointEntity) jointEntity)
                        .setMainWayPlace(InfrSchemaTestDataCreator.createTestMainWayPlaceEntity());
                setAdditionalIsolatingJointInfo((MainWaySignalIsolatingJointEntity) jointEntity);
                ((MainWaySignalIsolatingJointEntity) jointEntity).setSignalName("7Т");
            }
            case ISOLATING_SWITCH -> {
                jointEntity = new MainWaySwitchIsolatingJointEntity();
                ((MainWaySwitchIsolatingJointEntity) jointEntity)
                        .setMainWay(InfrSchemaTestDataCreator.createTestMainWayEntity());
                ((MainWaySwitchIsolatingJointEntity) jointEntity)
                        .setMainWayPlace(InfrSchemaTestDataCreator.createTestMainWayPlaceEntity());
                setAdditionalIsolatingJointInfo((MainWaySwitchIsolatingJointEntity) jointEntity);
                ((MainWaySwitchIsolatingJointEntity) jointEntity)
                        .setSwitchEntity(InfrSchemaTestDataCreator.createTestStationMainWaySwitchEntity());
                ((MainWaySwitchIsolatingJointEntity) jointEntity).setJointNumber(1);
            }
            default -> {
                jointEntity = new MainWayConductingJointEntity();
                ((MainWayConductingJointEntity) jointEntity)
                        .setMainWay(InfrSchemaTestDataCreator.createTestMainWayEntity());
                ((MainWayConductingJointEntity) jointEntity)
                        .setMainWayPlace(InfrSchemaTestDataCreator.createTestMainWayPlaceEntity());
                setAdditionalConductingJointInfo((MainWayConductingJointEntity) jointEntity);
            }
        }
        return jointEntity;
    }

    private static JointEntity createStationWayJointEntity(JointType type) {
        JointEntity jointEntity;
        switch (type) {
            case ISOLATING_SIGNAL -> {
                jointEntity = new StationWaySignalIsolatingJointEntity();
                ((StationWaySignalIsolatingJointEntity) jointEntity)
                        .setStationWayPlace(InfrSchemaTestDataCreator.createTestStationWayPlaceEntity());
                setAdditionalIsolatingJointInfo((StationWaySignalIsolatingJointEntity) jointEntity);
                ((StationWaySignalIsolatingJointEntity) jointEntity).setSignalName("7Т");
            }
            case ISOLATING_SWITCH -> {
                jointEntity = new StationWaySwitchIsolatingJointEntity();
                ((StationWaySwitchIsolatingJointEntity) jointEntity)
                        .setStationWayPlace(InfrSchemaTestDataCreator.createTestStationWayPlaceEntity());
                setAdditionalIsolatingJointInfo((StationWaySwitchIsolatingJointEntity) jointEntity);
                ((StationWaySwitchIsolatingJointEntity) jointEntity)
                        .setSwitchEntity(InfrSchemaTestDataCreator.createTestStationMainWaySwitchEntity());
                ((StationWaySwitchIsolatingJointEntity) jointEntity).setJointNumber(1);
            }
            default -> {
                jointEntity = new StationWayConductingJointEntity();
                ((StationWayConductingJointEntity) jointEntity)
                        .setStationWayPlace(InfrSchemaTestDataCreator.createTestStationWayPlaceEntity());
                setAdditionalConductingJointInfo((StationWayConductingJointEntity) jointEntity);
            }
        }
        return jointEntity;
    }

    private static void setAdditionalConductingJointInfo(ConductingJointEntity jointEntity) {
        jointEntity.setConnectorTypes("СРСП");
        jointEntity.setConnectorInstallationDate(LocalDate.of(2024, 1, 1));
        jointEntity.setContactResistance(77);
        jointEntity.setLastResistanceMeasureDate(LocalDate.of(2024, 1, 1));
        jointEntity.setLastPadsRemovalDate(LocalDate.of(2024, 1, 1));
    }

    private static void setAdditionalIsolatingJointInfo(IsolatingJointEntity jointEntity) {
        jointEntity.setObjectType("СИГНАЛ");
        jointEntity.setLastPadsRemovalDate(LocalDate.of(2024, 1, 1));
        jointEntity.setMagnetization(10);
        jointEntity.setLastDemagnetizationDate(LocalDate.of(2024, 1, 1));
        jointEntity.setLastDemagnetizationMethod("ТЕСТОВЫЙ");
        jointEntity.setLastMagnetizationMeasureDate(LocalDate.of(2024, 1, 1));
    }

}