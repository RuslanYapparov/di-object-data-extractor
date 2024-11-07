package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.mini.JointFailureProtCreatingDataDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DiodePProtFailureHandlerIntTest {
    private final DiodePProtFailureHandler handler;

    @Autowired
    public DiodePProtFailureHandlerIntTest(DiodePProtFailureHandler diodePObjInfoHandler) {
        this.handler = diodePObjInfoHandler;
    }

    @Test
    public void getJointFailureProtCreatingDataDtoByLocationDto_whenGetCorrectIntStationTrackLocation_thenReturnCorrectDto() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                1L,
                "1",
                1234,
                9,
                20,
                LineSide.RIGHT);
        JointFailureProtCreatingDataDto dto =
                handler.getJointFailureProtCreatingDataDtoByLocationDto(location, JointType.CONDUCTING);

        assertThat(dto).isNotNull();
        assertThat(dto.jointVariants().length).isEqualTo(10);
        assertThat(dto.jointVariants()[0].coordinate()).isEqualTo(1234.812f);
        assertThat(dto.diodeLocationDescription()).isEqualTo("КУДА - КОЕ-ГДЕНЬСК");
        assertThat(dto.participants().size()).isEqualTo(22);
        assertThat(dto.participants().getFirst().getJobTitleAbbreviation()).isEqualTo("ПЧ");
    }

    @Test
    public void getJointFailureProtCreatingDataDtoByLocationDto_whenGetCorrectStationMainWayLocation_thenReturnCorrectDto() {
        LocationDto location = new LocationDto(
                WayType.STATION_MAIN_WAY,
                3L,
                "1",
                14,
                1,
                1,
                LineSide.RIGHT);
        JointFailureProtCreatingDataDto dto =
                handler.getJointFailureProtCreatingDataDtoByLocationDto(location, JointType.CONDUCTING);

        assertThat(dto).isNotNull();
        assertThat(dto.jointVariants().length).isEqualTo(8);
        assertThat(dto.jointVariants()[0].coordinate()).isEqualTo(13.765f);
        assertThat(dto.diodeLocationDescription()).isEqualTo("ЗДЕСЯ");
        assertThat(dto.participants().size()).isEqualTo(23);
        assertThat(dto.participants().getFirst().getJobTitleAbbreviation()).isEqualTo("ИЧ");
    }

    @Test
    public void getJointFailureProtCreatingDataDtoByLocationDto_whenGetCorrectStationWayLocation_thenReturnCorrectDto() {
        LocationDto location = new LocationDto(
                WayType.STATION_WAY,
                9L,
                "2",
                1,
                1,
                1,
                LineSide.RIGHT);
        JointFailureProtCreatingDataDto dto =
                handler.getJointFailureProtCreatingDataDtoByLocationDto(location, JointType.CONDUCTING);

        assertThat(dto).isNotNull();
        assertThat(dto.jointVariants().length).isEqualTo(33);
        assertThat(dto.jointVariants()[0].coordinate()).isEqualTo(0.508f);
        assertThat(dto.diodeLocationDescription()).isNull();
        assertThat(dto.participants().size()).isEqualTo(22);
        assertThat(dto.participants().getFirst().getJobTitleAbbreviation()).isEqualTo("ПЧ");
    }

    @Test
    public void getJointFailureProtCreatingDataDtoByLocationDto_whenGetIncorrectLocation_thenThrowObjectNotFoundException() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                2L,
                "1",
                1234,
                9,
                20,
                LineSide.RIGHT);

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> handler.getJointFailureProtCreatingDataDtoByLocationDto(location, JointType.CONDUCTING));
        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект 'массив вариантов " +
                "рельсовых стыков для конкретного места' со следующими критериями выборки: LocationDto[wayType=" +
                "INTERSTATION_TRACK, locationId=2, wayNumber=1, km=1234, picket=9, meter=20, lineSide=RIGHT]");
    }

}