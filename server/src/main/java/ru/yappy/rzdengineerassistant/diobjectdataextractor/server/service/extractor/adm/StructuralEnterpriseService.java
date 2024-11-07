package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.adm;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm.StructuralEnterpriseDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm.StructuralEnterpriseMiniDto;

import java.util.List;

/**
 * Интерфейс, определяющий тип и поведение всех объектов-сервисов, работающих с сущностями структурных подразделений.
 * Имплементируется для структурных подразделений каждого хозяйства ОАО "РЖД".
 */
public interface StructuralEnterpriseService {

    /**
     * Получает из БД и мэппит в DTO-объект полную информацию о структурном подразделении по его идентификатору.
     *
     * @param id идентификатор структурного подразделения
     * @return DTO, содержащий исчерпывающую информацию о структурном подразделении
     */
    StructuralEnterpriseDto getFullStructuralEnterpriseInfoById(Long id);

    /**
     * Получает из БД и мэппит в сет DTO минимальную информацию о структурных подразделениях определенного
     * в имплементирующем классе хозяйства по аббревиатуре региональной дирекции, в состав которой они входят.
     *
     * @param directorateAbbreviation аббревиатура региональной дирекции, ее идентификатор в БД
     * @return сет DTO с минимальной информации о структурных подразделениях
     */
    List<? extends StructuralEnterpriseMiniDto> getAllStructuralEnterprisesMiniInfoByDirectorateAbbreviation(
            String directorateAbbreviation);

}