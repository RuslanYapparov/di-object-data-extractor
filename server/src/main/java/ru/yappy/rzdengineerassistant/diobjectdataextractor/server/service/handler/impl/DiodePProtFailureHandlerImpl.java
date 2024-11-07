package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl.EmployeeDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.empl.EmployeeService;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr.LocationService;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.JointService;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.handler.DiodePProtFailureHandler;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.mini.JointFailureProtCreatingDataDto;

import java.util.List;

/**
 * Реализация интерфейса {@link DiodePProtFailureHandler}, содержащая методы для получения информации, необходимой для
 * создания протоколов отказов технических средств хозяйства пути.
 */
@Service
@Slf4j
public class DiodePProtFailureHandlerImpl implements DiodePProtFailureHandler {
    /** Сервис для работы с сущностями рельсовых стыков. */
    private final JointService jointService;
    /** Сервис для работы с сущностями работников ОАО "РЖД". */
    private final EmployeeService employeeService;
    /** Сервис для работы с сущностями локаций (перегонов, станций). */
    private final LocationService locationService;

    /**
     * Конструктор хэндлера запросов, получающий зависимости из Spring-контекста.
     *
     * @param jointService сервис для работы с сущностями рельсовых стыков
     * @param employeeService сервис для работы с сущностями работников ОАО "РЖД"
     * @param locationService сервис для работы с сущностями локаций (перегонов, станций)
     */
    @Autowired
    private DiodePProtFailureHandlerImpl(JointService jointService,
                                         EmployeeService employeeService,
                                         LocationService locationService) {
        this.jointService = jointService;
        this.employeeService = employeeService;
        this.locationService = locationService;
    }

    /**
     * Обрабатывает запрос на получение информации, необходимой для создания протокола отказа из-за неисправности
     * рельсового стыка
     *
     * @param location DTO с данными о конкретном месте, введенными пользователем в UI
     * @return DTO с вариантами для выбора работников дистанции пути, вариантов рельсовых стыков и описанием локации
     */
    @Override
    public JointFailureProtCreatingDataDto getJointFailureProtCreatingDataDtoByLocationDto(LocationDto location,
                                                                                           JointType jointType) {
        List<JointMiniDto> jointVariants = jointService.getJointMiniInfoVariantsByLocationDto(location, jointType);
        String locationDescription = getLocationDescription(location);
        List<EmployeeDto> employeeVariants =
                employeeService.getAllWayDistanceEmployeesServingTheLocationWithoutValidation(location);
        return new JointFailureProtCreatingDataDto(jointVariants.toArray(JointMiniDto[]::new),
                locationDescription,
                employeeVariants);
    }

    /**
     * Получает из сервиса работы с локациями короткое описание местоположения (название станции или перегона) объекта.
     *
     * @param locationDto объект, хранящий исчерпывающую информацию о локации
     * @return строка с названием станции или перегона
     */
    private String getLocationDescription(LocationDto locationDto) {
        return switch (locationDto.wayType()) {
            case INTERSTATION_TRACK -> locationService.getInterstationTrackNameByLocationDto(locationDto);
            case STATION_MAIN_WAY -> locationService.getStationNameByLocationDto(locationDto);
            default -> null;
        };
    }

}