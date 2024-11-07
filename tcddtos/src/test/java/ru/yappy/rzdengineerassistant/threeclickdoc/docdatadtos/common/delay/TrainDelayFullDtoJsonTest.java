package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.delay;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util.TcdTestDataCreator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class TrainDelayFullDtoJsonTest {
    @Autowired
    private JacksonTester<TrainDelayFullDto> jackson;
    private final TrainDelayFullDto delay =
            TcdTestDataCreator.createTrainDelayFullDto(TrainType.PASSENGER, true, true);

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<TrainDelayFullDto> result = jackson.write(delay);
        TrainDelayFullDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).isEqualTo(delay);
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        JsonContent<TrainDelayFullDto> result = jackson.write(delay);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.delay.trainType")
                .isEqualTo(TrainType.PASSENGER.name());
        assertThat(result).extractingJsonPathStringValue("$.delay.trainNumber")
                .isEqualTo("888");
        assertThat(result).extractingJsonPathStringValue("$.delay.delayStart")
                .isEqualTo(formatter.format(LocalDateTime.of(2024, 8, 1, 12, 0, 0)));
        assertThat(result).extractingJsonPathStringValue("$.delay.delayEnd")
                .isEqualTo(formatter.format(LocalDateTime.of(2024, 8, 1, 12, 15, 0)));
        assertThat(result).extractingJsonPathStringValue("$.trafficLight")
                .isEqualTo("светофор сигнальной установки № 7");
        assertThat(result).extractingJsonPathStringValue("$.location")
                .isEqualTo("перегона Пельмень - Кетчунез");
        assertThat(result).extractingJsonPathStringValue("$.machinist")
                .isEqualTo("Кукурузин А.А.");
        assertThat(result).extractingJsonPathStringValue("$.machinistDepot")
                .isEqualTo("ТЧЭ-7");
        assertThat(result).extractingJsonPathStringValue("$.locoModel")
                .isEqualTo("ВЛ-80С");
        assertThat(result).extractingJsonPathStringValue("$.locoNumber")
                .isEqualTo("1234");
        assertThat(result).extractingJsonPathStringValue("$.locoDepot")
                .isEqualTo("ТЧЭ-8");
        assertThat(result).extractingJsonPathNumberValue("$.trainWeight")
                .isEqualTo(1234);
        assertThat(result).extractingJsonPathNumberValue("$.wagonAmount")
                .isEqualTo(77);
        assertThat(result).extractingJsonPathNumberValue("$.axleAmount")
                .isEqualTo(308);
        assertThat(result).extractingJsonPathBooleanValue("$.isTrafficLightPassed")
                .isEqualTo(true);
        assertThat(result).extractingJsonPathBooleanValue("$.isNearEntranceTrafficLight")
                .isEqualTo(true);
    }

}