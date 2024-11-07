package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ArtificialConstructionMiniDtoMiniDtoJsonTest {
    @Autowired
    private JacksonTester<ArtificialConstructionMiniDto> jackson;
    private ArtificialConstructionMiniDto artificialConstructionDto;

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        artificialConstructionDto = new ArtificialConstructionMiniDto(1L, "ТРУБА", "ТРУБА");

        JsonContent<ArtificialConstructionMiniDto> result = jackson.write(artificialConstructionDto);
        ArtificialConstructionMiniDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(artificialConstructionDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        artificialConstructionDto = new ArtificialConstructionMiniDto(1L, "ТРУБА", "ТРУБА");

        JsonContent<ArtificialConstructionMiniDto> result = jackson.write(artificialConstructionDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("ТРУБА");
        assertThat(result).extractingJsonPathStringValue("$.type").isEqualTo("ТРУБА");
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        artificialConstructionDto = new ArtificialConstructionMiniDto(null, null, null);

        JsonContent<ArtificialConstructionMiniDto> result = jackson.write(artificialConstructionDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.name").isNull();
        assertThat(result).extractingJsonPathStringValue("$.type").isNull();
    }

}