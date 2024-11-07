package ru.yappy.rzdengineerassistant.commonclasses;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Predicate;

/**
 * Утилитарный класс для хранения константных значений некоторых данных и сущностей, используемых в проекте.
  */
public class ReaConstants {
    /** Float-значение со значением, после которого участок считается просроченным капитальным ремонтом. */
    public static final float OVERDUE_REPAIR_TONNAGE = 800.0f;

    /** Строка с дефолтным обозначением отсутствующих данных. */
    public static final String N_A = "[Нет сведений]";

    /** Строка с дефолтным обозначением ФИО человека, которые в дальнейшем необходимо самостоятельно заполнить
     *  пользователю. */
    public static final String FIO = "[Фамилия И.О.]";

    /** Строка с дефолтным обозначением названия дистанции, которое в дальнейшем необходимо самостоятельно заполнить
     * пользователю. */
    public static final String DISTANCE_NAME = "[Наименование дистанции]";

    /** Объект форматтера нецелочисленных чисел, оставляющий 2 знака после запятой. */
    public static final DecimalFormat floatFormat2;

    /** Объект форматтера нецелочисленных чисел, оставляющий 3 знака после запятой. */
    public static final DecimalFormat floatFormat3;

    /** Объект форматтера дат. */
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /** Объект форматтера даты и времени. */
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy в HH:mm");

    /** Реализация функционального интерфейса Predicate для проверки строк на не null и не на пустое значение. */
    public static final Predicate<String> stringNotNullAndBlank = string -> string != null && !string.isBlank();

    /** Реализация функционального интерфейса Predicate для проверки строк на null и на пустое значение. */
    public static final Predicate<String> stringNullOrBlank = string -> string == null || string.isBlank();

    /** Приватная константа, определяющая запятую как знак, отделяющий целую и дробную часть нецелых чисел. */
    private static final DecimalFormatSymbols decimalFormatSymbols;

    // Статический блок для инициализации форматтеров нецелых чисел.
    static {
        decimalFormatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
        decimalFormatSymbols.setDecimalSeparator(',');
        floatFormat2 = new DecimalFormat("#0.00", decimalFormatSymbols);
        floatFormat3 = new DecimalFormat("#0.000", decimalFormatSymbols);
    }

}