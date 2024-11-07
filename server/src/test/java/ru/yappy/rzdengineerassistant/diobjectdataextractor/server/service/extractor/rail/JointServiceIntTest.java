package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class JointServiceIntTest {
    private final JointService jointService;

    @Autowired
    public JointServiceIntTest(JointService jointService) {
        this.jointService = jointService;
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_MAIN_WAY", "INTERSTATION_TRACK" })
    void getFullJointInfo_whenGetId1AndMainWayTypeAndConductingJointType_thenReturnFullJointDtoTypeAndJoint(
            WayType wayType) {
        JointDto joint = jointService.getFullJointInfo(1L, wayType, JointType.CONDUCTING);

        assertThat(joint).isNotNull();
        assertThat(joint.getId()).isEqualTo(1L);
        assertThat(joint.getLineSide()).isEqualTo(LineSide.LEFT);
        assertThat(joint.getPadsType()).isEqualTo("ДВУХГОЛОВЫЕ");
        assertThat(joint.getGap()).isEqualTo(5);
        assertThat(joint.getLastMeasureDate()).isEqualTo(LocalDate.of(2024, 3, 29));
        assertThat(joint).isInstanceOf(MainWayConductingJointDto.class);

        MainWayConductingJointDto mainWayJoint = (MainWayConductingJointDto) joint;

        assertThat(mainWayJoint.getMainWayNumber()).isEqualTo(1);
        assertThat(mainWayJoint.getMainWayPlace().km()).isEqualTo(1234);
        assertThat(mainWayJoint.getMainWayPlace().meterOnKm()).isEqualTo(12);
        assertThat(mainWayJoint.getConnectorTypes()).isEqualTo("СРСП");
        assertThat(mainWayJoint.getLastResistanceMeasureDate())
                .isEqualTo(LocalDate.of(2024, 3, 20));
    }

    @ParameterizedTest
    @EnumSource(value = JointType.class, names = { "ISOLATING_SIGNAL", "ISOLATING_SWITCH" })
    void getFullJointInfo_whenGetId1AndMainWayTypeAndWrongJointTypeAndJointType_thenThrowObjectNotFoundException(
            JointType jointType) {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> jointService.getFullJointInfo(1L, WayType.STATION_MAIN_WAY, jointType));
        if (JointType.ISOLATING_SIGNAL.equals(jointType)) {
            assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                    "'изолирующий стык сигнала главного пути' с ид=1");
        } else {
            assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                    "'стрелочный изолирующий стык главного пути' с ид=1");
        }
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_MAIN_WAY", "INTERSTATION_TRACK" })
    void getFullJointInfo_whenGetId2587AndMainWayTypeAndSwitchIsolatingJointType_thenReturnFullJointDtoTypeAndJoint(
            WayType wayType) {
        JointDto joint = jointService.getFullJointInfo(2587L, wayType, JointType.ISOLATING_SWITCH);

        assertThat(joint).isNotNull();
        assertThat(joint.getId()).isEqualTo(2587L);
        assertThat(joint.getLineSide()).isEqualTo(LineSide.LEFT);
        assertThat(joint.getPadsType()).isEqualTo("АПАТЭК");
        assertThat(joint.getGap()).isNull();
        assertThat(joint.getHorizontalStep()).isEqualTo(0.6f);
        assertThat(joint.getLastMeasureDate()).isEqualTo(LocalDate.of(2024, 3, 29));
        assertThat(joint).isInstanceOf(MainWaySwitchIsolatingJointDto.class);

        MainWaySwitchIsolatingJointDto mainWayJoint = (MainWaySwitchIsolatingJointDto) joint;

        assertThat(mainWayJoint.getMainWayNumber()).isEqualTo(2);
        assertThat(mainWayJoint.getMainWayPlace().km()).isEqualTo(1272);
        assertThat(mainWayJoint.getMainWayPlace().meterOnKm()).isEqualTo(820);
        assertThat(mainWayJoint.getLastMagnetizationMeasureDate())
                .isEqualTo(LocalDate.of(2024, 2, 17));
        assertThat(mainWayJoint.getMagnetization()).isEqualTo(22);
        assertThat(mainWayJoint.getLastDemagnetizationMethod()).isEqualTo("ДЕМ-Р ПОСТОЯННЫЙ");
        assertThat(mainWayJoint.getLastDemagnetizationDate())
                .isEqualTo(LocalDate.of(2023, 4, 16));
        assertThat(mainWayJoint.getJointNumber()).isEqualTo(5);

        SwitchDto switchDto = mainWayJoint.getJointSwitch();
        if (WayType.INTERSTATION_TRACK.equals(wayType)) {
            assertThat(switchDto).isNull();
            return;
        }
        assertThat(switchDto).isNotNull();
        assertThat(switchDto).isInstanceOf(StationMainWaySwitchDto.class);
        assertThat(switchDto.getId()).isEqualTo(21L);
        assertThat(switchDto.getName()).isEqualTo("2");
    }

    @ParameterizedTest
    @EnumSource(value = JointType.class, names = { "CONDUCTING", "ISOLATING_SIGNAL" })
    void getFullJointInfo_whenGetId2587AndMainWayTypeAndWrongJointTypeAndJointType_thenThrowObjectNotFoundException(
            JointType jointType) {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> jointService.getFullJointInfo(2587L, WayType.STATION_MAIN_WAY, jointType));
        if (JointType.CONDUCTING.equals(jointType)) {
            assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                    "'токопроводящий рельсовый стык на главном пути' с ид=2587");
        } else {
            assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                    "'изолирующий стык сигнала главного пути' с ид=2587");
        }
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_MAIN_WAY", "INTERSTATION_TRACK" })
    void getFullJointInfo_whenGetId925AndMainWayTypeAndSignalIsolatingJointType_thenReturnFullJointDtoTypeAndJoint(
            WayType wayType) {
        JointDto joint = jointService.getFullJointInfo(925L, wayType, JointType.ISOLATING_SIGNAL);

        assertThat(joint).isNotNull();
        assertThat(joint.getId()).isEqualTo(925L);
        assertThat(joint.getLineSide()).isEqualTo(LineSide.LEFT);
        assertThat(joint.getPadsType()).isEqualTo("КЛЕЕБОЛТОВЫЕ");
        assertThat(joint.getGap()).isEqualTo(8);
        assertThat(joint.getHorizontalStep()).isEqualTo(0.6f);
        assertThat(joint.getLastMeasureDate()).isEqualTo(LocalDate.of(2024, 3, 30));
        assertThat(joint).isInstanceOf(MainWaySignalIsolatingJointDto.class);

        MainWaySignalIsolatingJointDto mainWayJoint = (MainWaySignalIsolatingJointDto) joint;

        assertThat(mainWayJoint.getMainWayNumber()).isEqualTo(2);
        assertThat(mainWayJoint.getMainWayPlace().km()).isEqualTo(1268);
        assertThat(mainWayJoint.getMainWayPlace().meterOnKm()).isEqualTo(841);
        assertThat(mainWayJoint.getSignalName()).isEqualTo("4");
        assertThat(mainWayJoint.getLastMagnetizationMeasureDate())
                .isEqualTo(LocalDate.of(2024, 1, 20));
        assertThat(mainWayJoint.getMagnetization()).isEqualTo(1);
        assertThat(mainWayJoint.getLastDemagnetizationMethod()).isEqualTo("-");
        assertThat(mainWayJoint.getLastDemagnetizationDate()).isNull();
    }

    @ParameterizedTest
    @EnumSource(value = JointType.class, names = { "CONDUCTING", "ISOLATING_SWITCH" })
    void getFullJointInfo_whenGetId925AndMainWayTypeAndWrongJointTypeAndJointType_thenThrowObjectNotFoundException(
            JointType jointType) {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> jointService.getFullJointInfo(925L, WayType.STATION_MAIN_WAY, jointType));
        if (JointType.CONDUCTING.equals(jointType)) {
            assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                    "'токопроводящий рельсовый стык на главном пути' с ид=925");
        } else {
            assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                    "'стрелочный изолирующий стык главного пути' с ид=925");
        }
    }

    @Test
    void getFullJointInfo_whenGetId2394AndStationWayTypeAndConductingJointType_thenReturnFullJointDtoTypeAndJoint() {
        JointDto joint = jointService.getFullJointInfo(2394L, WayType.STATION_WAY,
                JointType.CONDUCTING);

        assertThat(joint).isNotNull();
        assertThat(joint.getId()).isEqualTo(2394L);
        assertThat(joint.getLineSide()).isEqualTo(LineSide.RIGHT);
        assertThat(joint.getPadsType()).isEqualTo("ДВУХГОЛОВЫЕ");
        assertThat(joint.getGap()).isEqualTo(15);
        assertThat(joint.getLastMeasureDate()).isEqualTo(LocalDate.of(2024, 3, 29));
        assertThat(joint).isInstanceOf(StationWayConductingJointDto.class);

        StationWayConductingJointDto stationWayJoint = (StationWayConductingJointDto) joint;

        assertThat(stationWayJoint.getStationWayPlace().stationWay().station().getName()).isEqualTo("ЗДЕСЯ");
        assertThat(stationWayJoint.getStationWayPlace().stationWay().number()).isEqualTo("3");
        assertThat(stationWayJoint.getStationWayPlace().meterOnStationWay()).isEqualTo(1233);
        assertThat(stationWayJoint.getConnectorTypes()).isEqualTo("СРСП");
        assertThat(stationWayJoint.getLastResistanceMeasureDate())
                .isEqualTo(LocalDate.of(2024, 4, 3));
    }

    @ParameterizedTest
    @EnumSource(value = JointType.class, names = { "ISOLATING_SIGNAL", "ISOLATING_SWITCH" })
    void getFullJointInfo_whenGetId2394AndStationWayTypeAndWrongJointTypeAndJointType_thenThrowObjectNotFoundException(
            JointType jointType) {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> jointService.getFullJointInfo(2394L, WayType.STATION_WAY, jointType));
        if (JointType.ISOLATING_SIGNAL.equals(jointType)) {
            assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                    "'изолирующий стык сигнала станционного пути' с ид=2394");
        } else {
            assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                    "'стрелочный изолирующий стык станционного пути' с ид=2394");
        }
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_MAIN_WAY", "INTERSTATION_TRACK" })
    void getFullJointInfo_whenGetId2394AndMainWayTypeAndCorrectJointTypeAndJointType_thenThrowObjectNotFoundException(
            WayType wayType) {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> jointService.getFullJointInfo(2394L, wayType, JointType.CONDUCTING));
        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                "'токопроводящий рельсовый стык на главном пути' с ид=2394");
    }

    @Test
    void getFullJointInfo_whenGetId1773AndStWayTypeAndSignalIsolatingJointType_thenReturnFullJointDtoTypeAndJoint() {
        JointDto joint = jointService.getFullJointInfo(1773L, WayType.STATION_WAY,
                JointType.ISOLATING_SIGNAL);

        assertThat(joint).isNotNull();
        assertThat(joint.getId()).isEqualTo(1773L);
        assertThat(joint.getLineSide()).isEqualTo(LineSide.LEFT);
        assertThat(joint.getPadsType()).isEqualTo("ПЛАСТРОН");
        assertThat(joint.getGap()).isEqualTo(23);
        assertThat(joint.getLastMeasureDate()).isEqualTo(LocalDate.of(2024, 3, 29));
        assertThat(joint).isInstanceOf(StationWaySignalIsolatingJointDto.class);

        StationWaySignalIsolatingJointDto stationWayJoint = (StationWaySignalIsolatingJointDto) joint;

        assertThat(stationWayJoint.getStationWayPlace().stationWay().station().getName()).isEqualTo("ГДЕ-ЛИБО");
        assertThat(stationWayJoint.getStationWayPlace().stationWay().number()).isEqualTo("4");
        assertThat(stationWayJoint.getStationWayPlace().meterOnStationWay()).isEqualTo(1160);
        assertThat(stationWayJoint.getSignalName()).isEqualTo("Ч4");
        assertThat(stationWayJoint.getLastMagnetizationMeasureDate())
                .isEqualTo(LocalDate.of(2024, 3, 5));
        assertThat(stationWayJoint.getMagnetization()).isEqualTo(3);
        assertThat(stationWayJoint.getLastDemagnetizationMethod()).isEqualTo("-");
        assertThat(stationWayJoint.getLastDemagnetizationDate())
                .isEqualTo(LocalDate.of(2024, 8, 10));
    }

    @ParameterizedTest
    @EnumSource(value = JointType.class, names = { "CONDUCTING", "ISOLATING_SWITCH" })
    void getFullJointInfo_whenGetId1773AndStationWayTypeAndWrongJointTypeAndJointType_thenThrowObjectNotFoundException(
            JointType jointType) {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> jointService.getFullJointInfo(1773L, WayType.STATION_WAY, jointType));
        if (JointType.CONDUCTING.equals(jointType)) {
            assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                    "'токопроводящий рельсовый стык на станционном пути' с ид=1773");
        } else {
            assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                    "'стрелочный изолирующий стык станционного пути' с ид=1773");
        }
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_MAIN_WAY", "INTERSTATION_TRACK" })
    void getFullJointInfo_whenGetId1773AndMainWayTypeAndCorrectJointTypeAndJointType_thenThrowObjectNotFoundException(
            WayType wayType) {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> jointService.getFullJointInfo(1773L, wayType, JointType.ISOLATING_SIGNAL));
        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                "'изолирующий стык сигнала главного пути' с ид=1773");
    }

    @Test
    void getFullJointInfo_whenGetId1773AndNullTypeAndJointTypeParameters_thenThrowIllegalStateException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> jointService.getFullJointInfo(1773L, null, JointType.ISOLATING_SIGNAL));
        assertThat(exception.getMessage()).isEqualTo("Не удалось получить полную информацию о рельсом стыке, " +
                "так как при вызове метода не был указан тип пути (wayType=null) или тип стыка (jointType=null).");

        exception = assertThrows(IllegalStateException.class,
                () -> jointService.getFullJointInfo(1773L, WayType.STATION_WAY, null));
        assertThat(exception.getMessage()).isEqualTo("Не удалось получить полную информацию о рельсом стыке, " +
                "так как при вызове метода не был указан тип пути (wayType=null) или тип стыка (jointType=null).");
    }

    @ParameterizedTest
    @EnumSource(JointType.class)
    void getJointMiniInfoVariantsByLocationDto_whenGetCorrectStationMainWayLocationDto_thenReturnJointMiniDtoArray(
            JointType jointType) {
        LocationDto location = new LocationDto(WayType.STATION_MAIN_WAY,
                1L,
                "2",
                1272,
                10,
                1,
                LineSide.LEFT);
        List<JointMiniDto> joints = jointService.getJointMiniInfoVariantsByLocationDto(location, jointType);

        switch (jointType) {
            case CONDUCTING -> {
                assertThat(joints).hasSize(6);
                assertThat(joints.getFirst()).isEqualTo(new JointMiniDto(959L, "ТОКОПРОВОДЯЩИЙ",
                        "ЛЕВАЯ", 1272.715f));
            }
            case ISOLATING_SIGNAL -> {
                assertThat(joints).hasSize(5);
                assertThat(joints.getFirst()).isEqualTo(new JointMiniDto(963L, "ИЗОЛИРУЮЩИЙ",
                        "ЛЕВАЯ", 1272.74f));
            }
            case ISOLATING_SWITCH -> {
                assertThat(joints).hasSize(9);
                assertThat(joints.getFirst()).isEqualTo(new JointMiniDto(2585L, "ИЗОЛИРУЮЩИЙ", "ЛЕВАЯ",
                        1272.82f));
            }
        }
    }

    @ParameterizedTest
    @EnumSource(JointType.class)
    void getJointMiniInfoVariantsByLocationDto_whenGetCorrectInterstationTrackLocationDto_thenReturnJointMiniDtoArray(
            JointType jointType) {
        LocationDto location = new LocationDto(WayType.INTERSTATION_TRACK,
                1L,
                "1",
                1238,
                1,
                10,
                LineSide.RIGHT);
        List<JointMiniDto> joints = jointService.getJointMiniInfoVariantsByLocationDto(location, jointType);

        switch (jointType) {
            case CONDUCTING -> {
                assertThat(joints).hasSize(4);
                assertThat(joints.getFirst()).isEqualTo(new JointMiniDto(78L, "ТОКОПРОВОДЯЩИЙ",
                        "ПРАВАЯ", 1237.95f));
                assertThat(joints.getLast()).isEqualTo(new JointMiniDto(86L, "ТОКОПРОВОДЯЩИЙ",
                        "ПРАВАЯ", 1238.0f));
            }
            case ISOLATING_SIGNAL -> {
                assertThat(joints).hasSize(1);
                assertThat(joints.getFirst()).isEqualTo(new JointMiniDto(82L, "ИЗОЛИРУЮЩИЙ",
                        "ПРАВАЯ", 1237.974f));
            }
            case ISOLATING_SWITCH -> assertThat(joints).hasSize(0);
        }
    }

    @ParameterizedTest
    @EnumSource(JointType.class)
    void getJointMiniInfoVariantsByLocationDto_whenGetCorrectStationWayLocationDto_thenReturnJointMiniDtoArray(
            JointType jointType) {
        LocationDto location = new LocationDto(WayType.STATION_WAY,
                8L,
                JointType.CONDUCTING.equals(jointType) ? "5 станции Кусь" : "5",
                0,
                1,
                80,
                LineSide.LEFT);
        List<JointMiniDto> joints = jointService.getJointMiniInfoVariantsByLocationDto(location, jointType);

        switch (jointType) {
            case CONDUCTING -> {
                assertThat(joints).hasSize(21);
                assertThat(joints.getFirst()).isEqualTo(new JointMiniDto(1819L, "ТОКОПРОВОДЯЩИЙ",
                        "ЛЕВАЯ", 0.033f));
                assertThat(joints.getLast()).isEqualTo(new JointMiniDto(1861L, "ТОКОПРОВОДЯЩИЙ",
                        "ЛЕВАЯ", 0.558f));
            }
            case ISOLATING_SIGNAL -> {
                assertThat(joints).hasSize(1);
                assertThat(joints.getFirst()).isEqualTo(new JointMiniDto(1821L, "ИЗОЛИРУЮЩИЙ",
                        "ЛЕВАЯ", 0.058f));
            }
            case ISOLATING_SWITCH -> assertThat(joints).hasSize(0);
        }
    }

    @ParameterizedTest
    @EnumSource(WayType.class)
    void getJointMiniInfoVariantsByLocationDto_whenGetIncorrectLocationDto_thenThrowObjectNotFoundException(
            WayType wayType) {
        LocationDto location = new LocationDto(wayType,
                2L,
                "1",
                345,
                1,
                80,
                LineSide.LEFT);

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> jointService.getJointMiniInfoVariantsByLocationDto(location, JointType.CONDUCTING));
        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект 'массив " +
                "вариантов рельсовых стыков для конкретного места' со следующими критериями выборки: " +
                "LocationDto[wayType=" + wayType + ", locationId=2, wayNumber=1, km=345, picket=1, meter=80, " +
                "lineSide=LEFT]");
        }

}