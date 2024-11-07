package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location;

/**
 * DTO-класс для хранения и передачи информации о расположении какого-либо железнодорожного объекта.
 *
 * @param wayType тип пути (станционный или главный)
 * @param locationId идентификатор объекта-локации, в пределах которого находится объект (главный путь или станция)
 * @param wayNumber строка, содержащая название (номер) пути
 * @param km целочисленное значение километра, на котором находится железнодорожный объект
 * @param picket пикет километра, на котором находится железнодорожный объект
 * @param meter метр пикета, на котором находится железнодорожный объект
 * @param lineSide сторонность рельсовой нити
 */
public record LocationDto(WayType wayType,
                          Long locationId,
                          String wayNumber,
                          Integer km,
                          Integer picket,
                          Integer meter,
                          LineSide lineSide) {}