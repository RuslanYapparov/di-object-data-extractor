package ru.yappy.rzdengineerassistant.diobjectdataextractor.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Запуск сервера для получения данных об объектах ДИ.
 */
@SpringBootApplication
public class DiObjectDataExtractorServer {

    /**
     * Запускает сервис для получения данных об объектах ДИ.
     *
     * @param args аргументы командной строки */
    public static void main(String[] args) {
        SpringApplication.run(DiObjectDataExtractorServer.class);
    }

}