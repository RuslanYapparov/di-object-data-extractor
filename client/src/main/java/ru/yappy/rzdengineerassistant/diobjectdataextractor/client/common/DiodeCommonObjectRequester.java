package ru.yappy.rzdengineerassistant.diobjectdataextractor.client.common;

import org.springframework.http.ResponseEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto;

/**
 * Интерфейс, определяющий тип и поведение всех объектов-клиентов, запрашивающих у сервера DiObjectDataExtractor
 * данные об объектах и сущностях, характерных для хозяйств ОАО "РЖД", необходимые для работы пользователей в приложении.
 */
public interface DiodeCommonObjectRequester {

    /**
     * Запрашивает минимальную информацию для отображения вариантов станционных путей, находящихся в границах станции
     * по ее идентификатору.
     *
     * @param stationId идентификатор станции
     * @return сущность ответа сервера, содержащая массив объектов с минимальной информацией о станционных путях
     */
    ResponseEntity<StationWayMiniDto[]> getStationWayMiniDtosByStationId(Long stationId);

}