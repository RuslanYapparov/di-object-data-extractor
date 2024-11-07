package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LineSide;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParameterValidatorTest {

    @Test
    public void creatingInstance_whenCalled_thenThrowException() {
        Constructor<?> tabSwitcherPrivateConstructor = ParameterValidator.class.getDeclaredConstructors()[0];
        tabSwitcherPrivateConstructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class,
                tabSwitcherPrivateConstructor::newInstance);
        assertThat(exception.getMessage()).isNull();

        Throwable causeException = exception.getTargetException();
        assertThat(causeException).isInstanceOf(UnsupportedOperationException.class);
        assertThat(causeException.getMessage()).isEqualTo("This is a utility class and cannot be instantiated");
    }

    @Test
    public void validateForIdParameter_whenGetIncorrectParameter_thenThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ParameterValidator.validate(null, "test id"));
        assertThat(exception.getMessage()).isEqualTo("Параметр 'id объекта 'test id'' не может быть null.");
        exception = assertThrows(IllegalArgumentException.class,
                () -> ParameterValidator.validate(0L, null));
        assertThat(exception.getMessage()).isEqualTo("Параметр 'id объекта 'null'' должен быть больше нуля.");
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "  ", "\n", "\t", "\r" })
    @NullSource
    public void validateForLocationParameter_whenGetIncorrectParameter_thenThrowException(String value) {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                1L,
                value,
                7,
                7,
                7,
                LineSide.LEFT);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ParameterValidator.validate(location));
        assertThat(exception.getMessage()).isEqualTo(value == null ?
                "Параметр 'какой-либо из параметров конкретного места' не может быть null." :
                "Параметр 'wayNumber' не может быть пустым.");
    }

    @ParameterizedTest
    @ValueSource(strings = { "test", "-1" })
    public void validateForLocationParameter_whenGetIncorrectParameterForParsing_thenThrowException(String value) {
        LocationDto location = new LocationDto(
                WayType.INTERSTATION_TRACK,
                1L,
                value,
                7,
                7,
                7,
                LineSide.LEFT);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ParameterValidator.validate(location));
        assertThat(exception.getMessage()).isEqualTo("test".equals(value) ?
                "Параметр 'wayNumber' должен быть целым числом, но было получено значение 'test'." :
                "Параметр 'wayNumber' должен быть больше нуля.");
    }

    @Test
    public void validateForLocationParameter_whenGetNullLocation_thenThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> ParameterValidator.validate(null));
        assertThat(exception.getMessage()).isEqualTo("Параметр 'конкретное место железнодорожного направления'" +
                " не может быть null.");
    }

}
