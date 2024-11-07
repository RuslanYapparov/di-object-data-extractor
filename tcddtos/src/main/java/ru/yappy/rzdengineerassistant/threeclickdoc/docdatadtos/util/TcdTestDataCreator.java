package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl.EmployeeDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;
import ru.yappy.rzdengineerassistant.employeedataextractor.dtos.EdeTestDataCreator;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.*;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.culprit.*;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.delay.*;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.*;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.*;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Утилитарный класс, создающий данные для тестирования работы сервиса ThreeClickDoc и всех сервисов, зависящих от него.
 */
public class TcdTestDataCreator {

    /**
     * Создает тестовый экземпляр DTO c информацией об исполнителе документа.
     *
     * @return DTO с информацией об исполнителе документа
     */
    public static PerformerDto createPerformerDto() {
        return new PerformerDto("Тестов",
                "Тест",
                "Тестович",
                "Инженер",
                "12345678",
                "test@test");
    }

    /**
     * Создает тестовый экземпляр DTO c метаданными документа.
     *
     * @return DTO с метаданными документа
     */
    public static DocMetaDataDto createDocMetaDataDto() {
        return new DocMetaDataDto(DiodeTestDataCreator.createWayMaintenanceDistanceMiniDto(),
                DiodeTestDataCreator.createManagerMiniDto(),
                LocalDate.now(),
                createPerformerDto());
    }

    /**
     * Перегруженный метод, создающий тестовый экземпляр DTO c метаданными документа для недефолтного структурного
     * предприятия.
     *
     * @param structuralEnterpriseMiniDto DTO с минимальной информацией о структурном предприятии ОАО "РЖД"
     * @return DTO с метаданными документа
     */
    public static DocMetaDataDto createDocMetaDataDto(StructuralEnterpriseMiniDto structuralEnterpriseMiniDto) {
        return new DocMetaDataDto(structuralEnterpriseMiniDto,
                DiodeTestDataCreator.createManagerMiniDto(),
                LocalDate.now(),
                createPerformerDto());
    }

    /**
     * Создает тестовый экземпляр DTO c метаданными протокола.
     *
     * @param protocolType тип протокола
     * @return DTO с метаданными протокола
     */
    public static ProtMetaDataDto createProtMetaDataDto(ProtocolType protocolType) {
        EmployeeDto testEmployeeDto = DiodeTestDataCreator.createEmployeeDto();
        return new ProtMetaDataDto(protocolType,
                "777",
                "1234567890",
                "г. Шмаровск",
                new EmployeeDto[] { testEmployeeDto,
                        new EmployeeDto(2L, 4444L, "Девов", "Дев",
                                "Девович", "РУКОВДИТЕЛЬ", "ПЧ",
                                "Начальник дистанции пути"),
                        new EmployeeDto(3L, 5555L, "Девопсов", "Сисадмин",
                                "Свичевич", "РУКОВДИТЕЛЬ", "НПТО",
                                "Начальник отдела") },
                "начальники участков, техники линейных участков, инженера дистанции пути",
                "(Девов, Тестов, Девов)",
                testEmployeeDto);
    }

    /**
     * Создает тестовый экземпляр DTO c данными о виновном сотруднике.
     *
     * @return DTO с данными о виновном сотруднике
     */
    public static CulpritDto createCulpritDto(PunishmentDto[] punishmentDtos,
                                              PunishmentDto[] decreasedPunishmentDtos,
                                              WarningCardType takedWarningCardType) {
        return new CulpritDto(EdeTestDataCreator.createAsutrEmployeeDto(),
                punishmentDtos,
                "нарушения правил технической эксплуатации жд транспорта, требований должностной инструкции",
                decreasedPunishmentDtos,
                "тестовая причина",
                takedWarningCardType
        );
    }



