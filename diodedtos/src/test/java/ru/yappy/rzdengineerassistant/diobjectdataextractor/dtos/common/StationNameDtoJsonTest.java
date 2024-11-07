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
public class StationNameDtoJsonTest {
    @Autowired
    private JacksonTester<StationNameDto> jackson;
    private StationNameDto stationDto = DiodeTestDataCreator.createStationNameDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<StationNameDto> result = jackson.write(stationDto);
        StationNameDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(stationDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<StationNameDto> result = jackson.write(stationDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.type").isEqualTo("Станция");
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("ТЕСТОВАЯ");
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        stationDto = new StationNameDto(null, null);

        JsonContent<StationNameDto> result = jackson.write(stationDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.type").isEqualTo("Станция");
        assertThat(result).extractingJsonPathNumberValue("$.id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.name").isNull();
    }

}