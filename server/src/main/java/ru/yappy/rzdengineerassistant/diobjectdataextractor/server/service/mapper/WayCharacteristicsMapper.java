package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.mapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.wayc.WayCharacteristicsDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc.*;

import java.time.LocalDate;
import java.util.Set;

/** Интерфейс-мэппер для сущностей участков характеристик пути, на основании которого MapStruct создает реализацию. */
@Mapper(componentModel = "spring")
public interface WayCharacteristicsMapper
        extends DiObjectEntityToDtoMapper<Set<MainWayCharacteristicSectionEntity>, WayCharacteristicsDto> {

    /**
     * Переопределенный метод суперинтерфейса {@link DiObjectEntityToDtoMapper}, описывающий логику мэппинга сущностей
     * участков характеристик главного пути в DTO.
     *
     * @param characteristics сет entity-сущностей участков характеристик главного пути, полученный из
     *                        SpringJpa-репозитотрия
     * @return DTO, содержащий информацию о характеристиках пути в каком-либо месте главного хода
     */
    @Override
    default WayCharacteristicsDto toDto(Set<MainWayCharacteristicSectionEntity> characteristics) {
        return mapWayCharacteristicsHolderToDto(mapMainWayCharacteristicsSetToHolder(characteristics));
    }

    /**
     * Мэппит сет entity-сущностей участков характеристик станционного пути в DTO.
     *
     * @param characteristics сет entity-сущностей участков характеристик станционного пути, полученный из
     *                        SpringJpa-репозитотрия
     * @return DTO, содержащий информацию о характеристиках пути в каком-либо месте станционного пути
     */
    default WayCharacteristicsDto mapStationWayCharacteristicsToDto(Set<StationWayCharacteristicSectionEntity> characteristics) {
        return mapWayCharacteristicsHolderToDto(mapStationWayCharacteristicsSetToHolder(characteristics));
    }

    /**
     * Мэппит объекта класса {@link WayCharacteristicHolder}, содержащий данные о характеристиках пути из
     * entity-сущностей, полученных из базы данных в DTO.
     */
    WayCharacteristicsDto mapWayCharacteristicsHolderToDto(WayCharacteristicHolder holder);

    /**
     * Мэппит сет entity-сущностей участков характеристик главного пути в объект-хранилище всех характеристик.
     *
     * @param characteristics сет entity-сущностей участков характеристик главного пути, полученный из базы данных
     * @return объект-хранилище всех характеристик
     */
    private WayCharacteristicHolder mapMainWayCharacteristicsSetToHolder(
            Set<MainWayCharacteristicSectionEntity> characteristics) {
        WayCharacteristicHolder holder = new WayCharacteristicHolder();
        characteristics.forEach(characteristic -> {
            switch (characteristic) {
                case MainWayProfileSectionEntity profile -> holder.setSlope(profile.getSlope());
                case MainWayPlanSectionEntity plan -> {
                    holder.setStraight(plan.getStraight());
                    holder.setCurveSide(plan.getCurveLineSide());
                    holder.setRadius(plan.getRadius());
                    holder.setRailElevation(plan.getRailElevation());
                }
                case MainWaySpeedSectionEntity speed -> {
                    holder.setPassengerTrainSpeed(speed.getPassengerTrainSpeed());
                    holder.setFreightTrainSpeed(speed.getFreightTrainSpeed());
                }
                case MainWayTonnageSectionEntity tonnage -> {
                    holder.setPassedTonnageBeforeInstall(tonnage.getPassedTonnageBeforeInstall());
                    holder.setPassedTonnageAfterInstall(tonnage.getPassedTonnageAfterInstall());
                }
                case MainWayClassificationSectionEntity classification -> {
                    holder.setFreightIntensity(classification.getFreightIntensity());
                    holder.setLineClassSpecialization(classification.getLineClassSpecialization());
                    holder.setGroupClassCode(classification.getGroupClassCode());
                }
                case MainWayRailSectionEntity rail -> {
                    holder.setRailType(rail.getRailType());
                    holder.setRailCategory(rail.getRailCategory());
                    holder.setThermalHardening(rail.getThermalHardening());
                    holder.setWayType(rail.getWayType());
                    holder.setFactory(rail.getFactory());
                    holder.setFactoryYear(rail.getFactoryYear());
                    holder.setInstallationDate(rail.getInstallationDate());
                }
                case MainWayUnderrailSectionEntity underrail -> {
                    holder.setSleeperMaterial(underrail.getSleeperMaterial());
                    holder.setSleepersPerKm(underrail.getSleepersPerKm());
                    holder.setFasteningType(underrail.getFasteningType());
                    holder.setBallastType(underrail.getBallastType());
                    holder.setBallastHeight(underrail.getBallastHeight());
                }
                case MainWayRepairSectionEntity repair -> {
                    holder.setCapitalRepairDate(repair.getCapitalRepairYear());
                    holder.setCapitalRepairType(repair.getCapitalRepairType());
                    holder.setIntermediateRepairDate(repair.getIntermediateRepairYear());
                    holder.setIntermediateRepairType(repair.getIntermediateRepairType());
                }
                default -> {
                }
            }
        });
        return holder;
    }

    /**
     * Мэппит сет entity-сущностей участков характеристик станционного пути в объект-хранилище всех характеристик.
     *
     * @param characteristics сет entity-сущностей участков характеристик станционного пути, полученный из базы данных
     * @return объект-хранилище всех характеристик
     */
    private WayCharacteristicHolder mapStationWayCharacteristicsSetToHolder(
            Set<StationWayCharacteristicSectionEntity> characteristics) {
        WayCharacteristicHolder holder = new WayCharacteristicHolder();
        characteristics.forEach(characteristic -> {
            switch (characteristic) {
                case StationWayProfileSectionEntity profile -> {
                    holder.setSlope(profile.getSlope());
                    holder.setPassengerTrainSpeed(profile.getStationWaySection()
                            .getStationWay().getPassengerTrainSpeed());
                    holder.setFreightTrainSpeed(profile.getStationWaySection()
                            .getStationWay().getFreightTrainSpeed());
                    holder.setFreightIntensity(0.1f);
                    holder.setLineClassSpecialization("4Г");
                    holder.setGroupClassCode("4V");
                }
                case StationWayPlanSectionEntity plan -> {
                    holder.setStraight(plan.getStraight());
                    holder.setCurveSide(plan.getLineSide());
                    holder.setRadius(plan.getRadius());
                    holder.setRailElevation(0);
                }
                case StationWayTonnageSectionEntity tonnage -> {
                    holder.setPassedTonnageBeforeInstall(tonnage.getPassedTonnageBeforeInstall());
                    holder.setPassedTonnageAfterInstall(tonnage.getPassedTonnageAfterInstall());
                }
                case StationWayRailSectionEntity rail -> {
                    holder.setRailType(rail.getRailType());
                    holder.setRailCategory(rail.getRailCategory());
                    holder.setThermalHardening(rail.getThermalHardening());
                    holder.setWayType(rail.getWayType());
                    holder.setFactory(rail.getFactory());
                    holder.setFactoryYear(rail.getFactoryYear());
                    holder.setInstallationDate(rail.getInstallationDate());
                }
                case StationWayUnderrailSectionEntity underrail -> {
                    holder.setSleeperMaterial(underrail.getSleeperMaterial());
                    holder.setSleepersPerKm(underrail.getSleepersPerKm());
                    holder.setFasteningType(underrail.getFasteningType());
                    holder.setBallastType(underrail.getBallastType());
                    holder.setBallastHeight(underrail.getBallastHeight());
                }
                case StationWayRepairSectionEntity repair -> {
                    holder.setCapitalRepairDate(repair.getCapitalRepairYear());
                    holder.setCapitalRepairType(repair.getCapitalRepairType());
                    holder.setIntermediateRepairDate(repair.getIntermediateRepairYear());
                    holder.setIntermediateRepairType(repair.getIntermediateRepairType());
                }
                default -> {
                }
            }
        });
        return holder;
    }

    /** Класс для мэппинга, характеризующий объект-хранилище всей информации о характеристиках пути в конкретном месте,
     * полученную из базы данных в виде отдельных entity-сущностей участков характеристик.
     * <p>
     *     Мэппится в
     *     {@link ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.wayc.WayCharacteristicsDto}
     */
    @NoArgsConstructor
    @Getter
    @Setter
    class WayCharacteristicHolder {
        /** Уклон. */
        private Float slope;
        /** Флаг, определяющий, прямой участок или кривая. */
        private Boolean straight;
        /** Сторонность кривой (null для прямого участка). */
        private String curveSide;
        /** Радиус кривой (null для прямого участка). */
        private Integer radius;
        /** Возвышение рельса (null для прямого участка). */
        private Integer railElevation;
        /** Скорость движения пассажирских поездов. */
        private Integer passengerTrainSpeed;
        /** Скорость движения грузовых поездов. */
        private Integer freightTrainSpeed;
        /** Пропущенный тоннаж до укладки. */
        private Float passedTonnageBeforeInstall;
        /** Пропущенный тоннаж после укладки. */
        private Float passedTonnageAfterInstall;
        /** Грузонапряженность. */
        private Float freightIntensity;
        /** Класс и специализация линии. */
        private String lineClassSpecialization;
        /** Код группы пути. */
        private String groupClassCode;
        /** Тип рельсов. */
        private String railType;
        /** Категория рельсов. */
        private String railCategory;
        /** Тип термоупрочнения рельсов. */
        private String thermalHardening;
        /** Тип пути. */
        private String wayType;
        /** Завод-изготовитель рельсов. */
        private String factory;
        /** Год производства рельсов. */
        private String factoryYear;
        /** Дата укладки рельсов в путь. */
        private LocalDate installationDate;
        /** Материал шпал. */
        private String sleeperMaterial;
        /** Количество шпал на км. */
        private Integer sleepersPerKm;
        /** Тип рельсовых скреплений. */
        private String fasteningType;
        /** Тип балласта. */
        private String ballastType;
        /** Высота балласта. */
        private Integer ballastHeight;
        /** Год (и месяц) капитального ремонта. */
        private LocalDate capitalRepairDate;
        /** Тип капитального ремонта. */
        private String capitalRepairType;
        /** Год (и месяц) промежуточного ремонта. */
        private LocalDate intermediateRepairDate;
        /** Тип промежуточного ремонта. */
        private String intermediateRepairType;

    }

}