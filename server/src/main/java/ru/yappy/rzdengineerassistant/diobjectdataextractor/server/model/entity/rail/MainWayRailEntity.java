package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import lombok.*;
import jakarta.persistence.*;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWayEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWaySectionEntity;

/** Класс, описывающий сущности рельсов, лежащих в главных путях железнодорожного направления, в базе данных. */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "rails_in_main_ways", schema = "rail")
public class MainWayRailEntity extends RailEntity {
    /** Участок железнодорожного направления, на котором расположен рельс. */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rail_main_section_id")
    private MainWaySectionEntity mainWaySection;
    /** Сущность главного пути железнодорожного направления, на котором расположен рельс. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_way_id")
    private MainWayEntity mainWay;

    /** Метод, возвращающий строковое представление рельса, лежащего в главных путях железнодорожного направления.
     *
     * @return строка вида "рельс (длина) м, (сторонность) нить, (вид рельсового элемента), (номер пути) путь,
     * (направление), (км.метр начала - км.метр конца)",
     * например "рельс 12.52 м, ЛЕВАЯ нить, УРАВНИТЕЛЬНАЯ РУБКА, 2 путь, ГДЕ-ЛИБО - ЗДЕСЯ, 11.542 - 11.554".
     */
    @Override
    public String toString() {
        return super.toString() + ", " + mainWay.getNumber() + " путь, " + mainWaySection;
    }

}