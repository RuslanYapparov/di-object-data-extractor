package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.empl.ManagerMiniDto;

import java.util.Objects;
import java.util.Set;

/**
 * Абстрактный класс полноценного DTO, содержащий общие поля для всех сущностей структурных предприятий
 * (подразделений). Определяет логику сериализации/десериализации объектов классов-наследников.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = WayMaintenanceDistanceDto.class, name = "ПЧ")
})
public abstract class StructuralEnterpriseDto {
    /** Идентификатор структурного предприятия. */
    private final Long id;
    /** Название структурного предприятия. */
    private final String name;
    /** Аббревиатура региональной дирекции, в состав которой входит структурное предприятие. */
    private final String regionalDirectorateAbbreviation;
    /** Сет DTO-объектов, содержащий информацию обо всех руководителях структурного предприятия. */
    private final Set<ManagerMiniDto> managers;

    /**
     * Конструктор суперкласса полноценного DTO сущности структурного предприятия, принимающий значения всех полей.
     *
     * @param id идентификатор структурного предприятия
     * @param name название структурного предприятия
     * @param regionalDirectorateAbbreviation аббревиатура региональной дирекции
     * @param managers сет DTO-объектов, содержащий информацию обо всех руководителях структурного предприятия
     */
    protected StructuralEnterpriseDto(Long id,
                                      String name,
                                      String regionalDirectorateAbbreviation,
                                      Set<ManagerMiniDto> managers) {
        this.id = id;
        this.name = name;
        this.regionalDirectorateAbbreviation = regionalDirectorateAbbreviation;
        this.managers = managers;
    }

    /**
     * Геттер идентификатора структурного предприятия.
     *
     * @return идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Геттер названия структурного предприятия.
     *
     * @return название
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер аббревиатуры региональной дирекции, в состав которой входит структурное предприятие.
     *
     * @return аббревиатура региональной дирекции
     */
    public String getRegionalDirectorateAbbreviation() {
        return regionalDirectorateAbbreviation;
    }

    /**
     * Геттер сета DTO-объектов, содержащего информацию обо всех руководителях структурного предприятия.
     *
     * @return сет с объектами, содержащими информацию о руководителях
     */
    public Set<ManagerMiniDto> getManagers() {
        return managers;
    }

    /** Сравнение двух объектов, используя их состояние (значения всех полей).
     *
     * @param o другой объект для сравнения
     * @return булевое значение, определяющее равенство объектов
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StructuralEnterpriseDto that)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(regionalDirectorateAbbreviation, that.regionalDirectorateAbbreviation) &&
                Objects.equals(managers, that.managers);
    }

    /** Получение хэш-кода объекта.
     *
     * @return числовое представление объекта на основании его состояния (значений всех полей)
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, regionalDirectorateAbbreviation, managers);
    }

}