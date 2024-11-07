package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.MainWayPlaceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationWayPlaceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.InterstationTrackSwitchDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.StationMainWaySwitchDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.StationWaySwitchDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.SwitchDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.SwitchMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.RailEntity;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.RailSchemaTestDataCreator;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SwitchServiceIntTest {
    private final SwitchService switchService;

    @Autowired
    public SwitchServiceIntTest(SwitchService switchService) {
        this.switchService = switchService;
    }

    @Test
    public void getFullSwitchInfoByIdAndWayType_whenGetId41AndCorrectWayType_thenReturnInterstationTrackSwitchDto() {
        SwitchDto switchDto = switchService.getFullSwitchInfoByIdAndWayType(41L, WayType.INTERSTATION_TRACK);

        assertThat(switchDto).isNotNull();
        assertThat(switchDto).isInstanceOf(InterstationTrackSwitchDto.class);
        assertThat(switchDto.getId()).isEqualTo(41L);
        assertThat(switchDto.getName()).isEqualTo("8");
        assertThat(switchDto.getFullName()).isNull();
        assertThat(switchDto.getProject()).isEqualTo("2750");
        assertThat(switchDto.getInstallationDate()).isEqualTo(LocalDate.of(2016, 10, 1));
        assertThat(switchDto.getPassedTonnageAfterInstall()).isEqualTo(380.2F);

        InterstationTrackSwitchDto interstationTrackSwitchDto = (InterstationTrackSwitchDto) switchDto;

        assertThat(interstationTrackSwitchDto.getControlStation()).isEqualTo("ГДЕ-ЛИБО");
        assertThat(interstationTrackSwitchDto.getMainWayNumber()).isEqualTo(2);

        MainWayPlaceDto frameRailJointPlace = interstationTrackSwitchDto.getFrameRailJointPlace();

        assertThat(frameRailJointPlace).isNotNull();
        assertThat(frameRailJointPlace.transportDirection().id()).isEqualTo(1L);
        assertThat(frameRailJointPlace.km()).isEqualTo(1262);
        assertThat(frameRailJointPlace.meterOnKm()).isEqualTo(737);
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_WAY", "STATION_MAIN_WAY" })
    public void getFullSwitchInfoByIdAndWayType_whenGetId41AndIncorrectWayType_thenThrowException(WayType wayType) {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> switchService.getFullSwitchInfoByIdAndWayType(41L, wayType));
        assertThat(exception.getMessage()).startsWith("Не удалось найти в базе данных объект " +
                "'стрелочный перевод на");
        assertThat(exception.getMessage()).endsWith("' с ид=41");
    }

    @Test
    public void getFullSwitchInfoByIdAndWayType_whenGetId1AndCorrectWayType_thenReturnStationMainWaySwitchDto() {
        SwitchDto switchDto = switchService.getFullSwitchInfoByIdAndWayType(1L, WayType.STATION_MAIN_WAY);

        assertThat(switchDto).isNotNull();
        assertThat(switchDto).isInstanceOf(StationMainWaySwitchDto.class);
        assertThat(switchDto.getId()).isEqualTo(1L);
        assertThat(switchDto.getName()).isEqualTo("1");
        assertThat(switchDto.getFullName()).isNull();
        assertThat(switchDto.getProject()).isEqualTo("2750");
        assertThat(switchDto.getInstallationDate()).isEqualTo(LocalDate.of(2018, 7, 1));
        assertThat(switchDto.getPassedTonnageAfterInstall()).isEqualTo(517.3F);

        StationMainWaySwitchDto stationMainWaySwitchDto = (StationMainWaySwitchDto) switchDto;

        assertThat(stationMainWaySwitchDto.getStationMainWay().station().getName()).isEqualTo("КОЕ-ГДЕНЬ");
        assertThat(stationMainWaySwitchDto.getStationParkName()).isNull();
        assertThat(stationMainWaySwitchDto.getStationMainWay().number()).isEqualTo("1");
        assertThat(stationMainWaySwitchDto.getWayNameSwitchGoesOn()).isEqualTo("2");

        MainWayPlaceDto frameRailJointPlace = stationMainWaySwitchDto.getFrameRailJointPlace();

        assertThat(frameRailJointPlace).isNotNull();
        assertThat(frameRailJointPlace.transportDirection().id()).isEqualTo(1L);
        assertThat(frameRailJointPlace.km()).isEqualTo(1236);
        assertThat(frameRailJointPlace.meterOnKm()).isEqualTo(311);
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_WAY", "INTERSTATION_TRACK" })
    public void getFullSwitchInfoByIdAndWayType_whenGetId1AndIncorrectWayType_thenThrowException(WayType wayType) {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> switchService.getFullSwitchInfoByIdAndWayType(1L, wayType));
        assertThat(exception.getMessage()).startsWith("Не удалось найти в базе данных объект " +
                "'стрелочный перевод на");
        assertThat(exception.getMessage()).endsWith("' с ид=1");
    }

    @Test
    public void getFullSwitchInfoByIdAndWayType_whenGetId37AndCorrectWayType_thenReturnStationWaySwitchDto() {
        SwitchDto switchDto = switchService.getFullSwitchInfoByIdAndWayType(37L, WayType.STATION_WAY);

        assertThat(switchDto).isNotNull();
        assertThat(switchDto).isInstanceOf(StationWaySwitchDto.class);
        assertThat(switchDto.getId()).isEqualTo(37L);
        assertThat(switchDto.getName()).isEqualTo("4");
        assertThat(switchDto.getFullName()).isNull();
        assertThat(switchDto.getProject()).isEqualTo("2766");
        assertThat(switchDto.getInstallationDate()).isEqualTo(LocalDate.of(1985, 9, 1));
        assertThat(switchDto.getPassedTonnageAfterInstall()).isEqualTo(1.1F);

        StationWaySwitchDto stationWaySwitchDto = (StationWaySwitchDto) switchDto;
        assertThat(stationWaySwitchDto.getWayNameSwitchGoesOn()).isEqualTo("3");
        assertThat(stationWaySwitchDto.getStationParkName()).isNull();

        StationWayPlaceDto frameRailJointPlace = stationWaySwitchDto.getFrameRailStationWayPlace();

        assertThat(frameRailJointPlace).isNotNull();
        assertThat(frameRailJointPlace.stationWay().station().getName()).isEqualTo("КОЕ-ГДЕНЬ ГРУЗОВАЯ");
        assertThat(frameRailJointPlace.stationWay().number()).isEqualTo("2");
        assertThat(frameRailJointPlace.meterOnStationWay()).isEqualTo(1087);
    }

    @ParameterizedTest
    @EnumSource(value = WayType.class, names = { "STATION_MAIN_WAY", "INTERSTATION_TRACK" })
    public void getFullSwitchInfoByIdAndWayType_whenGetId37AndIncorrectWayType_thenThrowException(WayType wayType) {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class,
                () -> switchService.getFullSwitchInfoByIdAndWayType(37L, wayType));
        assertThat(exception.getMessage()).startsWith("Не удалось найти в базе данных объект " +
                "'стрелочный перевод на");
        assertThat(exception.getMessage()).endsWith("' с ид=37");
    }

    @Test
    public void getFullSwitchInfoByIdAndWayType_whenGetSomeIdAndNullWayType_thenThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> switchService.getFullSwitchInfoByIdAndWayType(Long.MAX_VALUE, null));
        assertThat(exception.getMessage()).isEqualTo("При выполнении операции поиска стрелочного " +
                "перевода по его идентификатору получен признак wayType=null");
    }

    @Test
    public void getSwitchMiniDtoVariantsByLocationDto_haveIncorrectWorkMode_andReturnOnlyEmptySet() {
        Set<SwitchMiniDto> switchMiniDtoVariants = switchService.getSwitchMiniDtoVariantsByLocationDto(null);

        assertThat(switchMiniDtoVariants).isNotNull();
        assertThat(switchMiniDtoVariants).isInstanceOf(Set.class);
        assertThat(switchMiniDtoVariants).isEmpty();
    }

    @Test
    public void getSwitchEntityByRailEntityAndWayType_whenGetNullValues_thenThrowException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> switchService.getSwitchEntityByRailEntityAndWayType(null, WayType.STATION_WAY));
        assertThat(exception.getMessage()).isEqualTo("Для выполнения операции поиска стрелочного перевода " +
                "по сущности рельса в его составе не получен сам объект сущности рельса (railEntity=null");

        RailEntity railEntity = RailSchemaTestDataCreator.createTestMainWayRailEntity();

        exception = assertThrows(IllegalStateException.class,
                () -> switchService.getSwitchEntityByRailEntityAndWayType(railEntity, null));
        assertThat(exception.getMessage()).isEqualTo("Для выполнения операции поиска стрелочного перевода " +
                "по сущности рельса в его составе не получен признак типа пути (wayType=null)");

        exception = assertThrows(IllegalStateException.class,
                () -> switchService.getSwitchEntityByRailEntityAndWayType(railEntity, WayType.INTERSTATION_TRACK));
        assertThat(exception.getMessage()).isEqualTo("Для выполнения операции поиска стрелочного перевода " +
                "по сущности рельса в его составе получен объект railEntity с признаком railKind " +
                "не 'СТРЕЛОЧНЫЙ ПЕРВЕОД'");
    }

}