package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущность участка классификации главных путей железнодорожных направлений. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "main_classification_sections", schema = "wayc")
public class MainWayClassificationSectionEntity extends MainWayCharacteristicSectionEntity {
    /** Грузонапряженность участка главного пути железнодорожного направления. */
    @Column(name = "freight_intensity")
    private Float freightIntensity;
    /** Класс и специализация железнодорожной линии. Цифробуквенное значение, характеризующее класс (зависит от
     *  грузонапряженности и установленных скоростей) и специализацию (зависит от некоторых эксплуатационных
     *  показателей). Распоряжение ОАО "РЖД" от 04.03.2015 № 551р. */
    @Column(name = "line_class_specialization")
    private String lineClassSpecialization;
    /** Код группы главного железнодорожного пути. Значение состоящее из арабской и римской цифры (или буквы кириллицы),
     * характеризует размеры грузового движения (зависит от грузонапряженности и установленных скоростей). */
    @Column(name = "group_class_code")
    private String groupClassCode;

    /** Метод, возвращающий строковое представление участка классификации главного пути железнодорожного направления.
     *
     * @return строка вида "(грузонапряженность) / (класс и специализация) / (код группы), (направление),
     * (км.метр начала - км.метр конца)", например, "92.7 / 1Т / 1О, ТАМ - СЯМСК, 1249.115 - 1256.570". */
    @Override
    public String toString() {
        return freightIntensity + " / " + lineClassSpecialization + " / " + groupClassCode + ", " + super.toString();
    }

}