package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

@JsonTest
public class WayMaintenanceDistanceMiniDtoJsonTest {
    @Autowired
    private JacksonTester<WayMaintenanceDistanceMiniDto> jackson;
    private WayMaintenanceDistanceMiniDto distanceDto;

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        distanceDto = new WayMaintenanceDistanceMiniDto(1L, "ТЕСТОВАЯ", 777, true);

        JsonContent<WayMaintenanceDistanceMiniDto> result = jackson.write(distanceDto);
        WayMaintenanceDistanceMiniDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(distanceDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        distanceDto = new WayMaintenanceDistanceMiniDto(1L, "ТЕСТОВАЯ", 777, true);

        JsonContent<WayMaintenanceDistanceMiniDto> result = jackson.write(distanceDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.type").isEqualTo("ПЧ");
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("ТЕСТОВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.number").isEqualTo(777);
        assertThat(result).extractingJsonPathBooleanValue("$.ich").isEqualTo(true);
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        distanceDto = new WayMaintenanceDistanceMiniDto(1L, null, null, null);

        JsonContent<WayMaintenanceDistanceMiniDto> result = jackson.write(distanceDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.type").isEqualTo("ПЧ");
        assertThat(result).extractingJsonPathStringValue("$.name").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.number").isNull();
        assertThat(result).extractingJsonPathBooleanValue("$.ich").isNull();
    }

}