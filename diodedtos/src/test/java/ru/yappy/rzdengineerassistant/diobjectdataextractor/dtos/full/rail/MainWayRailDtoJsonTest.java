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
public class MainWayRailDtoJsonTest {
    @Autowired
    private JacksonTester<MainWayRailDto> jackson;
    private final MainWayRailDto mainWayRailDto = (MainWayRailDto)
            DiodeTestDataCreator.createRailDto(WayType.INTERSTATION_TRACK);

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<MainWayRailDto> result = jackson.write(mainWayRailDto);
        MainWayRailDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(mainWayRailDto);
        assertThat(parsedDto.hashCode()).isEqualTo(mainWayRailDto.hashCode());
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<MainWayRailDto> result = jackson.write(mainWayRailDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(777);
        assertThat(result).extractingJsonPathNumberValue("$.length").isEqualTo(25.0);
        assertThat(result).extractingJsonPathStringValue("$.lineSide").isEqualTo("ПРАВАЯ");
        assertThat(result).extractingJsonPathStringValue("$.railKind").isEqualTo("ЗВЕНЬЕВОЙ");
        assertThat(result).extractingJsonPathStringValue("$.type").isEqualTo("Р65");
        assertThat(result).extractingJsonPathStringValue("$.category").isEqualTo("Т-415");
        assertThat(result).extractingJsonPathStringValue("$.thermalHardening")
                .isEqualTo("ТЕРМО-ПРОЧНЫЙ");
        assertThat(result).extractingJsonPathStringValue("$.factory").isEqualTo("К");
        assertThat(result).extractingJsonPathStringValue("$.rollingDate").isEqualTo("X-2017");
        assertThat(result).extractingJsonPathStringValue("$.fuseNumber").isEqualTo("KV-77777");
        assertThat(result).extractingJsonPathStringValue("$.installationDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathStringValue("$.installationType").isEqualTo("НОВЫЙ");
        assertThat(result).extractingJsonPathNumberValue("$.gapBefore").isEqualTo(15);
        assertThat(result).extractingJsonPathNumberValue("$.gapAfter").isEqualTo(17);
        assertThat(result).extractingJsonPathNumberValue("$.passedTonnageBeforeInstall")
                .isEqualTo(77.77);
        assertThat(result).extractingJsonPathNumberValue("$.passedTonnageAfterInstall")
                .isEqualTo(777.777);
        assertThat(result).extractingJsonPathNumberValue("$.verticalWear").isEqualTo(2);
        assertThat(result).extractingJsonPathNumberValue("$.horizontalWear").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.lastMeasureDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathNumberValue("$.lastMeasureRailTemp").isEqualTo(10);
        assertThat(result).extractingJsonPathNumberValue("$.mainWayNumber").isEqualTo(7);

        assertThat(result).extractingJsonPathNumberValue("$.mainWaySection.transportDirection.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.mainWaySection.transportDirection.name")
                .isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathNumberValue("$.mainWaySection.startKm").isEqualTo(7777);
        assertThat(result).extractingJsonPathNumberValue("$.mainWaySection.startMeter").isEqualTo(777);
        assertThat(result).extractingJsonPathNumberValue("$.mainWaySection.endKm").isEqualTo(7777);
        assertThat(result).extractingJsonPathNumberValue("$.mainWaySection.endMeter").isEqualTo(777);

        assertThat(result).extractingJsonPathNumberValue("$.railLash.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.railLash.name").isEqualTo("77ТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.railLash.lineSide").isEqualTo("ПРАВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.railLash.length").isEqualTo(800.07);
        assertThat(result).extractingJsonPathNumberValue("$.railLash.startCentimeterOnMeter").isEqualTo(15);
        assertThat(result).extractingJsonPathNumberValue("$.railLash.endCentimeterOnMeter").isEqualTo(17);
        assertThat(result).extractingJsonPathStringValue("$.railLash.weldingCompany").isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.railLash.installationDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathStringValue("$.railLash.installedBy").isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathNumberValue("$.railLash.passedTonnageBeforeInstall")
                .isEqualTo(100.01);
        assertThat(result).extractingJsonPathNumberValue("$.railLash.passedTonnageAfterInstall")
                .isEqualTo(200.02);
        assertThat(result).extractingJsonPathStringValue("$.railLash.lastTemperatureTensionDischargingDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathNumberValue("$.railLash.lastTemperatureTensionDischargingTemperature")
                .isEqualTo(10);
        assertThat(result).extractingJsonPathStringValue("$.railLash.lastTemperatureTensionDischargingReason")
                .isEqualTo("ВЫПРАВКА");
        assertThat(result).extractingJsonPathNumberValue("$.railLash.mainWayNumber")
                .isEqualTo(7);
        assertThat(result).extractingJsonPathNumberValue("$.railLash.mainWaySection.transportDirection.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.railLash.mainWaySection.transportDirection.name")
                .isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathNumberValue("$.railLash.mainWaySection.startKm")
                .isEqualTo(7777);
        assertThat(result).extractingJsonPathNumberValue("$.railLash.mainWaySection.startMeter")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathNumberValue("$.railLash.mainWaySection.endKm")
                .isEqualTo(7777);
        assertThat(result).extractingJsonPathNumberValue("$.railLash.mainWaySection.endMeter")
                .isEqualTo(777);

        assertThat(result).extractingJsonPathNumberValue("$.railSwitch.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.railSwitch.name").isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.railSwitch.fullName")
                .isEqualTo("ТЕСТТЕСТТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.railSwitch.project").isEqualTo("2750");
        assertThat(result).extractingJsonPathStringValue("$.railSwitch.railType").isEqualTo("Р65");
        assertThat(result).extractingJsonPathStringValue("$.railSwitch.beamsMaterial").isEqualTo("Ж/Б");
        assertThat(result).extractingJsonPathStringValue("$.railSwitch.ballastType")
                .isEqualTo("ЩЕБЕНЬ");
        assertThat(result).extractingJsonPathStringValue("$.railSwitch.crossMarking").isEqualTo("1/11");
        assertThat(result).extractingJsonPathStringValue("$.railSwitch.controlType").isEqualTo("ЭЦ");
        assertThat(result).extractingJsonPathStringValue("$.railSwitch.lineSide").isEqualTo("ЛЕВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.railSwitch.gauge").isEqualTo(1524);
        assertThat(result).extractingJsonPathStringValue("$.railSwitch.installationDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathNumberValue("$.railSwitch.passedTonnageBeforeInstall")
                .isEqualTo(777.777);
        assertThat(result).extractingJsonPathNumberValue("$.railSwitch.passedTonnageAfterInstall")
                .isEqualTo(777.777);
        assertThat(result).extractingJsonPathNumberValue("$.railSwitch.outcrossCurveLength")
                .isEqualTo(77);
        assertThat(result).extractingJsonPathStringValue("$.railSwitch.stationParkName")
                .isEqualTo("ПАРК");
        assertThat(result).extractingJsonPathNumberValue("$.railSwitch.stationMainWay.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.railSwitch.stationMainWay.number")
                .isEqualTo("7 станции Тестовая");
        assertThat(result).extractingJsonPathNumberValue("$.railSwitch.stationMainWay.station.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.railSwitch.stationMainWay.station.name")
                .isEqualTo("ТЕСТОВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.railSwitch.frameRailJointPlace.transportDirection.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.railSwitch.frameRailJointPlace.transportDirection.name")
                .isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathNumberValue("$.railSwitch.frameRailJointPlace.km")
                .isEqualTo(7777);
        assertThat(result).extractingJsonPathNumberValue("$.railSwitch.frameRailJointPlace.meterOnKm")
                .isEqualTo(777);
    }

}