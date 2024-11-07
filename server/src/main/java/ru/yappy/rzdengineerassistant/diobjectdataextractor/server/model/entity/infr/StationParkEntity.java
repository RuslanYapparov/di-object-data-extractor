package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import lombok.*;
import jakarta.persistence.*;

/** Класс, описывающий сущность парка железнодорожной станции в базе данных. Опциональная сущность, так как
 * абсолютное большинство станций не подразделяется на парки. */
@Data
@ToString(onlyExplicitlyIncluded = true, includeFieldNames = false)
@Entity
@Table(name = "station_parks", schema = "infr")
public class StationParkEntity {
    /** Идентификатор парка железнодорожной станции. Первичный ключ (тип данных в БД - SMALLINT). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_park_id")
    private Long id;
    /** Станция, к которой относится парк. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private StationEntity station;
    /** Название парка железнодорожной станции. */
    @ToString.Include
    @Column(name = "park_name", nullable = false)
    private String name;

}