package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.culprit;

/**
 * Класс DTO, содержащий информацию о мере и значении наказания, применяющегося к работнику, совершившему проступок.
 *
 * @param type тип меры наказания
 * @param punishmentValue значение величины наказания, характерное для некоторых типов мер наказания
 */
public record PunishmentDto(PunishType type,
                            Float punishmentValue) {

    /**
     * Переопределенный канонический конструктор record-класса, содержащий логику соотнесения типа меры наказания и
     * значения ее величины.
     *
     * @param type тип меры наказания
     * @param punishmentValue значение величины наказания
     */
    public PunishmentDto(PunishType type, Float punishmentValue) {
        this.type = type;
        this.punishmentValue = switch (this.type) {
            case COST_COMPENSATION, AWARD_REDUCTION -> punishmentValue;
            case REPROACH -> 50.0f;
            case REPRIMAND, SUSPENSION_FROM_WORK -> 100.0f;
            default -> null;
        };
    }

}