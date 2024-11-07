package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl.EmployeeDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util.TcdTestDataCreator;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ProtMetaDataJsonTest {
    @Autowired
    private JacksonTester<ProtMetaDataDto> json;
    private final ProtMetaDataDto protData = TcdTestDataCreator.createProtMetaDataDto(ProtocolType.PROT_TECHNICAL_FAILURE);

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<ProtMetaDataDto> result = json.write(protData);
        ProtMetaDataDto parsedDto = json.parse(result.getJson()).getObject();
        EmployeeDto[] dtoParticipants = protData.participants();
        EmployeeDto[] parsedParticipants = parsedDto.participants();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto.type()).isEqualTo(protData.type());
        assertThat(parsedDto.protNumber()).isEqualTo(protData.protNumber());
        assertThat(parsedDto.techNumber()).isEqualTo(protData.techNumber());
        assertThat(parsedDto.locality()).isEqualTo(protData.locality());
        assertThat(dtoParticipants.length).isEqualTo(parsedParticipants.length);
        assertThat(Arrays.equals(parsedParticipants, dtoParticipants)).isTrue();
        assertThat(parsedDto.listeners()).isEqualTo(protData.listeners());
        assertThat(parsedDto.speakerOrder()).isEqualTo(protData.speakerOrder());
        assertThat(parsedDto.executionControlEmployee()).isEqualTo(protData.executionControlEmployee());
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<ProtMetaDataDto> result = json.write(protData);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.type").isEqualTo("PROT_TECHNICAL_FAILURE");
        assertThat(result).extractingJsonPathStringValue("$.protNumber").isEqualTo("777");
        assertThat(result).extractingJsonPathStringValue("$.techNumber").isEqualTo("1234567890");
        assertThat(result).extractingJsonPathStringValue("$.locality").isEqualTo("г. Шмаровск");
        assertThat(result).extractingJsonPathStringValue("$.listeners")
                .isEqualTo("начальники участков, техники линейных участков, инженера дистанции пути");
        assertThat(result).extractingJsonPathStringValue("$.speakerOrder")
                .isEqualTo("(Девов, Тестов, Девов)");
        assertThat(result).extractingJsonPathStringValue("$.participants[0].surname")
                .isEqualTo("Тестов");
        assertThat(result).extractingJsonPathStringValue("$.participants[0].jobTitleAbbreviation")
                .isEqualTo("Т");
        assertThat(result).extractingJsonPathNumberValue("$.participants[1].asutrPersonnelNumber")
                .isEqualTo(4444);
        assertThat(result).extractingJsonPathStringValue("$.participants[1].fullTitle")
                        .isEqualTo("Начальник дистанции пути");
        assertThat(result).extractingJsonPathStringValue("$.participants[2].name")
                .isEqualTo("Сисадмин");
        assertThat(result).extractingJsonPathStringValue("$.participants[2].employeeType")
                .isEqualTo("РУКОВДИТЕЛЬ");
        assertThat(result).extractingJsonPathNumberValue("$.executionControlEmployee.asutrPersonnelNumber")
                .isEqualTo(3333);
        assertThat(result).extractingJsonPathStringValue("$.executionControlEmployee.fullTitle")
                .isEqualTo("Тестирующий сотрудник");
    }

}