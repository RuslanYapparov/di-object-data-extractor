package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.SwitchEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SwitchMapperTest {
    private final SwitchMapper switchMapper;

    @Autowired
    public SwitchMapperTest(SwitchMapper switchMapper) {
        this.switchMapper = switchMapper;
    }

    @Test
    public void toDto_whenGetSwitchEntityWithNotSupportedClass_thenThrowException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> switchMapper.toDto(new TestSwitchEntity()));
        assertThat(exception).hasMessageContaining("Не реализована логика мэппинга для типа стрелочного перевода " +
                "'TestSwitchEntity' в DTO");
    }

    private static class TestSwitchEntity extends SwitchEntity {
    }

}