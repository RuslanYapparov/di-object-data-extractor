package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import lombok.*;
import jakarta.persistence.*;

/** Класс, описывающий сущности мостов, расположенных на главных путях, в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "main_bridges", schema = "infr")
public class MainWayBridgeEntity extends BridgeEntity {
    /** Главный путь, на котором расположен мост. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_way_id")
    private MainWayEntity mainWay;
    /** Участок пути железнодорожного направления, на котором расположен мост. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private MainWaySectionEntity section;

    /** Метод, возвращающий строковое представление моста, расположенного на главных путях.
     *
     * @return строка вида "(размер моста) (название моста), (направление), (км.метр начала - км.метр конца)",
     * например "СРЕДНИЙ МОСТ ЧЕРЕЗ Р. ЧУВАК 1 ПУТЬ, ТАМ - СЯМСК, 1234.514 - 1234.521". */
    @Override
    public String toString() {
        return super.toString() + ", " + section;
    }

}