package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class LocationDtoJsonTest {
    @Autowired
    private JacksonTester<LocationDto> jackson;

    private final LocationDto locationDto = DiodeTestDataCreator.createLocationDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<LocationDto> result = jackson.write(locationDto);
        LocationDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(locationDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<LocationDto> result = jackson.write(locationDto);

        assertThat(result).extractingJsonPathStringValue("$.wayType").isEqualTo(WayType.STATION_MAIN_WAY.name());
        assertThat(result).extractingJsonPathNumberValue("$.locationId").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.wayNumber").isEqualTo("1a");
        assertThat(result).extractingJsonPathNumberValue("$.km").isEqualTo(1);
        assertThat(result).extractingJsonPathNumberValue("$.picket").isEqualTo(1);
        assertThat(result).extractingJsonPathNumberValue("$.meter").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.lineSide").isEqualTo(LineSide.LEFT.name());
    }

}