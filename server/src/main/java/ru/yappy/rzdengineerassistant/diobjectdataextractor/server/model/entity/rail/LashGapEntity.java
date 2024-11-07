package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail;

import lombok.*;
import jakarta.persistence.*;

/** Класс, описывающий сущности уравнительных пролетов между рельсовыми плетями в базе данных. */
@Data
@Entity
@Table(name = "lash_gaps", schema = "rail")
public class LashGapEntity {
    /** Идентификатор уравнительного пролета. Первичный ключ в БД (тип данных - INTEGER). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lash_gap_id")
    private Long id;
    /** Идентификатор рельсовой плети до уравнительного пролета. */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lash_id_before")
    private LashEntity lashBefore;
    /** Идентификатор рельсовой плети после уравнительного пролета. */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lash_id_after")
    private LashEntity lashAfter;
    /** Количество рельсов уравнительного пролета (2 - для обычных, 4 - для пролетов с изолирующим стыком). */
    @Column(name = "amount_of_gap_rails")
    private Integer amountOfGapRails;

    /** Метод, возвращающий строковое представление уравнительного пролета.
     *
     * @return строка вида "уравнительный пролет между плетями (имя плети до, "-" если до пролета не плеть) и
     * (имя плети после, "-" если после пролета не плеть)", например "уравнительный пролет между плетями 25П и -".
     */
    @Override
    public String toString() {
        String lashNameBefore = (lashBefore == null) ? "-" : lashBefore.getName();
        String lashNameAfter = (lashAfter == null) ? "-" : lashAfter.getName();
        return "уравнительный пролет между плетями " + lashNameBefore + " и " + lashNameAfter;
    }

}