package ru.yappy.rzdengineerassistant.diobjectdataextractor.client.p;

import org.springframework.http.ResponseEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.LocationDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.mini.JointFailureProtCreatingDataDto;

/**
 * Интерфейс, определяющий тип и поведение всех объектов-клиентов, запрашивающих у сервера DiObjectDataExtractor
 * данные для создания протоколов отказов технических средств хозяйства пути.
 */
public interface DiodePProtFailureClient {

    /**
     * Отправляет запрос и обрабатывает ответ от сервера с информацией, необходимой для работы пользователя с данными
     * протокола отказа технических средств хозяйства пути в пользовательском интерфейсе.
     *
     * @param locationDto DTO с данными конкретного места, для которого будет получена нужная информация
     * @param jointType тип стыка для сбора информации
     * @return DTO c информацией для работы в пользовательском интерфейсе при создании протокола отказа рельсового
     * стыка - массив вариантов стыков определенного типа, список причастных работников, наименование локации
     */
    ResponseEntity<JointFailureProtCreatingDataDto> getJointFailureProtCreatingDataDto(LocationDto locationDto,
                                                                                       JointType jointType);

}