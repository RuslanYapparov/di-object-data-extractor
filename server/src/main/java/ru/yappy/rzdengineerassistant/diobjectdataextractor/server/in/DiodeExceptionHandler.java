package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.in;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yappy.rzdengineerassistant.commonclasses.component.ReaExceptionHandler;
import ru.yappy.rzdengineerassistant.commonclasses.dto.ExceptionDto;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;

/**
 * Класс SpringBoot-обработчика исключений, выброшенных в ходе работы сервиса DiObjectDataExtractor.
 */
@RestControllerAdvice
public class DiodeExceptionHandler extends ReaExceptionHandler {

    /**
     * Обрабатывает исключения неприемлемого состояния (IllegalStateException), выброшенные в ходе работы сервиса.
     *
     * @param exception IllegalState-исключение выброшенное в ходе работы приложения
     * @return DTO c информацией об исключении
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleIllegalStateException(IllegalStateException exception) {
        return handleException(exception);
    }

    /**
     * Обрабатывает исключения неверного аргумента метода (IllegalArgumentException), выброшенные в ходе работы сервиса.
     *
     * @param exception IllegalArgument-исключение выброшенное в ходе работы приложения
     * @return DTO c информацией об исключении
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleIllegalArgumentException(IllegalArgumentException exception) {
        return handleException(exception);
    }

    /**
     * Обрабатывает исключения ненайденного объекта (ObjectNotFoundException), выброшенные в ходе работы сервиса.
     *
     * @param exception ObjectNotFound-исключение выброшенное в ходе работы приложения
     * @return DTO c информацией об исключении
     */
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleObjectNotFoundException(ObjectNotFoundException exception) {
        return handleException(exception);
    }

    /**
     * Обрабатывает все Runtime-исключения, выброшенные в ходе работы сервиса, для которых не реализована отдельная
     * обработка в данном классе.
     *
     * @param exception Runtime-исключение выброшенное в ходе работы приложения, для которого не реализована обработка
     * @return DTO c информацией об исключении
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleRuntimeException(RuntimeException exception) {
        return handleException(exception);
    }

}