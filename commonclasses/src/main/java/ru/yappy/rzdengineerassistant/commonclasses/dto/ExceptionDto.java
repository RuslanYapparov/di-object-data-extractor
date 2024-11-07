package ru.yappy.rzdengineerassistant.commonclasses.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * DTO для передачи информации об исключении, возникшем в одном из сервисов приложения.
 *
 * @param timeStamp время, когда было выброшено исключение
 * @param exception тип исключения, простое название класса
 * @param message сообщение, предусмотренное при выбрасывании исключения
 * @param details дополнительная информация, касающаяся исключения
 */
public record ExceptionDto(@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SS") LocalDateTime timeStamp,
                           String exception,
                           String message,
                           String details) {
}