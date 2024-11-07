package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import jakarta.persistence.*;
import lombok.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationWayEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.StationWaySectionEntity;

/** Абстрактный класс, содержащий поля для привязки участков характеристик станционных путей железнодорожных станций. */
@Data
@Entity
@Table(name = "station_way_characteristic_sections", schema = "wayc")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class StationWayCharacteristicSectionEntity {
    /** Идентификатор участка характеристики станционного пути. Первичный ключ в таблице участков станционных путей
     * (infr.station_way_sections) и таблице характеристик пути (wayc). */
    @Id
    @Column(name = "sw_section_id")
    protected Long id;
    /** Привязка участка характеристики к участку станционного пути. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sw_section_id")
    protected StationWaySectionEntity stationWaySection;

    /** Метод, возвращающий строковое представление участка станционного пути в случае, если указана привязка
     * к станционному пути со всеми атрибутами.
     *
     * @return строка вида "путь № (номер ст. пути) станции (название станции), (метр начала)-(метр конца)",
     * например "путь № 4 станции ГДЕ-ЛИБО, 507-532".
     * @throws IllegalStateException если не удалось выгрузить информацию о станционном пути из базы данных */
    @Override
    public String toString() {
        if (stationWaySection == null || stationWaySection.getStationWay() == null) {
            throw new IllegalStateException("Ошибка чтения информации о станционном пути, на котором расположен " +
                    "рассматриваемый участок, из базы данных.");
        }
        StationWayEntity stationWay = stationWaySection.getStationWay();
        Integer startMeter = stationWaySection.getStartMeter();
        Integer endMeter = stationWaySection.getEndMeter();
        return String.format("%s, %d-%d", stationWay, startMeter, endMeter);
    }

}