    /**
     * Создает тестовый экземпляр DTO, содержащий данные о задержках поездов, допущенных в результате какого-либо
     * нарушения перевозочного процесса.
     *
     * @return DTO, содержащий данные о задержках поездов, допущенных в результате какого-либо
     *      * нарушения перевозочного процесса.
     */
    public static TrainDelaysInfoDto createTrainDelaysInfoDto(TrainType type,
                                                              boolean isTrafficLightPassed,
                                                              boolean isNearEntranceTrafficLight) {
        TrainDelayFullDto main = createTrainDelayFullDto(type, isTrafficLightPassed, isNearEntranceTrafficLight);
        return new TrainDelaysInfoDto(main,
                Stream.iterate(0, i -> i <= TrainType.values().length, i -> (i + 1))
                        .map(i -> {
                            if (i == 0) {
                                return main.delay();
                            }
                            return createTrainDelayDto(TrainType.values()[i - 1]);
                        })
                        .sorted(Comparator.comparing(delay -> delay.trainType().getPriority()))
                        .toArray(TrainDelayDto[]::new)
        );
    }

    /**
     * Создает тестовый экземпляр DTO c полными данными о задержке поезда.
     *
     * @param type тип поезда
     * @param isTrafficLightPassed флаг, обозначающий допущен ли проезд запрещающего показания
     * @param isNearEntranceTrafficLight флаг, обозначающий допущена ли стоянка у входного светофора станции
     * @return DTO с полными данными о задержке поезда
     */
    public static TrainDelayFullDto createTrainDelayFullDto(TrainType type,
                                                            boolean isTrafficLightPassed,
                                                            boolean isNearEntranceTrafficLight) {
        return new TrainDelayFullDto(
                new TrainDelayDto(type,
                        "888",
                        LocalDateTime.of(2024, 8, 1, 12, 0, 0),
                        LocalDateTime.of(2024, 8, 1, 12, 15, 0)),
                "светофор сигнальной установки № 7",
                "перегона Пельмень - Кетчунез",
                "Кукурузин А.А.",
                "ТЧЭ-7",
                "ВЛ-80С",
                "1234",
                "ТЧЭ-8",
                1234,
                77,
                308,
                isTrafficLightPassed,
                isNearEntranceTrafficLight);
    }

    /**
     * Создает тестовый экземпляр DTO с минимальными данными о задержке поезда.
     *
     * @param type тип задержанного поезда
     * @return DTO с минимальной информацией о задержке поезда
     */
    public static TrainDelayDto createTrainDelayDto(TrainType type) {
        return new TrainDelayDto(
                type,
                "777",
                LocalDateTime.of(2024, 1, 1, 12, 0, 0),
                LocalDateTime.of(2024, 1, 1, 12, 10, 0)
        );
    }

    /**
     * Создает тестовый экземпляр DTO c полной информацией об отказе.
     *
     * @return DTO с полной информацией об отказе
     */
    public static ComplexFailureInfoDto createComplexFailureInfoDto(FailureReasoningInfoDto reasoning) {
        LocalDateTime current = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
        return new ComplexFailureInfoDto(
                new FailureBasicInfoDto(1234567L,
                        2,
                        current.minusHours(2L),
                        current.minusHours(1L),
                        FailureCharacter.EXPLOITATION,
                        "тестовые недостатки работников дистанции",
                        FailureAppearance.FALSE_OCCUPIED_SECTION,
                        "3-5"),
                new FailureRecoveryInfoDto("дежурной по станции Пельмень Майонезовой У.У.",
                        current.minusMinutes(45),
                        "начальнику дистанции",
                        DiodeTestDataCreator.createEmployeeDto(),
                        current.minusMinutes(30),
                        current.minusMinutes(15),
                        "тестового тестирования тестовой неисправности",
                        "начальник участка пути Мясков А.А., начальник участка СЦБ Сыров Б.Б., " +
                                "старший электромонтер Колбаскин В.В.",
                        70),
                reasoning,
                TcdTestDataCreator.createTrainDelaysInfoDto(TrainType.FREIGHT, true, false),
                new CulpritDto[] { createCulpritDto(new PunishmentDto[] {
                        new PunishmentDto(PunishType.REPROACH, 77.7f) },
                        new PunishmentDto[] { new PunishmentDto(PunishType.WARNING, 77.7f) },
                        WarningCardType.RED)
                }
        );
    }

}