package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.empl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class SpecialistMiniDtoJsonTest {
    @Autowired
    private JacksonTester<SpecialistMiniDto> jackson;

    private SpecialistMiniDto specialistMiniDto;

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        specialistMiniDto = new SpecialistMiniDto(1L, "Тестин Тест Тестович", "Инженер 1 категории");

        JsonContent<SpecialistMiniDto> result = jackson.write(specialistMiniDto);
        SpecialistMiniDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(specialistMiniDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        specialistMiniDto = new SpecialistMiniDto(1L, "Тестин Тест Тестович", "Инженер 1 категории");

        JsonContent<SpecialistMiniDto> result = jackson.write(specialistMiniDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.fullName")
                .isEqualTo("Тестин Тест Тестович");
        assertThat(result).extractingJsonPathStringValue("$.jobTitle")
                .isEqualTo("Инженер 1 категории");
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        specialistMiniDto = new SpecialistMiniDto(1L, null, null);

        JsonContent<SpecialistMiniDto> result = jackson.write(specialistMiniDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.fullName").isNull();
        assertThat(result).extractingJsonPathStringValue("$.jobTitle").isNull();
    }

}