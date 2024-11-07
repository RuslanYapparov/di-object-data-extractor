package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import jakarta.persistence.*;
import lombok.*;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWayEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWaySectionEntity;

/** Класс, описывающий сущности рельсовых плетей, лежащих в главных путях железнодорожного направления, в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lashes_in_main_ways", schema = "rail")
public class MainWayLashEntity extends LashEntity {
    /** Участок железнодорожного направления, на котором расположена рельсовая плеть. */
    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name = "lash_main_section_id")
    private MainWaySectionEntity mainWaySection;
    /** Сущность главного пути железнодорожного направления, на котором расположена рельсовая плеть. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_way_id")
    private MainWayEntity mainWay;

    /** Метод, возвращающий строковое представление рельсовой плети, лежащей в главных путях железнодорожного направления.
     *
     * @return строка вида "плеть № (номер плети) (длина плети) м, (сторонность рельсовой нити) + нить, (номер пути) путь,
     * (направление), (км.метр начала - км.метр конца)",
     * например "плеть № 22Л 799,95 м, ЛЕВАЯ нить, 2 путь, ГДЕ-ЛИБО - ЗДЕСЯ, 4.099 - 4.801". */
    @Override
    public String toString() {
        return super.toString() + ", " + mainWay.getNumber() + " путь, " + mainWaySection;
    }

}