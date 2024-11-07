package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import jakarta.persistence.*;
import lombok.*;

/** Класс, описывающий сущности туннелей, расположенных на главных путях, в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "main_tunnels", schema = "infr")
public class MainWayTunnelEntity extends TunnelEntity {
    /** Главный путь, на котором расположен туннель. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_way_id")
    private MainWayEntity mainWay;
    /** Участок пути железнодорожного направления, на котором расположен туннель. */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private MainWaySectionEntity section;

    /** Метод, возвращающий строковое представление туннеля, расположенного на главных путях.
     *
     * @return строка вида "(размер туннеля) (название туннеля), (направление), (км.метр начала - км.метр конца)",
     * например "СРЕДНИЙ ТОННЕЛЬ ЧЕРЕЗ Г. БАЛДА 2 ПУТЬ, ТАМ - СЯМСК, 1267.273 - 1267.349". */
    @Override
    public String toString() {
        return super.toString() + ", " + section;
    }

}