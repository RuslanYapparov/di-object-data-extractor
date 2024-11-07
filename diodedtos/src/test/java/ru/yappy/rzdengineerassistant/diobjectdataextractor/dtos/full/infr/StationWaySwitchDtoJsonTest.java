package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class StationWaySwitchDtoJsonTest {
    @Autowired
    private JacksonTester<StationWaySwitchDto> jackson;
    private StationWaySwitchDto stationWaySwitchDto =
            (StationWaySwitchDto) DiodeTestDataCreator.createSwitchDto(WayType.STATION_WAY);

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<StationWaySwitchDto> result = jackson.write(stationWaySwitchDto);
        StationWaySwitchDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(stationWaySwitchDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<StationWaySwitchDto> result = jackson.write(stationWaySwitchDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.fullName").isEqualTo("ТЕСТТЕСТТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.project").isEqualTo("2750");
        assertThat(result).extractingJsonPathStringValue("$.railType").isEqualTo("Р65");
        assertThat(result).extractingJsonPathStringValue("$.beamsMaterial").isEqualTo("Ж/Б");
        assertThat(result).extractingJsonPathStringValue("$.ballastType").isEqualTo("ЩЕБЕНЬ");
        assertThat(result).extractingJsonPathStringValue("$.crossMarking").isEqualTo("1/11");
        assertThat(result).extractingJsonPathStringValue("$.controlType").isEqualTo("ЭЦ");
        assertThat(result).extractingJsonPathStringValue("$.lineSide").isEqualTo("ЛЕВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.gauge").isEqualTo(1524);
        assertThat(result).extractingJsonPathStringValue("$.installationDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathNumberValue("$.passedTonnageBeforeInstall")
                .isEqualTo(777.777);
        assertThat(result).extractingJsonPathNumberValue("$.passedTonnageAfterInstall")
                .isEqualTo(777.777);
        assertThat(result).extractingJsonPathNumberValue("$.outcrossCurveLength").isEqualTo(77);
        assertThat(result).extractingJsonPathStringValue("$.stationParkName").isEqualTo("ПАРК");
        assertThat(result).extractingJsonPathNumberValue("$.frameRailStationWayPlace.stationWay.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.frameRailStationWayPlace.stationWay.number")
                .isEqualTo("7 станции Тестовая");
        assertThat(result).extractingJsonPathNumberValue("$.frameRailStationWayPlace.stationWay.station.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.frameRailStationWayPlace.stationWay.station.name")
                .isEqualTo("ТЕСТОВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.frameRailStationWayPlace.meterOnStationWay")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.wayNameSwitchGoesOn").isEqualTo("43");
    }

}