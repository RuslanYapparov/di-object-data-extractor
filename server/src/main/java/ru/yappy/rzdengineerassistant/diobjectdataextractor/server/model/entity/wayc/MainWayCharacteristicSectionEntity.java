package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import lombok.*;
import jakarta.persistence.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWayEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.MainWaySectionEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr.TransportDirectionEntity;

/** Абстрактный класс, содержащий поля для привязки участков характеристик главных путей железнодорожных направлений. */
@Data
@Entity
@Table(name = "main_way_characteristic_sections", schema = "wayc")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MainWayCharacteristicSectionEntity {
    /** Идентификатор участка характеристики главного пути железнодорожного направления. Первичный ключ в таблице
     * участков железнодорожных направлений (infr.transport_direction_sections) и таблице характеристик пути (wayc). */
    @Id
    @Column(name = "main_way_section_id")
    protected Long id;
    /** Привязка участка характеристики к участку железнодорожного направления. */
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_way_section_id")
    protected MainWaySectionEntity mainWaySection;
    /** Привязка участка характеристики к главному пути железнодорожного направления. */
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_way_id")
    protected MainWayEntity mainWay;

    /** Метод, возвращающий строковое представление участка железнодорожного направления в случае если указаны главный
     * путь и километр начала участка.
     *
     * @return строка вида "(номер пути) путь, (направление), (км.метр начала - км.метр конца)",
     * например "1 путь, ТАМ - СЯМСК, 1234.514 - 1234.521".
     * @throws IllegalStateException если не удалось выгрузить информацию об участке из базы данных. */
    @Override
    public String toString() {
        if (mainWay == null || mainWaySection == null || mainWaySection.getStartKm() == null) {
            throw new IllegalStateException("Ошибка чтения значений координат участка железнодорожного направления " +
                    "из базы данных.");
        }
        TransportDirectionEntity transportDirection = mainWay.getTransportDirection();
        String sectionPresentation = getSectionPresentation();
        return (transportDirection == null) ? sectionPresentation :
                transportDirection.getName() + ", " + sectionPresentation;
    }

    /** Метод, подготавливающий строку с координатами, необходимую для метода toString().
     *
     * @return строка вида "(номер пути) путь, (км.метр начала - км.метр конца)",
     * например "1 путь, 1234.567 - 1234.890". */
    private String getSectionPresentation() {
        Integer wayNumber = mainWay.getNumber();
        Integer startKm = mainWaySection.getStartKm().getKm();
        Integer startMeter = mainWaySection.getStartMeter();
        Integer endKm = mainWaySection.getEndKm();
        Integer endMeter = mainWaySection.getEndMeter();
        return wayNumber + " путь, " + startKm + "." + startMeter + " - " + endKm + "." + endMeter;
    }

}