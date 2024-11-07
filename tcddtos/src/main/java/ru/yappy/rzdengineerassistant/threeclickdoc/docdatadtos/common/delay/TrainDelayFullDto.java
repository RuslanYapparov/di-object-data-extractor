package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.delay;

/**
 * Класс DTO для хранения и передачи полной информации о задержке поезда.
 *
 * @param delay DTO c минимальной информацией о задержке
 * @param location локация, на которой произошла задержка поезда
 * @param trafficLight полное наименование светофора, сигнализировавшего красным огнем поезду
 * @param machinist фамилия или ФИО машиниста задержанного поезда
 * @param machinistDepot депо приписки задержанного поезда
 * @param locoModel тип (название модели) локомотива задержанного поезда
 * @param locoNumber номер локомотива задержанного поезда
 * @param locoDepot депо приписки локомотива задержанного поезда
 * @param trainWeight масса поезда брутто в тоннах
 * @param wagonAmount количество вагонов в поезде
 * @param axleAmount количество осей в поезде
 * @param isTrafficLightPassed флаг, отражающий проезд светофора с запрещающим показанием
 * @param isNearEntranceTrafficLight флаг, отмечающий, что задержка поезда произошла у входного светофора станции
 */
public record TrainDelayFullDto(TrainDelayDto delay,
                                String trafficLight,
                                String location,
                                String machinist,
                                String machinistDepot,
                                String locoModel,
                                String locoNumber,
                                String locoDepot,
                                Integer trainWeight,
                                Integer wagonAmount,
                                Integer axleAmount,
                                Boolean isTrafficLightPassed,
                                Boolean isNearEntranceTrafficLight) {}