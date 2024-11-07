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
public class MainWayPlaceDtoJsonTest {
    @Autowired
    private JacksonTester<MainWayPlaceDto> jackson;
    private MainWayPlaceDto placeDto = DiodeTestDataCreator.createMainWayPlaceDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<MainWayPlaceDto> result = jackson.write(placeDto);
        MainWayPlaceDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(placeDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<MainWayPlaceDto> result = jackson.write(placeDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.transportDirection.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.transportDirection.name")
                .isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathNumberValue("$.km").isEqualTo(7777);
        assertThat(result).extractingJsonPathNumberValue("$.meterOnKm").isEqualTo(777);
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        placeDto = new MainWayPlaceDto(null, null, null);

        JsonContent<MainWayPlaceDto> result = jackson.write(placeDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.transportDirection.id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.transportDirection.name").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.km").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.meterOnKm").isNull();
    }

}