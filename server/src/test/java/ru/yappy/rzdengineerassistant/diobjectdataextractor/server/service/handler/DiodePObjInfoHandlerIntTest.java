package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.MainWayRailDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.RailDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.StationWayRailDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.wayc.WayCharacteristicsDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common.DocType;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.JointInfoDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.PObjectInfoType;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.RailInfoDto;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DiodePObjInfoHandlerIntTest {
    private final DiodePObjInfoHandler diodePObjInfoHandler;

    @Autowired
    public DiodePObjInfoHandlerIntTest(DiodePObjInfoHandler diodePObjInfoHandler) {
        this.diodePObjInfoHandler = diodePObjInfoHandler;
    }

    @Test
    public void handleRailInfoRequest_whenGetCorrectInterstationTrackRailParameters_thenReturnCorrectRailInfoDto() {
        RailInfoDto handledRailInfoDto = diodePObjInfoHandler.handleRailInfoRequest(4547L, WayType.INTERSTATION_TRACK);

        assertThat(handledRailInfoDto).isNotNull();
        assertThat(handledRailInfoDto.getLocationDescription()).isEqualTo("КОЕ-ГДЕНЬ - ГДЕ-ЛИБО");
        assertThat(handledRailInfoDto.getDocType()).isEqualTo(DocType.OBJ_INFO);
        assertThat(handledRailInfoDto.getDocSubType()).isEqualTo(PObjectInfoType.P_OBJ_INFO_RAIL);
        assertThat(handledRailInfoDto.getBrokenRailExtraInfo()).isNull();
        assertThat(handledRailInfoDto.getMetaData()).isNull();

        RailDto railDto = handledRailInfoDto.getRail();

        assertThat(railDto).isNotNull();
        assertThat(railDto.getId()).isEqualTo(4547L);
        assertThat(railDto.getRailKind()).isEqualTo("ПЛЕТЬ");
        assertThat(railDto.getLineSide()).isEqualTo("ЛЕВАЯ");
        assertThat(railDto.getRollingDate()).isEqualTo("VII-2018");
        assertThat(railDto.getRailSwitch()).isNull();
        assertThat(railDto.getRailLash()).isNotNull();
        assertThat(railDto).isInstanceOf(MainWayRailDto.class);

        MainWayRailDto mainWayRailDto = (MainWayRailDto) railDto;

        assertThat(mainWayRailDto.getMainWayNumber()).isEqualTo(2);
        assertThat(mainWayRailDto.getMainWaySection().startKm()).isEqualTo(1247);
        assertThat(mainWayRailDto.getMainWaySection().startMeter()).isEqualTo(183);
        assertThat(mainWayRailDto.getMainWaySection().transportDirection().name()).isEqualTo("ТАМ - СЯМСК");

        WayCharacteristicsDto wayc = handledRailInfoDto.getWayCharacteristics();

        assertThat(wayc).isNotNull();
        assertThat(wayc.slope()).isEqualTo(1.5f);
        assertThat(wayc.curveSide()).isNull();
        assertThat(wayc.lineClassSpecialization()).isEqualTo("1Т");
        assertThat(wayc.capitalRepairDate()).isEqualTo(LocalDate.of(2019, 6, 1));
    }

    @Test
    public void handleRailInfoRequest_whenGetCorrectStationMainWayRailParameters_thenReturnCorrectRailInfoDto() {
        RailInfoDto handledRailInfoDto = diodePObjInfoHandler.handleRailInfoRequest(8038L, WayType.STATION_MAIN_WAY);

        assertThat(handledRailInfoDto).isNotNull();
        assertThat(handledRailInfoDto.getLocationDescription()).isEqualTo("ЗДЕСЯ");
        assertThat(handledRailInfoDto.getDocType()).isEqualTo(DocType.OBJ_INFO);
        assertThat(handledRailInfoDto.getDocSubType()).isEqualTo(PObjectInfoType.P_OBJ_INFO_RAIL);
        assertThat(handledRailInfoDto.getBrokenRailExtraInfo()).isNull();
        assertThat(handledRailInfoDto.getMetaData()).isNull();

        RailDto railDto = handledRailInfoDto.getRail();

        assertThat(railDto).isNotNull();
        assertThat(railDto.getId()).isEqualTo(8038L);
        assertThat(railDto.getRailKind()).isEqualTo("СТРЕЛОЧНЫЙ ПЕРЕВОД");
        assertThat(railDto.getLineSide()).isEqualTo("ПРАВАЯ");
        assertThat(railDto.getRollingDate()).isEqualTo("V-1997");
        assertThat(railDto.getPassedTonnageBeforeInstall()).isEqualTo(690.2f);
        assertThat(railDto.getRailSwitch()).isNotNull();
        assertThat(railDto.getRailSwitch().getId()).isEqualTo(39L);
        assertThat(railDto.getRailLash()).isNull();
        assertThat(railDto).isInstanceOf(MainWayRailDto.class);

        MainWayRailDto mainWayRailDto = (MainWayRailDto) railDto;

        assertThat(mainWayRailDto.getMainWayNumber()).isEqualTo(1);
        assertThat(mainWayRailDto.getMainWaySection().startKm()).isEqualTo(13);
        assertThat(mainWayRailDto.getMainWaySection().startMeter()).isEqualTo(902);
        assertThat(mainWayRailDto.getMainWaySection().transportDirection().name()).isEqualTo("ГДЕ-ЛИБО - ЗДЕСЯ");

        WayCharacteristicsDto wayc = handledRailInfoDto.getWayCharacteristics();

        assertThat(wayc).isNotNull();
        assertThat(wayc.slope()).isEqualTo(-0.9f);
        assertThat(wayc.curveSide()).isNull();
        assertThat(wayc.lineClassSpecialization()).isEqualTo("4Г");
        assertThat(wayc.capitalRepairDate()).isEqualTo(LocalDate.of(2013, 7, 1));
    }

    @Test
    public void handleRailInfoRequest_whenGetCorrectStationWayRailParameters_thenReturnCorrectRailInfoDto() {
        RailInfoDto handledRailInfoDto = diodePObjInfoHandler.handleRailInfoRequest(9870L, WayType.STATION_WAY);

        assertThat(handledRailInfoDto).isNotNull();
        assertThat(handledRailInfoDto.getLocationDescription()).isNull();
        assertThat(handledRailInfoDto.getDocType()).isEqualTo(DocType.OBJ_INFO);
        assertThat(handledRailInfoDto.getDocSubType()).isEqualTo(PObjectInfoType.P_OBJ_INFO_RAIL);
        assertThat(handledRailInfoDto.getBrokenRailExtraInfo()).isNull();
        assertThat(handledRailInfoDto.getMetaData()).isNull();

        RailDto railDto = handledRailInfoDto.getRail();

        assertThat(railDto).isNotNull();
        assertThat(railDto.getId()).isEqualTo(9870L);
        assertThat(railDto.getRailKind()).isEqualTo("ЗВЕНЬЕВОЙ ПУТЬ");
        assertThat(railDto.getLineSide()).isEqualTo("ПРАВАЯ");
        assertThat(railDto.getRollingDate()).isEqualTo("X-1990");
        assertThat(railDto.getThermalHardening()).isEqualTo("НЕТЕРМОУПРОЧНЕННЫЙ");
        assertThat(railDto.getPassedTonnageBeforeInstall()).isEqualTo(0.0f);
        assertThat(railDto.getRailSwitch()).isNull();
        assertThat(railDto.getRailLash()).isNull();
        assertThat(railDto).isInstanceOf(StationWayRailDto.class);

        StationWayRailDto stationWayRailDto = (StationWayRailDto) railDto;

        assertThat(stationWayRailDto.getStationWaySection().stationWay().station().getName()).isEqualTo("ЗДЕСЯ");
        assertThat(stationWayRailDto.getStationWaySection().stationWay().number()).isEqualTo("3");
        assertThat(stationWayRailDto.getStationWaySection().startMeter()).isEqualTo(1333);

        WayCharacteristicsDto wayc = handledRailInfoDto.getWayCharacteristics();

        assertThat(wayc).isNotNull();
        assertThat(wayc.slope()).isEqualTo(2.0f);
        assertThat(wayc.curveSide()).isNull();
        assertThat(wayc.lineClassSpecialization()).isEqualTo("4Г");
        assertThat(wayc.capitalRepairDate()).isEqualTo(LocalDate.of(1991, 4, 1));
    }

    @Test
    public void handleJointInfoRequest_whenGetCorrectMainWayJointParameters_thenReturnCorrectJointInfoDto() {
        JointInfoDto handledJointInfoDto = diodePObjInfoHandler.handleJointInfoRequest(
                1037L, WayType.INTERSTATION_TRACK, JointType.CONDUCTING);

        assertThat(handledJointInfoDto).isNotNull();
        assertThat(handledJointInfoDto.getLocationDescription()).isEqualTo("ГДЕ-ЛИБО - ЗДЕСЯ");
        assertThat(handledJointInfoDto.getJointLocation().meter()).isEqualTo(33);
        assertThat(handledJointInfoDto.getWayCharacteristics().intermediateRepairType()).isEqualTo("ППВ");
        assertThat(handledJointInfoDto.getJoint().getHorizontalStep()).isEqualTo(0.9f);
        assertThat(handledJointInfoDto.getMetaData()).isNull();
    }

    @Test
    public void handleJointInfoRequest_whenGetCorrectStationWayJointParameters_thenReturnCorrectJointInfoDto() {
        JointInfoDto handledJointInfoDto = diodePObjInfoHandler.handleJointInfoRequest(
                2139L, WayType.STATION_WAY, JointType.ISOLATING_SIGNAL);

        assertThat(handledJointInfoDto).isNotNull();
        assertThat(handledJointInfoDto.getLocationDescription()).isNull();
        assertThat(handledJointInfoDto.getJointLocation().picket()).isEqualTo(4);
        assertThat(handledJointInfoDto.getWayCharacteristics().installationDate())
                .isEqualTo(LocalDate.of(1985, 10, 1));
        assertThat(handledJointInfoDto.getJoint().getPadsType()).isEqualTo("СБОРНЫЕ");
        assertThat(handledJointInfoDto.getMetaData()).isNull();
    }

}