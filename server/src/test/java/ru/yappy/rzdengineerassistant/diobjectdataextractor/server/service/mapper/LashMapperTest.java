package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.LashEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class LashMapperTest {
    private final LashMapper lashMapper;

    @Autowired
    public LashMapperTest(LashMapper lashMapper) {
        this.lashMapper = lashMapper;
    }

    @Test
    public void toDto_whenGetLashEntityWithNotSupportedClass_thenThrowException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> lashMapper.toDto(new TestLashEntity()));
        assertThat(exception.getMessage())
                .isEqualTo("Не реализована логика мэппинга для типа плети 'TestLashEntity' в DTO");
    }

    private static class TestLashEntity extends LashEntity {
    }

}
