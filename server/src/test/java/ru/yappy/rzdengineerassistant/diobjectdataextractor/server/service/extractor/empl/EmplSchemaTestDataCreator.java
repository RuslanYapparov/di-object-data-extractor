package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.empl;

import lombok.experimental.UtilityClass;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.adm.WayMaintenanceDistanceEntity;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.model.entity.empl.WayDistanceAdmEmployeeEntity;

@UtilityClass
public class EmplSchemaTestDataCreator {

    public static WayDistanceAdmEmployeeEntity createTestWayMaintenanceDistanceEmployeeEntity() {
        WayDistanceAdmEmployeeEntity employeeEntity = new WayDistanceAdmEmployeeEntity();
        employeeEntity.setId(777L);
        employeeEntity.setName("TestName");
        employeeEntity.setSurname("TestSurname");
        employeeEntity.setPatronymic("TestPatronymic");
        employeeEntity.setFullTitle("TestFullTitle");
        employeeEntity.setJobTitleAbbreviation("ПЧ");
        employeeEntity.setEmployeeType("TestEmployeeType");
        employeeEntity.setAsutrPersonnelNumber(1234567L);
        employeeEntity.setWayMaintenanceDistance(new WayMaintenanceDistanceEntity());
        return employeeEntity;
    }

}
