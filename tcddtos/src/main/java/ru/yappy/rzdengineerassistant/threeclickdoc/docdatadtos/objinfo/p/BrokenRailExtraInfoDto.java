package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * DTO-класс для хранения и передачи дополнительной информации об изломе рельса для создания характеристики места излома.
 *
 * @param railBreakDate дата случившегося излома
 * @param railTemp температура рельса в момент излома
 * @param airTemp температура воздуха в момент излома
 */
public record BrokenRailExtraInfoDto(@JsonFormat(pattern = "dd-MM-yyyy")
                                    LocalDate railBreakDate,
                                     Integer railTemp,
                                     Integer airTemp) {}