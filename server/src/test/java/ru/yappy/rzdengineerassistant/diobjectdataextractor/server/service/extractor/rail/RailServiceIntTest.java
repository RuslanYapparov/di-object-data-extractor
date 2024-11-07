package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.RailMiniDto;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RailServiceIntTest {
    private final RailService railService;

    @Autowired
    public RailServiceIntTest(RailService railService) {
        this.railService = railService;
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_MAIN_WAY", "INTERSTATION_TRACK" })
    public void getFullRailInfoByIdAndWayType_whenGetId1AndMainWayType_thenReturnFullRailDtoWithNullLashOrSwitchDto(
            WayType wayType) {
        // Получение и контроль параметров рельса главного пути на перегоне с типом УРАВНИТЕЛЬНАЯ РУБКА
        RailDto rail = railService.getFullRailInfoByIdAndWayType(1L, wayType);

        assertThat(rail).isNotNull();
        assertThat(rail.getId()).isEqualTo(1L);
        assertThat(rail.getRailKind()).isEqualTo("УРАВНИТЕЛЬНАЯ РУБКА");
        assertThat(rail.getLineSide()).isEqualTo("ЛЕВАЯ");
        assertThat(rail.getFactory()).isEqualTo("К");
        assertThat(rail.getFuseNumber()).isEqualTo("ЭVБ-67479");
        assertThat(rail.getInstallationDate()).isEqualTo(LocalDate.of(2023, 9, 17));
        assertThat(rail.getPassedTonnageAfterInstall()).isEqualTo(258.7F);
        assertThat(rail.getLastMeasureDate()).isEqualTo(LocalDate.of(2024, 3, 29));
        assertThat(rail.getRailLash()).isNull();
        assertThat(rail.getRailSwitch()).isNull();
        assertThat(rail).isInstanceOf(MainWayRailDto.class);

        MainWayRailDto mainWayRail = (MainWayRailDto) rail;

        assertThat(mainWayRail.getMainWayNumber()).isEqualTo(1);

        MainWaySectionDto mainWaySection = mainWayRail.getMainWaySection();

        assertThat(mainWaySection).isNotNull();
        assertThat(mainWaySection.transportDirection().id()).isEqualTo(1L);
        assertThat(mainWaySection.transportDirection().name()).isEqualTo("ТАМ - СЯМСК");
        assertThat(mainWaySection.startKm()).isEqualTo(1234);
        assertThat(mainWaySection.startMeter()).isEqualTo(0);
        assertThat(mainWaySection.endKm()).isEqualTo(1234);
        assertThat(mainWaySection.endMeter()).isEqualTo(12);
    }

    @Test
    public void getFullRailInfoByIdAndWayType_whenGetId1AndStationWayType_thenThrowException() {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> railService.getFullRailInfoByIdAndWayType(1L, WayType.STATION_WAY));

        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект 'рельс " +
                "станционного пути' с ид=1");
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_MAIN_WAY", "INTERSTATION_TRACK" })
    // Получение рельса главного пути на станции с типом ПЛЕТЬ, расположенного у переднего торца плети
    public void getFullRailInfoByIdAndWayType_whenGetId5422AndCorrectWayType_thenReturnFullRailDtoWithLashInfo(
            WayType wayType) {
        RailDto rail = railService.getFullRailInfoByIdAndWayType(5422L, wayType);

        assertThat(rail).isNotNull();
        assertThat(rail.getId()).isEqualTo(5422L);
        assertThat(rail.getRailKind()).isEqualTo("ПЛЕТЬ");
        assertThat(rail.getLineSide()).isEqualTo("ПРАВАЯ");
        assertThat(rail.getFactory()).isEqualTo("К");
        assertThat(rail.getRollingDate()).isEqualTo("IV-2015");
        assertThat(rail.getFuseNumber()).isEqualTo("ЭVБ-19470");
        assertThat(rail.getInstallationDate()).isEqualTo(LocalDate.of(2016, 10, 17));
        assertThat(rail.getPassedTonnageAfterInstall()).isEqualTo(380.2F);
        assertThat(rail.getLastMeasureDate()).isEqualTo(LocalDate.of(2024, 3, 30));
        assertThat(rail.getRailLash()).isNotNull();
        assertThat(rail.getRailSwitch()).isNull();
        assertThat(rail).isInstanceOf(MainWayRailDto.class);

        MainWayRailDto mainWayRail = (MainWayRailDto) rail;

        assertThat(mainWayRail.getMainWayNumber()).isEqualTo(2);

        MainWaySectionDto railMainWaySection = mainWayRail.getMainWaySection();

        assertThat(railMainWaySection).isNotNull();
        assertThat(railMainWaySection.transportDirection().id()).isEqualTo(1L);
        assertThat(railMainWaySection.transportDirection().name()).isEqualTo("ТАМ - СЯМСК");
        assertThat(railMainWaySection.startKm()).isEqualTo(1257);
        assertThat(railMainWaySection.startMeter()).isEqualTo(558);
        assertThat(railMainWaySection.endKm()).isEqualTo(1257);
        assertThat(railMainWaySection.endMeter()).isEqualTo(583);

        LashDto lash = rail.getRailLash();

        assertThat(lash).isNotNull();
        assertThat(lash.getId()).isEqualTo(186L);
        assertThat(lash.getName()).isEqualTo("1ПБ");
        assertThat(lash.getLastTemperatureTensionDischargingDate()).isEqualTo(LocalDate.of(2023, 9, 23));
        assertThat(lash.getInstallationType()).isEqualTo("НОВАЯ УКЛАДКА");

        assertThat(lash).isInstanceOf(MainWayLashDto.class);

        MainWayLashDto mainWayLash = (MainWayLashDto) lash;

        assertThat(mainWayLash.getMainWayNumber()).isEqualTo(2);
        assertThat(mainWayLash.getMainWaySection()).isNotNull();

        MainWaySectionDto lashMainWaySection = mainWayLash.getMainWaySection();

        assertThat(lashMainWaySection.transportDirection().id()).isEqualTo(1L);
        assertThat(lashMainWaySection.transportDirection().name()).isEqualTo("ТАМ - СЯМСК");
        assertThat(lashMainWaySection.startKm()).isEqualTo(1257);
        assertThat(lashMainWaySection.startMeter()).isEqualTo(558);
        assertThat(lashMainWaySection.endKm()).isEqualTo(1258);
        assertThat(lashMainWaySection.endMeter()).isEqualTo(107);
    }

    @Test
    public void getFullRailInfoByIdAndWayType_whenGetId5422AndStationWayType_thenThrowException() {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> railService.getFullRailInfoByIdAndWayType(5422L, WayType.STATION_WAY));

        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект 'рельс " +
                "станционного пути' с ид=5422");
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_MAIN_WAY", "INTERSTATION_TRACK" })
    // Получение и контроль параметров рельса с типом ПЛЕТЬ на перегоне, расположенного у заднего торца плети
    public void getFullRailInfoByIdAndWayType_whenGetId7920AndCorrectWayType_thenReturnFullRailDtoWithLashInfo(
            WayType wayType) {
        RailDto rail = railService.getFullRailInfoByIdAndWayType(7920L, wayType);

        assertThat(rail).isNotNull();
        assertThat(rail.getId()).isEqualTo(7920L);
        assertThat(rail.getRailKind()).isEqualTo("ПЛЕТЬ");
        assertThat(rail.getLineSide()).isEqualTo("ПРАВАЯ");
        assertThat(rail.getFactory()).isEqualTo("Т");
        assertThat(rail.getRollingDate()).isEqualTo("X-2005");
        assertThat(rail.getFuseNumber()).isEqualTo("КV-21860");
        assertThat(rail.getInstallationDate()).isEqualTo(LocalDate.of(2013, 7, 22));
        assertThat(rail.getPassedTonnageAfterInstall()).isEqualTo(2.9F);
        assertThat(rail.getLastMeasureDate()).isEqualTo(LocalDate.of(2024, 3, 30));
        assertThat(rail.getRailLash()).isNotNull();
        assertThat(rail.getRailSwitch()).isNull();
        assertThat(rail).isInstanceOf(MainWayRailDto.class);

        MainWayRailDto mainWayRail = (MainWayRailDto) rail;

        assertThat(mainWayRail.getMainWayNumber()).isEqualTo(1);

        MainWaySectionDto railMainWaySection = mainWayRail.getMainWaySection();

        assertThat(railMainWaySection).isNotNull();
        assertThat(railMainWaySection.transportDirection().id()).isEqualTo(3L);
        assertThat(railMainWaySection.transportDirection().name()).isEqualTo("ГДЕ-ЛИБО - ЗДЕСЯ");
        assertThat(railMainWaySection.startKm()).isEqualTo(12);
        assertThat(railMainWaySection.startMeter()).isEqualTo(537);
        assertThat(railMainWaySection.endKm()).isEqualTo(12);
        assertThat(railMainWaySection.endMeter()).isEqualTo(562);

        LashDto lash = rail.getRailLash();

        assertThat(lash).isNotNull();
        assertThat(lash.getId()).isEqualTo(270L);
        assertThat(lash.getName()).isEqualTo("37П");
        assertThat(lash.getLastTemperatureTensionDischargingDate()).isEqualTo(LocalDate.of(2020, 10, 14));
        assertThat(lash.getLength()).isEqualTo(800.09F);
        assertThat(lash.getInstallationType()).isEqualTo("УКЛАДКА СГ");

        assertThat(lash).isInstanceOf(MainWayLashDto.class);

        MainWayLashDto mainWayLash = (MainWayLashDto) lash;

        assertThat(mainWayLash.getMainWayNumber()).isEqualTo(1);
        assertThat(mainWayLash.getMainWaySection()).isNotNull();

        MainWaySectionDto lashMainWaySection = mainWayLash.getMainWaySection();

        assertThat(lashMainWaySection.transportDirection().id()).isEqualTo(3L);
        assertThat(lashMainWaySection.transportDirection().name()).isEqualTo("ГДЕ-ЛИБО - ЗДЕСЯ");
        assertThat(lashMainWaySection.startKm()).isEqualTo(11);
        assertThat(lashMainWaySection.startMeter()).isEqualTo(762);
        assertThat(lashMainWaySection.endKm()).isEqualTo(12);
        assertThat(lashMainWaySection.endMeter()).isEqualTo(562);
    }

    @Test
    public void getFullRailInfoByIdAndWayType_whenGetId7920AndStationWayType_thenThrowException() {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> railService.getFullRailInfoByIdAndWayType(7920L, WayType.STATION_WAY));

        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект 'рельс " +
                "станционного пути' с ид=7920");
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_MAIN_WAY", "INTERSTATION_TRACK" })
    public void getFullRailInfoByIdAndWayType_whenGetId8674AndMainWayType_thenReturnFullRailDtoWithNullLashOrSwitchDto(
            WayType wayType) {
        // Получение и контроль параметров рельса главного пути станции с типом ЗВЕНЬЕВОЙ ПУТЬ перед стрелочным переводом
        RailDto rail = railService.getFullRailInfoByIdAndWayType(8674L, wayType);

        assertThat(rail).isNotNull();
        assertThat(rail.getId()).isEqualTo(8674L);
        assertThat(rail.getRailKind()).isEqualTo("ЗВЕНЬЕВОЙ ПУТЬ");
        assertThat(rail.getLineSide()).isEqualTo("ПРАВАЯ");
        assertThat(rail.getLength()).isEqualTo(19.23F);
        assertThat(rail.getFactory()).isEqualTo("Т");
        assertThat(rail.getFuseNumber()).isEqualTo("КV-73444");
        assertThat(rail.getInstallationDate()).isEqualTo(LocalDate.of(1985, 9, 10));
        assertThat(rail.getPassedTonnageAfterInstall()).isEqualTo(11.9F);
        assertThat(rail.getLastMeasureDate()).isEqualTo(LocalDate.of(2024, 3, 30));
        assertThat(rail.getRailLash()).isNull();
        assertThat(rail.getRailSwitch()).isNull();
        assertThat(rail).isInstanceOf(MainWayRailDto.class);

        MainWayRailDto mainWayRail = (MainWayRailDto) rail;

        assertThat(mainWayRail.getMainWayNumber()).isEqualTo(1);

        MainWaySectionDto mainWaySection = mainWayRail.getMainWaySection();

        assertThat(mainWaySection).isNotNull();
        assertThat(mainWaySection.transportDirection().id()).isEqualTo(2L);
        assertThat(mainWaySection.transportDirection().name()).isEqualTo("КОЕ-ГДЕНЬ - КОЕ-ГДЕНЬ ГРУЗОВАЯ");
        assertThat(mainWaySection.startKm()).isEqualTo(6);
        assertThat(mainWaySection.startMeter()).isEqualTo(205);
        assertThat(mainWaySection.endKm()).isEqualTo(6);
        assertThat(mainWaySection.endMeter()).isEqualTo(224);
    }

    @Test
    public void getFullRailInfoByIdAndWayType_whenGetId8674AndStationWayType_thenThrowException() {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> railService.getFullRailInfoByIdAndWayType(8674L, WayType.STATION_WAY));

        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект 'рельс " +
                "станционного пути' с ид=8674");
    }

    @Test
    // Получение и контроль параметров рельса главного пути станции с типом СТРЕЛОЧНЫЙ ПЕРЕВОД
    public void getFullRailInfoByIdAndWayType_whenGetId3405AndCorrectWayType_thenReturnFullRailDtoWithSwitchInfo() {
        RailDto rail = railService.getFullRailInfoByIdAndWayType(3405L, WayType.STATION_MAIN_WAY);

        assertThat(rail).isNotNull();
        assertThat(rail.getId()).isEqualTo(3405L);
        assertThat(rail.getRailKind()).isEqualTo("СТРЕЛОЧНЫЙ ПЕРЕВОД");
        assertThat(rail.getLineSide()).isEqualTo("ЛЕВАЯ");
        assertThat(rail.getFactory()).isEqualTo("М");
        assertThat(rail.getRollingDate()).isEqualTo("XI-2013");
        assertThat(rail.getFuseNumber()).isEqualTo("ОVР-91580");
        assertThat(rail.getInstallationDate()).isEqualTo(LocalDate.of(2015, 3, 27));
        assertThat(rail.getPassedTonnageAfterInstall()).isEqualTo(817.5F);
        assertThat(rail.getLastMeasureDate()).isEqualTo(LocalDate.of(2024, 3, 29));
        assertThat(rail.getRailLash()).isNull();
        assertThat(rail.getRailSwitch()).isNotNull();
        assertThat(rail.getLength()).isEqualTo(34.86F);
        assertThat(rail).isInstanceOf(MainWayRailDto.class);

        MainWayRailDto mainWayRail = (MainWayRailDto) rail;

        assertThat(mainWayRail.getMainWayNumber()).isEqualTo(1);

        MainWaySectionDto railMainWaySection = mainWayRail.getMainWaySection();

        assertThat(railMainWaySection).isNotNull();
        assertThat(railMainWaySection.transportDirection().id()).isEqualTo(1L);
        assertThat(railMainWaySection.transportDirection().name()).isEqualTo("ТАМ - СЯМСК");
        assertThat(railMainWaySection.startKm()).isEqualTo(1274);
        assertThat(railMainWaySection.startMeter()).isEqualTo(621);
        assertThat(railMainWaySection.endKm()).isEqualTo(1274);
        assertThat(railMainWaySection.endMeter()).isEqualTo(656);

        SwitchDto railSwitch = rail.getRailSwitch();

        assertThat(railSwitch.getId()).isEqualTo(26L);
        assertThat(railSwitch.getName()).isEqualTo("7");
        assertThat(railSwitch.getProject()).isEqualTo("2750");
        assertThat(railSwitch.getFullName()).isNull();
        assertThat(railSwitch.getInstallationDate()).isEqualTo(LocalDate.of(2015, 3, 1));
        assertThat(railSwitch.getPassedTonnageAfterInstall()).isEqualTo(817.5F);
        assertThat(railSwitch).isInstanceOf(StationMainWaySwitchDto.class);

        StationMainWaySwitchDto stationMainWaySwitch = (StationMainWaySwitchDto) railSwitch;

        assertThat(stationMainWaySwitch.getStationParkName()).isNull();
        assertThat(stationMainWaySwitch.getStationMainWay().station().getName()).isEqualTo("ГДЕ-НИБУДЬСК");
        assertThat(stationMainWaySwitch.getStationMainWay().number()).isEqualTo("1");
        assertThat(stationMainWaySwitch.getWayNameSwitchGoesOn()).isEqualTo("2");
        assertThat(stationMainWaySwitch.getFrameRailJointPlace()).isNotNull();

        MainWayPlaceDto frameRailJointPlace = stationMainWaySwitch.getFrameRailJointPlace();

        assertThat(frameRailJointPlace.transportDirection().name()).isEqualTo("ТАМ - СЯМСК");
        assertThat(frameRailJointPlace.km()).isEqualTo(1274);
        assertThat(frameRailJointPlace.meterOnKm()).isEqualTo(621);
    }

    @Test
    public void getFullRailInfoByIdAndWayType_whenGetId3405AndStationWayType_thenThrowException() {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> railService.getFullRailInfoByIdAndWayType(3405L, WayType.STATION_WAY));

        assertThat(exception.getMessage()).startsWith("Не удалось найти в базе данных объект 'рельс станционного пути");
        assertThat(exception.getMessage()).endsWith("' с ид=3405");
    }

    @Test
    public void getFullRailInfoByIdAndWayType_whenGetId3405AndInterstationTrackType_thenReturnRailDtoWithNullSwitchDto() {
        RailDto rail = railService.getFullRailInfoByIdAndWayType(3405L, WayType.INTERSTATION_TRACK);

        assertThat(rail).isNotNull();
        assertThat(rail.getRailKind()).isEqualTo("СТРЕЛОЧНЫЙ ПЕРЕВОД");
        assertThat(rail.getRailSwitch()).isNull();
        assertThat(rail.getRailLash()).isNull();
    }

    @Test
    // Получение и контроль параметров рельса на перегоне с типом СТРЕЛОЧНЫЙ ПЕРЕВОД
    public void getFullRailInfoByIdAndWayType_whenGetId5858AndCorrectWayType_thenReturnFullRailDtoWithSwitchInfo() {
        RailDto rail = railService.getFullRailInfoByIdAndWayType(5858L, WayType.INTERSTATION_TRACK);

        assertThat(rail).isNotNull();
        assertThat(rail.getId()).isEqualTo(5858L);
        assertThat(rail.getRailKind()).isEqualTo("СТРЕЛОЧНЫЙ ПЕРЕВОД");
        assertThat(rail.getLineSide()).isEqualTo("ПРАВАЯ");
        assertThat(rail.getFactory()).isEqualTo("М");
        assertThat(rail.getRollingDate()).isEqualTo("IX-2015");
        assertThat(rail.getFuseNumber()).isEqualTo("ОVР-33205");
        assertThat(rail.getInstallationDate()).isEqualTo(LocalDate.of(2016, 10, 17));
        assertThat(rail.getPassedTonnageAfterInstall()).isEqualTo(380.2F);
        assertThat(rail.getLastMeasureDate()).isEqualTo(LocalDate.of(2024, 3, 30));
        assertThat(rail.getRailLash()).isNull();
        assertThat(rail.getRailSwitch()).isNotNull();
        assertThat(rail.getLength()).isEqualTo(34.86F);
        assertThat(rail).isInstanceOf(MainWayRailDto.class);

        MainWayRailDto mainWayRail = (MainWayRailDto) rail;

        assertThat(mainWayRail.getMainWayNumber()).isEqualTo(2);

        MainWaySectionDto railMainWaySection = mainWayRail.getMainWaySection();

        assertThat(railMainWaySection).isNotNull();
        assertThat(railMainWaySection.transportDirection().id()).isEqualTo(1L);
        assertThat(railMainWaySection.transportDirection().name()).isEqualTo("ТАМ - СЯМСК");
        assertThat(railMainWaySection.startKm()).isEqualTo(1262);
        assertThat(railMainWaySection.startMeter()).isEqualTo(737);
        assertThat(railMainWaySection.endKm()).isEqualTo(1262);
        assertThat(railMainWaySection.endMeter()).isEqualTo(772);

        SwitchDto railSwitch = rail.getRailSwitch();

        assertThat(railSwitch.getId()).isEqualTo(41L);
        assertThat(railSwitch.getName()).isEqualTo("8");
        assertThat(railSwitch.getProject()).isEqualTo("2750");
        assertThat(railSwitch.getFullName()).isNull();
        assertThat(railSwitch.getInstallationDate()).isEqualTo(LocalDate.of(2016, 10, 1));
        assertThat(railSwitch.getPassedTonnageAfterInstall()).isEqualTo(380.2f);
        assertThat(railSwitch).isInstanceOf(InterstationTrackSwitchDto.class);

        InterstationTrackSwitchDto interstationTrackSwitchDto = (InterstationTrackSwitchDto) railSwitch;

        assertThat(interstationTrackSwitchDto.getMainWayNumber()).isEqualTo(2);
        assertThat(interstationTrackSwitchDto.getControlStation()).isEqualTo("ГДЕ-ЛИБО");
        assertThat(interstationTrackSwitchDto.getFrameRailJointPlace()).isNotNull();

        MainWayPlaceDto frameRailJointPlace = interstationTrackSwitchDto.getFrameRailJointPlace();

        assertThat(frameRailJointPlace.transportDirection().name()).isEqualTo("ТАМ - СЯМСК");
        assertThat(frameRailJointPlace.km()).isEqualTo(1262);
        assertThat(frameRailJointPlace.meterOnKm()).isEqualTo(737);
    }

    @Test
    public void getFullRailInfoByIdAndWayType_whenGetId5858AndStationWayType_thenThrowException() {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> railService.getFullRailInfoByIdAndWayType(5858L, WayType.STATION_WAY));

        assertThat(exception.getMessage()).startsWith("Не удалось найти в базе данных объект 'рельс ");
        assertThat(exception.getMessage()).endsWith("' с ид=5858");
    }

    @Test
    public void getFullRailInfoByIdAndWayType_whenGetId5858AndStationMainWayType_thenReturnRailDtoWithNullSwitchDto() {
        RailDto rail = railService.getFullRailInfoByIdAndWayType(5858L, WayType.STATION_MAIN_WAY);

        assertThat(rail).isNotNull();
        assertThat(rail.getRailKind()).isEqualTo("СТРЕЛОЧНЫЙ ПЕРЕВОД");
        assertThat(rail.getRailSwitch()).isNull();
        assertThat(rail.getRailLash()).isNull();
    }

    @Test
    // Получение и контроль параметров рельса станционного пути с типом УРАВНИТЕЛЬНАЯ РУБКА
    public void getFullRailInfoByIdAndWayType_whenGetId8733AndCorrectWayType_thenReturnFullRailDtoWithNoAdditionalInfo() {
        RailDto rail = railService.getFullRailInfoByIdAndWayType(8733L, WayType.STATION_WAY);

        assertThat(rail).isNotNull();
        assertThat(rail.getId()).isEqualTo(8733L);
        assertThat(rail.getRailKind()).isEqualTo("УРАВНИТЕЛЬНАЯ РУБКА");
        assertThat(rail.getLineSide()).isEqualTo("ЛЕВАЯ");
        assertThat(rail.getFactory()).isEqualTo("К");
        assertThat(rail.getRollingDate()).isEqualTo("V-2010");
        assertThat(rail.getFuseNumber()).isEqualTo("КV-28571");
        assertThat(rail.getInstallationDate()).isEqualTo(LocalDate.of(2023, 9, 17));
        assertThat(rail.getInstallationType()).isEqualTo("СЕЗОННАЯ СМЕНА");
        assertThat(rail.getPassedTonnageAfterInstall()).isEqualTo(3.4F);
        assertThat(rail.getLastMeasureDate()).isEqualTo(LocalDate.of(2024, 3, 29));
        assertThat(rail.getRailLash()).isNull();
        assertThat(rail.getRailSwitch()).isNull();
        assertThat(rail.getLength()).isEqualTo(12.51F);
        assertThat(rail).isInstanceOf(StationWayRailDto.class);

        StationWayRailDto stationWayRailDto = (StationWayRailDto) rail;
        StationWaySectionDto stationWaySection = stationWayRailDto.getStationWaySection();

        assertThat(stationWaySection).isNotNull();
        assertThat(stationWaySection.stationWay().station().getName()).isEqualTo("КОЕ-ГДЕНЬ");
        assertThat(stationWaySection.stationWay().number()).isEqualTo("3");
        assertThat(stationWaySection.startMeter()).isEqualTo(550);
        assertThat(stationWaySection.endMeter()).isEqualTo(562);
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "INTERSTATION_TRACK", "STATION_MAIN_WAY" })
    public void getFullRailInfoByIdAndWayType_whenGetId8733AndIncorrectWayType_thenThrowException(WayType wayType) {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> railService.getFullRailInfoByIdAndWayType(8733L, wayType));

        assertThat(exception.getMessage()).startsWith("Не удалось найти в базе данных объект 'рельс ");
        assertThat(exception.getMessage()).endsWith("' с ид=8733");
    }

    @Test
    // Получение и контроль параметров рельса станционного пути с типом ЗВЕНЬЕВОЙ ПУТЬ
    public void getFullRailInfoByIdAndWayType_whenGetId9434AndCorrectWayType_thenReturnFullRailDtoWithNoAdditionalInfo() {
        RailDto rail = railService.getFullRailInfoByIdAndWayType(9434L, WayType.STATION_WAY);

        assertThat(rail).isNotNull();
        assertThat(rail.getId()).isEqualTo(9434L);
        assertThat(rail.getRailKind()).isEqualTo("ЗВЕНЬЕВОЙ ПУТЬ");
        assertThat(rail.getLineSide()).isEqualTo("ПРАВАЯ");
        assertThat(rail.getFactory()).isEqualTo("К");
        assertThat(rail.getRollingDate()).isEqualTo("IV-1984");
        assertThat(rail.getFuseNumber()).isEqualTo("ЭVБ-65422");
        assertThat(rail.getInstallationDate()).isEqualTo(LocalDate.of(1991, 4, 16));
        assertThat(rail.getInstallationType()).isEqualTo("СТАРОГОДНЫЙ");
        assertThat(rail.getPassedTonnageAfterInstall()).isEqualTo(0.9F);
        assertThat(rail.getLastMeasureDate()).isEqualTo(LocalDate.of(2024, 3, 29));
        assertThat(rail.getRailLash()).isNull();
        assertThat(rail.getRailSwitch()).isNull();
        assertThat(rail.getLength()).isEqualTo(25F);
        assertThat(rail).isInstanceOf(StationWayRailDto.class);

        StationWayRailDto stationWayRailDto = (StationWayRailDto) rail;
        StationWaySectionDto stationWaySection = stationWayRailDto.getStationWaySection();

        assertThat(stationWaySection).isNotNull();
        assertThat(stationWaySection.stationWay().station().getName()).isEqualTo("ГДЕ-НИБУДЬСК");
        assertThat(stationWaySection.stationWay().number()).isEqualTo("6");
        assertThat(stationWaySection.startMeter()).isEqualTo(356);
        assertThat(stationWaySection.endMeter()).isEqualTo(381);
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "INTERSTATION_TRACK", "STATION_MAIN_WAY" })
    public void getFullRailInfoByIdAndWayType_whenGetId9434AndIncorrectWayType_thenThrowException(WayType wayType) {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> railService.getFullRailInfoByIdAndWayType(9434L, wayType));

        assertThat(exception.getMessage()).startsWith("Не удалось найти в базе данных объект 'рельс ");
        assertThat(exception.getMessage()).endsWith("' с ид=9434");
    }

    @Test
    // Получение и контроль параметров рельса станционного пути с типом ПЛЕТЬ? расположенной у конечного торца плети
    public void getFullRailInfoByIdAndWayType_whenGetId8983AndCorrectWayType_thenReturnFullRailDtoWithLashInfo() {
        RailDto rail = railService.getFullRailInfoByIdAndWayType(8983L, WayType.STATION_WAY);

        assertThat(rail).isNotNull();
        assertThat(rail.getId()).isEqualTo(8983L);
        assertThat(rail.getRailKind()).isEqualTo("ПЛЕТЬ");
        assertThat(rail.getLineSide()).isEqualTo("ЛЕВАЯ");
        assertThat(rail.getFactory()).isEqualTo("Т");
        assertThat(rail.getRollingDate()).isEqualTo("V-2010");
        assertThat(rail.getFuseNumber()).isEqualTo("КV-52518");
        assertThat(rail.getInstallationDate()).isEqualTo(LocalDate.of(2012, 6, 9));
        assertThat(rail.getInstallationType()).isEqualTo("СТАРОГОДНЫЙ");
        assertThat(rail.getPassedTonnageAfterInstall()).isEqualTo(2.9F);
        assertThat(rail.getLastMeasureDate()).isEqualTo(LocalDate.of(2024, 3, 29));
        assertThat(rail.getRailLash()).isNotNull();
        assertThat(rail.getRailSwitch()).isNull();
        assertThat(rail.getLength()).isEqualTo(14.07F);
        assertThat(rail).isInstanceOf(StationWayRailDto.class);

        StationWayRailDto stationWayRailDto = (StationWayRailDto) rail;
        StationWaySectionDto stationWaySection = stationWayRailDto.getStationWaySection();

        assertThat(stationWaySection).isNotNull();
        assertThat(stationWaySection.stationWay().station().getName()).isEqualTo("ГДЕ-ЛИБО");
        assertThat(stationWaySection.stationWay().number()).isEqualTo("4");
        assertThat(stationWaySection.startMeter()).isEqualTo(1131);
        assertThat(stationWaySection.endMeter()).isEqualTo(1145);

        LashDto lash = rail.getRailLash();

        assertThat(lash).isNotNull();
        assertThat(lash.getId()).isEqualTo(289L);
        assertThat(lash.getName()).isEqualTo("7-2СЛ");
        assertThat(lash.getLastTemperatureTensionDischargingDate()).isEqualTo(LocalDate.of(2012, 6, 9));
        assertThat(lash.getLength()).isEqualTo(529.07F);
        assertThat(lash.getInstallationType()).isEqualTo("УКЛАДКА СГ");
        assertThat(lash).isInstanceOf(StationWayLashDto.class);

        StationWayLashDto stationWayLashDto = (StationWayLashDto) lash;
        StationWaySectionDto lashStationWaySection = stationWayLashDto.getStationWaySection();

        assertThat(lashStationWaySection).isNotNull();
        assertThat(lashStationWaySection.stationWay().station().getName()).isEqualTo("ГДЕ-ЛИБО");
        assertThat(lashStationWaySection.stationWay().number()).isEqualTo("4");
        assertThat(lashStationWaySection.startMeter()).isEqualTo(616);
        assertThat(lashStationWaySection.endMeter()).isEqualTo(1145);
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "INTERSTATION_TRACK", "STATION_MAIN_WAY" })
    public void getFullRailInfoByIdAndWayType_whenGetId8983AndIncorrectWayType_thenThrowException(WayType wayType) {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> railService.getFullRailInfoByIdAndWayType(8983L, wayType));

        assertThat(exception.getMessage()).startsWith("Не удалось найти в базе данных объект 'рельс ");
        assertThat(exception.getMessage()).endsWith("' с ид=8983");
    }

    @ParameterizedTest
    @ValueSource(longs = { 81, 82, 85, 86, 89, 90, 175, 176, 183, 184, 191, 192, 1927, 1928, 2019, 2020, 3257, 3258,
            3261, 3262, 3269, 3270, 3399, 3400, 3405, 3406, 3411, 3412, 3515, 3516, 3521, 3522, 3527, 3528, 3625, 3626,
            3629, 3630, 3633, 3634, 5367, 5368, 5371, 5372, 5465, 5466, 5469, 5470, 6707, 6708, 6711, 6712, 6715, 6716,
            6845, 6846, 6853, 6854, 6857, 6858, 8037, 8038, 8043, 8044, 8165, 8166, 8571, 8572, 8675,
            8676 })
    public void getFullRailInfoByIdAndWayType_whenGetStationMainWaySwitchRailId_thenReturnRailDtoWithSwitchDto(long id) {
        RailDto rail = railService.getFullRailInfoByIdAndWayType(id, WayType.STATION_MAIN_WAY);

        assertThat(rail).isNotNull();
        assertThat(rail.getId()).isEqualTo(id);
        assertThat(rail.getRailKind()).isEqualTo("СТРЕЛОЧНЫЙ ПЕРЕВОД");
        assertThat(rail.getRailLash()).isNull();

        SwitchDto railSwitch = rail.getRailSwitch();

        assertThat(railSwitch).isNotNull();
        assertThat(railSwitch).isInstanceOf(StationMainWaySwitchDto.class);

        StationMainWaySwitchDto stationMainWaySwitchDto = (StationMainWaySwitchDto) railSwitch;

        assertThat(stationMainWaySwitchDto.getStationMainWay()).isNotNull();
        assertThat(stationMainWaySwitchDto.getStationMainWay().station()).isNotNull();
        assertThat(stationMainWaySwitchDto.getStationParkName()).isNull();
        assertThat(stationMainWaySwitchDto.getFrameRailJointPlace()).isNotNull();
        assertThat(stationMainWaySwitchDto.getFrameRailJointPlace().transportDirection()).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(longs = { 386, 391, 450, 455, 2081, 4667, 5400, 5421, 7325, 7331, 8163, 8164 })
    public void getFullRailInfoByIdAndWayType_whenGetCorrectParametersForLashGetting_thenReturnRailDtoWithLashDto(long id) {
        RailDto rail = railService.getFullRailInfoByIdAndWayType(id, WayType.STATION_MAIN_WAY);

        assertThat(rail).isNotNull();
        assertThat(rail.getId()).isEqualTo(id);
        assertThat(rail.getRailKind()).isEqualTo("ПЛЕТЬ");
        assertThat(rail.getRailLash()).isNotNull();

        LashDto lashDto = rail.getRailLash();

        assertThat(lashDto).isNotNull();
        assertThat(lashDto).isInstanceOf(MainWayLashDto.class);

        MainWayLashDto mainWayLashDto = (MainWayLashDto) lashDto;

        assertThat(mainWayLashDto.getMainWayNumber()).isNotNull();
        assertThat(mainWayLashDto.getMainWaySection()).isNotNull();
        assertThat(mainWayLashDto.getMainWaySection().transportDirection()).isNotNull();

        rail = railService.getFullRailInfoByIdAndWayType(id, WayType.INTERSTATION_TRACK);

        assertThat(rail).isNotNull();
        assertThat(rail.getId()).isEqualTo(id);
        assertThat(rail.getRailKind()).isEqualTo("ПЛЕТЬ");
        assertThat(rail.getRailLash()).isNotNull();

        lashDto = rail.getRailLash();

        assertThat(lashDto).isNotNull();
        assertThat(lashDto).isInstanceOf(MainWayLashDto.class);

        mainWayLashDto = (MainWayLashDto) lashDto;

        assertThat(mainWayLashDto.getMainWayNumber()).isNotNull();
        assertThat(mainWayLashDto.getMainWaySection()).isNotNull();
        assertThat(mainWayLashDto.getMainWaySection().transportDirection()).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(longs = { 386, 391, 450, 455, 2081, 4667, 5400, 5421, 7325, 7331, 8163, 8164 })
    public void getFullRailInfoByIdAndWayType_whenGetIncorrectWayTypeParametersForLashGetting_thenThrowException(long id) {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
            railService.getFullRailInfoByIdAndWayType(id, WayType.STATION_WAY));

        assertThat(exception).isInstanceOf(ObjectNotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект 'рельс " +
                "станционного пути' с ид=" + id);

        exception = assertThrows(RuntimeException.class, () ->
            railService.getFullRailInfoByIdAndWayType(id, null));

        assertThat(exception).isInstanceOf(IllegalStateException.class);
        assertThat(exception.getMessage()).isEqualTo("Не удалось получить полную информацию о рельсе, так " +
                "как при вызове метода не был указан тип пути (wayType=null).");
    }

    @ParameterizedTest
    @ValueSource(longs = { 8771, 8772, 9267, 9268, 9237, 9238, 9123, 9124, 8997, 8998 })
    public void getFullRailInfoByIdAndWayType_whenGetStationParametersForLashGetting_thenReturnRailDtoWithLashDto(long id) {
        RailDto rail = railService.getFullRailInfoByIdAndWayType(id, WayType.STATION_WAY);

        assertThat(rail).isNotNull();
        assertThat(rail.getId()).isEqualTo(id);
        assertThat(rail.getRailKind()).isEqualTo("ПЛЕТЬ");
        assertThat(rail.getRailLash()).isNotNull();

        LashDto lashDto = rail.getRailLash();

        assertThat(lashDto).isNotNull();
        assertThat(lashDto).isInstanceOf(StationWayLashDto.class);

        StationWayLashDto stationWayLashDto = (StationWayLashDto) lashDto;

        assertThat(stationWayLashDto.getStationWaySection()).isNotNull();
        assertThat(stationWayLashDto.getStationWaySection().stationWay()).isNotNull();
        assertThat(stationWayLashDto.getStationWaySection().stationWay().station()).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(longs = { 8687, 8688, 8777, 8778, 8779, 8780, 8885, 8886, 8887, 8888, 8989, 8990, 8991, 8992, 9129,
            9130, 9131, 9132, 9273, 9274, 9275, 9276, 9403, 9404, 9405, 9406, 9497, 9498, 9603, 9604, 9605, 9606, 9629,
            9630, 9763, 9764, 9883, 9884 })
    public void getFullRailInfoByIdAndWayType_whenGetStationParametersForSwitchGetting_thenReturnRailDtoWithNullSwitchDto(long id) {
        RailDto rail = railService.getFullRailInfoByIdAndWayType(id, WayType.STATION_WAY);

        assertThat(rail).isNotNull();
        assertThat(rail.getId()).isEqualTo(id);
        assertThat(rail.getRailKind()).isEqualTo("СТРЕЛОЧНЫЙ ПЕРЕВОД");
        assertThat(rail.getRailLash()).isNull();
        assertThat(rail.getRailSwitch()).isNull();
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class)
    public void getRailMiniInfoVariantsByLocationDto_whenGetCorrectMainWayLocation_thenReturnListOfMiniDtos(
            WayType wayType) {
        LocationDto testLocation = new LocationDto(wayType,
                3L,
                "1",
                7,
                1,
                1,
                LineSide.LEFT);
        if (wayType == WayType.INTERSTATION_TRACK) {
            List<RailMiniDto> miniRailList = railService.getRailMiniInfoVariantsByLocationDto(testLocation);

            assertThat(miniRailList).isNotNull();
            assertThat(miniRailList).isNotEmpty();
            assertThat(miniRailList).hasSize(8);
            assertThat(miniRailList).contains(new RailMiniDto(7463L,
                    "ЛЕВАЯ",
                    "ПЛЕТЬ",
                    6.987f,
                    7.012f));
        } else {
            ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                    () -> railService.getRailMiniInfoVariantsByLocationDto(testLocation));
            assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект 'массив " +
                    "вариантов рельсов для конкретного места' со следующими критериями выборки: " + testLocation);
        }
    }

    @Test
    public void getRailMiniInfoVariantsByLocationDto_whenGetCorrectStationWayLocation_thenReturnListOfMiniDtos() {
        LocationDto testLocation = new LocationDto(WayType.STATION_WAY,
                8L,
                "6",
                0,
                5,
                1,
                LineSide.RIGHT);
        List<RailMiniDto> miniRailList = railService.getRailMiniInfoVariantsByLocationDto(testLocation);

        assertThat(miniRailList).isNotNull();
        assertThat(miniRailList).isNotEmpty();
        assertThat(miniRailList).hasSize(7);
        assertThat(miniRailList).contains(new RailMiniDto(9436L,
                "ПРАВАЯ",
                "ЗВЕНЬЕВОЙ ПУТЬ",
                0.381f,
                0.406f));
    }

}