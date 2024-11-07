package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class StationWayConductingJointDtoJsonTest {
    @Autowired
    private JacksonTester<StationWayConductingJointDto> jackson;

    private final StationWayConductingJointDto stationWayConductingJointDto =
            (StationWayConductingJointDto) DiodeTestDataCreator.createJointDto(WayType.STATION_WAY, JointType.CONDUCTING);

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<StationWayConductingJointDto> result = jackson.write(stationWayConductingJointDto);
        StationWayConductingJointDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(stationWayConductingJointDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<StationWayConductingJointDto> result = jackson.write(stationWayConductingJointDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.lineSide").isEqualTo("RIGHT");
        assertThat(result).extractingJsonPathStringValue("$.status").isEqualTo("ACTIVE");
        assertThat(result).extractingJsonPathStringValue("$.padsType").isEqualTo("2-хголовые");
        assertThat(result).extractingJsonPathNumberValue("$.padAmountOfHoles").isEqualTo(4);
        assertThat(result).extractingJsonPathNumberValue("$.gap").isEqualTo(7);
        assertThat(result).extractingJsonPathNumberValue("$.verticalStep").isEqualTo(1.1);
        assertThat(result).extractingJsonPathNumberValue("$.horizontalStep").isEqualTo(2.2);
        assertThat(result).extractingJsonPathStringValue("$.lastMeasureDate")
                .isEqualTo("29-03-2024");
        assertThat(result).extractingJsonPathNumberValue("$.lastMeasureRailTemp").isEqualTo(-10);
        assertThat(result).extractingJsonPathStringValue("$.lastPadsRemovalDate")
                .isEqualTo("05-05-2024");
        assertThat(result).extractingJsonPathNumberValue("$.contactResistance").isEqualTo(100);
        assertThat(result).extractingJsonPathStringValue("$.lastResistanceMeasureDate")
                .isEqualTo("05-05-2024");
        assertThat(result).extractingJsonPathStringValue("$.connectorTypes").isEqualTo("СРСП");
        assertThat(result).extractingJsonPathStringValue("$.connectorInstallationDate")
                .isNull();
        assertThat(result).extractingJsonPathNumberValue("$.stationWayPlace.stationWay.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.stationWayPlace.stationWay.number")
                .isEqualTo("7 станции Тестовая");
        assertThat(result).extractingJsonPathNumberValue("$.stationWayPlace.stationWay.station.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.stationWayPlace.stationWay.station.name")
                .isEqualTo("ТЕСТОВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.stationWayPlace.meterOnStationWay")
                .isEqualTo(777);
    }

}