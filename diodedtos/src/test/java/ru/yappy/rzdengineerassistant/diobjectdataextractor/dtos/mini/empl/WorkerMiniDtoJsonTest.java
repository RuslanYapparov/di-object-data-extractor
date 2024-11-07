package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.empl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class WorkerMiniDtoJsonTest {
    @Autowired
    private JacksonTester<WorkerMiniDto> jackson;

    private WorkerMiniDto workerMiniDto;

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        workerMiniDto = new WorkerMiniDto(1L, "Тестин Тест Тестович", "м/п", 3);

        JsonContent<WorkerMiniDto> result = jackson.write(workerMiniDto);
        WorkerMiniDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(workerMiniDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        workerMiniDto = new WorkerMiniDto(1L, "Тестин Тест Тестович", "м/п", 3);

        JsonContent<WorkerMiniDto> result = jackson.write(workerMiniDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.fullName")
                .isEqualTo("Тестин Тест Тестович");
        assertThat(result).extractingJsonPathStringValue("$.jobTitleAbbreviation").isEqualTo("м/п");
        assertThat(result).extractingJsonPathNumberValue("$.jobRank").isEqualTo(3);
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        workerMiniDto = new WorkerMiniDto(1L, null, null, null);

        JsonContent<WorkerMiniDto> result = jackson.write(workerMiniDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.fullName").isNull();
        assertThat(result).extractingJsonPathStringValue("$.jobTitleAbbreviation").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.jobRank").isNull();
    }

}