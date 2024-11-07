package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.out.repo.rail.LashRepository;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.impl.LashServiceImpl;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class LashServiceUnitTest {
    private static final LashRepository mockLashRepository = Mockito.mock(LashRepository.class);
    private final LashService lashService = new LashServiceImpl(mockLashRepository);

    private final MainWayRailEntity mainWayRailEntity = RailSchemaTestDataCreator.createTestMainWayRailEntity();
    private final StationWayRailEntity stationWayRailEntity =
            RailSchemaTestDataCreator.createTestStationWayRailEntity();

    @BeforeAll
    public static void mockLashRepositorySetup() {
        Mockito.when(mockLashRepository.findFullMainWayLashInfoByLocationParameters(777L,
                777L,
                "ЛЕВАЯ",
                7777777))
                .thenReturn(Optional.of(RailSchemaTestDataCreator.createTestMainWayLashEntity()));
        Mockito.when(mockLashRepository.findFullStationWayLashInfoByLocationParameters(777L,
                "ПРАВАЯ",
                777)).thenReturn(Optional.of(RailSchemaTestDataCreator.createTestStationWayLashEntity()));
    }

    @Test
    public void getLashEntityByRailEntity_whenGetCorrectMainWayRailEntity_thenReturnLashEntity() {
        LashEntity lashEntity = lashService.getLashEntityByRailEntity(mainWayRailEntity);

        assertThat(lashEntity).isNotNull();
        assertThat(lashEntity).isInstanceOf(MainWayLashEntity.class);
        assertThat(lashEntity.getId()).isEqualTo(777L);
        assertThat(lashEntity.getName()).isEqualTo("ТЕСТ");
        assertThat(lashEntity.getLastTemperatureTensionDischargingDate())
                .isEqualTo(LocalDate.of(2024, 1, 1));

        MainWayLashEntity mainWayLashEntity = (MainWayLashEntity) lashEntity;

        assertThat(mainWayLashEntity.getMainWay()).isNotNull();
        assertThat(mainWayLashEntity.getMainWay().getId()).isEqualTo(777L);
        assertThat(mainWayLashEntity.getMainWaySection()).isNotNull();
        assertThat(mainWayLashEntity.getMainWaySection().getId()).isEqualTo(777L);
        assertThat(mainWayLashEntity.getMainWaySection().getStartKm().getKm()).isEqualTo(7777);
    }

    @Test
    public void getLashEntityByRailEntity_whenGetIncorrectMainWayRailEntity_thenThrowException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> lashService.getLashEntityByRailEntity(null));
        assertThat(exception.getMessage()).isEqualTo("Для выполнения операции поиска рельсовой плети по " +
                "сущности рельса в ее составе получен null");

        mainWayRailEntity.setRailKind("ТЕСТ");

        exception = assertThrows(IllegalStateException.class,
                () -> lashService.getLashEntityByRailEntity(mainWayRailEntity));
        assertThat(exception.getMessage()).isEqualTo("Для выполнения операции поиска рельсовой плети по " +
                "сущности рельса в ее составе получен объект railEntity с признаком railKind не 'ПЛЕТЬ'");
    }

    @Test
    public void getLashEntityByRailEntity_whenGetMainWayRailEntityAndCannotFindLashEntityInDb_thenReturnNullLashInfo() {
        mainWayRailEntity.getMainWaySection().setStartMeter(778);

        assertDoesNotThrow(() -> lashService.getLashEntityByRailEntity(mainWayRailEntity));
        assertThat(lashService.getLashEntityByRailEntity(mainWayRailEntity)).isNull();
    }

    @Test
    public void getLashEntityByRailEntity_whenGetUnsupportedClassOfRailEntity_thenReturnNullLashInfo() {
        RailEntity railEntity = new TestRailEntity();
        railEntity.setRailKind("ПЛЕТЬ");
        assertDoesNotThrow(() -> lashService.getLashEntityByRailEntity(railEntity));
        assertThat(lashService.getLashEntityByRailEntity(railEntity)).isNull();
    }

    @Test
    public void getLashEntityByRailEntity_whenGetCorrectStationWayRailEntity_thenReturnLashEntity() {
        LashEntity lashEntity = lashService.getLashEntityByRailEntity(stationWayRailEntity);

        assertThat(lashEntity).isNotNull();
        assertThat(lashEntity).isInstanceOf(StationWayLashEntity.class);
        assertThat(lashEntity.getId()).isEqualTo(777L);
        assertThat(lashEntity.getName()).isEqualTo("ТЕСТ");
        assertThat(lashEntity.getLastTemperatureTensionDischargingDate())
                .isEqualTo(LocalDate.of(2024, 1, 1));

        StationWayLashEntity stationWayLashEntity = (StationWayLashEntity) lashEntity;

        assertThat(stationWayLashEntity.getStationWaySection()).isNotNull();
        assertThat(stationWayLashEntity.getStationWaySection().getId()).isEqualTo(777L);
        assertThat(stationWayLashEntity.getStationWaySection().getStartMeter()).isEqualTo(777);
    }

    @Test
    public void getLashEntityByRailEntity_whenGetStationWayRailEntityAndCannotFindLashEntityInDb_thenReturnNullLashInfo() {
        stationWayRailEntity.getStationWaySection().setStartMeter(778);

        assertDoesNotThrow(() -> lashService.getLashEntityByRailEntity(stationWayRailEntity));
        assertThat(lashService.getLashEntityByRailEntity(stationWayRailEntity)).isNull();
    }

    private static class TestRailEntity extends RailEntity {
    }

}