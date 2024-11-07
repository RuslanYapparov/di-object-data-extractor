package ru.yappy.rzdengineerassistant.diobjectdataextractor.client.p;

import org.springframework.http.ResponseEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm.WayMaintenanceDistanceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm.WayMaintenanceDistanceMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.*;

/**
 * Интерфейс, определяющий тип и поведение всех объектов-клиентов, запрашивающих у сервера DiObjectDataExtractor
 * данные об объектах хозяйства пути, необходимые для работы пользователей в приложении.
 */
public interface DiodePSpecificObjectRequester {

    /**
     * Запрашивает полную информацию о дистанции пути (ПЧ или ИЧ).
     *
     * @param id идентификатор дистанции пути (инфраструктуры)
     * @return сущность ответа сервера с информацией о дистанции пути,
     * содержащейся в объекте {@link WayMaintenanceDistanceDto}
     */
    ResponseEntity<WayMaintenanceDistanceDto> getWayMaintenanceDistanceDtoById(Long id);

    /**
     * Запрашивает минимальную информацию для отображения вариантов дистанций пути (дистанций инфраструктуры),
     * находящихся в границах дирекции инфраструктуры по её аббревиатуре.
     *
     * @param directorateAbbreviation аббревиатура дирекции инфраструктуры
     * @return сущность ответа сервера, содержащая массив объектов с минимальной информацией о дистанциях пути
     */
    ResponseEntity<WayMaintenanceDistanceMiniDto[]> getAllWayMaintenanceDistanceMiniDtosByDirectorateAbbreviation(
            String directorateAbbreviation);

    /**
     * Запрашивает минимальную информацию для отображения вариантов рельсов, находящихся вблизи конкретного места,
     * заданного объектом {@link LocationDto}.
     *
     * @param locationDto объект, содержащий информацию о месте, вблизи которого будет выгружена информация о рельсах
     * @return сущность ответа сервера, содержащей массив объектов с минимальной информацией о рельсах
     */
    ResponseEntity<RailMiniDto[]> getRailMiniDtosByLocationDto(LocationDto locationDto);

    /**
     * Запрашивает минимальную информацию для отображения вариантов рельсовых стыков определенного типа, находящихся
     * вблизи конкретного места, заданного объектом {@link LocationDto}.
     *
     * @param locationDto объект, содержащий информацию о месте, вблизи которого будет выгружена информация о стыках
     * @param jointType тип рельсовых стыков для выгрузки
     * @return сущность ответа сервера, содержащей массив объектов с минимальной информацией о рельсовых стыках
     */
    ResponseEntity<JointMiniDto[]> getJointMiniDtosByLocationAndType(LocationDto locationDto, JointType jointType);

}