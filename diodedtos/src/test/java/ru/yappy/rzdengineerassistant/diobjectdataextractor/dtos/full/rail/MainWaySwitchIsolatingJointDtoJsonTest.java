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
public class MainWaySwitchIsolatingJointDtoJsonTest {
    @Autowired
    private JacksonTester<MainWaySwitchIsolatingJointDto> jackson;
    private MainWaySwitchIsolatingJointDto mainWaySwitchIsolatingJointDto = (MainWaySwitchIsolatingJointDto)
            DiodeTestDataCreator.createJointDto(WayType.STATION_MAIN_WAY, JointType.ISOLATING_SWITCH);

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<MainWaySwitchIsolatingJointDto> result = jackson.write(mainWaySwitchIsolatingJointDto);
        MainWaySwitchIsolatingJointDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(mainWaySwitchIsolatingJointDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<MainWaySwitchIsolatingJointDto> result = jackson.write(mainWaySwitchIsolatingJointDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.lineSide").isEqualTo("RIGHT");
        assertThat(result).extractingJsonPathStringValue("$.status").isEqualTo("ACTIVE");
        assertThat(result).extractingJsonPathStringValue("$.padsType").isEqualTo("2-хголовые");
        assertThat(result).extractingJsonPathNumberValue("$.padAmountOfHoles").isEqualTo(4);
        assertThat(result).extractingJsonPathNumberValue("$.gap").isEqualTo(7);
        assertThat(result).extractingJsonPathNumberValue("$.verticalStep").isEqualTo(1.1);
        assertThat(result).extractingJsonPathNumberValue("$.horizontalStep").isEqualTo(2.2);
        assertThat(result).extractingJsonPathStringValue("$.lastMeasureDate")
                .isEqualTo("29-03-2024");
        assertThat(result).extractingJsonPathNumberValue("$.lastMeasureRailTemp").isEqualTo(-10);
        assertThat(result).extractingJsonPathStringValue("$.lastPadsRemovalDate")
                .isEqualTo("05-05-2024");
        assertThat(result).extractingJsonPathStringValue("$.lastDemagnetizationDate")
                .isEqualTo("05-05-2024");
        assertThat(result).extractingJsonPathStringValue("$.lastDemagnetizationMethod")
                .isEqualTo("гуляш с пюрешкой");
        assertThat(result).extractingJsonPathNumberValue("$.magnetization")
                .isEqualTo(100);
        assertThat(result).extractingJsonPathStringValue("$.lastMagnetizationMeasureDate")
                .isEqualTo("05-05-2024");
        assertThat(result).extractingJsonPathNumberValue("$.jointNumber").isEqualTo(7);
        assertThat(result).extractingJsonPathNumberValue("$.mainWayNumber").isEqualTo(2);
        assertThat(result).extractingJsonPathNumberValue("$.mainWayPlace.transportDirection.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.mainWayPlace.transportDirection.name")
                .isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathNumberValue("$.mainWayPlace.km")
                .isEqualTo(7777);
        assertThat(result).extractingJsonPathNumberValue("$.mainWayPlace.meterOnKm")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathNumberValue("$.jointSwitch.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.jointSwitch.name").isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.jointSwitch.lineSide").isEqualTo("ЛЕВАЯ");
        assertThat(result).extractingJsonPathStringValue("$.jointSwitch.installationDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathNumberValue("$.jointSwitch.stationMainWay.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathNumberValue("$.jointSwitch.frameRailJointPlace.km")
                .isEqualTo(7777);
    }

}