package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class RailMiniDtoJsonTest {
    @Autowired
    private JacksonTester<RailMiniDto> jackson;
    private RailMiniDto railDto;

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        railDto = new RailMiniDto(1234L,
                                  "ПРАВАЯ",
                                  "ЗВЕНЬЕВОЙ ПУТЬ",
                                  12345.678f,
                                  12345.678f);

        JsonContent<RailMiniDto> result = jackson.write(railDto);
        RailMiniDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(railDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        railDto = new RailMiniDto(12345L,
                                  "ПРАВАЯ",
                                  "ЗВЕНЬЕВОЙ ПУТЬ",
                                  12345.678f,
                                  12345.678f);

        JsonContent<RailMiniDto> result = jackson.write(railDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(12345);
        assertThat(result).extractingJsonPathStringValue("$.lineSide").isEqualTo("ПРАВАЯ");
        assertThat(result).extractingJsonPathStringValue("$.railKind").isEqualTo("ЗВЕНЬЕВОЙ ПУТЬ");
        assertThat(result).extractingJsonPathNumberValue("$.startCoordinate").isEqualTo(12345.678);
        assertThat(result).extractingJsonPathNumberValue("$.endCoordinate").isEqualTo(12345.678);
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        railDto = new RailMiniDto(null, null, null, null, null);

        JsonContent<RailMiniDto> result = jackson.write(railDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.lineSide").isNull();
        assertThat(result).extractingJsonPathStringValue("$.railKind").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.startCoordinate").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.endCoordinate").isNull();
    }

}