package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl.EmployeeDto;

/**
 * Класс DTO, содержащий метаданные, характерные для каждого протокола.
 *
 * @param type тип протокола
 * @param protNumber порядковый номер протокола
 * @param techNumber технический номер документа, под которым зарегистрирован протокол
 * @param locality город или иной населенный пункт, в котором проходило совещание
 * @param participants данные о сотрудниках, участвующих в совещании, по которому составляется протокол
 * @param listeners обозначения должностей сотрудников, присутствовавших на совещании в качестве слушателей
 * @param speakerOrder список фамилий работников в порядке их докладов на совещании
 * @param executionControlEmployee данные о сотруднике, контролирующем исполнение протокола
 */
public record ProtMetaDataDto(ProtocolType type,
                              String protNumber,
                              String techNumber,
                              String locality,
                              EmployeeDto[] participants,
                              String listeners,
                              String speakerOrder,
                              EmployeeDto executionControlEmployee) {}