package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.delay;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TrainTypeUnitTest {

    @Test
    void getRuNominativeSortedByStopHourCost_thenReturnCorrectString() {
        String sortedTrainTypes = Arrays.stream(TrainType.values())
                .sorted(Comparator.comparingDouble(TrainType::getStopHourCost))
                .map(TrainType::getRuNominative)
                .collect(Collectors.joining(", "));
        assertThat(sortedTrainTypes).isEqualTo("рельсовый автобус, грузовой локомотив на электротяге, " +
                "мотовоз (автомотриса) на дизельной тяге, хозяйственный поезд на дизельной тяге, хозяйственный " +
                "поезд на электротяге, мотовоз (автомотриса) на электротяге, маневровый локомотив, пассажирский " +
                "локомотив на электротяге, пассажирский локомотив на дизельной тяге, грузовой локомотив на " +
                "дизельной тяге, пригородный поезд, пассажирский поезд, грузовой поезд");
    }

    @Test
    void getRuGenitiveSortedByPriority_thenReturnCorrectString() {
        Set<TrainType> typeSet = Arrays.stream(TrainType.values()).collect(Collectors.toSet());
        String sortedTrainTypes = typeSet.stream()
                .sorted(Comparator.comparingInt(TrainType::getPriority))
                .map(TrainType::getRuGenitive)
                .collect(Collectors.joining(", "));
        assertThat(sortedTrainTypes).isEqualTo("пассажирского поезда, пригородного поезда, грузового " +
                "поезда, хозяйственного поезда на электротяге, хозяйственного поезда на дизельной тяге, мотовоза " +
                "(автомотрисы) на электротяге, мотовоза (автомотрисы) на дизельной тяге, рельсового автобуса, " +
                "пассажирского локомотива на электротяге, пассажирского локомотива на дизельной тяге, грузового " +
                "локомотива на электротяге, грузового локомотива на дизельной тяге, маневрового локомотива");
    }

}