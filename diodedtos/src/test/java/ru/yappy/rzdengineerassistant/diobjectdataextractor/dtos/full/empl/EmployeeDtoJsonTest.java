package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class EmployeeDtoJsonTest {
    @Autowired
    private JacksonTester<EmployeeDto> jackson;
    private EmployeeDto employeeDto = DiodeTestDataCreator.createEmployeeDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<EmployeeDto> result = jackson.write(employeeDto);
        EmployeeDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(employeeDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<EmployeeDto> result = jackson.write(employeeDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathNumberValue("$.asutrPersonnelNumber").isEqualTo(3333);
        assertThat(result).extractingJsonPathStringValue("$.surname")
                .isEqualTo("Тестов");
        assertThat(result).extractingJsonPathStringValue("$.name")
                .isEqualTo("Тест");
        assertThat(result).extractingJsonPathStringValue("$.patronymic")
                .isEqualTo("Тестович");
        assertThat(result).extractingJsonPathStringValue("$.employeeType")
                .isEqualTo("РАБОЧИЙ");
        assertThat(result).extractingJsonPathStringValue("$.jobTitleAbbreviation")
                .isEqualTo("Т");
        assertThat(result).extractingJsonPathStringValue("$.fullTitle")
                .isEqualTo("Тестирующий сотрудник");
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        employeeDto = new EmployeeDto(null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        JsonContent<EmployeeDto> result = jackson.write(employeeDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.surname").isNull();
        assertThat(result).extractingJsonPathStringValue("$.name").isNull();
        assertThat(result).extractingJsonPathStringValue("$.patronymic").isNull();
        assertThat(result).extractingJsonPathStringValue("$.employeeType").isNull();
        assertThat(result).extractingJsonPathStringValue("$.jobTitleAbbreviation").isNull();
        assertThat(result).extractingJsonPathStringValue("$.fullTitle").isNull();
    }

}
