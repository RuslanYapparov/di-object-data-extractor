package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.common;

import org.junit.jupiter.api.Test;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.ObjInfoDto;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.*;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util.TcdTestDataCreator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DocumentDtoUnitTest {
    private DocumentDto doc = new DocumentDto(DocType.LETTER, TcdTestDataCreator.createDocMetaDataDto());

    @Test
    public void getDocType_whenInvoked_thenReturnCorrectValue() {
        assertThat(doc.getDocType()).isEqualTo(DocType.LETTER);
    }

    @Test
    public void getDocSubType_whenInvokedWithoutOverriding_thenThrowUnsupportedOpException() {
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class,
                () -> doc.getDocSubType());
        assertThat(exception.getMessage()).isEqualTo("Для класса 'DocumentDto' не переопределен " +
                "метод getDocSubType()");
    }

    @Test
    public void getDocSubType_whenInvokedInClassWithOverriding_thenReturnCorrectValue() {
        doc = new PObjInfoDto(PObjectInfoType.P_OBJ_INFO_STATION, TcdTestDataCreator.createDocMetaDataDto());
        assertThat(doc.getDocSubType()).isEqualTo(PObjectInfoType.P_OBJ_INFO_STATION);
    }

    @Test
    public void getMetaData_whenInvoked_thenReturnCorrectValue() {
        assertThat(doc.getMetaData()).isEqualTo(TcdTestDataCreator.createDocMetaDataDto());
    }

    @Test
    public void equals_whenInvokedInDifferentVariants_thenReturnCorrectBoolean() {
        DocumentDto anotherDoc = new DocumentDto(DocType.LETTER, TcdTestDataCreator.createDocMetaDataDto());

        assertThat(doc.equals(anotherDoc)).isTrue();

        anotherDoc = new DocumentDto(DocType.PROTOCOL, TcdTestDataCreator.createDocMetaDataDto());

        assertThat(doc.equals(anotherDoc)).isFalse();

        anotherDoc = new ObjInfoDto(TcdTestDataCreator.createDocMetaDataDto());

        assertThat(doc.equals(anotherDoc)).isFalse();

        anotherDoc = new DocumentDto(DocType.LETTER, null);

        assertThat(doc.equals(anotherDoc)).isFalse();
    }

    @Test
    public void hashcode_whenInvoked_thenReturnCorrectValue() {
        assertThat(doc.hashCode()).isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    @Test
    public void toString_whenInvoked_thenReturnCorrectValue() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        assertThat(doc.toString()).isEqualTo("DocumentDto{type=LETTER, metaData=DocMetaDataDto[enterprise=" +
                "WayMaintenanceDistanceMiniDto{number=7, isIch=false, id=1, name='ТЕСТОВАЯ'}, signer=ManagerMiniDto[id=1, " +
                "fullName=Начальников Начальник Начальникович, jobTitleAbbreviation=ПЧ], date=" +
                formatter.format(LocalDate.now()) + ", performer=PerformerDto[lastName=Тестов, firstName=Тест, patronymic=" +
                "Тестович, jobTitle=Инженер, telephone=12345678, workEmail=test@test]]}");
    }

}