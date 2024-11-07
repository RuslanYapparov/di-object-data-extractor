package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.wayc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LineSide;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.wayc.WayCharacteristicsDto;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class WayCharacteristicServiceIntTest {
    private final WayCharacteristicService wayCharacteristicService;

    @Autowired
    public WayCharacteristicServiceIntTest(WayCharacteristicService wayCharacteristicService) {
        this.wayCharacteristicService = wayCharacteristicService;
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_MAIN_WAY", "INTERSTATION_TRACK" })
    public void getWayCharacteristicsByLocationDto_whenGetCorrectStartMainWayParameter_thenReturnWayCharacteristicsDto(
            WayType wayType) {
        LocationDto locationDto = new LocationDto(
                wayType,
                1L,
                "1",
                1234,
                1,
                1,
                LineSide.RIGHT);
        WayCharacteristicsDto wayCharacteristicsDto =
                wayCharacteristicService.getWayCharacteristicsByLocationDto(locationDto);

        assertThat(wayCharacteristicsDto).isNotNull();
        assertThat(wayCharacteristicsDto.slope()).isEqualTo(0.8f);
        assertThat(wayCharacteristicsDto.radius()).isEqualTo(850);
        assertThat(wayCharacteristicsDto.passengerTrainSpeed()).isEqualTo(120);
        assertThat(wayCharacteristicsDto.passedTonnageAfterInstall()).isEqualTo(517.3f);
        assertThat(wayCharacteristicsDto.groupClassCode()).isEqualTo("1О");
        assertThat(wayCharacteristicsDto.thermalHardening()).isEqualTo("ОБЪЕМНО-ТЕРМОУПРОЧНЕННЫЕ");
        assertThat(wayCharacteristicsDto.fasteningType()).isEqualTo("КБ");
        assertThat(wayCharacteristicsDto.capitalRepairDate()).isEqualTo(LocalDate.of(2018, 7, 1));
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_MAIN_WAY", "INTERSTATION_TRACK" })
    public void getWayCharacteristicsByLocationDto_whenGetCorrectEndMainWayParameter_thenReturnWayCharacteristicsDto(
            WayType wayType) {
        LocationDto locationDto = new LocationDto(
                wayType,
                1L,
                "2",
                1274,
                10,
                99,
                LineSide.RIGHT);
        WayCharacteristicsDto wayCharacteristicsDto =
                wayCharacteristicService.getWayCharacteristicsByLocationDto(locationDto);

        assertThat(wayCharacteristicsDto).isNotNull();
        assertThat(wayCharacteristicsDto.slope()).isEqualTo(0.1f);
        assertThat(wayCharacteristicsDto.radius()).isNull();
        assertThat(wayCharacteristicsDto.passengerTrainSpeed()).isEqualTo(110);
        assertThat(wayCharacteristicsDto.passedTonnageAfterInstall()).isEqualTo(738.5f);
        assertThat(wayCharacteristicsDto.groupClassCode()).isEqualTo("2II");
        assertThat(wayCharacteristicsDto.thermalHardening()).isEqualTo("ОБЪЕМНО-ТЕРМОУПРОЧНЕННЫЕ");
        assertThat(wayCharacteristicsDto.fasteningType()).isEqualTo("КБ");
        assertThat(wayCharacteristicsDto.capitalRepairDate()).isEqualTo(LocalDate.of(2009, 9, 1));
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_MAIN_WAY", "INTERSTATION_TRACK" })
    public void getWayCharacteristicsByLocationDto_whenGetCorrectMiddleMainWayParameter_thenReturnWayCharacteristicsDto(
            WayType wayType) {
        LocationDto locationDto = new LocationDto(
                wayType,
                3L,
                "1",
                9,
                1,
                1,
                LineSide.LEFT);
        WayCharacteristicsDto wayCharacteristicsDto =
                wayCharacteristicService.getWayCharacteristicsByLocationDto(locationDto);

        assertThat(wayCharacteristicsDto).isNotNull();
        assertThat(wayCharacteristicsDto.slope()).isEqualTo(2.3f);
        assertThat(wayCharacteristicsDto.radius()).isEqualTo(795);
        assertThat(wayCharacteristicsDto.passengerTrainSpeed()).isEqualTo(60);
        assertThat(wayCharacteristicsDto.passedTonnageBeforeInstall()).isEqualTo(2.9f);
        assertThat(wayCharacteristicsDto.groupClassCode()).isEqualTo("4V");
        assertThat(wayCharacteristicsDto.thermalHardening()).isEqualTo("ОБЪЕМНО-ТЕРМОУПРОЧНЕННЫЕ");
        assertThat(wayCharacteristicsDto.fasteningType()).isEqualTo("КБ");
        assertThat(wayCharacteristicsDto.capitalRepairDate()).isEqualTo(LocalDate.of(2013, 7, 1));
    }

    @Test
    public void getWayCharacteristicsByLocationDto_whenGetIncorrectLocationParameter_thenReturnWayCharacteristicsDtoWithNulls() {
        LocationDto locationDto = new LocationDto(
                WayType.STATION_MAIN_WAY,
                2L,
                "1",
                10,
                1,
                1,
                LineSide.LEFT);

        assertDoesNotThrow(() -> wayCharacteristicService.getWayCharacteristicsByLocationDto(locationDto));

        WayCharacteristicsDto wayCharacteristicsDto =
                wayCharacteristicService.getWayCharacteristicsByLocationDto(locationDto);

        assertThat(wayCharacteristicsDto).isNotNull();
        assertThat(wayCharacteristicsDto.slope()).isNull();
        assertThat(wayCharacteristicsDto.radius()).isNull();
        assertThat(wayCharacteristicsDto.passengerTrainSpeed()).isNull();
        assertThat(wayCharacteristicsDto.passedTonnageBeforeInstall()).isNull();
        assertThat(wayCharacteristicsDto.groupClassCode()).isNull();
        assertThat(wayCharacteristicsDto.thermalHardening()).isNull();
        assertThat(wayCharacteristicsDto.fasteningType()).isNull();
        assertThat(wayCharacteristicsDto.capitalRepairDate()).isNull();
    }

    @Test
    public void getWayCharacteristicsByLocationDto_whenGetCorrectStartStationWayParameter_thenReturnWayCharacteristicsDto() {
        LocationDto locationDto = new LocationDto(
                WayType.STATION_WAY,
                7L,
                "4",
                0,
                1,
                1,
                LineSide.RIGHT);

        WayCharacteristicsDto wayCharacteristicsDto =
                wayCharacteristicService.getWayCharacteristicsByLocationDto(locationDto);

        assertThat(wayCharacteristicsDto.slope()).isEqualTo(-1.0f);
        assertThat(wayCharacteristicsDto.radius()).isEqualTo(300);
        assertThat(wayCharacteristicsDto.passengerTrainSpeed()).isEqualTo(40);
        assertThat(wayCharacteristicsDto.passedTonnageBeforeInstall()).isEqualTo(790.8f);
        assertThat(wayCharacteristicsDto.groupClassCode()).isEqualTo("4V");
        assertThat(wayCharacteristicsDto.thermalHardening()).isEqualTo("ОБЪЕМНО-ТЕРМОУПРОЧНЕННЫЕ");
        assertThat(wayCharacteristicsDto.fasteningType()).isEqualTo("КБ");
        assertThat(wayCharacteristicsDto.capitalRepairDate()).isEqualTo(LocalDate.of(2012, 6, 1));
    }

    @Test
    public void getWayCharacteristicsByLocationDto_whenGetCorrectEndStationWayParameter_thenReturnWayCharacteristicsDto() {
        LocationDto locationDto = new LocationDto(
                WayType.STATION_WAY,
                10L,
                "3",
                1,
                6,
                43,
                LineSide.RIGHT);

        WayCharacteristicsDto wayCharacteristicsDto =
                wayCharacteristicService.getWayCharacteristicsByLocationDto(locationDto);

        assertThat(wayCharacteristicsDto.slope()).isEqualTo(1.0f);
        assertThat(wayCharacteristicsDto.radius()).isEqualTo(300);
        assertThat(wayCharacteristicsDto.passengerTrainSpeed()).isNull();
        assertThat(wayCharacteristicsDto.freightTrainSpeed()).isEqualTo(40);
        assertThat(wayCharacteristicsDto.passedTonnageBeforeInstall()).isEqualTo(0.0f);
        assertThat(wayCharacteristicsDto.passedTonnageAfterInstall()).isEqualTo(1.3f);
        assertThat(wayCharacteristicsDto.groupClassCode()).isEqualTo("4V");
        assertThat(wayCharacteristicsDto.thermalHardening()).isEqualTo("НЕТЕРМОУПРОЧНЕННЫЕ");
        assertThat(wayCharacteristicsDto.fasteningType()).isEqualTo("ДО");
        assertThat(wayCharacteristicsDto.capitalRepairDate()).isEqualTo(LocalDate.of(1991, 4, 1));
    }

    @Test
    public void getWayCharacteristicsByLocationDto_whenGetIncorrectEndStationWayParameter_thenReturnWayCharacteristicsDtoWithNulls() {
        LocationDto locationDto = new LocationDto(
                WayType.STATION_WAY,
                10L,
                "3",
                1,
                6,
                44,
                LineSide.RIGHT);

        assertDoesNotThrow(() -> wayCharacteristicService.getWayCharacteristicsByLocationDto(locationDto));

        WayCharacteristicsDto wayCharacteristicsDto =
                wayCharacteristicService.getWayCharacteristicsByLocationDto(locationDto);

        assertThat(wayCharacteristicsDto.slope()).isNull();
        assertThat(wayCharacteristicsDto.radius()).isNull();
        assertThat(wayCharacteristicsDto.passengerTrainSpeed()).isNull();
        assertThat(wayCharacteristicsDto.passedTonnageBeforeInstall()).isNull();
        assertThat(wayCharacteristicsDto.groupClassCode()).isNull();
        assertThat(wayCharacteristicsDto.thermalHardening()).isNull();
        assertThat(wayCharacteristicsDto.fasteningType()).isNull();
        assertThat(wayCharacteristicsDto.capitalRepairDate()).isNull();
    }

    @Test
    public void getWayCharacteristicsByLocationDto_whenGetCorrectMiddleStationWayParameter_thenReturnWayCharacteristicsDto() {
        LocationDto locationDto = new LocationDto(
                WayType.STATION_WAY,
                8L,
                "6",
                1,
                1,
                1,
                LineSide.RIGHT);

        WayCharacteristicsDto wayCharacteristicsDto =
                wayCharacteristicService.getWayCharacteristicsByLocationDto(locationDto);

        assertThat(wayCharacteristicsDto.slope()).isEqualTo(-2.0f);
        assertThat(wayCharacteristicsDto.radius()).isNull();
        assertThat(wayCharacteristicsDto.passengerTrainSpeed()).isNull();
        assertThat(wayCharacteristicsDto.freightTrainSpeed()).isEqualTo(15);
        assertThat(wayCharacteristicsDto.passedTonnageBeforeInstall()).isEqualTo(318.3f);
        assertThat(wayCharacteristicsDto.passedTonnageAfterInstall()).isEqualTo(0.9f);
        assertThat(wayCharacteristicsDto.groupClassCode()).isEqualTo("4V");
        assertThat(wayCharacteristicsDto.thermalHardening()).isEqualTo("НЕТЕРМОУПРОЧНЕННЫЕ");
        assertThat(wayCharacteristicsDto.fasteningType()).isEqualTo("ДО");
        assertThat(wayCharacteristicsDto.capitalRepairDate()).isEqualTo(LocalDate.of(1991, 4, 1));
    }

}