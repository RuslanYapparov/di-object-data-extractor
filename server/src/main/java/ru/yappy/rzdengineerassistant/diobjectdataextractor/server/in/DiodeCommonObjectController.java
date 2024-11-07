package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.in;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr.LocationService;

import java.util.Set;

/**
 * REST-контроллер, обрабатывающий http-запросы на получение минимальной информации об объектах и сущностях,
 * используемых во всех хозяйствах ОАО "РЖД"(вариантов для выбора пользователем).
 */
@RestController
@RequestMapping("/api/v1/objects/common")
@Slf4j
public class DiodeCommonObjectController {
    /** Сервис для получения информации о сущностях и объектах, касающихся местоположения. */
    private final LocationService locationService;

    /**
     * Конструктор объекта-контроллера, получающий зависимости из Spring-контейнера.
     *
     * @param locationService сервис для получения информации о сущностях и объектах, касающихся местоположения
     */
    @Autowired
    public DiodeCommonObjectController(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * Возвращает сет с DTO, содержащими минимальную информацию о станционных путях по идентификатору станции.
     *
     * @param stationId идентификатор станции
     * @return сет DTO с минимальной информацией о станционных путях
     */
    @GetMapping(value = "/stationway/mini/{station_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<StationWayMiniDto> getStationWaysMiniInfoByStationId(@PathVariable(name = "station_id") Long stationId) {
        log.info("Начало обработки http-запроса на получение минимальной информации о станционных путях " +
                "для станции с ид={}", stationId);
        Set<StationWayMiniDto> stationWays = locationService.getStationWaysMiniDtoByStationId(stationId);
        log.info("Данные о {} станционных путях, расположенных на станции с ид={} подготовлены и отправляются клиенту",
                (stationWays == null) ? 0 : stationWays.size(), stationId);
        return stationWays;
    }

}