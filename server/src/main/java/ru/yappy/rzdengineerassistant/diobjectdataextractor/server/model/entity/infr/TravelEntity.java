package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;

/** Класс, описывающий сущность железнодорожного съезда (между стрелочными переводами) в базе данных. */
@Data
@Entity
@Table(name = "travels", schema = "infr")
public class TravelEntity {
    /** Идентификатор железнодорожного съезда. Первичный ключ (тип данных в БД - INTEGER). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_id")
    private Long id;
    /** Первый стрелочный перевод железнодорожного съезда. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_first_switch_id")
    private SwitchEntity firstSwitch;
    /** Второй стрелочный перевод железнодорожного съезда. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_last_switch_id")
    private SwitchEntity lastSwitch;
    /** Длина железнодорожного съезда. */
    @Column(name = "travel_length")
    private Integer length;
    /** Тип рельсов железнодорожного съезда. По умолчанию значение "Р65". */
    @Column(name = "rail_type")
    private String railType;
    /** Материал, из которого изготовлены шпалы железнодорожного съезда ("Ж/Б" и "ДЕРЕВ."). */
    @Column(name = "sleepers_material")
    private String sleepersMaterial;
    /** Тип материала балластной призмы железнодорожного съезда. */
    @Column(name = "ballast_type")
    private String ballastType;
    /** Дата укладки железнодорожного съезда. */
    @Column(name = "installation_date")
    private LocalDate installationDate;
    /** Пропущенный тоннаж по рельсошпальной решетке железнодорожного съезда до укладки. */
    @Column(name = "passed_tonnage_before_install")
    private Float passedTonnageBeforeInstall;
    /** Пропущенный тоннаж по рельсошпальной решетке железнодорожного съезда после укладки. */
    @Column(name = "passed_tonnage_after_install")
    private Float passedTonnageAfterInstall;
    /** Поле-флаг, показывающее, что железнодорожный съезд расположен на перегоне. */
    @Column(name = "is_interstation_track_travel")
    private Boolean isInterstationTrackTravel;

    /** Метод, возвращающий строковое представление железнодорожного съезда в случае, если указаны ограничивающие
     * стрелочные переводы.
     *
     * @return строка вида "съезд (номер первой стрелки)-(номер второй стрелки)", например "съезд 1-3".
     * Если съезд расположен на перегоне, то добавляется "(перегон)" в конце строки.
     * @throws IllegalStateException если не удалось выгрузить информацию о стрелочных переводах из базы данных. */
    @Override
    public String toString() {
        if (firstSwitch == null || lastSwitch == null) {
            throw new IllegalStateException("Ошибка чтения информации о стрелочных переводах, ограничивающих " +
                    "железнодорожный съезд, из базы данных.");
        }
        return String.format("съезд %s-%s" + (isInterstationTrackTravel ? " (перегон)" : ""), firstSwitch.getName(),
                lastSwitch.getName());
    }

}