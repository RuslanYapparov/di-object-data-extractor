package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class InterstationTrackSwitchDtoJsonTest {
    @Autowired
    private JacksonTester<InterstationTrackSwitchDto> jackson;
    private final InterstationTrackSwitchDto interstationTrackSwitchDto =
            (InterstationTrackSwitchDto) DiodeTestDataCreator.createSwitchDto(WayType.INTERSTATION_TRACK);

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<InterstationTrackSwitchDto> result = jackson.write(interstationTrackSwitchDto);
        InterstationTrackSwitchDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(interstationTrackSwitchDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<InterstationTrackSwitchDto> result = jackson.write(interstationTrackSwitchDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.fullName").isEqualTo("ТЕСТТЕСТТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.project").isEqualTo("2750");
        assertThat(result).extractingJsonPathStringValue("$.railType").isEqualTo("Р65");
        assertThat(result).extractingJsonPathStringValue("$.beamsMaterial").isEqualTo("Ж/Б");
        assertThat(result).extractingJsonPathStringValue("$.ballastType").isEqualTo("ЩЕБЕНЬ");
        assertThat(result).extractingJsonPathStringValue("$.crossMarking").isEqualTo("1/11");
        assertThat(result).extractingJsonPathStringValue("$.controlType").isEqualTo("ЭЦ");
        assertThat(result).extractingJsonPathStringValue("$.lineSide").isEqualTo("ЛЕВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.gauge").isEqualTo(1524);
        assertThat(result).extractingJsonPathStringValue("$.installationDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathNumberValue("$.passedTonnageBeforeInstall")
                .isEqualTo(777.777);
        assertThat(result).extractingJsonPathNumberValue("$.passedTonnageAfterInstall")
                .isEqualTo(777.777);
        assertThat(result).extractingJsonPathNumberValue("$.mainWayNumber").isEqualTo(7);
        assertThat(result).extractingJsonPathNumberValue("$.outcrossCurveLength").isEqualTo(77);
        assertThat(result).extractingJsonPathStringValue("$.controlStation").isEqualTo("ТЕСТОВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.frameRailJointPlace.transportDirection.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.frameRailJointPlace.transportDirection.name")
                .isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathNumberValue("$.frameRailJointPlace.km")
                .isEqualTo(7777);
        assertThat(result).extractingJsonPathNumberValue("$.frameRailJointPlace.meterOnKm")
                .isEqualTo(777);
    }

}