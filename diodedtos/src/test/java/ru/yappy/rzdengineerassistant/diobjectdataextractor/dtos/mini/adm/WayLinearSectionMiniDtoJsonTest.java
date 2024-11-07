package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class WayLinearSectionMiniDtoJsonTest {
    @Autowired
    private JacksonTester<WayLinearSectionMiniDto> jackson;
    private WayLinearSectionMiniDto linearSectionDto;

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        linearSectionDto = new WayLinearSectionMiniDto(30L, 4);

        JsonContent<WayLinearSectionMiniDto> result = jackson.write(linearSectionDto);
        WayLinearSectionMiniDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(linearSectionDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        linearSectionDto = new WayLinearSectionMiniDto(1L, 4);

        JsonContent<WayLinearSectionMiniDto> result = jackson.write(linearSectionDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathNumberValue("$.number").isEqualTo(4);
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        linearSectionDto = new WayLinearSectionMiniDto(1L, null);

        JsonContent<WayLinearSectionMiniDto> result = jackson.write(linearSectionDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathNumberValue("$.number").isNull();
    }

}