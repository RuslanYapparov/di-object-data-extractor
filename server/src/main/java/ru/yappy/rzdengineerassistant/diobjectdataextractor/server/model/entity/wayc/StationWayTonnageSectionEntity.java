package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущность участка пропущенного тоннажа станционных путей железнодорожных станций. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "station_tonnage_sections", schema = "wayc")
public class StationWayTonnageSectionEntity extends StationWayCharacteristicSectionEntity {
    /** Количество миллионов тонн груза брутто, пропущенное по рельсошпальной решетке до установки на данном участке
     * станционного пути */
    @Column(name = "passed_tonnage_before_install")
    private Float passedTonnageBeforeInstall;
    /** Количество миллионов тонн груза брутто, пропущенное по рельсошпальной решетке после установки на данном участке
     * станционного пути */
    @Column(name = "passed_tonnage_after_install")
    private Float passedTonnageAfterInstall;

    /** Метод, возвращающий строковое представление участка пропущенного тоннажа станционного пути.
     *
     * @return строка вида "тоннаж (до укладки)/(после), путь № (номер ст. пути) станции (название станции),
     * (метр начала)-(метр конца)", например "тоннаж 750.3/2.2, путь № 4 станции ГДЕ-ЛИБО, 507-532". */
    @Override
    public String toString() {
        return "тоннаж " + passedTonnageBeforeInstall + "/" + passedTonnageAfterInstall + ", " + super.toString();
    }

}