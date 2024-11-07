package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.empl.ManagerMiniDto;

import java.io.IOException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class WayMaintenanceDistanceDtoJsonTest {
    @Autowired
    private JacksonTester<WayMaintenanceDistanceDto> jackson;
    private WayMaintenanceDistanceDto wayMaintenanceDistanceDto = createWayMaintenanceDistanceDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<WayMaintenanceDistanceDto> result = jackson.write(wayMaintenanceDistanceDto);
        WayMaintenanceDistanceDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto.equals(wayMaintenanceDistanceDto)).isTrue();
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<WayMaintenanceDistanceDto> result = jackson.write(wayMaintenanceDistanceDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.type").isEqualTo("ПЧ");
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.regionalDirectorateAbbreviation").isEqualTo("ДИ");
        assertThat(result).extractingJsonPathNumberValue("$.number").isEqualTo(7);
        assertThat(result).extractingJsonPathBooleanValue("$.isIch").isTrue();
        assertThat(result).extractingJsonPathNumberValue("$.mainWays[0].id").isEqualTo(777);
        assertThat(result).extractingJsonPathNumberValue("$.stations[0].id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.stations[0].name").isEqualTo("ТЕСТОВАЯ");
        assertThat(result).extractingJsonPathStringValue("$.stations[0].type").isEqualTo("Станция");
        assertThat(result).extractingJsonPathNumberValue("$.managers[0].id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.managers[0].fullName")
                .isEqualTo("Начальников Начальник Начальникович");
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        wayMaintenanceDistanceDto = new WayMaintenanceDistanceDto(null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        JsonContent<WayMaintenanceDistanceDto> result = jackson.write(wayMaintenanceDistanceDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.type").isEqualTo("ПЧ");
        assertThat(result).extractingJsonPathNumberValue("$.id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.name").isNull();
        assertThat(result).extractingJsonPathStringValue("$.regionalDirectorateAbbreviation").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.number").isNull();
        assertThat(result).extractingJsonPathBooleanValue("$.isIch").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.mainWays[0].id").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.stations[0].id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.stations[0].name").isNull();
        assertThat(result).extractingJsonPathStringValue("$.stations[0].type").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.managers[0].id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.managers[0].fullName").isNull();
    }

    private WayMaintenanceDistanceDto createWayMaintenanceDistanceDto() {
        Set<MainWayDto> mainWays = Set.of(DiodeTestDataCreator.createMainWayDto());
        Set<StationNameDto> stations = Set.of(DiodeTestDataCreator.createStationNameDto());
        Set<ManagerMiniDto> managers = Set.of(DiodeTestDataCreator.createManagerMiniDto());

        WayMaintenanceDistanceDto wayMaintenanceDistanceDto = new WayMaintenanceDistanceDto(
                777L,
                "ТЕСТ",
                "ДИ",
                managers,
                7,
                true,
                mainWays,
                stations
        );

        return wayMaintenanceDistanceDto;
    }

}