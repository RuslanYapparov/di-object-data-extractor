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
public class MainWayDtoJsonTest {
    @Autowired
    private JacksonTester<MainWayDto> jackson;

    private MainWayDto mainWay = DiodeTestDataCreator.createMainWayDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<MainWayDto> result = jackson.write(mainWay);
        MainWayDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(mainWay).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<MainWayDto> result = jackson.write(mainWay);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.transportDirection.name").isEqualTo("ТЕСТ - ТЕСТ");
        assertThat(result).extractingJsonPathNumberValue("$.transportDirection.id").isEqualTo(7);
        assertThat(result).extractingJsonPathNumberValue("$.number").isEqualTo(7);
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        mainWay = new MainWayDto(null, null, null);

        JsonContent<MainWayDto> result = jackson.write(mainWay);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.transportDirection.name").isNull();
        assertThat(result).extractingJsonPathStringValue("$.transportDirection.id").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.number").isNull();
    }

}