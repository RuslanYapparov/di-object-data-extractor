package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl.EmployeeDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.wayc.WayCharacteristicsDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.empl.ManagerMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto;

import java.time.LocalDate;

/**
 * Утилитарный класс, создающий данные для тестирования работы сервиса DiObjectDataExtractor и всех сервисов,
 * зависящих от него.
 */
public class DiodeTestDataCreator {

    /**
     * Создает тестовый экземпляр DTO железнодорожного направления
     *
     * @return DTO железнодорожного направления
     */
    public static TransportDirectionDto createTransportDirectionDto() {
        return new TransportDirectionDto(777L, "ТЕСТ");
    }

    /**
     * Создает тестовый экземпляр DTO главного пути железнодорожного направления.
     *
     * @return DTO главного пути железнодорожного направления
     */
    public static MainWayDto createMainWayDto() {
        return new MainWayDto(777L, new TransportDirectionDto(7L, "ТЕСТ - ТЕСТ"), 7);
    }

    /**
     * Создает тестовый экземпляр DTO участка главного пути железнодорожного направления.
     *
     * @return DTO участка главного пути железнодорожного направления
     */
    public static MainWaySectionDto createMainWaySectionDto() {
        return new MainWaySectionDto(createTransportDirectionDto(),
                7777,
                777,
                7777,
                777);
    }

    /**
     * Создает тестовый экземпляр DTO конкретного места главного пути железнодорожного направления.
     *
     * @return DTO конкретного места главного пути железнодорожного направления
     */
    public static MainWayPlaceDto createMainWayPlaceDto() {
        return new MainWayPlaceDto(createTransportDirectionDto(), 7777, 777);
    }

    /**
     * Создает тестовый экземпляр DTO с минимальной информацией о станции и ее названии.
     *
     * @return DTO с минимальной информацией о станции
     */
    public static StationNameDto createStationNameDto() {
        return new StationNameDto(777L, "ТЕСТОВАЯ");
    }

    /**
     * Создает тестовый экземпляр DTO с минимальной информацией о станционном пути.
     *
     * @return DTO с минимальной информацией о станционном пути
     */
    public static StationWayMiniDto createStationWayMiniDto() {
        return new StationWayMiniDto(777L, "7 станции Тестовая", createStationNameDto());
    }

    /**
     * Создает тестовый экземпляр DTO с минимальной информацией об участке станционного пути.
     *
     * @return DTO с минимальной информацией об участке станционного пути
     */
    public static StationWaySectionDto createStationWaySectionDto() {
        return new StationWaySectionDto(createStationWayMiniDto(), 7777, 7777);
    }

    /**
     * Создает тестовый экземпляр DTO с минимальной информацией о конкретном месте станционного пути.
     *
     * @return DTO с минимальной информацией о конкретном месте станционного пути
     */
    public static StationWayPlaceDto createStationWayPlaceDto() {
        return new StationWayPlaceDto(createStationWayMiniDto(), 777);
    }

    /**
     * Создает тестовый экземпляр DTO c минимальной информацией о дистанции пути.
     *
     * @return DTO с минимальной информацией о дистанции пути
     */
    public static StructuralEnterpriseMiniDto createWayMaintenanceDistanceMiniDto() {
        return new WayMaintenanceDistanceMiniDto(1L, "ТЕСТОВАЯ", 7, false);
    }

    /**
     * Создает тестовый экземпляр DTO c минимальной информацией о станции.
     *
     * @return DTO с минимальной информацией о станции
     */
    public static StructuralEnterpriseMiniDto createStationMiniDto() {
        return new StationNameDto(1L, "Тестовая");
    }

    /**
     * Создает тестовый экземпляр DTO c минимальной информацией о руководителе подразделения.
     *
     * @return DTO с минимальной информацией о руководителе подразделения
     */
    public static ManagerMiniDto createManagerMiniDto() {
        return new ManagerMiniDto(1L, "Начальников Начальник Начальникович", "ПЧ");
    }

    /**
     * Создает тестовый экземпляр DTO c данными о конкретном месте.
     *
     * @return DTO с данными о конкретном месте
     */
    public static LocationDto createLocationDto() {
        return new LocationDto(WayType.STATION_MAIN_WAY, 1L, "1a", 1, 1, 1, LineSide.LEFT);
    }

    /**
     * Создает тестовый экземпляр DTO c данными о сотруднике.
     *
     * @return DTO с данными о сотруднике
     */
    public static EmployeeDto createEmployeeDto() {
        return new EmployeeDto(1L, 3333L, "Тестов", "Тест", "Тестович",
                "РАБОЧИЙ", "Т", "Тестирующий сотрудник");
    }

