package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class LashMiniDtoJsonTest {
    @Autowired
    private JacksonTester<LashMiniDto> jackson;
    private LashMiniDto lashDto;

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        lashDto = new LashMiniDto(12345L, "27П", "ЛЕВАЯ", 1234.567f, 1234.567f);

        JsonContent<LashMiniDto> result = jackson.write(lashDto);
        LashMiniDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(lashDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        lashDto = new LashMiniDto(12345L, "27П", "ЛЕВАЯ", 1234.567f, 1234.567f);

        JsonContent<LashMiniDto> result = jackson.write(lashDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(12345);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("27П");
        assertThat(result).extractingJsonPathStringValue("$.lineSide").isEqualTo("ЛЕВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.startCoordinate").isEqualTo(1234.567);
        assertThat(result).extractingJsonPathNumberValue("$.endCoordinate").isEqualTo(1234.567);
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        lashDto = new LashMiniDto(null, null, null, null, null);

        JsonContent<LashMiniDto> result = jackson.write(lashDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.name").isNull();
        assertThat(result).extractingJsonPathStringValue("$.lineSide").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.startCoordinate").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.endCoordinate").isNull();
    }

}