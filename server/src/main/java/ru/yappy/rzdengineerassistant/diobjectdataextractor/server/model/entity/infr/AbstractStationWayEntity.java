package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import lombok.*;
import jakarta.persistence.*;

/** Абстрактный класс, содержащий поля, характерные для всех станционных путей (главных и неглавных) в базе данных. */
@Data
@Entity
@Table(name = "station_ways", schema = "infr")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractStationWayEntity {
    /** Идентификатор станционного пути. Первичный ключ в БД (тип данных - INTEGER). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_way_id")
    protected Long id;
    /** Поле для связи с сущностью станции, к которой относится станционный путь. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    protected StationEntity station;
    /** Парк, к которому относится станционный путь. В большинстве случаев значение null */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_park_id")
    protected StationParkEntity stationPark;
    /** Название (номер) станционного пути.
     * Имеет тип данных в БД VARCHAR, т.к. может содержать буквы, например "путь № 3А-Г". */
    @Column(name = "station_way_name")
    protected String name;
    /** Полное название станционного пути. По умолчанию - null, заполняется,
     * если у станционного пути есть длинное название, например "путь № 2 Гальваник-Сервис". */
    @Column(name = "station_way_full_name")
    protected String fullName;
    /** Назначение станционного пути ("ГЛАВНЫЙ", "ПРИМЕООТПРАВОЧНЫЙ", "ПОГРУЗОЧНО-ВЫГРУЗОЧНЫЙ" и др.). */
    @Column(name = "appointment")
    protected String appointment;
    /** Полная длина станционного пути в метрах. Для главных путей - участок от входного сигнала до знака
     * "Граница станции" или дополнительного входного сигнала в противоположной горловине станции. */
    @Column(name = "full_length")
    protected Integer fullLength;

    /** Метод, возвращающий строковое представление сущности станционного пути.
     *
     * @return строка вида "путь № (номер пути) станции (название станции)", например "путь №3 станции КОЕ-ГДЕНЬ"
     */
    @Override
    public String toString() {
        return String.format("путь № %s станции %s", name, station);
    }

}