package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.in.p;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.handler.DiodePProtFailureHandler;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.mini.JointFailureProtCreatingDataDto;

/**
 * REST-контроллер, обрабатывающий http-запросы на получение информации, необходимой для работы пользователя в UI при
 * создании протоколов отказов технических средств объектов путевого хозяйства.
 */
@RestController
@RequestMapping("/api/v1/protocol/failure/p")
@RequiredArgsConstructor
@Slf4j
public class DiodePProtFailureController {
    /** Сервис-обработчик, который находит в базе данных и компонует в DTO информацию, необходимую для работы
     * пользователя в UI при создании протокола отказа технического средства путевого хозяйства. */
    private final DiodePProtFailureHandler diodePProtFailureHandler;

    /**
     * Возвращает DTO с данными, необходимыми для работы пользователя в UI при создании протокола отказа из-за
     * неисправности рельсового стыка - список DTO причастных работников, описание локации и массив вариантов стыков.
     *
     * @param jointType тип отказавшего рельсового стыка
     * @param locationDto DTO с данными о конкретном месте, введенными пользователем в UI
     * @return DTO с данными необходимыми для работы пользователя в UI при создании протокола отказа из-за стыка
     */
    @GetMapping(value = "/joint", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JointFailureProtCreatingDataDto getJointFailureProtCreatingDataDto(
            @RequestParam(name = "type") JointType jointType,
            @RequestBody LocationDto locationDto) {
        log.info("Начало обработки http-запроса на получение данных для создания протокола отказа технических средств " +
                "из-за неисправности '{}' рельсового стыка для конкретного места {}", jointType, locationDto);
        JointFailureProtCreatingDataDto creatingData =
                diodePProtFailureHandler.getJointFailureProtCreatingDataDtoByLocationDto(locationDto, jointType);
        log.info("Информация для создания протокола отказа '{}' рельсового стыка получена и отправляется клиенту",
                jointType);
        return creatingData;
    }

}