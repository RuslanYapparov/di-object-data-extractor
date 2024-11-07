package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.JointEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr.InfrSchemaTestDataCreator;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.RailSchemaTestDataCreator;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JointMapperTest {
    private final JointMapper jointMapper;
    private final SwitchMapper switchMapper;

    @Autowired
    public JointMapperTest(JointMapper jointMapper, SwitchMapper switchMapper) {
        this.jointMapper = jointMapper;
        this.switchMapper = switchMapper;
    }

    @ParameterizedTest
    @EnumSource(JointType.class)
    void toDto_whenGetTestMainWayJointEntity_thenReturnDto(JointType type) {
        JointEntity jointEntity = RailSchemaTestDataCreator.createTestJointEntity(type, true);
        JointDto joint = jointMapper.toDto(jointEntity);

        assertThat(joint).isInstanceOf(MainWayJoint.class);

        MainWayJoint mainWayJoint = (MainWayJoint) joint;

        assertThat(mainWayJoint.getMainWayNumber()).isEqualTo(1);
        assertThat(mainWayJoint.getMainWayPlace().km()).isEqualTo(7777);
        assertThat(mainWayJoint.getMainWayPlace().meterOnKm()).isEqualTo(777);
        if (mainWayJoint instanceof MainWaySwitchIsolatingJointDto msijd) {
            assertThat(msijd.getJointNumber()).isEqualTo(1);
            assertThat(msijd.getJointSwitch())
                    .isEqualTo(switchMapper.toDto(InfrSchemaTestDataCreator.createTestStationMainWaySwitchEntity()));
        }
    }

    @ParameterizedTest
    @EnumSource(JointType.class)
    void toDto_whenGetTestStationWayJointEntity_thenReturnDto(JointType type) {
        JointEntity jointEntity = RailSchemaTestDataCreator.createTestJointEntity(type, false);
        jointEntity.setLineSide("ПРАВАЯ");
        JointDto joint = jointMapper.toDto(jointEntity);

        assertThat(joint).isInstanceOf(StationWayJoint.class);

        StationWayJoint stationWayJoint = (StationWayJoint) joint;

        assertThat(stationWayJoint.getStationWayPlace().stationWay().station().getName()).isEqualTo("StationName");
        assertThat(stationWayJoint.getStationWayPlace().stationWay().number()).isEqualTo("7а");
        assertThat(stationWayJoint.getStationWayPlace().meterOnStationWay()).isEqualTo(777);
        if (stationWayJoint instanceof StationWaySwitchIsolatingJointDto ssijd) {
            assertThat(ssijd.getJointNumber()).isEqualTo(1);
            assertThat(ssijd.getJointSwitch())
                    .isEqualTo(switchMapper.toDto(InfrSchemaTestDataCreator.createTestStationMainWaySwitchEntity()));
        }
    }

    @Test
    void toDto_whenGetNull_thenReturnNull() {
        JointDto joint = jointMapper.toDto(null);

        assertThat(joint).isNull();
    }

    @Test
    void toDto_whenGetNotSupportedJointEntityClass_thenThrowException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> jointMapper.toDto(new TestJointEntity()));
        assertThat(exception.getMessage()).isEqualTo("Не реализована логика мэппинга для типа рельсового " +
                "стыка 'TestJointEntity' в DTO");
    }

    @Test
    void mapMainWayJointEntityListToMiniDtoList_whenGetTestList_thenReturnList() {
        List<JointMiniDto> result = jointMapper.mapMainWayJointEntityListToMiniDtoList(
                List.of(createMainWayJointProjection(), createMainWayJointProjection()));
        assertThat(result).hasSize(2);
        assertThat(result.getFirst())
                .isEqualTo(new JointMiniDto(7L, "ТЕСТ", "ПРАВАЯ", 7777.777f));
    }

    @Test
    void mapMainWayJointEntityListToMiniDtoList_whenGetNull_thenReturnNull() {
        assertThat(jointMapper.mapMainWayJointEntityListToMiniDtoList(null)).isNull();
    }

    @Test
    void mapMainWayJointEntityListToMiniDtoList_whenGetEmptyList_thenReturnEmptyUnmodifiableList() {
        List<JointMiniDto> result = jointMapper.mapMainWayJointEntityListToMiniDtoList(new ArrayList<>());
        assertThat(result).isEmpty();
        assertThrows(UnsupportedOperationException.class, () -> result.add(
                new JointMiniDto(1L, "ТЕСТ", "ПРАВАЯ", 7f)));
    }

    @Test
    void mapStationWayJointEntityListToMiniDtoList_whenGetTestList_thenReturnList() {
        List<JointMiniDto> result = jointMapper.mapStationWayJointEntityListToMiniDtoList(
                List.of(createStationWayJointProjection(), createStationWayJointProjection()));
        assertThat(result).hasSize(2);
        assertThat(result.getFirst())
                .isEqualTo(new JointMiniDto(7L, "ТЕСТ", "ЛЕВАЯ", 0.777f));
    }

    @Test
    void mapStationWayJointEntityListToMiniDtoList_whenGetNull_thenReturnNull() {
        assertThat(jointMapper.mapStationWayJointEntityListToMiniDtoList(null)).isNull();
    }

    @Test
    void mapStationWayJointEntityListToMiniDtoList_whenGetEmptyList_thenReturnEmptyUnmodifiableList() {
        List<JointMiniDto> result = jointMapper.mapStationWayJointEntityListToMiniDtoList(new ArrayList<>());
        assertThat(result).isEmpty();
        assertDoesNotThrow(() -> result.add(
                new JointMiniDto(1L, "ТЕСТ", "ЛЕВАЯ", 7f)));
    }

    private MainWayJointProjection createMainWayJointProjection() {
        return new MainWayJointProjection() {

            @Override
            public Long getId() {
                return 7L;
            }

            @Override
            public String getType() {
                return "ТЕСТ";
            }

            @Override
            public String getLineSide() {
                return "ПРАВАЯ";
            }

            @Override
            public MainWayPlaceEntity getMainWayPlace() {
                return InfrSchemaTestDataCreator.createTestMainWayPlaceEntity();
            }

        };
    }

    private StationWayJointProjection createStationWayJointProjection() {
        return new StationWayJointProjection() {

            @Override
            public Long getId() {
                return 7L;
            }

            @Override
            public String getType() {
                return "ТЕСТ";
            }

            @Override
            public String getLineSide() {
                return "ЛЕВАЯ";
            }

            @Override
            public StationWayPlaceEntity getStationWayPlace() {
                return InfrSchemaTestDataCreator.createTestStationWayPlaceEntity();
            }
        };

    }

    private static class TestJointEntity extends JointEntity {
    }

}