package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.mini;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.ConductingJointPlacingType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.ProtocolType;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.*;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util.TcdTestDataCreator;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ConductingJointFailureProtMiniDtoJsonTest {
    @Autowired
    private JacksonTester<ConductingJointFailureProtMiniDto> jackson;
    private final ConductingJointFailureProtMiniDto condProtDto = new ConductingJointFailureProtMiniDto(
            new JointMiniDto(777L, "ТЕСТОВЫЙ", "ПРАВАЯ", 12345.67f),
            WayType.STATION_MAIN_WAY,
            ConductingJointPlacingType.JOINT_LINKED_WAY,
            TcdTestDataCreator.createDocMetaDataDto(),
            TcdTestDataCreator.createProtMetaDataDto(ProtocolType.PROT_TECHNICAL_FAILURE),
            TcdTestDataCreator.createComplexFailureInfoDto(new FailureReasoningInfoDto("Тестовое описание случая",
                    "Тестовое пояснение к характеру отказа",
                    "Тестовая причина отказа",
                    "Тестовая подпричина отказа"))
    );

    @Test
    public void writeAndRead_whenGetCorrectJson_thenCanParseToDto() throws IOException {
        JsonContent<ConductingJointFailureProtMiniDto> result = jackson.write(condProtDto);
        ConductingJointFailureProtMiniDto parsedDto = jackson.parse(result.getJson()).getObject();

        assertThat(parsedDto).isNotNull();
        assertThat(parsedDto.getJoint()).isEqualTo(condProtDto.getJoint());
        assertThat(parsedDto.getPlacingType()).isEqualTo(condProtDto.getPlacingType());
        assertThat(parsedDto.getWayType()).isEqualTo(condProtDto.getWayType());
        assertThat(parsedDto.getMetaData()).isEqualTo(condProtDto.getMetaData());
        assertThat(parsedDto.getProtMetaData().executionControlEmployee())
                .isEqualTo(condProtDto.getProtMetaData().executionControlEmployee());
        assertThat(parsedDto.getFailureInfo().basis()).isEqualTo(condProtDto.getFailureInfo().basis());
    }

    @Test
    public void write_whenGetCorrectDto_thenJsonShouldHaveSpecifiedFieldsAndValues() throws IOException {
        LocalDateTime current = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        JsonContent<ConductingJointFailureProtMiniDto> result = jackson.write(condProtDto);

        assertThat(result).isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.pProtFailType").isEqualTo("COND_JOINT_MINI");
        assertThat(result).extractingJsonPathStringValue("$.docType").isEqualTo("PROTOCOL");
        assertThat(result).extractingJsonPathStringValue("$.docSubType").isEqualTo("P_FAIL_CONDUCTING_JOINT");
        assertThat(result).extractingJsonPathStringValue("$.joint.type")
                .isEqualTo("ТЕСТОВЫЙ");
        assertThat(result).extractingJsonPathStringValue("$.wayType").isEqualTo("STATION_MAIN_WAY");
        assertThat(result).extractingJsonPathStringValue("$.placingType").isEqualTo("JOINT_LINKED_WAY");
        assertThat(result).extractingJsonPathStringValue("$.metaData.enterprise.type").isEqualTo("ПЧ");
        assertThat(result).extractingJsonPathStringValue("$.metaData.signer.fullName")
                .isEqualTo("Начальников Начальник Начальникович");
        assertThat(result).extractingJsonPathStringValue("$.metaData.performer.patronymic")
                .isEqualTo("Тестович");
        assertThat(result).extractingJsonPathStringValue("$.metaData.performer.jobTitle")
                .isEqualTo("Инженер");
        assertThat(result).extractingJsonPathStringValue("$.protMetaData.type").isEqualTo("PROT_TECHNICAL_FAILURE");
        assertThat(result).extractingJsonPathStringValue("$.protMetaData.locality").isEqualTo("г. Шмаровск");
        assertThat(result).extractingJsonPathStringValue("$.protMetaData.speakerOrder")
                .isEqualTo("(Девов, Тестов, Девов)");
        assertThat(result).extractingJsonPathStringValue("$.protMetaData.participants[0].surname")
                .isEqualTo("Тестов");
        assertThat(result).extractingJsonPathNumberValue("$.protMetaData.executionControlEmployee.asutrPersonnelNumber")
                .isEqualTo(3333);
        assertThat(result).extractingJsonPathNumberValue("$.failureInfo.basis.kasantNumber")
                .isEqualTo(1234567);
        assertThat(result).extractingJsonPathStringValue("$.failureInfo.basis.failureDateTime")
                .isEqualTo(current.minusHours(2L).format(formatter));
        assertThat(result).extractingJsonPathStringValue("$.failureInfo.basis.appearance")
                .isEqualTo("FALSE_OCCUPIED_SECTION");
        assertThat(result).extractingJsonPathStringValue("$.failureInfo.basis.appearanceInfo")
                .isEqualTo("3-5");
        assertThat(result).extractingJsonPathStringValue("$.failureInfo.recovery.notifier")
                .isEqualTo("дежурной по станции Пельмень Майонезовой У.У.");
        assertThat(result).extractingJsonPathStringValue("$.failureInfo.recovery.notifyDateTime")
                .isEqualTo(current.minusMinutes(45).format(formatter));
        assertThat(result).extractingJsonPathStringValue("$.failureInfo.recovery.employeeOnPlace.fullTitle")
                .isEqualTo("Тестирующий сотрудник");
        assertThat(result).extractingJsonPathStringValue("$.failureInfo.reasoning.description")
                .isEqualTo("Тестовое описание случая");
        assertThat(result).extractingJsonPathStringValue("$.failureInfo.reasoning.characterJustification")
                .isEqualTo("Тестовое пояснение к характеру отказа");
        assertThat(result).extractingJsonPathStringValue("$.failureInfo.trainDelays.mainDelay.delay.trainType")
                .isEqualTo("FREIGHT");
        assertThat(result).extractingJsonPathStringValue("$.failureInfo.trainDelays.delays[0].trainType")
                .isEqualTo("PASSENGER");
        assertThat(result).extractingJsonPathStringValue("$.failureInfo.trainDelays.delays[13].delayStart")
                .isEqualTo(LocalDateTime.of(2024, 1, 1, 12, 0, 0).format(formatter));
        assertThat(result).extractingJsonPathNumberValue("$.failureInfo.culprits[0].employee.asutrPersonnelNumber")
                .isEqualTo(1234567);
    }

}