package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/** Класс, описывающий сущность участка проведения последних ремонтов станционных путей железнодорожных станций. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "station_repair_sections", schema = "wayc")
public class StationWayRepairSectionEntity extends StationWayCharacteristicSectionEntity {
    /** Дата (месяц и год) проведения последнего капитального ремонта главного пути железнодорожного направления. */
    @Column(name = "capital_repair_year")
    private LocalDate capitalRepairYear;
    /** Тип капитального ремонта ("СТРОИТЕЛЬСТВО", "РЕКОНСТРУКЦИЯ", "КРН", "КРС" и др.). */
    @Column(name = "capital_repair_type")
    private String capitalRepairType;
    /** Дата (месяц и год) проведения последнего промежуточного ремонта главного пути железнодорожного направления. */
    @Column(name = "intermediate_repair_year")
    private LocalDate intermediateRepairYear;
    /** Тип промежуточного ремонта ("РИС", "СРЕДНИЙ РЕМОНТ", "ППВ" и др.). */
    @Column(name = "intermediate_repair_type")
    private String intermediateRepairType;

    /** Метод, возвращающий строковое представление участка ремонтов станционного пути железнодорожной станции.
     *
     * @return строка вида "(тип капитального ремонта)-(месяц.год) / (тип промежуточного ремонта)-(месяц.год),
     * путь № (номер ст. пути) станции (название станции), (метр начала)-(метр конца)",
     * например, "КРН-07.2016 / -, путь № 4 станции ГДЕ-ЛИБО, 507-532"". */
    @Override
    public String toString() {
        return capitalRepairType + "-" + capitalRepairYear.getMonthValue() + "." + capitalRepairYear.getYear() + " / " +
           WayCharacteristicToStringConverter.getIntermediateRepairInfo(intermediateRepairYear, intermediateRepairType) +
                ", " + super.toString();
    }

}