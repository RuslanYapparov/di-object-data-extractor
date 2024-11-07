package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm.StructuralEnterpriseMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.empl.ManagerMiniDto;

import java.time.LocalDate;

/** Класс DTO, метаданные документа, содержит данные, характерные для документов всех типов.
 *
 * @param enterprise DTO структурного предприятия, издающего будущий документ {@link StructuralEnterpriseMiniDto}
 * @param signer минимизированный DTO руководителя, подписывающего документ {@link ManagerMiniDto}
 * @param date дата создания документа
 * @param performer DTO исполнителя документа
 */
public record DocMetaDataDto(StructuralEnterpriseMiniDto enterprise,
                             ManagerMiniDto signer,
                             @JsonFormat(pattern = "dd-MM-yyyy")
                             LocalDate date,
                             PerformerDto performer) {}