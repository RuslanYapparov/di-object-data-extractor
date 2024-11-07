package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.mini;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl.EmployeeDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto;

import java.util.List;

/**
 * Класс DTO с данными, необходимыми для создания протокола отказа токопроводящего или изолирующего стыка.
 *
 * @param jointVariants массив DTO с минимальной информацией о стыках
 * @param diodeLocationDescription наименование перегона или станции
 * @param participants участники совещания, работники дистанции пути, так или иначе имеющие отношение к отказу
 */
public record JointFailureProtCreatingDataDto(JointMiniDto[] jointVariants,
                                              String diodeLocationDescription,
                                              List<EmployeeDto> participants) { }