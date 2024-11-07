package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FailureCharacterUnitTest {

    @Test
    void getEnumStringFields_whenJoined_thenReturnCorrectBigString() {
        String bigEnumString = Arrays.stream(FailureCharacter.values())
                .map(character -> character.getRuNominative() + " " + character.getRuGenitive() + " " +
                        Arrays.toString(character.getCharacterSubCauses()))
                .collect(Collectors.joining("; "));
        assertThat(bigEnumString).isEqualTo("деградационный деградационного []; эксплуатационный " +
                "эксплуатационного [непредумышленные ошибочные действия работника, низкая исполнительская " +
                "дисциплина, недостаточные знания/квалификация работников, недостаточная укомплектованность " +
                "штата]; производственный производственного [при изготовлении, при монтаже, при ремонте]; " +
                "конструктивный конструктивного []; внешнее воздействие внешнего воздействия [особые метеоусловия, " +
                "несанкционированное вмешательство третьих лиц в работу железнодорожного транспорта, последствия " +
                "стихийных бедствий, боевые действия в районе возникновения отказа, террористический акт]");
    }

}