package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущность участка пропущенного тоннажа главных путей железнодорожных направлений. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "main_tonnage_sections", schema = "wayc")
public class MainWayTonnageSectionEntity extends MainWayCharacteristicSectionEntity {
    /** Количество миллионов тонн груза брутто, пропущенное по рельсошпальной решетке до установки на данном участке
     * главного пути железнодорожного направления  */
    @Column(name = "passed_tonnage_before_install")
    private Float passedTonnageBeforeInstall;
    /** Количество миллионов тонн груза брутто, пропущенное по рельсошпальной решетке после установки на данном участке
     * главного пути железнодорожного направления  */
    @Column(name = "passed_tonnage_after_install")
    private Float passedTonnageAfterInstall;

    /** Метод, возвращающий строковое представление участка пропущенного тоннажа железнодорожного направления.
     *
     * @return строка вида "тоннаж (до укладки)/(после), (номер пути) путь, (направление), (км.метр начала -
     * км.метр конца)", например, "тоннаж 850.7/2.9, 1 путь, ГДЕ-ЛИБО - ЗДЕСЯ, 7.157 - 7.400". */
    @Override
    public String toString() {
        return "тоннаж " + passedTonnageBeforeInstall + "/" + passedTonnageAfterInstall + ", " + super.toString();
    }

}