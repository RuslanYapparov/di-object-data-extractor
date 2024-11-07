package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.PerformerDto;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class PerformerDtoJsonTest {
    @Autowired
    private JacksonTester<PerformerDto> jackson;
    private PerformerDto performerDto;

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        performerDto = new PerformerDto("Тест",
                                        "Тестов",
                                        "Тестович",
                                        "Инженер",
                                        "12345678",
                                        "@test@test");

        JsonContent<PerformerDto> result = jackson.write(performerDto);
        PerformerDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(performerDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        performerDto = new PerformerDto("Тестов",
                                        "Тест",
                                        "Тестович",
                                        "Инженер",
                                        "12345678",
                                        "@test@test");

        JsonContent<PerformerDto> result = jackson.write(performerDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.firstName").isEqualTo("Тест");
        assertThat(result).extractingJsonPathStringValue("$.lastName").isEqualTo("Тестов");
        assertThat(result).extractingJsonPathStringValue("$.patronymic").isEqualTo("Тестович");
        assertThat(result).extractingJsonPathStringValue("$.jobTitle").isEqualTo("Инженер");
        assertThat(result).extractingJsonPathStringValue("$.telephone").isEqualTo("12345678");
        assertThat(result).extractingJsonPathStringValue("$.workEmail").isEqualTo("@test@test");
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        performerDto = new PerformerDto(null, null, null, null, null, null);

        JsonContent<PerformerDto> result = jackson.write(performerDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.firstName").isNull();
        assertThat(result).extractingJsonPathStringValue("$.lastName").isNull();
        assertThat(result).extractingJsonPathStringValue("$.patronymic").isNull();
        assertThat(result).extractingJsonPathStringValue("$.jobTitle").isNull();
        assertThat(result).extractingJsonPathStringValue("$.telephone").isNull();
        assertThat(result).extractingJsonPathStringValue("$.workEmail").isNull();
    }

}