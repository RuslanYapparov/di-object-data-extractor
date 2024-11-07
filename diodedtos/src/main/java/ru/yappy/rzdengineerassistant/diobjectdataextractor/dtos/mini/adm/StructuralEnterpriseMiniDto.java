package ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationNameDto;

import java.util.Objects;

/** Абстрактный класс минимизированного DTO, содержащий общие поля для всех сущностей структурных предприятий
 * (подразделений). Определяет логику сериализации/десериализации объектов классов-наследников.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = WayMaintenanceDistanceMiniDto.class, name = "ПЧ"),
        @JsonSubTypes.Type(value = StationNameDto.class, name = "Станция")
})
public abstract class StructuralEnterpriseMiniDto {
    /** Идентификатор структурного предприятия (дистанция, станция). */
    protected final Long id;
    /** Наименование структурного предприятия. */
    protected final String name;

    protected StructuralEnterpriseMiniDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /** Геттер идентификатора структурного предприятия.
     *
     * @return числовой идентификатор структурного предприятия
     */
    public Long getId() {
        return id;
    }

    /** Геттер наименования структурного предприятия.
     *
     * @return строка с наименованием структурного предприятия
     */
    public String getName() {
        return name;
    }

    /** Сравнение двух объектов, используя их состояние (значения всех полей).
     *
     * @param o другой объект для сравнения
     * @return булевое значение, определяющее равенство объектов
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StructuralEnterpriseMiniDto that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    /** Получение хэш-кода объекта.
     *
     * @return числовое представление объекта на основании его состояния (значений всех полей)
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /** Получение строкового представления объекта.
     *
     * @return строка с названием класса объекта и описанием значений всех полей
     */
    @Override
    public String toString() {
        return "StructuralEnterpriseMiniDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}