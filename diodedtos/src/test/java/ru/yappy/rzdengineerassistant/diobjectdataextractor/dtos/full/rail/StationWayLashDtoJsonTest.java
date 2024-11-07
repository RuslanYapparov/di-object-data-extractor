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
public class StationWayLashDtoJsonTest {
    @Autowired
    private JacksonTester<StationWayLashDto> jackson;
    private final StationWayLashDto stationWayLashDto = (StationWayLashDto)
            DiodeTestDataCreator.createLashDto(WayType.STATION_WAY);

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<StationWayLashDto> result = jackson.write(stationWayLashDto);
        StationWayLashDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(stationWayLashDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<StationWayLashDto> result = jackson.write(stationWayLashDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("77ТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.lineSide").isEqualTo("ПРАВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.length").isEqualTo(800.07);
        assertThat(result).extractingJsonPathNumberValue("$.startCentimeterOnMeter").isEqualTo(15);
        assertThat(result).extractingJsonPathNumberValue("$.endCentimeterOnMeter").isEqualTo(17);
        assertThat(result).extractingJsonPathStringValue("$.weldingCompany").isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.installationDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathStringValue("$.installedBy").isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathNumberValue("$.passedTonnageBeforeInstall")
                .isEqualTo(100.01);
        assertThat(result).extractingJsonPathNumberValue("$.passedTonnageAfterInstall")
                .isEqualTo(200.02);
        assertThat(result).extractingJsonPathStringValue("$.lastTemperatureTensionDischargingDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathNumberValue("$.lastTemperatureTensionDischargingTemperature")
                .isEqualTo(10);
        assertThat(result).extractingJsonPathStringValue("$.lastTemperatureTensionDischargingReason")
                .isEqualTo("ВЫПРАВКА");
        assertThat(result).extractingJsonPathNumberValue("$.stationWaySection.stationWay.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.stationWaySection.stationWay.number")
                .isEqualTo("7 станции Тестовая");
        assertThat(result).extractingJsonPathNumberValue("$.stationWaySection.stationWay.station.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.stationWaySection.stationWay.station.name")
                .isEqualTo("ТЕСТОВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.stationWaySection.startMeter")
                .isEqualTo(7777);
        assertThat(result).extractingJsonPathNumberValue("$.stationWaySection.endMeter")
                .isEqualTo(7777);
    }

}