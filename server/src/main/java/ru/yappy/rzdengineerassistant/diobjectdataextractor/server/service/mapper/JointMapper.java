package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import org.mapstruct.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LineSide;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.projection.rail.*;

import java.util.List;

/**
 * Интерфейс-мэппер для сущностей рельсовых стыков, на основании которого Mapstruct создает реализацию.
 */
@Mapper(componentModel = "spring", uses = {
        WayPlaceMapper.class,
        SwitchMapper.class
})
public interface JointMapper extends DiObjectEntityToDtoMapper<JointEntity, JointDto> {

    /**
     * Переопределенный метод суперинтерфейса {@link DiObjectEntityToDtoMapper}, описывающий логику мэппинга сущностей
     * рельсовых стыков в DTO.
     *
     * @param jointEntity объект-entity стыка (предусмотренных разновидностей), полученный из SpringJpa-репозитотрия
     * @return DTO с информацией о рельсовом стыке
     */
    @Override
    default JointDto toDto(JointEntity jointEntity) {
        return switch (jointEntity) {
            case null -> null;
            case MainWayConductingJointEntity mwcj -> mapMainWayConductingJointEntityToDto(mwcj);
            case MainWaySignalIsolatingJointEntity mwij -> mapMainWaySignalIsolatingJointEntityToDto(mwij);
            case MainWaySwitchIsolatingJointEntity mwij -> mapMainWaySwitchIsolatingJointEntityToDto(mwij);
            case StationWayConductingJointEntity swcj -> mapStationWayConductingJointEntityToDto(swcj);
            case StationWaySignalIsolatingJointEntity swij -> mapStationWaySignalIsolatingJointEntityToDto(swij);
            case StationWaySwitchIsolatingJointEntity swij -> mapStationWaySwitchIsolatingJointEntityToDto(swij);
            default -> throw new IllegalStateException("Не реализована логика мэппинга для типа рельсового стыка '" +
                    jointEntity.getClass().getSimpleName() + "' в DTO");
        };
    }

    /**
     * Дополнительный метод мэппинга, преобразует entity-объект токопроводящего рельсового стыка
     * главного пути в DTO.
     *
     * @param mainWayConductingJointEntity объект-сущность токопроводящего рельсового стыка главного пути, полученный
     *                                     из SpringJpa-репозитотрия
     * @return DTO токопроводящего рельсового стыка главного пути
     */
    @Mapping(target = "mainWayNumber", source = "mainWay.number")
    MainWayConductingJointDto mapMainWayConductingJointEntityToDto(
            MainWayConductingJointEntity mainWayConductingJointEntity);

    /**
     * Дополнительный метод мэппинга, преобразует entity-объект изолирующего рельсового стыка
     * сигнала главного пути в DTO.
     *
     * @param mainWaySignalIsolatingJointEntity объект-сущность изолирующего рельсового стыка сигнала главного пути,
     *                                          полученный из SpringJpa-репозитотрия
     * @return DTO изолирующего рельсового стыка сигнала главного пути
     */
    @Mapping(target = "mainWayNumber", source = "mainWay.number")
    MainWaySignalIsolatingJointDto mapMainWaySignalIsolatingJointEntityToDto(
            MainWaySignalIsolatingJointEntity mainWaySignalIsolatingJointEntity);

    /**
     * Дополнительный метод мэппинга, преобразует entity-объект изолирующего рельсового стыка
     * стрелочного перевода главного пути в DTO.
     *
     * @param mainWaySwitchIsolatingJointEntity объект-сущность изолирующего рельсового стыка стрелочного перевода
     *                                          главного пути, полученный из SpringJpa-репозитотрия
     * @return DTO изолирующего рельсового стыка стрелочного перевода главного пути
     */
    @Mapping(target = "mainWayNumber", source = "mainWay.number")
    @Mapping(target = "jointSwitch", source = "switchEntity")
    MainWaySwitchIsolatingJointDto mapMainWaySwitchIsolatingJointEntityToDto(
            MainWaySwitchIsolatingJointEntity mainWaySwitchIsolatingJointEntity);

    /**
     * Дополнительный метод мэппинга, преобразует entity-объект токопроводящего рельсового стыка
     * станционного пути в DTO.
     *
     * @param stationWayConductingJointEntity объект-сущность токопроводящего рельсового стыка станционного пути,
     *                                          полученный из SpringJpa-репозитотрия
     * @return DTO токопроводящего рельсового стыка станционного пути
     */
    StationWayConductingJointDto mapStationWayConductingJointEntityToDto(
            StationWayConductingJointEntity stationWayConductingJointEntity);

