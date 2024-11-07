module diode.client {

    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.web;

    requires common.classes;
    requires tcd.docdatadtos;

    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.client.common;
    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.client.common.impl;
    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.client.p;
    exports ru.yappy.rzdengineerassistant.diobjectdataextractor.client.p.impl;

}