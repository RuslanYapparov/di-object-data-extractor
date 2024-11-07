package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.adm.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm.WayMaintenanceDistanceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm.*;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.adm.StructuralEnterpriseService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class WayMaintenanceDistanceServiceIntTest {
    private final StructuralEnterpriseService wayMaintenanceDistanceService;

    @Autowired
    public WayMaintenanceDistanceServiceIntTest(@Qualifier("way")
                                                 StructuralEnterpriseService wayMaintenanceDistanceService) {
        this.wayMaintenanceDistanceService = wayMaintenanceDistanceService;
    }

    @Test
    public void getFullWayMaintenanceDistanceInfoById_whenGetIdEqual1_thenReturnWayMaintenanceDistanceDto() {
        WayMaintenanceDistanceDto koeGdenskayaWayDistance = (WayMaintenanceDistanceDto)
                wayMaintenanceDistanceService.getFullStructuralEnterpriseInfoById(1L);

        assertThat(koeGdenskayaWayDistance).isNotNull();
        assertThat(koeGdenskayaWayDistance.getId()).isEqualTo(1L);
        assertThat(koeGdenskayaWayDistance.getName()).isEqualTo("Кое-гденьская");
        assertThat(koeGdenskayaWayDistance.getNumber()).isEqualTo(1);
        assertThat(koeGdenskayaWayDistance.getIsIch()).isFalse();
        assertThat(koeGdenskayaWayDistance.getMainWays().size()).isEqualTo(3);
        assertThat(koeGdenskayaWayDistance.getStations().size()).isEqualTo(2);
        assertThat(koeGdenskayaWayDistance.getManagers().size()).isEqualTo(3);
        assertThat(koeGdenskayaWayDistance.getRegionalDirectorateAbbreviation()).isEqualTo("ВЗДИ");
    }

    @Test
    public void getFullWayMaintenanceDistanceInfoById_whenGetIdEqual2_thenReturnWayMaintenanceDistanceDto() {
        WayMaintenanceDistanceDto gdeNibudskayaWayDistance = (WayMaintenanceDistanceDto)
                wayMaintenanceDistanceService.getFullStructuralEnterpriseInfoById(2L);

        assertThat(gdeNibudskayaWayDistance).isNotNull();
        assertThat(gdeNibudskayaWayDistance.getId()).isEqualTo(2L);
        assertThat(gdeNibudskayaWayDistance.getName()).isEqualTo("Где-нибудьская");
        assertThat(gdeNibudskayaWayDistance.getNumber()).isEqualTo(1);
        assertThat(gdeNibudskayaWayDistance.getIsIch()).isTrue();
        assertThat(gdeNibudskayaWayDistance.getMainWays().size()).isEqualTo(3);
        assertThat(gdeNibudskayaWayDistance.getStations().size()).isEqualTo(3);
        assertThat(gdeNibudskayaWayDistance.getManagers().size()).isEqualTo(4);
        assertThat(gdeNibudskayaWayDistance.getRegionalDirectorateAbbreviation()).isEqualTo("ВЗДИ");
    }

    @Test
    public void getFullWayMaintenanceDistanceInfoById_whenGetIdEqual3_thenReturnWayMaintenanceDistanceDto() {
        WayMaintenanceDistanceDto issoWayDistance = (WayMaintenanceDistanceDto)
                wayMaintenanceDistanceService.getFullStructuralEnterpriseInfoById(3L);

        assertThat(issoWayDistance).isNotNull();
        assertThat(issoWayDistance.getId()).isEqualTo(3L);
        assertThat(issoWayDistance.getName()).isEqualTo("ИССО");
        assertThat(issoWayDistance.getNumber()).isEqualTo(0);
        assertThat(issoWayDistance.getIsIch()).isFalse();
        assertThat(issoWayDistance.getMainWays().size()).isEqualTo(4);
        assertThat(issoWayDistance.getStations().size()).isEqualTo(5);
        assertThat(issoWayDistance.getManagers().size()).isEqualTo(1);
        assertThat(issoWayDistance.getRegionalDirectorateAbbreviation()).isEqualTo("ВЗДИ");
    }

    @ParameterizedTest
    @ValueSource(longs = { 4, 7 })
    public void getFullWayMaintenanceDistanceInfoById_whenGetNotPDistanceId_thenThrowException(long value) {
        Exception exception = assertThrows(Exception.class, () ->
                wayMaintenanceDistanceService.getFullStructuralEnterpriseInfoById(value));

        assertThat(exception).isInstanceOf(ObjectNotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                "'дистанция пути' с ид=" + value);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1, Long.MIN_VALUE, Long.MAX_VALUE})
    public void getFullWayMaintenanceDistanceInfoById_whenGetIncorrectId_thenThrowException(Long value) {
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () ->
                wayMaintenanceDistanceService.getFullStructuralEnterpriseInfoById(value));
        assertThat(exception.getMessage()).isEqualTo("Не удалось найти в базе данных объект " +
                "'дистанция пути' с ид=" + value);
    }

    @Test
    public void getAllWayMaintenanceDistanceInfoByDirectorateAbbreviation_whenGetDirectorateAbbreviationVZDI_thenOk() {
        List<? extends StructuralEnterpriseMiniDto> miniDtos = wayMaintenanceDistanceService
                .getAllStructuralEnterprisesMiniInfoByDirectorateAbbreviation("ВЗДИ");
        WayMaintenanceDistanceMiniDto testDto = (new WayMaintenanceDistanceMiniDto(1L,
                "Кое-гденьская",
                1,
                false));

        assertThat(miniDtos).isNotNull();
        miniDtos.forEach(dto -> assertThat(dto).isInstanceOf(WayMaintenanceDistanceMiniDto.class));
        assertThat(miniDtos.size()).isEqualTo(3);
        assertThat(miniDtos.contains(testDto)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "  ", "\n", "\t", "\r", "VZDI", "ВЗД" })
    @NullSource
    public void getAllWayMaintenanceDistanceInfoByDirectorateAbbreviation_whenGetIncorrectValue_thenReturnEmptyList(
            String directorateAbbreviation) {
        List<? extends StructuralEnterpriseMiniDto> miniDtos = wayMaintenanceDistanceService
                .getAllStructuralEnterprisesMiniInfoByDirectorateAbbreviation(directorateAbbreviation);

        assertThat(miniDtos).isEmpty();
    }

}