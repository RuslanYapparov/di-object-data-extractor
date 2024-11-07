package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.empl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ManagerMiniDtoJsonTest {
    @Autowired
    private JacksonTester<ManagerMiniDto> jackson;

    private ManagerMiniDto managerMiniDto = DiodeTestDataCreator.createManagerMiniDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<ManagerMiniDto> result = jackson.write(managerMiniDto);
        ManagerMiniDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(managerMiniDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<ManagerMiniDto> result = jackson.write(managerMiniDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.fullName")
                .isEqualTo("Начальников Начальник Начальникович");
        assertThat(result).extractingJsonPathStringValue("$.jobTitleAbbreviation").isEqualTo("ПЧ");
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        managerMiniDto = new ManagerMiniDto(1L, null, null);

        JsonContent<ManagerMiniDto> result = jackson.write(managerMiniDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.fullName").isNull();
        assertThat(result).extractingJsonPathStringValue("$.jobTitleAbbreviation").isNull();
    }

}