package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol;

import org.junit.jupiter.api.Test;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.util.TcdTestDataCreator;

import static org.assertj.core.api.Assertions.assertThat;

public class ProtocolDtoUnitTest {

    @Test
    void getDocSubType_whenNewInstanceCreatedWithoutExtendedSubType_thenReturnCorrectDocSubType() {
        ProtocolDto prot = new ProtocolDto(TcdTestDataCreator.createDocMetaDataDto(),
                TcdTestDataCreator.createProtMetaDataDto(ProtocolType.PROT_INSPECTION_OF_WAY_AND_STRUCTURES));
        assertThat(prot.getDocSubType()).isEqualTo(ProtocolType.PROT_INSPECTION_OF_WAY_AND_STRUCTURES);
    }

}