    /**
     * Дополнительный метод мэппинга, преобразует entity-объект изолирующего рельсового стыка сигнала станционного
     * пути в DTO.
     *
     * @param stationWaySignalIsolatingJointEntity объект-сущность изолирующего рельсового стыка сигнала станционного
     *                                              пути, полученный из SpringJpa-репозитотрия
     * @return DTO изолирующего рельсового стыка сигнала станционного пути
     */
    StationWaySignalIsolatingJointDto mapStationWaySignalIsolatingJointEntityToDto(
            StationWaySignalIsolatingJointEntity stationWaySignalIsolatingJointEntity);

    /**
     * Дополнительный метод мэппинга, преобразует entity-объект изолирующего рельсового стыка
     * стрелочного перевода станционного пути в DTO.
     *
     * @param stationWaySwitchIsolatingJointEntity объект-сущность изолирующего рельсового стыка стрелочного перевода
     *                                              станционного пути, полученный из SpringJpa-репозитотрия
     * @return DTO изолирующего рельсового стыка стрелочного перевода станционного пути
     */
    @Mapping(target = "jointSwitch", source = "switchEntity")
    StationWaySwitchIsolatingJointDto mapStationWaySwitchIsolatingJointEntityToDto(
            StationWaySwitchIsolatingJointEntity stationWaySwitchIsolatingJointEntity);

    /**
     * Реализованный по умолчанию метод для мэппинга одного объекта-проекции рельсового стыка главного пути в мини-DTO.
     *
     * @param jointProjection объект-проекция рельсового стыка главного пути, полученный из SpringJpa-репозитотрия
     * @param withDecrement параметр-флаг, указывающий на необходимость уменьшения значения километра в координате на 1
     * @return минимизированный DTO рельсового стыка
     */
    default JointMiniDto mapMainWayJointProjectionToMiniDto(MainWayJointProjection jointProjection,
                                                           boolean withDecrement) {
        MainWayPlaceEntity place = jointProjection.getMainWayPlace();
        float kmValue = (float) (withDecrement ? place.getKm().getKm() - 1 : place.getKm().getKm());
        float coordinate = kmValue + (place.getMeter().floatValue() / 1000);

        return new JointMiniDto(
                jointProjection.getId(),
                jointProjection.getType(),
                jointProjection.getLineSide(),
                coordinate
        );
    }

    /**
     * Реализованный по умолчанию метод для мэппинга одного объекта-проекции рельсового стыка станционного пути в
     * мини-DTO.
     *
     * @param jointProjection объект-проекция рельсового стыка станционного пути, полученный из SpringJpa-репозитотрия
     * @return минимизированный DTO рельсового стыка
     */
    default JointMiniDto mapStationWayJointProjectionToMiniDto(StationWayJointProjection jointProjection) {
        StationWayPlaceEntity place = jointProjection.getStationWayPlace();
        int km = place.getPlaceMeter() / 1000;
        int meter = place.getPlaceMeter() % 1000;

        return new JointMiniDto(
                jointProjection.getId(),
                jointProjection.getType(),
                jointProjection.getLineSide(),
                (float) km + ((float) meter / 1000f)
        );
    }

    /**
     * Преобразует список объектов-проекций рельсовых стыков главного пути в список мини-DTO стыков.
     *
     * @param jointProjections список объектов-проекций стыков главного пути, полученный из SpringJpa-репозитория
     * @return список мини-DTO рельсов
     */
    default List<JointMiniDto> mapMainWayJointEntityListToMiniDtoList(List<MainWayJointProjection> jointProjections) {
        if (jointProjections == null) {
            return null;
        }
        if (jointProjections.isEmpty()) {
            return List.of();
        }
        int lastCoordinate = jointProjections.getLast().getMainWayPlace().getKm().getKm() *
                1000 + jointProjections.getLast().getMainWayPlace().getMeter();
        return jointProjections.stream()
                .map(jointProj -> {
                    float jointCoord = jointProj.getMainWayPlace().getKm().getKm() * 1000 +
                            jointProj.getMainWayPlace().getMeter();
                    return mapMainWayJointProjectionToMiniDto(jointProj, lastCoordinate < jointCoord);
                })
                .toList();
    }

    /**
     * Преобразует список объектов-проекций рельсовых стыков станционного пути в список мини-DTO стыков.
     *
     * @param jointProjections список объектов-проекций стыков станционного пути, полученный из SpringJpa-репозитория
     * @return список мини-DTO рельсов
     */
    List<JointMiniDto> mapStationWayJointEntityListToMiniDtoList(List<StationWayJointProjection> jointProjections);

    /**
     * Преобразует строку с обозначением сторонности рельсовой нити в объект перечисления LineSide.
     *
     * @param lineSide строка с названием сторонности рельсовой нити
     * @return объект перечисления LineSide
     */
    default LineSide mapRuStringLineSideToEnum(String lineSide) {
        return switch (lineSide) {
            case "ЛЕВАЯ" -> LineSide.LEFT;
            case "ПРАВАЯ" -> LineSide.RIGHT;
            default -> null;
        };
    }

}