package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import lombok.*;
import jakarta.persistence.*;

/** Класс, описывающий сущности участков железнодорожных направлений (главных путей) в базе данных. */
@Data
@Entity
@Table(name = "transport_direction_sections", schema = "infr")
public class MainWaySectionEntity {
    /** Идентификатор участка железнодорожного направления. Первичный ключ (тип данных в БД - BIGINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Long id;
    /** Километр, на котором расположено начало участка. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "transport_direction_id", referencedColumnName = "transport_direction_id"),
                  @JoinColumn(name = "start_km", referencedColumnName = "km")})
    private KilometerEntity startKm;
    /** Метр километра, на котором расположено начало участка. */
    @Column(name = "start_meter_of_km")
    private Integer startMeter;
    /** Километр, на котором расположен конец участка. */
    @Column(name = "end_km")
    private Integer endKm;
    /** Метр километра, на котором расположен конец участка. */
    @Column(name = "end_meter_of_km")
    private Integer endMeter;
    /** Длина участка. */
    @Column(name = "section_length")
    private Integer length;

    /** Метод, возвращающий строковое представление участка железнодорожного направления в случае, если указан
     * километр начала.
     *
     * @return строка вида "(направление), (км.метр начала - км.метр конца)",
     * например "ТАМ - СЯМСК, 1234.514 - 1234.521".
     * @throws IllegalStateException если километр начала не указан. */
    @Override
    public String toString() {
        if (startKm == null) {
            throw new IllegalStateException("Ошибка чтения значений координат участка железнодорожного направления " +
                    "из базы данных.");
        }
        TransportDirectionEntity transportDirection = startKm.getTransportDirection();
        String sectionPresentation = startKm.getKm() + "." + startMeter + " - " + endKm + "." + endMeter;
        return (transportDirection == null) ? sectionPresentation : transportDirection.getName() +
                ", " + sectionPresentation;
    }

}