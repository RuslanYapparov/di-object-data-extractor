package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JointMiniDtoJsonTest {
    @Autowired
    private JacksonTester<JointMiniDto> jackson;
    private JointMiniDto jointDto;

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        jointDto = new JointMiniDto(12345L, "ТОКОПРОВОДЯЩИЙ", "ЛЕВАЯ", 1234.567f);

        JsonContent<JointMiniDto> result = jackson.write(jointDto);
        JointMiniDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(jointDto).isEqualTo(parsedDto);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        jointDto = new JointMiniDto(12345L, "ТОКОПРОВОДЯЩИЙ", "ЛЕВАЯ", 1234.567f);

        JsonContent<JointMiniDto> result = jackson.write(jointDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(12345);
        assertThat(result).extractingJsonPathStringValue("$.type").isEqualTo("ТОКОПРОВОДЯЩИЙ");
        assertThat(result).extractingJsonPathStringValue("$.lineSide").isEqualTo("ЛЕВАЯ");
        assertThat(result).extractingJsonPathNumberValue("$.coordinate").isEqualTo(1234.567);
    }

    @Test
    public void write_whenGetDtoWithNullFieldValues_thenJsonShouldHaveSpecifiedFieldAndNullValue() throws IOException {
        jointDto = new JointMiniDto(null, null, null, null);

        JsonContent<JointMiniDto> result = jackson.write(jointDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.id").isNull();
        assertThat(result).extractingJsonPathStringValue("$.railKind").isNull();
        assertThat(result).extractingJsonPathStringValue("$.lineSide").isNull();
        assertThat(result).extractingJsonPathNumberValue("$.coordinate").isNull();
    }

}