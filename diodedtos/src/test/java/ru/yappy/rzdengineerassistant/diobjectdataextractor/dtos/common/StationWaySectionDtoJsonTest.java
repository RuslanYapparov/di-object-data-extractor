package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class StationWaySectionDtoJsonTest {
    @Autowired
    private JacksonTester<StationWaySectionDto> jackson;
    private StationWaySectionDto stationWaySectionDto = DiodeTestDataCreator.createStationWaySectionDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<StationWaySectionDto> result = jackson.write(stationWaySectionDto);
        StationWaySectionDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(stationWaySectionDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<StationWaySectionDto> result = jackson.write(stationWaySectionDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.stationWay.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.stationWay.number")
                .isEqualTo("7 станции Тестовая");
        assertThat(result).extractingJsonPathNumberValue("$.stationWay.station.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.stationWay.station.name").isEqualTo("ТЕСТОВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.startMeter").isEqualTo(7777);
        assertThat(result).extractingJsonPathNumberValue("$.endMeter").isEqualTo(7777);
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        stationWaySectionDto = new StationWaySectionDto(null, null, null);

        JsonContent<StationWaySectionDto> result = jackson.write(stationWaySectionDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.stationWay.id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.stationWay.name").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.startMeter").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.endMeter").isNull();
    }

}