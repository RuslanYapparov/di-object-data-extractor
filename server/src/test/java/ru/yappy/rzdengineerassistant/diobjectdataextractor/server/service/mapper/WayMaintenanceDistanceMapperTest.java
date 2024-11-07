package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm.WayMaintenanceDistanceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.WayMaintenanceDistanceEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.adm.impl.AdmSchemaTestDataCreator;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WayMaintenanceDistanceMapperTest {
    private final WayMaintenanceDistanceMapper mapper;

    @Autowired
    public WayMaintenanceDistanceMapperTest(WayMaintenanceDistanceMapper mapper) {
        this.mapper = mapper;
    }

    @Test
    public void mapListOfDistanceEntitiesToListOfDto_whenGetCorrectEntity_thenReturnCorrectWayMaintenanceDistanceDto() {
        WayMaintenanceDistanceEntity entity = AdmSchemaTestDataCreator.createTestWayMaintenanceDistanceEntity();

        WayMaintenanceDistanceDto dto = mapper.toDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(777L);
        assertThat(dto.getName()).isEqualTo("DistanceName");
        assertThat(dto.getNumber()).isEqualTo(7);
        assertThat(dto.getIsIch()).isEqualTo(false);
        assertThat(dto.getRegionalDirectorateAbbreviation()).isEqualTo("DI");
        assertThat(dto.getManagers().size()).isEqualTo(1);
        dto.getManagers().forEach(manager -> {
            assertThat(manager).isNotNull();
            assertThat(manager.id()).isEqualTo(777L);
            assertThat(manager.fullName()).isEqualTo("TestSurname TestName TestPatronymic");
            assertThat(manager.jobTitleAbbreviation()).isEqualTo("ПЧ");
        });
        assertThat(dto.getMainWays().size()).isEqualTo(1);
        dto.getMainWays().forEach(mainWay -> {
            assertThat(mainWay).isNotNull();
            assertThat(mainWay.id()).isEqualTo(777L);
            assertThat(mainWay.number()).isEqualTo(1);
            assertThat(mainWay.transportDirection().id()).isEqualTo(777L);
            assertThat(mainWay.transportDirection().name()).isEqualTo("ТЕСТ - ТЕСТ");
        });
        assertThat(dto.getStations()).hasSize(0);
    }

}