package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util.TcdTestDataCreator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class DocMetaDataDtoJsonTest {
    @Autowired
    private JacksonTester<DocMetaDataDto> jackson;
    private DocMetaDataDto docMetaDataDto = TcdTestDataCreator.createDocMetaDataDto();

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<DocMetaDataDto> result = jackson.write(docMetaDataDto);
        DocMetaDataDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(docMetaDataDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDtoWithWayDistance_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedStringToday = formatter.format(today);

        JsonContent<DocMetaDataDto> result = jackson.write(docMetaDataDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.enterprise.type").isEqualTo("ПЧ");
        assertThat(result).extractingJsonPathNumberValue("$.enterprise.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.enterprise.name").isEqualTo("ТЕСТОВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.enterprise.number").isEqualTo(7);
        assertThat(result).extractingJsonPathBooleanValue("$.enterprise.ich").isEqualTo(false);
        assertThat(result).extractingJsonPathNumberValue("$.signer.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.signer.fullName")
                .isEqualTo("Начальников Начальник Начальникович");
        assertThat(result).extractingJsonPathStringValue("$.signer.jobTitleAbbreviation").isEqualTo("ПЧ");
        assertThat(result).extractingJsonPathStringValue("$.date").isEqualTo(formattedStringToday);
        assertThat(result).extractingJsonPathStringValue("$.performer.firstName").isEqualTo("Тест");
        assertThat(result).extractingJsonPathStringValue("$.performer.lastName").isEqualTo("Тестов");
        assertThat(result).extractingJsonPathStringValue("$.performer.patronymic").isEqualTo("Тестович");
        assertThat(result).extractingJsonPathStringValue("$.performer.jobTitle").isEqualTo("Инженер");
        assertThat(result).extractingJsonPathStringValue("$.performer.telephone").isEqualTo("12345678");
        assertThat(result).extractingJsonPathStringValue("$.performer.workEmail").isEqualTo("test@test");
    }

    @Test
    public void write_whenGetCorrectDtoWithStation_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedStringToday = formatter.format(today);
        docMetaDataDto = new DocMetaDataDto(DiodeTestDataCreator.createStationMiniDto(),
                DiodeTestDataCreator.createManagerMiniDto(),
                today,
                TcdTestDataCreator.createPerformerDto());

        JsonContent<DocMetaDataDto> result = jackson.write(docMetaDataDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.enterprise.type").isEqualTo("Станция");
        assertThat(result).extractingJsonPathNumberValue("$.enterprise.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.enterprise.name").isEqualTo("Тестовая");
        assertThat(result).extractingJsonPathNumberValue("$.enterprise.number").isNull();
        assertThat(result).extractingJsonPathBooleanValue("$.enterprise.ich").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.signer.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.signer.fullName")
                .isEqualTo("Начальников Начальник Начальникович");
        assertThat(result).extractingJsonPathStringValue("$.signer.jobTitleAbbreviation").isEqualTo("ПЧ");
        assertThat(result).extractingJsonPathStringValue("$.date").isEqualTo(formattedStringToday);
        assertThat(result).extractingJsonPathStringValue("$.performer.firstName").isEqualTo("Тест");
        assertThat(result).extractingJsonPathStringValue("$.performer.lastName").isEqualTo("Тестов");
        assertThat(result).extractingJsonPathStringValue("$.performer.patronymic").isEqualTo("Тестович");
        assertThat(result).extractingJsonPathStringValue("$.performer.jobTitle").isEqualTo("Инженер");
        assertThat(result).extractingJsonPathStringValue("$.performer.telephone").isEqualTo("12345678");
        assertThat(result).extractingJsonPathStringValue("$.performer.workEmail").isEqualTo("test@test");
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        docMetaDataDto = new DocMetaDataDto(null, null, null, null);

        JsonContent<DocMetaDataDto> result = jackson.write(docMetaDataDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.enterprise").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.signer").isNull();
        assertThat(result).extractingJsonPathStringValue("$.date").isNull();
        assertThat(result).extractingJsonPathStringValue("$.performer").isNull();
    }

}