package ru.yappy.rzdengineerassistant.commonclasses.exception;

/**
 * Исключение, выбрасываемое в случае ошибок при получении информации из внешних сервисов.
 */
public class ExternalServiceException extends RuntimeException {
    /** Конструктор исключения, принимающий название сервиса, код ошибки и сообщение, раскрывающее информацию об ошибке.
     *
     * @param serviceName название сервиса, приславшего ответ с ошибкой
     * @param exceptionStatusCode код ошибки, с котором пришел ошибочный ответ из внешнего сервиса
     * @param externalExceptionMessage сообщение, раскрывающее информацию об ошибке
     * */
    public ExternalServiceException(String serviceName, int exceptionStatusCode, String externalExceptionMessage) {
        super(String.format("При выполнении запроса к внешнему сервису '%s' получен ответ с кодом '%d' и " +
                "сообщением: \"%s\"", serviceName, exceptionStatusCode, externalExceptionMessage));
    }

}