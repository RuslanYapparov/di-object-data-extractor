package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class MainWayLashDtoJsonTest {
    @Autowired
    private JacksonTester<MainWayLashDto> jackson;
    private final MainWayLashDto mainWayLashDto = (MainWayLashDto)
            DiodeTestDataCreator.createLashDto(WayType.STATION_MAIN_WAY);

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<MainWayLashDto> result = jackson.write(mainWayLashDto);
        MainWayLashDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(mainWayLashDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<MainWayLashDto> result = jackson.write(mainWayLashDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("77ТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.lineSide").isEqualTo("ПРАВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.length").isEqualTo(800.07);
        assertThat(result).extractingJsonPathNumberValue("$.startCentimeterOnMeter").isEqualTo(15);
        assertThat(result).extractingJsonPathNumberValue("$.endCentimeterOnMeter").isEqualTo(17);
        assertThat(result).extractingJsonPathStringValue("$.weldingCompany").isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.installationDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathStringValue("$.installedBy").isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathNumberValue("$.passedTonnageBeforeInstall")
                .isEqualTo(100.01);
        assertThat(result).extractingJsonPathNumberValue("$.passedTonnageAfterInstall")
                .isEqualTo(200.02);
        assertThat(result).extractingJsonPathStringValue("$.lastTemperatureTensionDischargingDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathNumberValue("$.lastTemperatureTensionDischargingTemperature")
                .isEqualTo(10);
        assertThat(result).extractingJsonPathStringValue("$.lastTemperatureTensionDischargingReason")
                .isEqualTo("ВЫПРАВКА");
        assertThat(result).extractingJsonPathNumberValue("$.mainWayNumber")
                .isEqualTo(7);
        assertThat(result).extractingJsonPathNumberValue("$.mainWaySection.transportDirection.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.mainWaySection.transportDirection.name")
                .isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathNumberValue("$.mainWaySection.startKm").isEqualTo(7777);
        assertThat(result).extractingJsonPathNumberValue("$.mainWaySection.startMeter").isEqualTo(777);
        assertThat(result).extractingJsonPathNumberValue("$.mainWaySection.endKm").isEqualTo(7777);
        assertThat(result).extractingJsonPathNumberValue("$.mainWaySection.endMeter").isEqualTo(777);
    }

}