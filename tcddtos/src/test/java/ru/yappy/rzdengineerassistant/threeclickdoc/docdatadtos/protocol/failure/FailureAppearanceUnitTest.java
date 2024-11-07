package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FailureAppearanceUnitTest {

    @Test
    void getEnumStringFields_whenJoined_thenReturnCorrectBigString() {
        String bigEnumString = Arrays.stream(FailureAppearance.values())
                .map(appearance -> appearance.getRuName() + " " + appearance.getAdditionalInfoDescription())
                .collect(Collectors.joining(", "));
        assertThat(bigEnumString).isEqualTo("ложная занятость секции/блок-участка [наименование секции или " +
                "блок-участка], некорректная сигнализация огнями светофора укажите через точку с запятой: [наименование " +
                "сигнала]; [какими огнями сигнализировал светофор (творительный падеж)], ложная свободность секции/" +
                "блок-участка [наименование секции или блок-участка], обнаружение неисправности, при которой не " +
                "допускается движение подвижного состава укажите через точку с запятой: [описание неисправности " +
                "(именительный падеж)]; [должность обнаружевшего]; [фамилия и.о. обнаружевшего]; [в ходе какой " +
                "проверки обнаружено (родительный падеж)]");
    }

}