open module tcd.docdatadtos {

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires com.fasterxml.jackson.annotation;
    requires transitive ede.dtos;
    requires transitive diode.dtos;

    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.act;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.letter;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.workerlist;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.mini;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.delay;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.seasoninspection;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.techviolation;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.mini;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.culprit;
    exports ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.delay;

}