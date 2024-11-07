package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.util;

import lombok.experimental.UtilityClass;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;

import java.util.stream.Stream;

/**
 * Утилитарный класс, содержащий статические перегруженные методы validate(...) для проверки поступающих в методы
 * сервисов параметров и выбрасывающий исключения с сообщениями на русском языке.
 */
@UtilityClass
public class ParameterValidator {

    /**
     * Проверяет значения полей объекта {@link LocationDto} на null, а также на возможность получения валидного
     * значения номера пути.
     *
     * @param location DTO, содержащий информацию о конкретном месте
     */
    public void validate(LocationDto location) {
        checkForNullValue(location, "конкретное место железнодорожного направления");
        Stream.of(location.wayType(), location.lineSide(), location.locationId(), location.km(),
                location.picket(), location.meter(), location.wayNumber())
                .forEach(parameter ->
                        checkForNullValue(parameter, "какой-либо из параметров конкретного места"));
        checkLongParameterForMoreThenZeroValue(location.locationId(), "locationId");
        if (!WayType.STATION_WAY.equals(location.wayType())) {
            Integer parsedInt = checkStringParameterToParseToInteger(location.wayNumber(), "wayNumber");
            checkIntegerParameterForMoreThenZeroValue(parsedInt, "wayNumber");
        }
    }

    /**
     * Проверяет Long-значение идентификатора объекта на null и на значение больше нуля.
     *
     * @param id идентификатор объекта
     * @param objectName название типа объекта
     */
    public void validate(Long id, String objectName) {
        String parameterDescription = "id объекта '" + objectName + "'";
        checkForNullValue(id, parameterDescription);
        checkLongParameterForMoreThenZeroValue(id, parameterDescription);
    }

    /**
     * Проверяет переданное значение на null.
     *
     * @param object объект, проверяемый на null
     * @param parameterName название типа объекта
     * @throws IllegalArgumentException если переданный объект null
     */
    private void checkForNullValue(Object object, String parameterName) {
        if (object == null) {
            throw new IllegalArgumentException("Параметр '" + parameterName + "' не может быть null.");
        }
    }

    /**
     * Проверяет строковый объект на возможность преобразования в Integer-значение.
     *
     * @param value строковый объект, который должен содержать целочисленное значение
     * @param parameterName  название типа параметра
     * @return Integer-значение после парсинга
     * @throws IllegalArgumentException если переданный объект null, пустая строка или не может быть преобразован
     * в Integer-значение
     */
    private Integer checkStringParameterToParseToInteger(String value, String parameterName) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("Параметр '" + parameterName + "' не может быть пустым.");
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Параметр '" + parameterName + "' должен быть целым числом, но было " +
                    "получено значение '" + value + "'.");
        }
    }

    /**
     * Проверяет Long-значение на значение больше нуля.
     *
     * @param value Long-значение, которое должно быть больше нуля
     * @param parameterName название параметра
     * @throws IllegalArgumentException если переданное значение меньше или равно нулю
     */
    private void checkLongParameterForMoreThenZeroValue(Long value, String parameterName) {
        if (value <= 0L) {
            throw new IllegalArgumentException("Параметр '" + parameterName + "' должен быть больше нуля.");
        }
    }

    /**
     * Проверяет Integer-значение на значение больше нуля.
     *
     * @param value Integer-значение, которое должно быть больше нуля
     * @param parameterName название параметра
     * @throws IllegalArgumentException если переданное значение меньше или равно нулю
     */
    private void checkIntegerParameterForMoreThenZeroValue(Integer value, String parameterName) {
        if (value <= 0) {
            throw new IllegalArgumentException("Параметр '" + parameterName + "' должен быть больше нуля.");
        }
    }

}