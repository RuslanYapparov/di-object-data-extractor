package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.culprit.CulpritDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.delay.TrainDelayDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util.TcdTestDataCreator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ComplexFailureInfoDtoJsonTest {
    @Autowired
    private JacksonTester<ComplexFailureInfoDto> jackson;
    private final ComplexFailureInfoDto complexFailureInfo = TcdTestDataCreator.createComplexFailureInfoDto(
            new FailureReasoningInfoDto("Тестовое описание случая",
                    "Тестовое пояснение к характеру отказа",
                    "Тестовая причина отказа",
                    "Тестовая подпричина отказа")
    );

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<ComplexFailureInfoDto> result = jackson.write(complexFailureInfo);
        ComplexFailureInfoDto parsedDto = jackson.parse(result.getJson()).getObject();
        CulpritDto[] dtoCulprits = complexFailureInfo.culprits();
        CulpritDto[] parsedCulprits = parsedDto.culprits();
        TrainDelayDto[] dtoDelays = complexFailureInfo.trainDelays().delays();
        TrainDelayDto[] parsedDelays = parsedDto.trainDelays().delays();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto.basis()).isEqualTo(complexFailureInfo.basis());
        assertThat(parsedDto.recovery()).isEqualTo(complexFailureInfo.recovery());
        assertThat(parsedDto.reasoning()).isEqualTo(complexFailureInfo.reasoning());
        assertThat(parsedDto.trainDelays().mainDelay()).isEqualTo(complexFailureInfo.trainDelays().mainDelay());
        assertThat(dtoCulprits.length).isEqualTo(parsedCulprits.length);
        assertThat(dtoDelays.length).isEqualTo(parsedDelays.length);
        Stream.iterate(0, i -> i < parsedCulprits.length, i -> i + 1).forEach(
                i -> {
                    assertThat(Arrays.equals(parsedCulprits[i].punishment(), dtoCulprits[i].punishment())).isTrue();
                    assertThat(Arrays.equals(parsedCulprits[i].decreasedPunishment(), dtoCulprits[i].decreasedPunishment()))
                            .isTrue();
                    assertThat(parsedCulprits[i].employee()).isEqualTo(dtoCulprits[i].employee());
                    assertThat(parsedCulprits[i].violations()).isEqualTo(dtoCulprits[i].violations());
                    assertThat(parsedCulprits[i].decreaseReason()).isEqualTo(dtoCulprits[i].decreaseReason());
                    assertThat(parsedCulprits[i].takenWarningCard()).isEqualTo(dtoCulprits[i].takenWarningCard());
                }
        );
        Stream.iterate(0, i -> i < parsedDelays.length, i -> i + 1)
                .forEach(i -> assertThat(parsedDelays[i]).isEqualTo(dtoDelays[i])
        );
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        LocalDateTime current = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        JsonContent<ComplexFailureInfoDto> result = jackson.write(complexFailureInfo);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.basis.kasantNumber")
                .isEqualTo(1234567);
        assertThat(result).extractingJsonPathNumberValue("$.basis.category").isEqualTo(2);
        assertThat(result).extractingJsonPathStringValue("$.basis.failureDateTime")
                .isEqualTo(current.minusHours(2L).format(formatter));
        assertThat(result).extractingJsonPathStringValue("$.basis.recoveryDateTime")
                .isEqualTo(current.minusHours(1L).format(formatter));
        assertThat(result).extractingJsonPathStringValue("$.basis.character")
                .isEqualTo("EXPLOITATION");
        assertThat(result).extractingJsonPathStringValue("$.basis.characterSubCause")
                .isEqualTo("тестовые недостатки работников дистанции");
        assertThat(result).extractingJsonPathStringValue("$.basis.appearance")
                .isEqualTo("FALSE_OCCUPIED_SECTION");
        assertThat(result).extractingJsonPathStringValue("$.basis.appearanceInfo")
                .isEqualTo("3-5");
        assertThat(result).extractingJsonPathStringValue("$.recovery.notifier")
                .isEqualTo("дежурной по станции Пельмень Майонезовой У.У.");
        assertThat(result).extractingJsonPathStringValue("$.recovery.notifyDateTime")
                .isEqualTo(current.minusMinutes(45).format(formatter));
        assertThat(result).extractingJsonPathStringValue("$.recovery.whoNotified")
                .isEqualTo("начальнику дистанции");
        assertThat(result).extractingJsonPathNumberValue("$.recovery.employeeOnPlace.id")
                .isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.recovery.employeeOnPlace.surname")
                .isEqualTo("Тестов");
        assertThat(result).extractingJsonPathStringValue("$.recovery.employeeOnPlace.fullTitle")
                .isEqualTo("Тестирующий сотрудник");
        assertThat(result).extractingJsonPathStringValue("$.recovery.arrivedAt")
                .isEqualTo(current.minusMinutes(30).format(formatter));
        assertThat(result).extractingJsonPathStringValue("$.recovery.causeFoundAt")
                .isEqualTo(current.minusMinutes(15).format(formatter));
        assertThat(result).extractingJsonPathStringValue("$.recovery.recoveryMethod")
                .isEqualTo("тестового тестирования тестовой неисправности");
        assertThat(result).extractingJsonPathStringValue("$.recovery.investigationParticipants")
                .isEqualTo("начальник участка пути Мясков А.А., начальник участка СЦБ Сыров Б.Б., " +
                        "старший электромонтер Колбаскин В.В.");
        assertThat(result).extractingJsonPathNumberValue("$.recovery.estimatedMinutes")
                .isEqualTo(70);
        assertThat(result).extractingJsonPathStringValue("$.reasoning.description")
                .isEqualTo("Тестовое описание случая");
        assertThat(result).extractingJsonPathStringValue("$.reasoning.characterJustification")
                .isEqualTo("Тестовое пояснение к характеру отказа");
        assertThat(result).extractingJsonPathStringValue("$.reasoning.cause")
                .isEqualTo("Тестовая причина отказа");
        assertThat(result).extractingJsonPathStringValue("$.reasoning.subCause")
                .isEqualTo("Тестовая подпричина отказа");
        assertThat(result).extractingJsonPathStringValue("$.trainDelays.mainDelay.delay.trainType")
                .isEqualTo("FREIGHT");
        assertThat(result).extractingJsonPathStringValue("$.trainDelays.mainDelay.delay.trainNumber")
                .isEqualTo("888");
        assertThat(result).extractingJsonPathNumberValue("$.trainDelays.mainDelay.wagonAmount")
                .isEqualTo(77);
        assertThat(result).extractingJsonPathBooleanValue("$.trainDelays.mainDelay.isTrafficLightPassed")
                .isEqualTo(true);
        assertThat(result).extractingJsonPathStringValue("$.trainDelays.delays[0].trainType")
                .isEqualTo("PASSENGER");
        assertThat(result).extractingJsonPathStringValue("$.trainDelays.delays[13].trainType")
                .isEqualTo("SHUNTING");
        assertThat(result).extractingJsonPathStringValue("$.trainDelays.delays[13].delayStart")
                .isEqualTo(LocalDateTime.of(2024, 1, 1, 12, 0, 0)
                        .format(formatter));
        assertThat(result).extractingJsonPathNumberValue("$.culprits[0].employee.asutrPersonnelNumber")
                .isEqualTo(1234567);
        assertThat(result).extractingJsonPathStringValue("$.culprits[0].employee.firstName")
                .isEqualTo("Тест");
        assertThat(result).extractingJsonPathStringValue("$.culprits[0].employee.edInstitutionName")
                .isEqualTo("Кусайкинская высшая тестировочная академия");
        assertThat(result).extractingJsonPathNumberValue("$.culprits[0].employee.edGraduationYear")
                .isEqualTo(2022);
        assertThat(result).extractingJsonPathStringValue("$.culprits[0].punishment[0].type")
                .isEqualTo("REPROACH");
        assertThat(result).extractingJsonPathNumberValue("$.culprits[0].punishment[0].punishmentValue")
                .isEqualTo(50.0);
        assertThat(result).extractingJsonPathStringValue("$.culprits[0].violations")
                .isEqualTo("нарушения правил технической эксплуатации жд транспорта," +
                        " требований должностной инструкции");
        assertThat(result).extractingJsonPathStringValue("$.culprits[0].decreasedPunishment[0].type")
                .isEqualTo("WARNING");
        assertThat(result).extractingJsonPathStringValue("$.culprits[0].decreaseReason")
                .isEqualTo("тестовая причина");
        assertThat(result).extractingJsonPathStringValue("$.culprits[0].takenWarningCard")
                .isEqualTo("RED");
    }

}