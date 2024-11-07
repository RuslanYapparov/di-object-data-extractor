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
public class MainWaySectionDtoJsonTest {
    @Autowired
    private JacksonTester<MainWaySectionDto> jackson;
    private MainWaySectionDto mainWaySectionDto = DiodeTestDataCreator.createMainWaySectionDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<MainWaySectionDto> result = jackson.write(mainWaySectionDto);
        MainWaySectionDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(mainWaySectionDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<MainWaySectionDto> result = jackson.write(mainWaySectionDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.transportDirection.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.transportDirection.name")
                .isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathNumberValue("$.startKm").isEqualTo(7777);
        assertThat(result).extractingJsonPathNumberValue("$.startMeter").isEqualTo(777);
        assertThat(result).extractingJsonPathNumberValue("$.endKm").isEqualTo(7777);
        assertThat(result).extractingJsonPathNumberValue("$.endMeter").isEqualTo(777);
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        mainWaySectionDto = new MainWaySectionDto(null,
                null,
                null,
                null,
                null);

        JsonContent<MainWaySectionDto> result = jackson.write(mainWaySectionDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.transportDirection.id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.transportDirection.name").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.startKm").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.startMeter").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.endKm").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.endMeter").isNull();
    }

}