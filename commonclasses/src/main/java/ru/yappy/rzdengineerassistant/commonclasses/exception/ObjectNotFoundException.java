package ru.yappy.rzdengineerassistant.commonclasses.exception;

/**
 * Кастомное исключение, применяющееся в приложении вместо одноименного исключения ORM-фреймворка hibernate и
 * позволяющее вывести сообщение о ситуации, когда не удалось получить нужный объект из базы данных.
 */
public class ObjectNotFoundException extends RuntimeException {

    /**
     * Коструктор исключения для поиска объекта в базе данных по его идентификатору.
     *
     * @param objectType тип объекта, который пытались найти
     * @param objectId идентификатор объекта, по которому пытались найти объект
     */
    public ObjectNotFoundException(String objectType, Object objectId) {
        super("Не удалось найти в базе данных объект '" + objectType + "' с ид=" + objectId);
    }

    /**
     * Конструктор исключения для поиска объекта в базе данных по некоторым критериям выборки.
     *
     * @param objectType тип объекта, который пытались найти
     * @param criteriaDescription описание способа и данных, по которым пытались найти объект
     */
    public ObjectNotFoundException(String objectType, String criteriaDescription) {
        super("Не удалось найти в базе данных объект '" + objectType + "' со следующими критериями выборки: " +
                criteriaDescription);
    }

}