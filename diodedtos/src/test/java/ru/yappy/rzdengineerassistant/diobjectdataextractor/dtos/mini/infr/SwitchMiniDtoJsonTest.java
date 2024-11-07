package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class SwitchMiniDtoJsonTest {
    @Autowired
    private JacksonTester<SwitchMiniDto> jackson;
    private SwitchMiniDto switchDto = createTestSwitchMiniDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<SwitchMiniDto> result = jackson.write(switchDto);
        SwitchMiniDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(switchDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<SwitchMiniDto> result = jackson.write(switchDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.number").isEqualTo("7 станции Тестовая");
        assertThat(result).extractingJsonPathNumberValue("$.station.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.station.name").isEqualTo("ТЕСТОВАЯ");
        assertThat(result).extractingJsonPathStringValue("$.station.type").isEqualTo("Станция");
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        switchDto = new SwitchMiniDto(null, null, null);

        JsonContent<SwitchMiniDto> result = jackson.write(switchDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.number").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.station.id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.station.name").isNull();
    }

    private SwitchMiniDto createTestSwitchMiniDto() {
        return new SwitchMiniDto(777L, "7 станции Тестовая", DiodeTestDataCreator.createStationNameDto());
    }

}