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
public class StationWayPlaceDtoJsonTest {
    @Autowired
    private JacksonTester<StationWayPlaceDto> jackson;
    private StationWayPlaceDto stationWayPlaceDto = DiodeTestDataCreator.createStationWayPlaceDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<StationWayPlaceDto> result = jackson.write(stationWayPlaceDto);
        StationWayPlaceDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(stationWayPlaceDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<StationWayPlaceDto> result = jackson.write(stationWayPlaceDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.stationWay.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.stationWay.number").isEqualTo("7 станции Тестовая");
        assertThat(result).extractingJsonPathNumberValue("$.stationWay.station.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.stationWay.station.name").isEqualTo("ТЕСТОВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.meterOnStationWay").isEqualTo(777);
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        stationWayPlaceDto = new StationWayPlaceDto(null, null);

        JsonContent<StationWayPlaceDto> result = jackson.write(stationWayPlaceDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.stationWay.id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.stationWay.number").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.stationWay.station.id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.stationWay.station.name").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.meterOnStationWay").isNull();
    }

}