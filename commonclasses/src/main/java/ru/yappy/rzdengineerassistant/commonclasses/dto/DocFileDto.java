package ru.yappy.rzdengineerassistant.commonclasses.dto;

import java.util.Base64;

/**
 * Простой DTO для передачи массива байтов готового файла документа и имени файла.
 *
 * @param fileName имя файла
 * @param fileContent массив байтов готового файла
 */
public record DocFileDto(String fileName,
                         byte[] fileContent) {

    /** Переопределенный канонический конструктор DTO, кодирующий массив байтов с помощью Base64.
     *
     * @param fileContent массив байтов готового файла
     * @param fileName имя файла
     */
    public DocFileDto(String fileName, byte[] fileContent) {
        this.fileName = fileName;
        this.fileContent = Base64.getEncoder().encode(fileContent);
    }

    /**
     * Переопределенный геттер декодированного массива байтов.
     *
     * @return массив байтов готового файла
     */
    @Override
    public byte[] fileContent() {
        return Base64.getDecoder().decode(fileContent);
    }

}