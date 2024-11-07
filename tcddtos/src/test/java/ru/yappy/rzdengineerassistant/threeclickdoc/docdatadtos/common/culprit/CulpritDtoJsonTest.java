package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.culprit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util.TcdTestDataCreator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class CulpritDtoJsonTest {
    @Autowired
    private JacksonTester<CulpritDto[]> jackson;
    private final CulpritDto[] culprits = new CulpritDto[] {
            TcdTestDataCreator.createCulpritDto(
                    new PunishmentDto[] {
                            new PunishmentDto(PunishType.AWARD_REDUCTION, 77.7f) },
                    new PunishmentDto[] {
                            new PunishmentDto(PunishType.WARNING, 77.7f) },
                    WarningCardType.YELLOW
            ),
            TcdTestDataCreator.createCulpritDto(
                    new PunishmentDto[] {
                            new PunishmentDto(PunishType.AWARD_REDUCTION, 77.7f),
                            new PunishmentDto(PunishType.REPRIMAND, 77.7f) },
                    new PunishmentDto[] { new PunishmentDto(PunishType.WARNING, 77.7f) },
                    WarningCardType.YELLOW
            ),
    };

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<CulpritDto[]> result = jackson.write(culprits);
        CulpritDto[] parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto).hasSize(2);
        assertThat(Arrays.equals(culprits, parsedDto)).isFalse();
        Stream.iterate(0, i -> i < parsedDto.length, i -> i + 1).forEach(
                i -> {
                    assertThat(Arrays.equals(parsedDto[i].punishment(), culprits[i].punishment())).isTrue();
                    assertThat(Arrays.equals(parsedDto[i].decreasedPunishment(), culprits[i].decreasedPunishment()))
                            .isTrue();
                    assertThat(parsedDto[i].employee()).isEqualTo(culprits[i].employee());
                    assertThat(parsedDto[i].violations()).isEqualTo(culprits[i].violations());
                    assertThat(parsedDto[i].decreaseReason()).isEqualTo(culprits[i].decreaseReason());
                    assertThat(parsedDto[i].takenWarningCard()).isEqualTo(culprits[i].takenWarningCard());
                }
        );
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        JsonContent<CulpritDto[]> result = jackson.write(culprits);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$[0].employee.asutrPersonnelNumber")
                .isEqualTo(1234567);
        assertThat(result).extractingJsonPathStringValue("$[1].employee.lastName")
                .isEqualTo("Тестов");
        assertThat(result).extractingJsonPathStringValue("$[0].employee.firstName")
                .isEqualTo("Тест");
        assertThat(result).extractingJsonPathStringValue("$[1].employee.patronymic")
                .isEqualTo("Тестович");
        assertThat(result).extractingJsonPathStringValue("$[0].employee.employeeType")
                .isEqualTo("РАБОЧИЙ");
        assertThat(result).extractingJsonPathStringValue("$[1].employee.jobTitleAbbreviation")
                .isEqualTo("Т");
        assertThat(result).extractingJsonPathStringValue("$[0].employee.fullJobTitle")
                .isEqualTo("Тестировщик путей сообщения");
        assertThat(result).extractingJsonPathStringValue("$[1].employee.employmentDate")
                .isEqualTo(formatter.format(LocalDate.of(2022, 1, 1)));
        assertThat(result).extractingJsonPathNumberValue("$[0].employee.employmentLength")
                .isEqualTo(2.8);
        assertThat(result).extractingJsonPathStringValue("$[1].employee.atPositionSince")
                .isEqualTo(formatter.format(LocalDate.of(2023, 1, 1)));
        assertThat(result).extractingJsonPathNumberValue("$[0].employee.atPositionJobLength")
                .isEqualTo(1.8);
        assertThat(result).extractingJsonPathStringValue("$[1].employee.edType")
                .isEqualTo("HIGHER_EDUCATION");
        assertThat(result).extractingJsonPathStringValue("$[0].employee.edInstitutionName")
                .isEqualTo("Кусайкинская высшая тестировочная академия");
        assertThat(result).extractingJsonPathStringValue("$[1].employee.faculty")
                .isEqualTo("Факультет прикладного тестирования");
        assertThat(result).extractingJsonPathNumberValue("$[0].employee.edGraduationYear")
                .isEqualTo(2022);
        assertThat(result).extractingJsonPathStringValue("$[0].punishment[0].type")
                .isEqualTo(PunishType.AWARD_REDUCTION.name());
        assertThat(result).extractingJsonPathNumberValue("$[0].punishment[0].punishmentValue")
                .isEqualTo(77.7);
        assertThat(result).extractingJsonPathStringValue("$[1].punishment[0].type")
                .isEqualTo(PunishType.AWARD_REDUCTION.name());
        assertThat(result).extractingJsonPathNumberValue("$[1].punishment[0].punishmentValue")
                .isEqualTo(77.7);
        assertThat(result).extractingJsonPathStringValue("$[1].punishment[1].type")
                .isEqualTo(PunishType.REPRIMAND.name());
        assertThat(result).extractingJsonPathNumberValue("$[1].punishment[1].punishmentValue")
                .isEqualTo(100.0);
        assertThat(result).extractingJsonPathStringValue("$[0].violations")
                .isEqualTo("нарушения правил технической эксплуатации жд транспорта, " +
                        "требований должностной инструкции");
        assertThat(result).extractingJsonPathStringValue("$[0].decreasedPunishment[0].type")
                .isEqualTo(PunishType.WARNING.name());
        assertThat(result).extractingJsonPathStringValue("$[1].decreasedPunishment[1].punishmentValue")
                .isEqualTo(null);
        assertThat(result).extractingJsonPathStringValue("$[0].decreaseReason")
                .isEqualTo("тестовая причина");
        assertThat(result).extractingJsonPathStringValue("$[1].takenWarningCard")
                .isEqualTo(WarningCardType.YELLOW.name());
    }

}