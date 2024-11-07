package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.RailDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.JointService;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.RailService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DiodePObjInfoHandlerMockTest {
    private final DiodePObjInfoHandler diodePObjInfoHandler;

    @MockBean
    private RailService railService;
    @MockBean
    private JointService jointService;

    @Autowired
    public DiodePObjInfoHandlerMockTest(DiodePObjInfoHandler diodePObjInfoHandler) {
        this.diodePObjInfoHandler = diodePObjInfoHandler;
    }

    @Test
    public void handleRailInfoRequest_whenRailServiceReturnUnexpectedRailDtoType_thenThrowsException() {
        when(railService.getFullRailInfoByIdAndWayType(1L, WayType.INTERSTATION_TRACK))
                .thenReturn(new TestRailDto());

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> diodePObjInfoHandler.handleRailInfoRequest(1L, WayType.INTERSTATION_TRACK));
        assertThat(exception.getMessage()).isEqualTo("При выполнении операции получения координаты " +
                "начала рельса получен класс DTO 'TestRailDto', для которого не предусмотрена " +
                "логика обработки.");
    }

    @Test
    public void handleJointInfoRequest_whenJointServiceReturnUnexpectedJointDtoType_thenThrowsException() {
        when(jointService.getFullJointInfo(1L, WayType.STATION_WAY, JointType.CONDUCTING))
                .thenReturn(new TestJointDto());

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> diodePObjInfoHandler.handleJointInfoRequest(1L, WayType.STATION_WAY, JointType.CONDUCTING));
        assertThat(exception.getMessage()).isEqualTo("При выполнении операции получения координаты " +
                "рельсового стыка получен класс DTO 'TestJointDto', для которого не предусмотрена " +
                "логика обработки.");
    }

    private static class TestRailDto extends RailDto {

        public TestRailDto() {
            super(null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
        }

    }

    private static class TestJointDto extends JointDto {

        public TestJointDto() {
            super(null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }

    }

}