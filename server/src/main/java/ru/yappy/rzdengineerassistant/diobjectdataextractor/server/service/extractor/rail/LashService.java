package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail;

import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.LashEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.rail.RailEntity;

/**
 * Интерфейс, определяющий тип и поведение всех объектов-сервисов, работающих с сущностями рельсовых плетей.
 */
public interface LashService {

    /**
     * Получает entity-объект рельсовой плети по entity-объекту рельса, имеющим признак вида ВСП railKind="ПЛЕТЬ" и
     * являющемуся частью рельсовой плети.
     *
     * @param railEntity entity-объект рельса в составе рельсовой плети
     * @return entity-объект рельсовой плети
     */
    LashEntity getLashEntityByRailEntity(RailEntity railEntity);

}