package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util.TcdTestDataCreator;

import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class RailInfoDtoJsonTest {
    @Autowired
    private JacksonTester<RailInfoDto> jackson;
    private RailInfoDto railInfoDto = createRailInfoDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<RailInfoDto> result = jackson.write(railInfoDto);
        RailInfoDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(railInfoDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<RailInfoDto> result = jackson.write(railInfoDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.docType").isEqualTo("OBJ_INFO");
        assertThat(result).extractingJsonPathStringValue("$.docSubType").isEqualTo("P_OBJ_INFO_RAIL");
        assertThat(result).extractingJsonPathStringValue("$.pObjInfoDtoType").isEqualTo("RAIL_FULL");
        assertThat(result).extractingJsonPathStringValue("$.locationDescription").isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathNumberValue("$.wayCharacteristics.slope")
                .isEqualTo(2.5);
        assertThat(result).extractingJsonPathNumberValue("$.wayCharacteristics.passedTonnageBeforeInstall")
                .isEqualTo(77.77);
        assertThat(result).extractingJsonPathStringValue("$.wayCharacteristics.intermediateRepairDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathNumberValue("$.rail.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.rail.lastMeasureDate")
                .isEqualTo("01-01-2024");
        assertThat(result).extractingJsonPathNumberValue("$.rail.railLash.id").isEqualTo(777);
        assertThat(result).extractingJsonPathNumberValue("$.brokenRailExtraInfo.railTemp")
                .isEqualTo(0);
        assertThat(result).extractingJsonPathStringValue("$.metaData.enterprise.type")
                .isEqualTo("ПЧ");
        assertThat(result).extractingJsonPathStringValue("$.metaData.signer.fullName")
                .isEqualTo("Начальников Начальник Начальникович");
        assertThat(result).extractingJsonPathStringValue("$.metaData.signer.jobTitleAbbreviation")
                .isEqualTo("ПЧ");
        assertThat(result).extractingJsonPathStringValue("$.metaData.performer.workEmail")
                .isEqualTo("test@test");
    }

    private RailInfoDto createRailInfoDto() {
        return new RailInfoDto(
                "ТЕСТ",
                DiodeTestDataCreator.createWayCharacteristicsDto(),
                DiodeTestDataCreator.createRailDto(WayType.STATION_MAIN_WAY),
                new BrokenRailExtraInfoDto(LocalDate.now(), 0, 0),
                TcdTestDataCreator.createDocMetaDataDto()
        );
    }

}