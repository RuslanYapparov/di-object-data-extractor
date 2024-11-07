package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationNameDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class LocationServiceIntTest {
    private final LocationService locationService;

    @Autowired
    public LocationServiceIntTest(LocationService locationService) {
        this.locationService = locationService;
    }

    @Test
    public void getInterstationTrackNameByLocationDto_whenGetCorrectLocationDto1_thenReturnCorrectInterstationTrackName() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                1L,
                "1",
                1243,
                1,
                1,
                LineSide.RIGHT
        );
        String interstationTrackName = locationService.getInterstationTrackNameByLocationDto(location);

        assertThat(interstationTrackName).isNotNull();
        assertThat(interstationTrackName).isEqualTo("КОЕ-ГДЕНЬ - ГДЕ-ЛИБО");
    }

    @Test
    public void getInterstationTrackNameByLocationDto_whenGetCorrectLocationDto2_thenReturnCorrectInterstationTrackName() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                1L,
                "2",
                1267,
                1,
                1,
                LineSide.RIGHT
        );
        String interstationTrackName = locationService.getInterstationTrackNameByLocationDto(location);

        assertThat(interstationTrackName).isNotNull();
        assertThat(interstationTrackName).isEqualTo("ГДЕ-ЛИБО - ГДЕ-НИБУДЬСК");
    }

    @Test
    public void getInterstationTrackNameByLocationDto_whenGetCorrectLocationDto3_thenReturnCorrectInterstationTrackName() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                2L,
                "1578",
                3,
                1,
                1,
                LineSide.RIGHT
        );
        String interstationTrackName = locationService.getInterstationTrackNameByLocationDto(location);

        assertThat(interstationTrackName).isNotNull();
        assertThat(interstationTrackName).isEqualTo("КОЕ-ГДЕНЬ - КОЕ-ГДЕНЬ ГРУЗОВАЯ");
    }

    @Test
    public void getInterstationTrackNameByLocationDto_whenGetCorrectLocationDto4_thenReturnCorrectInterstationTrackName() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                3L,
                "14",
                3,
                1,
                1,
                LineSide.RIGHT
        );
        String interstationTrackName = locationService.getInterstationTrackNameByLocationDto(location);

        assertThat(interstationTrackName).isNotNull();
        assertThat(interstationTrackName).isEqualTo("ГДЕ-ЛИБО - ЗДЕСЯ");
    }

    @Test
    public void getInterstationTrackNameByLocationDto_whenGetCorrectLocationDto5_thenReturnCorrectInterstationTrackName() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                1L,
                "1",
                1234,
                1,
                1,
                LineSide.RIGHT
        );
        String interstationTrackName = locationService.getInterstationTrackNameByLocationDto(location);

        assertThat(interstationTrackName).isNotNull();
        assertThat(interstationTrackName).isEqualTo("КУДА - КОЕ-ГДЕНЬСК");
    }

    @Test
    public void getInterstationTrackNameByLocationDto_whenGetCorrectLocationDto6_thenReturnCorrectInterstationTrackName() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                1L,
                "1",
                1274,
                10,
                99,
                LineSide.RIGHT
        );
        String interstationTrackName = locationService.getInterstationTrackNameByLocationDto(location);

        assertThat(interstationTrackName).isNotNull();
        assertThat(interstationTrackName).isEqualTo("ГДЕ-НИБУДЬСК - ДАЛЕКАЯ");
    }

    @Test
    public void getInterstationTrackNameByLocationDto_whenGetStationLocationDto1_thenReturnEmptyInterstationTrackName() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                1L,
                "77777",
                1235,
                1,
                1,
                LineSide.RIGHT
        );
        String interstationTrackName = locationService.getInterstationTrackNameByLocationDto(location);

        assertThat(interstationTrackName).isNotNull();
        assertThat(interstationTrackName).isEmpty();
    }

    @Test
    public void getInterstationTrackNameByLocationDto_whenGetStationLocationDto2_thenReturnEmptyInterstationTrackName() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                1L,
                "77777",
                1257,
                1,
                1,
                LineSide.RIGHT
        );
        String interstationTrackName = locationService.getInterstationTrackNameByLocationDto(location);

        assertThat(interstationTrackName).isNotNull();
        assertThat(interstationTrackName).isEmpty();
    }

    @Test
    public void getInterstationTrackNameByLocationDto_whenGetStationLocationDto3_thenReturnEmptyInterstationTrackName() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                1L,
                "77777",
                1273,
                1,
                1,
                LineSide.RIGHT
        );
        String interstationTrackName = locationService.getInterstationTrackNameByLocationDto(location);

        assertThat(interstationTrackName).isNotNull();
        assertThat(interstationTrackName).isEmpty();
    }

    @Test
    public void getInterstationTrackNameByLocationDto_whenGetStationLocationDto4_thenReturnEmptyInterstationTrackName() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                2L,
                "77777",
                5,
                1,
                1,
                LineSide.RIGHT
        );
        String interstationTrackName = locationService.getInterstationTrackNameByLocationDto(location);

        assertThat(interstationTrackName).isNotNull();
        assertThat(interstationTrackName).isEmpty();
    }

    @Test
    public void getInterstationTrackNameByLocationDto_whenGetStationLocationDto5_thenReturnEmptyInterstationTrackName() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                3L,
                "77777",
                14,
                1,
                1,
                LineSide.RIGHT
        );
        String interstationTrackName = locationService.getInterstationTrackNameByLocationDto(location);

        assertThat(interstationTrackName).isNotNull();
        assertThat(interstationTrackName).isEmpty();
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_WAY", "STATION_MAIN_WAY" })
    public void getInterstationTrackNameByLocationDto_whenGetIncorrectWayType_thenThrowException(WayType wayType) {
        LocationDto location = new LocationDto(
                wayType,
                1L,
                "1",
                1243,
                1,
                1,
                LineSide.RIGHT
        );

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> locationService.getInterstationTrackNameByLocationDto(location));
        assertThat(exception.getMessage()).isEqualTo("Для выполнения операции получения обозначения " +
                "перегона получены данные конкретного места с признаком wayType=" + wayType);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1275, Integer.MAX_VALUE, 5789, 1 })
    public void getInterstationTrackNameByLocationDto_whenGetUnknownLocationDto_thenReturnEmptyInterstationTrackName(int value) {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                1L,
                "77777",
                value,
                1,
                1,
                LineSide.RIGHT
        );
        String interstationTrackName = locationService.getInterstationTrackNameByLocationDto(location);

        assertThat(interstationTrackName).isNotNull();
        assertThat(interstationTrackName).isEmpty();
    }

    @Test
    public void getStationNameByLocationDto_whenGetKoeGdenLocationDto_thenReturnCorrectStationName() {
        LocationDto location = new LocationDto(
                WayType.STATION_MAIN_WAY,
                1L,
                "77777",
                1235,
                1,
                1,
                LineSide.RIGHT
        );
        String stationName = locationService.getStationNameByLocationDto(location);

        assertThat(stationName).isNotNull();
        assertThat(stationName).isEqualTo("КОЕ-ГДЕНЬ");
    }

    @Test
    public void getStationNameByLocationDto_whenGetGdeLiboLocationDto_thenReturnCorrectStationName() {
        LocationDto location = new LocationDto(
                WayType.STATION_MAIN_WAY,
                1L,
                "77777",
                1257,
                1,
                1,
                LineSide.RIGHT
        );
        String stationName = locationService.getStationNameByLocationDto(location);

        assertThat(stationName).isNotNull();
        assertThat(stationName).isEqualTo("ГДЕ-ЛИБО");
    }

    @Test
    public void getStationNameByLocationDto_whenGetGdeNibudskLocationDto_thenReturnCorrectStationName() {
        LocationDto location = new LocationDto(
                WayType.STATION_MAIN_WAY,
                1L,
                "77777",
                1273,
                1,
                1,
                LineSide.RIGHT
        );
        String stationName = locationService.getStationNameByLocationDto(location);

        assertThat(stationName).isNotNull();
        assertThat(stationName).isEqualTo("ГДЕ-НИБУДЬСК");
    }

    @Test
    public void getStationNameByLocationDto_whenGetKoeGdenGruzLocationDto_thenReturnCorrectStationName() {
        LocationDto location = new LocationDto(
                WayType.STATION_MAIN_WAY,
                2L,
                "77777",
                5,
                1,
                1,
                LineSide.RIGHT
        );
        String stationName = locationService.getStationNameByLocationDto(location);

        assertThat(stationName).isNotNull();
        assertThat(stationName).isEqualTo("КОЕ-ГДЕНЬ ГРУЗОВАЯ");
    }

    @Test
    public void getStationNameByLocationDto_whenGetZdesyaLocationDto_thenReturnCorrectStationName() {
        LocationDto location = new LocationDto(
                WayType.STATION_MAIN_WAY,
                3L,
                "77777",
                14,
                1,
                1,
                LineSide.RIGHT
        );
        String stationName = locationService.getStationNameByLocationDto(location);

        assertThat(stationName).isNotNull();
        assertThat(stationName).isEqualTo("ЗДЕСЯ");
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_WAY", "INTERSTATION_TRACK" })
    public void getStationNameByLocationDto_whenGetIncorrectWayType_thenThrowException(WayType wayType) {
        LocationDto location = new LocationDto(
                wayType,
                1L,
                "1",
                1243,
                1,
                1,
                LineSide.RIGHT
        );

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> locationService.getStationNameByLocationDto(location));
        assertThat(exception.getMessage()).isEqualTo("Для выполнения операции получения обозначения станции " +
                "получены данные конкретного места с признаком wayType=" + wayType);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1234, 1275, Integer.MAX_VALUE, 5789, 1 })
    public void getStationNameByLocationDto_whenGetUnknownLocationDto_thenReturnEmptystationName(int value) {
        LocationDto location = new LocationDto(
                WayType.STATION_MAIN_WAY,
                1L,
                "77777",
                value,
                1,
                1,
                LineSide.RIGHT
        );
        String stationName = locationService.getStationNameByLocationDto(location);

        assertThat(stationName).isNotNull();
        assertThat(stationName).isEmpty();
    }

    @Test
    public void canGetLocationProjectionByLocationDto_whenGetCorrectInterstationTrackLocation_thenReturnTrue() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                1L,
                "1",
                1250,
                1,
                1,
                LineSide.RIGHT
        );

        assertThat(locationService.canGetLocationProjectionByLocationDto(location)).isTrue();
    }

    @Test
    public void canGetLocationProjectionByLocationDto_whenGetCorrectStationMainWayLocation_thenReturnTrue() {
        LocationDto location = new LocationDto(
                WayType.STATION_MAIN_WAY,
                1L,
                "1",
                1257,
                1,
                1,
                LineSide.RIGHT
        );

        assertThat(locationService.canGetLocationProjectionByLocationDto(location)).isTrue();
    }

    @Test
    public void canGetLocationProjectionByLocationDto_whenGetCorrectStationLocation_thenReturnTrue() {
        LocationDto location = new LocationDto(
                WayType.STATION_WAY,
                8L,
                "5",
                1,
                1,
                1,
                LineSide.RIGHT
        );

        assertThat(locationService.canGetLocationProjectionByLocationDto(location)).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class)
    public void canGetLocationProjectionByLocationDto_whenGetIncorrectLocationDto_thenReturnFalse(WayType wayType) {
        LocationDto location = new LocationDto(
                wayType,
                1L,
                "1",
                1275,
                1,
                1,
                LineSide.RIGHT
        );

        assertThat(locationService.canGetLocationProjectionByLocationDto(location)).isFalse();
    }

    @Test
    public void canGetLocationProjectionByLocationDto_whenGetNullWayTypeLocation_thenThrowIllegalArgumentException() {
        LocationDto location = new LocationDto(
                null,
                8L,
                "5",
                1,
                1,
                1,
                LineSide.RIGHT
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> locationService.canGetLocationProjectionByLocationDto(location));
        assertThat(exception.getMessage()).isEqualTo("Параметр 'какой-либо из параметров конкретного места' " +
                "не может быть null.");
    }

    @Test
    public void canGetLocationProjectionByLocationDto_whenGetFirstSpecialInterstationTrackLocation_thenReturnTrue() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                1L,
                "2",
                1234,
                9,
                33,
                LineSide.RIGHT
        );

        assertThat(locationService.canGetLocationProjectionByLocationDto(location)).isTrue();
    }

    @Test
    public void canGetLocationProjectionByLocationDto_whenGetSecondSpecialInterstationTrackLocation_thenReturnTrue() {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                1L,
                "2",
                1274,
                9,
                86,
                LineSide.RIGHT
        );

        assertThat(locationService.canGetLocationProjectionByLocationDto(location)).isTrue();
    }

    @Test
    public void getStationWaysMiniDtoByStationId_whenGetCorrectStationId_thenReturnSetOfStation() {
        Set<StationWayMiniDto> stationWays = locationService.getStationWaysMiniDtoByStationId(8L);
        StationNameDto gdeNibudsk = new StationNameDto(8L, "ГДЕ-НИБУДЬСК");

        assertThat(stationWays).isNotNull();
        assertThat(stationWays.size()).isEqualTo(4);
        assertThat(stationWays).contains(
                new StationWayMiniDto(12L, "5", gdeNibudsk));
        stationWays.forEach(stationWay -> assertThat(stationWay.station()).isEqualTo(gdeNibudsk));
    }

    @Test
    public void getStationWaysMiniDtoByStationId_whenGetIncorrectStationId_thenReturnEmptySet() {
        Set<StationWayMiniDto> stationWays = locationService.getStationWaysMiniDtoByStationId(1L);

        assertThat(stationWays).isNotNull();
        assertThat(stationWays).isEmpty();
    }

}