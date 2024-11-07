package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.infr;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;

/** Абстрактный класс, содержащий основную информацию о стрелочных переводах.
 * Имеет субклассы для станционных и перегонных стрелочных переводов. */
@Data
@Entity
@Table(name = "switches", schema = "infr")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class SwitchEntity {
    /** Идентификатор стрелочного перевода. Первичный ключ (тип данных в БД - INTEGER). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "switch_id")
    protected Long id;
    /** Название (номер) стрелочного перевода.
     * Имеет тип данных в БД VARCHAR, т.к. может содержать буквы, например "стр. № 5А". */
    @Column(name = "switch_name")
    protected String name;
    /** Полное название стрелочного перевода. По умолчанию - null, заполняется,
     * если у стрелочного перевода есть длинное название, например "стр. № 2 Металл-Комбинат". */
    @Column(name = "switch_full_name")
    protected String fullName;
    /** Проект стрелочного перевода, определяет материал стрелочных брусьев, их эпюру, геометрические размеры
     * и другие характеристики стрелочного перевода. */
    @Column(name = "switch_project")
    protected String project;
    /** Тип рельсов стрелочного перевода. Значение по умолчанию - "Р65". */
    @Column(name = "rail_type")
    protected String railType;
    /** Материал стрелочных брусьев стрелочного перевода ("Ж/Б" или "ДЕРЕВ."). */
    @Column(name = "beams_material")
    protected String beamsMaterial;
    /** Тип материала балластной призмы стрелочного перевода. */
    @Column(name = "ballast_type")
    protected String ballastType;
    /** Марка крестовины стрелочного перевода. */
    @Column(name = "cross_marking")
    protected String crossMarking;
    /** Тип управления стрелочного перевода ("ЭЦ" или "РУЧН"). */
    @Column(name = "control_type")
    protected String controlType;
    /** Сторонность стрелочного перевода ("ЛЕВЫЙ" или "ПРАВЫЙ"). */
    @Column(name = "line_side")
    protected String lineSide;
    /** Ширина колеи, на которую "зашит" стрелочный перевод. */
    @Column(name = "gauge")
    protected Integer gauge;
    /** Дата укладки стрелочного перевода. */
    @Column(name = "installation_date")
    protected LocalDate installationDate;
    /** Пропущенный тоннаж по стрелочному переводу до укладки. */
    @Column(name = "passed_tonnage_before_install")
    protected Float passedTonnageBeforeInstall;
    /** Пропущенный тоннаж по стрелочному переводу после укладки. */
    @Column(name = "passed_tonnage_after_install")
    protected Float passedTonnageAfterInstall;
    /** Длина закрестовинной кривой стрелочного перевода. По умолчанию значение 0, что означает отсутствие кривой. */
    @Column(name = "outcross_curve_length")
    protected Integer outcrossCurveLength;

    /** Метод, возвращающий строковое представление сущности стрелочного перевода.
     *
     * @return строка вида "стр. №  + (номер(название) стрелочного перевода)". */
    @Override
    public String toString() {
        return "стр. № " + name;
    }

}