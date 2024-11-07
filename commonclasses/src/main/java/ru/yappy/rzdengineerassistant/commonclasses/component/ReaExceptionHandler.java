package ru.yappy.rzdengineerassistant.commonclasses.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yappy.rzdengineerassistant.commonclasses.dto.ExceptionDto;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Абстрактный класс, содержащий дефолтные методы обработки выбрасываемых исключений для классов-потомков.
 */
public abstract class ReaExceptionHandler {
    /** Логгер для отметки выброшенных исключений в логах. */
    private static final Logger log = LoggerFactory.getLogger(ReaExceptionHandler.class);

    /**
     * Делает отметку в логе и возвращает информацию об исключении в виде DTO.
     *
     * @param exception исключение, выброшенное в ходе работы приложения
     * @return DTO с информацией об исключении
     */
    protected ExceptionDto handleException(Exception exception) {
        log.warn(exception.getMessage(), exception);
        return new ExceptionDto(LocalDateTime.now(),
                exception.getClass().getSimpleName(),
                exception.getMessage(),
                getDefaultExceptionDetails(exception.getStackTrace())
        );
    }

    /**
     * Подготавливает строку из первых 7 элементов стэктрэйса для записи в поле details объекта ExceptionDto.
     *
     * @param stackTrace стэктрэйс выброшенного исключения
     * @return строка из обозначения "(stack trace)" и первых 7 элементов стэктрэйса, разделенных символом новой строки
     */
    private String getDefaultExceptionDetails(StackTraceElement[] stackTrace) {
        StringBuilder details = new StringBuilder("(stack trace) ");
        Arrays.stream(stackTrace)
                .limit(7)
                .forEach(stackTraceLine -> {
                    String line = stackTraceLine.toString();
                    if (line.length() > 80) {
                        line = line.substring(0, 80) + " " + line.substring(80);
                    }
                    details.append(line).append(" ");
                });
        return details.toString();
    }

}