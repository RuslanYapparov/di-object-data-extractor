package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.mini;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.RailMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util.TcdTestDataCreator;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.BrokenRailExtraInfoDto;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class RailInfoMiniDtoJsonTest {
    @Autowired
    private JacksonTester<RailInfoMiniDto> jackson;
    private final BrokenRailExtraInfoDto brokenRailExtraInfoDto =
            new BrokenRailExtraInfoDto(LocalDate.now(), 0, 0);
    private final RailMiniDto railMiniDto =
            new RailMiniDto(1L,
                    "ЛЕВАЯ",
                    "БЕССТЫКОВОЙ ПУТЬ",
                    1234.567F,
                    7654.321F);

    private final RailInfoMiniDto railInfoMiniDto = new RailInfoMiniDto(
            DiodeTestDataCreator.createLocationDto(),
            railMiniDto,
            brokenRailExtraInfoDto,
            TcdTestDataCreator.createDocMetaDataDto(DiodeTestDataCreator.createWayMaintenanceDistanceMiniDto()));

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<RailInfoMiniDto> result = jackson.write(railInfoMiniDto);
        RailInfoMiniDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(railInfoMiniDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedStringToday = formatter.format(today);
        JsonContent<RailInfoMiniDto> result = jackson.write(railInfoMiniDto);

        assertThat(result).extractingJsonPathStringValue("$.docType").isEqualTo("OBJ_INFO");
        assertThat(result).extractingJsonPathStringValue("$.docSubType").isEqualTo("P_OBJ_INFO_RAIL");
        assertThat(result).extractingJsonPathStringValue("$.pObjInfoDtoType").isEqualTo("RAIL_MINI");
        assertThat(result).extractingJsonPathStringValue("$.location.wayType")
                .isEqualTo("STATION_MAIN_WAY");
        assertThat(result).extractingJsonPathNumberValue("$.location.locationId").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.location.wayNumber")
                .isEqualTo("1a");
        assertThat(result).extractingJsonPathNumberValue("$.location.km").isEqualTo(1);
        assertThat(result).extractingJsonPathNumberValue("$.location.picket").isEqualTo(1);
        assertThat(result).extractingJsonPathNumberValue("$.location.meter").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.location.lineSide")
                .isEqualTo("LEFT");
        assertThat(result).extractingJsonPathNumberValue("$.rail.id")
                .isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.rail.lineSide").isEqualTo("ЛЕВАЯ");
        assertThat(result).extractingJsonPathStringValue("$.rail.railKind")
                .isEqualTo("БЕССТЫКОВОЙ ПУТЬ");
        assertThat(result).extractingJsonPathNumberValue("$.rail.startCoordinate")
                .isEqualTo(1234.567);
        assertThat(result).extractingJsonPathNumberValue("$.rail.endCoordinate")
                .isEqualTo(7654.321);
        assertThat(result).extractingJsonPathStringValue("$.brokenRailExtraInfo.railBreakDate")
                .isEqualTo(formattedStringToday);
        assertThat(result).extractingJsonPathNumberValue("$.brokenRailExtraInfo.railTemp")
                .isEqualTo(0);
        assertThat(result).extractingJsonPathNumberValue("$.brokenRailExtraInfo.airTemp")
                .isEqualTo(0);
        assertThat(result).extractingJsonPathStringValue("$.metaData.enterprise.type")
                .isEqualTo("ПЧ");
        assertThat(result).extractingJsonPathNumberValue("$.metaData.enterprise.id")
                .isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.metaData.enterprise.name")
                .isEqualTo("ТЕСТОВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.metaData.enterprise.number")
                .isEqualTo(7);
        assertThat(result).extractingJsonPathBooleanValue("$.metaData.enterprise.ich")
                .isEqualTo(false);
        assertThat(result).extractingJsonPathNumberValue("$.metaData.signer.id")
                .isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.metaData.signer.fullName")
                .isEqualTo("Начальников Начальник Начальникович");
        assertThat(result).extractingJsonPathStringValue("$.metaData.signer.jobTitleAbbreviation")
                .isEqualTo("ПЧ");
        assertThat(result).extractingJsonPathStringValue("$.metaData.date")
                .isEqualTo(formattedStringToday);
        assertThat(result).extractingJsonPathStringValue("$.metaData.performer.firstName")
                .isEqualTo("Тест");
        assertThat(result).extractingJsonPathStringValue("$.metaData.performer.lastName")
                .isEqualTo("Тестов");
        assertThat(result).extractingJsonPathStringValue("$.metaData.performer.patronymic")
                .isEqualTo("Тестович");
        assertThat(result).extractingJsonPathStringValue("$.metaData.performer.jobTitle")
                .isEqualTo("Инженер");
        assertThat(result).extractingJsonPathStringValue("$.metaData.performer.telephone")
                .isEqualTo("12345678");
        assertThat(result).extractingJsonPathStringValue("$.metaData.performer.workEmail")
                .isEqualTo("test@test");
    }

}