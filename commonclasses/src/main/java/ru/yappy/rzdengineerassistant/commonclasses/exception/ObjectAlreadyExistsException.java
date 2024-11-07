package ru.yappy.rzdengineerassistant.commonclasses.exception;

/** Исключение, выбрасываемое в случае нарушения предусмотренного условия уникальности объекта. */
public class ObjectAlreadyExistsException extends RuntimeException {

    /** Конструктор исключения, принимающий текст сообщения для ситуации выбрасывания. */
    public ObjectAlreadyExistsException(String message) {
        super(message);
    }

}