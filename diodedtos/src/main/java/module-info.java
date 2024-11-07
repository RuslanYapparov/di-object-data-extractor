open module diode.dtos {

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires com.fasterxml.jackson.annotation;
    requires spring.context;
    requires spring.core;
    requires spring.beans;

    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.adm;
    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.empl;
    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail;
    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr;
    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common;
    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm;
    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail;
    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.empl;
    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.infr;
    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.wayc;
    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location;
    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util;

}