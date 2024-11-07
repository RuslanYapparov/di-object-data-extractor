package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util.TcdTestDataCreator;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JointInfoDtoJsonTest {
    @Autowired
    private JacksonTester<JointInfoDto> jackson;
    private final JointInfoDto jointInfoDto = createJointInfoDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<JointInfoDto> result = jackson.write(jointInfoDto);
        JointInfoDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(jointInfoDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<JointInfoDto> result = jackson.write(jointInfoDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.docType").isEqualTo("OBJ_INFO");
        assertThat(result).extractingJsonPathStringValue("$.docSubType").isEqualTo("P_OBJ_INFO_JOINT");
        assertThat(result).extractingJsonPathStringValue("$.pObjInfoDtoType").isEqualTo("JOINT_FULL");
        assertThat(result).extractingJsonPathStringValue("$.locationDescription").isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.jointLocation.wayType")
                .isEqualTo("STATION_MAIN_WAY");
        assertThat(result).extractingJsonPathStringValue("$.jointLocation.wayNumber")
                .isEqualTo("1a");
        assertThat(result).extractingJsonPathStringValue("$.jointLocation.lineSide")
                .isEqualTo("LEFT");
        assertThat(result).extractingJsonPathNumberValue("$.wayCharacteristics.slope")
                .isEqualTo(2.5);
        assertThat(result).extractingJsonPathNumberValue("$.wayCharacteristics.passedTonnageBeforeInstall")
                .isEqualTo(77.77);
        assertThat(result).extractingJsonPathStringValue("$.wayCharacteristics.intermediateRepairDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathNumberValue("$.joint.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.joint.type").isEqualTo("MAIN_COND");
        assertThat(result).extractingJsonPathStringValue("$.joint.lineSide").isEqualTo("RIGHT");
        assertThat(result).extractingJsonPathStringValue("$.joint.lastMeasureDate")
                .isEqualTo("29-03-2024");
        assertThat(result).extractingJsonPathStringValue("$.joint.connectorTypes").isEqualTo("СРСП");
        assertThat(result).extractingJsonPathNumberValue("$.joint.mainWayPlace.transportDirection.id")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathNumberValue("$.joint.mainWayPlace.meterOnKm")
                .isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.metaData.enterprise.type")
                .isEqualTo("ПЧ");
        assertThat(result).extractingJsonPathStringValue("$.metaData.signer.fullName")
                .isEqualTo("Начальников Начальник Начальникович");
        assertThat(result).extractingJsonPathStringValue("$.metaData.signer.jobTitleAbbreviation")
                .isEqualTo("ПЧ");
        assertThat(result).extractingJsonPathStringValue("$.metaData.performer.workEmail")
                .isEqualTo("test@test");
    }

    private JointInfoDto createJointInfoDto() {
        return new JointInfoDto(
                "ТЕСТ",
                DiodeTestDataCreator.createLocationDto(),
                DiodeTestDataCreator.createWayCharacteristicsDto(),
                DiodeTestDataCreator.createJointDto(WayType.INTERSTATION_TRACK, JointType.CONDUCTING),
                TcdTestDataCreator.createDocMetaDataDto()
        );
    }

}