    /**
     * Создает тестовый экземпляр DTO c характеристиками пути.
     *
     * @return DTO с характеристиками пути
     */
    public static WayCharacteristicsDto createWayCharacteristicsDto() {
        return new WayCharacteristicsDto(2.5f, true, "ПРАВАЯ", 1000, 15,
                null, 40, 77.77f, 777.777f,
                0.5f, "1Т7", "Г7-II", "Р65", "У",
                "ДА", "БЕССТЫКОВОЙ", "А", "2007",
                LocalDate.of(2024, 1, 1), "ДЕРЕВО", 2007,
                "КОСТЫЛИ", "ЩЕБЕНЬ", 250, LocalDate.of(2024, 1, 1),
                "КРС", LocalDate.of(2024, 1, 1), "ППВ"
        );
    }

    /**
     * Создает тестовый экземпляр DTO с данными стрелочного перевода в зависимости от типа пути.
     *
     * @param wayType тип пути, на котором уложен тестовый стрелочный перевод
     * @return DTO с данными стрелочного перевода
     */
    public static SwitchDto createSwitchDto(WayType wayType) {
        return switch (wayType) {
            case INTERSTATION_TRACK -> new InterstationTrackSwitchDto(777L, "ТЕСТ", "ТЕСТТЕСТТЕСТ", "2750", "Р65",
                    "Ж/Б", "ЩЕБЕНЬ", "1/11", "ЭЦ", "ЛЕВАЯ", 1524,
                    LocalDate.of(2024, 1, 1), 777.777f,
                    777.777f, 77, 7, createMainWayPlaceDto(),
                    "ТЕСТОВАЯ"
            );
            case STATION_WAY -> new StationWaySwitchDto(777L, "ТЕСТ", "ТЕСТТЕСТТЕСТ", "2750", "Р65",
                    "Ж/Б", "ЩЕБЕНЬ", "1/11", "ЭЦ", "ЛЕВАЯ",
                    1524, LocalDate.of(2024, 1, 1), 777.777f,
                    777.777f, 77, createStationWayPlaceDto(), "ПАРК",
                    "43"
            );
            case null, default -> new StationMainWaySwitchDto(777L, "ТЕСТ",  "ТЕСТТЕСТТЕСТ", "2750",
                    "Р65", "Ж/Б", "ЩЕБЕНЬ", "1/11", "ЭЦ",
                    "ЛЕВАЯ", 1524, LocalDate.of(2024, 1, 1),
                    777.777f, 777.777f, 77,
                    "ПАРК", createStationWayMiniDto(), createMainWayPlaceDto(), "ТЕСТ"
            );
        };
    }

    /**
     * Создает тестовый экземпляр DTO с данными рельсовой плети.
     *
     * @param wayType тип пути, на котором уложена тестовая рельсовая плеть
     * @return DTO с данными рельсовой плети
     */
    public static LashDto createLashDto(WayType wayType) {
        return WayType.STATION_WAY.equals(wayType) ?
                new StationWayLashDto(777L, "77ТЕСТ", "ПРАВАЯ", 800.07f, 15,
                        17, "ТЕСТ", LocalDate.of(2024, 1, 1),
                        "НОВАЯ", "ТЕСТ", 100.01f, 200.02f,
                        LocalDate.of(2024, 1, 1), 10,
                        "ВЫПРАВКА", createStationWaySectionDto()) :
                new MainWayLashDto(777L, "77ТЕСТ", "ПРАВАЯ", 800.07f, 15,
                        17, "ТЕСТ", LocalDate.of(2024, 1, 1),
                        "НОВАЯ", "ТЕСТ", 100.01f, 200.02f,
                        LocalDate.of(2024, 1, 1), 10,
                        "ВЫПРАВКА", 7, createMainWaySectionDto()
                );
    }

