package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.culprit;

import ru.yappy.rzdengineerassistant.employeedataextractor.dtos.AsutrEmployeeDto;

/**
 * Класс DTO, содержащего информацию о виновнике нарушения для использования при создании соответствующего документа.
 *
 * @param employee расширенная информация о сотруднике, совершившем рабочий проступок
 * @param punishment виды наказания, применяющиеся к сотруднику
 * @param violations что было нарушено, причина применения наказания
 * @param decreasedPunishment виды наказания, применяющиеся к сотруднику в случаях, когда наказание уменьшается из-за
 *                           каких-либо причин
 * @param decreaseReason причина уменьшения наказания
 * @param takenWarningCard тип изъятого предупредительного талона по безопасности движения поездов
 */
public record CulpritDto(AsutrEmployeeDto employee,
                         PunishmentDto[] punishment,
                         String violations,
                         PunishmentDto[] decreasedPunishment,
                         String decreaseReason,
                         WarningCardType takenWarningCard) {}