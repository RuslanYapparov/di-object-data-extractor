package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.wayc;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;

/** Утилитарный класс содержащий простые статические методы для преобразования характеристик пути в строку
 * для использования в методах toString некоторых участков характеристик пути. */
@UtilityClass
public class WayCharacteristicToStringConverter {
    /** Метод, подготавливающий строку с типом продольного профиля в зависимости от значения уклона,
     * необходимую для метода toString().
     *
     * @param slope значение уклона продольного профиля.
     * @return строка с типом и значением уклона продольного профиля. */
    public String getProfileTypeBySlope(Float slope) {
        if (slope < 0.0) {
            return "спуск " + slope;
        } else if (slope > 0.0) {
            return "подъем +" + slope;
        } else {
            return "площадка 0.0";
        }
    }

    /** Метод, подготавливающий строку с типом плана в зависимости от значений полей.
     *
     * @param straight признак "прямой участок" или "кривая".
     * @param lineSide сторонность кривой (в случае, если straight=true, должен равняться null).
     * @param radius радиус кривой (в случае, если straight=true, должен равняться null).
     * @return строка вида "прямой участок" или "(сторонность) кривая радиусом (радиус) м", например
     * "ЛЕВАЯ кривая радиусом 1000 м". */
    public String getPlanType(Boolean straight, String lineSide, Integer radius) {
        return straight ? "прямой участок" : lineSide + " кривая радиусом " + radius + " м";
    }

    /** Метод, подготавливающий строку с типом промежуточного ремонта в зависимости от значений полей.
     *
     * @param intermediateRepairYear год проведения промежуточного ремонта.
     * @param intermediateRepairType тип проведенного промежуточного ремонта ("РИС", "СРЕДНИЙ РЕМОНТ", "ППВ" и др.).
     * @return строка вида "-" (если null) или "(тип промежуточного ремонта)-(месяц.год)", например, "ППВ-10.2019". */
    public String getIntermediateRepairInfo(LocalDate intermediateRepairYear, String intermediateRepairType) {
        return (intermediateRepairType == null) ? "-" : intermediateRepairType + "-" +
                intermediateRepairYear.getMonthValue() + "." + intermediateRepairYear.getYear();
    }

}