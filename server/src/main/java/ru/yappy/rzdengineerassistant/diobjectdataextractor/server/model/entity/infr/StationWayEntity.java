package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.TelemechanicsServiceSectionEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.WayLinearSectionEntity;

/** Класс, описывающий сущности станционных путей в базе данных.
 * Является родительским для сущностей главных путей станции. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "station_not_main_ways", schema = "infr")
public class StationWayEntity extends AbstractStationWayEntity {
    /** Номер стрелочного перевода, с которого начинается станционный путь. */
    @Column(name = "starts_from_switch_with_name")
    private String startSwitch;
    /** Номер стрелочного перевода, которым заканчивается станционный путь. Значение null означает,
     * что станционный путь оканчивается упором */
    @Column(name = "ends_on_switch_with_name")
    private String endSwitch;
    /** Полезная длина станционного пути в метрах. */
    @Column(name = "useful_length")
    private Integer usefulLength;
    /** Установленная на станционном пути скорость движения пассажирских поездов. */
    @Column(name = "passenger_train_speed")
    private Integer passengerTrainSpeed;
    /** Установленная на станционном пути скорость движения грузовых поездов. */
    @Column(name = "freight_train_speed")
    private Integer freightTrainSpeed;
    /** Линейный участок дистанции пути, обслуживающий данный станционный путь. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_linear_section_id")
    private WayLinearSectionEntity wayLinearSection;
    /** Участок телемеханики дистанции сигнализации, централизации и блокировки,
     * обслуживающий данный станционный путь. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sh_service_section_id")
    private TelemechanicsServiceSectionEntity telemechanicsServiceSection;

    /** Метод, возвращающий строковое представление сущности неглавного станционного пути.
     *
     * @return строка вида "путь № (номер пути) станции (название станции)", например "путь №3 станции КОЕ-ГДЕНЬ". */
    @Override
    public String toString() {
        return String.format("путь № %s станции %s", name, station);
    }

}