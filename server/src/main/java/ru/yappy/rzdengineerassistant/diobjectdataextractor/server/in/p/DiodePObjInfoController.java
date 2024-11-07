package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.in.p;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.handler.DiodePObjInfoHandler;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.*;

/**
 * REST-контроллер, обрабатывающий http-запросы на получение информации, необходимой для создания
 * документов-характеристик объектов инфраструктуры, относящихся к хозяйству пути.
 */
@RestController
@RequestMapping("/api/v1/objinfo/p")
@RequiredArgsConstructor
@Slf4j
public class DiodePObjInfoController {
    /** Сервис-обработчик, который находит в базе данных и компонует в DTO информациию, необходимую для создания
     * характеристик объектов инфраструктуры. */
    private final DiodePObjInfoHandler diodePObjInfoHandler;

    /**
     * Возвращает DTO c полной информацией о рельсе, наименовании станции/перегона и характеристиках пути.
     *
     * @param railId идентификатор рельса в базе данных
     * @param wayType признак типа пути, указанный пользователем в заполняемой во фронтенде форме
     * @return неполный {@link RailInfoDto} с информацией для создания документа, которая извлекается из базы данных
     */
    @GetMapping(value = "/rail/{rail_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RailInfoDto getFullRailInfo(@PathVariable(name = "rail_id") Long railId,
                                       @RequestParam(name = "waytype") WayType wayType) {
        log.info("Начало обработки http-запроса на получение информации о рельсе с ид={} и признаком waytype={} для " +
                        "создания документа-характеристики",
                railId, wayType);
        RailInfoDto railInfoDto = diodePObjInfoHandler.handleRailInfoRequest(railId, wayType);
        log.info("Информация из базы данных для рельса с ид={} получена и отправляется клиенту", railId);
        return railInfoDto;
    }

    /**
     * Возвращает DTO c полной информацией о рельсовом стыке, наименовании станции/перегона и характеристиках пути.
     *
     * @param jointId идентификатор рельсового стыка в базе данных
     * @param wayType признак типа пути, указанный пользователем в заполняемой во фронтенде форме
     * @param jointType признак типа рельсового стыка
     * @return неполный {@link JointInfoDto} с информацией для создания документа, которая извлекается из базы данных
     */
    @GetMapping(value = "/joint/{joint_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JointInfoDto getFullJointInfo(@PathVariable(name = "joint_id") Long jointId,
                                         @RequestParam(name = "waytype") WayType wayType,
                                         @RequestParam(name = "jointtype") JointType jointType) {
        log.info("Начало обработки http-запроса на получение информации о '{}' рельсовом стыке с ид={} и " +
                        "признаком waytype={} для создания документа-характеристики", jointType, jointId, wayType);
        JointInfoDto jointInfoDto = diodePObjInfoHandler.handleJointInfoRequest(jointId, wayType, jointType);
        log.info("Информация из базы данных для '{}' рельсового стыка с ид={} получена и отправляется клиенту",
                jointType, jointId);
        return jointInfoDto;
    }

}