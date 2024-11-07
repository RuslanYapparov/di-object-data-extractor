package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.mini;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JointFailureProtCreatingDataDtoJsonTest {
    @Autowired
    private JacksonTester<JointFailureProtCreatingDataDto> jackson;
    private final JointFailureProtCreatingDataDto jointProtDataDto = new JointFailureProtCreatingDataDto(
            new JointMiniDto[] { new JointMiniDto(777L, "ТЕСТОВЫЙ", "ПРАВАЯ", 12345.67f) },
            "ТЕСТ",
            List.of(DiodeTestDataCreator.createEmployeeDto()));

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<JointFailureProtCreatingDataDto> result = jackson.write(jointProtDataDto);
        JointFailureProtCreatingDataDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isNotEqualTo(jointProtDataDto);
        assertThat(Arrays.equals(parsedDto.jointVariants(), new JointMiniDto[] {
                new JointMiniDto(777L, "ТЕСТОВЫЙ", "ПРАВАЯ", 12345.67f) })).isTrue();
        assertThat(parsedDto.diodeLocationDescription()).isEqualTo("ТЕСТ");
        assertThat(parsedDto.participants()).isEqualTo(List.of(DiodeTestDataCreator.createEmployeeDto()));
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        JsonContent<JointFailureProtCreatingDataDto> result = jackson.write(jointProtDataDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.jointVariants[0].id").isEqualTo(777);
        assertThat(result).extractingJsonPathStringValue("$.jointVariants[0].lineSide").isEqualTo("ПРАВАЯ");
        assertThat(result).extractingJsonPathStringValue("$.diodeLocationDescription").isEqualTo("ТЕСТ");
        assertThat(result).extractingJsonPathStringValue("$.participants[0].surname").isEqualTo("Тестов");
        assertThat(result).extractingJsonPathStringValue("$.participants[0].fullTitle")
                .isEqualTo("Тестирующий сотрудник");
    }

}