    /**
     * Создает тестовый экземпляр DTO с данными рельса.
     *
     * @param wayType тип пути, на котором уложен тестовый рельс
     * @return DTO с данными рельса
     */
    public static RailDto createRailDto(WayType wayType) {
        return WayType.STATION_WAY.equals(wayType) ?
                new StationWayRailDto(777L, 25.0f, "ПРАВАЯ", "ЗВЕНЬЕВОЙ", "Р65",
                        "Т-415", "ТЕРМО-ПРОЧНЫЙ", "К", "X-2017",
                        "KV-77777", LocalDate.of(2024, 1, 1), "НОВЫЙ",
                        15, 17, 77.77f, 777.777f,
                        2, 1, LocalDate.of(2024, 1, 1), 10,
                        createLashDto(WayType.STATION_WAY), createSwitchDto(WayType.STATION_WAY),
                        createStationWaySectionDto()) :
                new MainWayRailDto(777L, 25.0f, "ПРАВАЯ", "ЗВЕНЬЕВОЙ", "Р65",
                        "Т-415", "ТЕРМО-ПРОЧНЫЙ", "К", "X-2017",
                        "KV-77777", LocalDate.of(2024, 1, 1), "НОВЫЙ",
                        15, 17, 77.77f, 777.777f,
                        2, 1, LocalDate.of(2024, 1, 1), 10,
                        createLashDto(WayType.INTERSTATION_TRACK), createSwitchDto(WayType.STATION_MAIN_WAY), 7,
                        createMainWaySectionDto()
                );
    }

    /**
     * Создает тестовый экземпляр DTO с данными рельсового стыка в зависимости от типа пути и типа стыка.
     *
     * @param wayType тип пути, на котором обустроен рельсовый стык
     * @param jointType тип стыка
     * @return DTO с данными рельсового стыка
     */
    public static JointDto createJointDto(WayType wayType, JointType jointType) {
        if (WayType.STATION_WAY.equals(wayType)) {
            return switch (jointType) {
                case ISOLATING_SIGNAL -> new StationWaySignalIsolatingJointDto(777L, LineSide.RIGHT, "ACTIVE",
                        "2-хголовые", 4, 7, 1.1f, 2.2f,
                        LocalDate.of(2024, 3, 29), -10,
                        LocalDate.of(2024, 5, 5), LocalDate.of(2024, 5, 5),
                        "гуляш с пюрешкой", 100,
                        LocalDate.of(2024, 5, 5), "Ч1", createStationWayPlaceDto()
                );
                case ISOLATING_SWITCH -> new StationWaySwitchIsolatingJointDto(777L, LineSide.RIGHT, "ACTIVE",
                        "2-хголовые", 4, 7, 1.1f, 2.2f,
                        LocalDate.of(2024, 3, 29), -10,
                        LocalDate.of(2024, 5, 5), LocalDate.of(2024, 5, 5),
                        "гуляш с пюрешкой", 100,
                        LocalDate.of(2024, 5, 5), createSwitchDto(WayType.STATION_WAY),
                        7, createStationWayPlaceDto()
                );
                case null, default -> new StationWayConductingJointDto(777L, LineSide.RIGHT, "ACTIVE",
                        "2-хголовые", 4, 7, 1.1f, 2.2f,
                        LocalDate.of(2024, 3, 29), -10,
                        LocalDate.of(2024, 5, 5), 100,
                        LocalDate.of(2024, 5, 5), "СРСП", null,
                        createStationWayPlaceDto()
                );
            };
        }
        return switch (jointType) {
            case ISOLATING_SIGNAL -> new MainWaySignalIsolatingJointDto(777L, LineSide.RIGHT, "ACTIVE",
                    "2-хголовые", 4, 7, 1.1f, 2.2f,
                    LocalDate.of(2024, 3, 29), -10,
                    LocalDate.of(2024, 5, 5), LocalDate.of(2024, 5, 5),
                    "гуляш с пюрешкой", 100, LocalDate.of(2024, 5, 5),
                    "Ч1", 2, createMainWayPlaceDto()
            );
            case ISOLATING_SWITCH -> new MainWaySwitchIsolatingJointDto(777L, LineSide.RIGHT, "ACTIVE",
                    "2-хголовые", 4, 7, 1.1f, 2.2f,
                    LocalDate.of(2024, 3, 29), -10,
                    LocalDate.of(2024, 5, 5), LocalDate.of(2024, 5, 5),
                    "гуляш с пюрешкой", 100, LocalDate.of(2024, 5, 5),
                    createSwitchDto(WayType.STATION_MAIN_WAY), 7, 2, createMainWayPlaceDto()
            );
            case null, default -> new MainWayConductingJointDto(777L, LineSide.RIGHT, "ACTIVE",
                    "2-хголовые", 4, 7, 1.1f, 2.2f,
                    LocalDate.of(2024, 3, 29), -10,
                    LocalDate.of(2024, 5, 5), 100,
                    LocalDate.of(2024, 5, 5), "СРСП", null,
                    1, createMainWayPlaceDto()
            );
        };
    }

}