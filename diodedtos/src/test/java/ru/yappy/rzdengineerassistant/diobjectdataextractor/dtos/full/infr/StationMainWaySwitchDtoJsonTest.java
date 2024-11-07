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
public class StationMainWaySwitchDtoJsonTest {
    @Autowired
    private JacksonTester<StationMainWaySwitchDto> jackson;
    private StationMainWaySwitchDto stationMainWaySwitchDto =
            (StationMainWaySwitchDto) DiodeTestDataCreator.createSwitchDto(WayType.STATION_MAIN_WAY);

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<StationMainWaySwitchDto> result = jackson.write(stationMainWaySwitchDto);
        StationMainWaySwitchDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto.equals(stationMainWaySwitchDto)).isTrue();
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<StationMainWaySwitchDto> result = jackson.write(stationMainWaySwitchDto);

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
        assertThat(result).extractingJsonPathNumberValue("$.stationMainWay.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.stationMainWay.number")
                .isEqualTo("7 станции Тестовая");
        assertThat(result).extractingJsonPathNumberValue("$.stationMainWay.station.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.stationMainWay.station.name")
                .isEqualTo("ТЕСТОВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.frameRailJointPlace.transportDirection.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.frameRailJointPlace.transportDirection.name")
                .isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathNumberValue("$.frameRailJointPlace.km")
                .isEqualTo(7777);
        assertThat(result).extractingJsonPathNumberValue("$.frameRailJointPlace.meterOnKm")
                .isEqualTo(777);
    }

}