package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.wayc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class WayCharacteristicsDtoJsonTest {
    @Autowired
    private JacksonTester<WayCharacteristicsDto> jackson;
    private final WayCharacteristicsDto wayCharacteristicsDto = DiodeTestDataCreator.createWayCharacteristicsDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<WayCharacteristicsDto> result = jackson.write(wayCharacteristicsDto);
        WayCharacteristicsDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(wayCharacteristicsDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<WayCharacteristicsDto> result = jackson.write(wayCharacteristicsDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.slope").isEqualTo(2.5);
        assertThat(result).extractingJsonPathBooleanValue("$.straight").isEqualTo(true);
        assertThat(result).extractingJsonPathStringValue("$.curveSide").isEqualTo("ПРАВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.radius").isEqualTo(1000);
        assertThat(result).extractingJsonPathNumberValue("$.railElevation").isEqualTo(15);
        assertThat(result).extractingJsonPathNumberValue("$.passengerTrainSpeed").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.freightTrainSpeed").isEqualTo(40);
        assertThat(result).extractingJsonPathNumberValue("$.passedTonnageBeforeInstall")
                .isEqualTo(77.77);
        assertThat(result).extractingJsonPathNumberValue("$.passedTonnageAfterInstall")
                .isEqualTo(777.777);
        assertThat(result).extractingJsonPathNumberValue("$.freightIntensity").isEqualTo(0.5);
        assertThat(result).extractingJsonPathStringValue("$.lineClassSpecialization").isEqualTo("1Т7");
        assertThat(result).extractingJsonPathStringValue("$.groupClassCode").isEqualTo("Г7-II");
        assertThat(result).extractingJsonPathStringValue("$.railType").isEqualTo("Р65");
        assertThat(result).extractingJsonPathStringValue("$.railCategory").isEqualTo("У");
        assertThat(result).extractingJsonPathStringValue("$.thermalHardening").isEqualTo("ДА");
        assertThat(result).extractingJsonPathStringValue("$.wayType").isEqualTo("БЕССТЫКОВОЙ");
        assertThat(result).extractingJsonPathStringValue("$.factory").isEqualTo("А");
        assertThat(result).extractingJsonPathStringValue("$.factoryYear").isEqualTo("2007");
        assertThat(result).extractingJsonPathStringValue("$.installationDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathStringValue("$.sleeperMaterial").isEqualTo("ДЕРЕВО");
        assertThat(result).extractingJsonPathStringValue("$.fasteningType").isEqualTo("КОСТЫЛИ");
        assertThat(result).extractingJsonPathNumberValue("$.sleepersPerKm").isEqualTo(2007);
        assertThat(result).extractingJsonPathStringValue("$.ballastType").isEqualTo("ЩЕБЕНЬ");
        assertThat(result).extractingJsonPathNumberValue("$.ballastHeight").isEqualTo(250);
        assertThat(result).extractingJsonPathStringValue("$.capitalRepairType")
                .isEqualTo("КРС");
        assertThat(result).extractingJsonPathStringValue("$.capitalRepairDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathStringValue("$.intermediateRepairType")
                .isEqualTo("ППВ");
        assertThat(result).extractingJsonPathStringValue("$.intermediateRepairDate")
                .isEqualTo("01-01-2024");
    }

}