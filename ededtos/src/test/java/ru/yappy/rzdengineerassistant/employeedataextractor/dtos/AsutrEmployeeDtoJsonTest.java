package ru.yappy.rzdengineerassistant.employeedataextractor.dtos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class AsutrEmployeeDtoJsonTest {
    @Autowired
    private JacksonTester<AsutrEmployeeDto> jackson;
    private AsutrEmployeeDto asutrEmployeeDto = EdeTestDataCreator.createAsutrEmployeeDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<AsutrEmployeeDto> result = jackson.write(asutrEmployeeDto);
        AsutrEmployeeDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(asutrEmployeeDto);
        assertThat(parsedDto.edType().getRuTitle()).isEqualTo("высшее");
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<AsutrEmployeeDto> result = jackson.write(asutrEmployeeDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.asutrPersonnelNumber")
                .isEqualTo(1234567);
        assertThat(result).extractingJsonPathStringValue("$.lastName").isEqualTo("Тестов");
        assertThat(result).extractingJsonPathStringValue("$.firstName").isEqualTo("Тест");
        assertThat(result).extractingJsonPathStringValue("$.patronymic").isEqualTo("Тестович");
        assertThat(result).extractingJsonPathStringValue("$.employeeType").isEqualTo("РАБОЧИЙ");
        assertThat(result).extractingJsonPathStringValue("$.jobTitleAbbreviation").isEqualTo("Т");
        assertThat(result).extractingJsonPathStringValue("$.fullJobTitle")
                .isEqualTo("Тестировщик путей сообщения");
        assertThat(result).extractingJsonPathStringValue("$.employmentDate").isEqualTo("01-01-2022");
        assertThat(result).extractingJsonPathNumberValue("$.employmentLength").isEqualTo(2.8);
        assertThat(result).extractingJsonPathStringValue("$.atPositionSince").isEqualTo("01-01-2023");
        assertThat(result).extractingJsonPathNumberValue("$.atPositionJobLength").isEqualTo(1.8);
        assertThat(result).extractingJsonPathStringValue("$.edType").isEqualTo("HIGHER_EDUCATION");
        assertThat(result).extractingJsonPathStringValue("$.edInstitutionName")
                .isEqualTo("Кусайкинская высшая тестировочная академия");
        assertThat(result).extractingJsonPathStringValue("$.faculty")
                .isEqualTo("Факультет прикладного тестирования");
        assertThat(result).extractingJsonPathNumberValue("$.edGraduationYear").isEqualTo(2022);
    }

}
