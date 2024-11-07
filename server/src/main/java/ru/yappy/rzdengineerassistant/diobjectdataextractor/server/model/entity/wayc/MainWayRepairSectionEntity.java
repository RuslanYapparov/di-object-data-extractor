package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/** Класс, описывающий сущность участка проведения последних ремонтов главных путей железнодорожных направлений. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "main_repair_sections", schema = "wayc")
public class MainWayRepairSectionEntity extends MainWayCharacteristicSectionEntity {
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

    /** Метод, возвращающий строковое представление участка ремонтов главного пути железнодорожного направления.
     *
     * @return строка вида "(тип капитального ремонта)-(месяц.год) / (тип промежуточного ремонта)-(месяц.год),
     * (номер пути) путь, (направление), (км.метр начала - км.метр конца)",
     * например, "КРН-07.2016 / ППВ-10.2019, 2 путь, ТАМ - СЯМСК, 1249.115 - 1256.570". */
    @Override
    public String toString() {
        return capitalRepairType + "-" + capitalRepairYear.getMonthValue() + "." + capitalRepairYear.getYear() + " / " +
          WayCharacteristicToStringConverter.getIntermediateRepairInfo(intermediateRepairYear, intermediateRepairType) +
                ", " + super.toString();
    }

}