package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.in.p;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.UriEncoder;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm.StructuralEnterpriseDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm.StructuralEnterpriseMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.adm.StructuralEnterpriseService;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.*;

import java.util.List;

/**
 * REST-контроллер, обрабатывающий http-запросы на получение минимальной информации об объектах хозяйства пути
 * (вариантов для выбора пользователем) и исчерпывающей информации о некоторых специфических объектах (дистанция пути).
 */
@RestController
@RequestMapping("/api/v1/objects/p")
@Slf4j
public class DiodePSpecificObjectController {
    /** Сервис для получения информации о дистанциях пути. */
    private final StructuralEnterpriseService wayDistanceService;
    /** Сервис для получения информации о рельсах. */
    private final RailService railService;
    /** Сервис для получения информации о рельсовых стыках. */
    private final JointService jointService;

    /**
     * Конструктор объекта-контроллера, получающий зависимости из Spring-контейнера.
     *
     * @param wayDistanceService сервис для получения информации о дистанциях пути
     * @param railService сервис для получения информации о рельсах
     */
    @Autowired
    public DiodePSpecificObjectController(@Qualifier("way") StructuralEnterpriseService wayDistanceService,
                                          RailService railService,
                                          JointService jointService) {
        this.wayDistanceService = wayDistanceService;
        this.railService = railService;
        this.jointService = jointService;
    }

    /**
     * Возвращает DTO с полной информацией о дистанции пути по ее идентификатору.
     *
     * @param distanceId идентификатор дистанции пути
     * @return DTO с полной информацией о дистанции пути
     */
    @GetMapping(value = "/distance/{distance_id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StructuralEnterpriseDto getWayMaintenanceDistanceFullInfoById(
            @PathVariable(name = "distance_id") Long distanceId) {
        log.info("Начало обработки http-запроса на получение полной информации о дистанции пути с ид={}", distanceId);
        StructuralEnterpriseDto wayMaintenanceDistanceDto =
                wayDistanceService.getFullStructuralEnterpriseInfoById(distanceId);
        log.info("Данные о дистанции пути с ид={} подготовлены и отправляются клиенту", distanceId);
        return wayMaintenanceDistanceDto;
    }

    /**
     * Возвращает список из DTO с минимальной информацией о дистанциях пути по аббревиатуре дирекции инфраструктуры.
     *
     * @param encodedDirectorateAbbreviation аббревиатура региональной дирекции инфраструктуры
     * @return список из DTO с минимальной информацией о дистанциях пути
     */
    @GetMapping(value = "/distance",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<? extends StructuralEnterpriseMiniDto> getWayMaintenanceDistanceVariantsByDirectorateAbbreviation(
            @RequestParam(name = "dirabbrev") String encodedDirectorateAbbreviation) {
        String directorateAbbreviation = UriEncoder.decode(encodedDirectorateAbbreviation);
        log.info("Начало обработки http-запроса на получение минимальной информации о дистанциях пути по аббревиатуре " +
                "региональной дирекции инфраструктуры: {}", directorateAbbreviation);
        List<? extends StructuralEnterpriseMiniDto> wayMaintenanceDistanceVariants =
                wayDistanceService.getAllStructuralEnterprisesMiniInfoByDirectorateAbbreviation(directorateAbbreviation);
        log.info("Минимальная информация о всех дистанциях пути в пределах {} получена и отправляется клиенту",
                directorateAbbreviation);
        return wayMaintenanceDistanceVariants;
    }

    /**
     * Вовзращает список из DTO с минимальной информацией о рельсах, находящихся вблизи конкретного места, определенного
     * объектом {@link LocationDto}.
     *
     * @param locationDto объект с информацией о месте, относительно которого будут получены варианты рельсов
     * @return список из DTO с минимальной информацией о рельсах
     */
    @GetMapping(value = "/rail",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RailMiniDto> getRailVariantsByLocationDto(@RequestBody LocationDto locationDto) {
        log.info("Начало обработки http-запроса на получение минимальной информации о рельсах, находящихся вблизи " +
                " определенного места: {}", locationDto);
        List<RailMiniDto> railVariants = railService.getRailMiniInfoVariantsByLocationDto(locationDto);
        log.info("Минимальная информация о {} рельсах, находящихся вблизи указанного пользователем места, получена и " +
                        "отправляется клиенту", railVariants.size());
        return railVariants;
    }

    /**
     * Вовзращает список DTO с минимальной информацией о рельсовых стыках, находящихся вблизи конкретного места,
     * определенного объектом {@link LocationDto}.
     *
     * @param locationDto объект с информацией о месте, относительно которого будут получены варианты рельсовых стыков
     * @param jointType тип рельсового стыка
     * @return массив DTO с минимальной информацией о рельсовых стыках
     */
    @GetMapping(value = "/joint",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JointMiniDto> getJointVariantsByLocationDto(@RequestParam(name = "type") JointType jointType,
                                                            @RequestBody LocationDto locationDto) {
        log.info("Начало обработки http-запроса на получение минимальной информации о рельсовых стыках, " +
                "находящихся вблизи определенного места: {}", locationDto);
        List<JointMiniDto> jointVariants = jointService.getJointMiniInfoVariantsByLocationDto(locationDto, jointType);
        log.info("Минимальная информация о {} рельсовых стыках, находящихся вблизи указанного пользователем " +
                "места, получена и отправляется клиенту", jointVariants.size());
        return jointVariants;
    }

}