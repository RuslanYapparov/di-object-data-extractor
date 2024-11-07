package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr.SwitchDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.LashDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.RailDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.RailEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr.InfrSchemaTestDataCreator;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.RailSchemaTestDataCreator;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RailMapperTest {
    private final RailMapper railMapper;

    @Autowired
    public RailMapperTest(RailMapper railMapper) {
        this.railMapper = railMapper;
    }

    @Test
    public void toDto_whenGetMaximumRailEntity_thenReturnDto() {
        RailEntity railEntity = RailSchemaTestDataCreator.createTestStationWayRailEntity();
        railEntity.setRailLash(RailSchemaTestDataCreator.createTestMainWayLashEntity());
        railEntity.setRailSwitch(InfrSchemaTestDataCreator.createTestStationMainWaySwitchEntity());

        RailDto railDto = railMapper.toDto(railEntity);
        assertThat(railDto).isNotNull();
        assertThat(railDto.getId()).isEqualTo(777L);
        assertThat(railDto.getLineSide()).isEqualTo("ПРАВАЯ");
        assertThat(railDto.getRailKind()).isEqualTo("ПЛЕТЬ");
        assertThat(railDto.getRailLash()).isNotNull();
        assertThat(railDto.getRailSwitch()).isNotNull();

        LashDto lashDto = railDto.getRailLash();
        assertThat(lashDto.getId()).isEqualTo(777L);
        assertThat(lashDto.getInstallationDate()).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(lashDto.getPassedTonnageBeforeInstall()).isEqualTo(7777.77f);

        SwitchDto switchDto = railDto.getRailSwitch();
        assertThat(switchDto.getName()).isEqualTo("ТЕСТ");
        assertThat(switchDto.getId()).isEqualTo(777L);
        assertThat(switchDto.getInstallationDate()).isEqualTo(LocalDate.of(2024, 1, 1));
    }

    @Test
    public void toDto_whenGetNullRailEntityWithNotSupportedClass_thenReturnNull() {
        assertThat(railMapper.toDto(null)).isNull();
    }

    @Test
    public void toDto_whenGetRailEntityWithNotSupportedClass_thenThrowException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> railMapper.toDto(new TestRailEntity()));
        assertThat(exception.getMessage())
                .isEqualTo("Не реализована логика мэппинга для типа рельса 'TestRailEntity' в DTO");
    }

    private static class TestRailEntity extends RailEntity {
    }

}
