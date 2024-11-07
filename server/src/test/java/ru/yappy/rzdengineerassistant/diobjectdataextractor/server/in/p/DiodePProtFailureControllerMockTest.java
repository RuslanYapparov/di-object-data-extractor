package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.in.p;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.JointMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.util.DiodeTestDataCreator;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.handler.DiodePProtFailureHandler;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure.p.mini.JointFailureProtCreatingDataDto;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiodePProtFailureController.class)
public class DiodePProtFailureControllerMockTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @MockBean
    private DiodePProtFailureHandler handler;

    @Autowired
    public DiodePProtFailureControllerMockTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void getJointFailureProtCreatingDataDto_whenHaveNoProblem_thenReturnCorrectDto() throws Exception {
        JointFailureProtCreatingDataDto jointFailureDataDto = new JointFailureProtCreatingDataDto(
                new JointMiniDto[] { new JointMiniDto(777L, "ТОКОПРОВОДЯЩИЙ", "ЛЕВАЯ", 1.234f) },
                "ТЕСТ",
                List.of(DiodeTestDataCreator.createEmployeeDto()));

        when(handler.getJointFailureProtCreatingDataDtoByLocationDto(createTestLocationDto(), JointType.CONDUCTING))
                .thenReturn(jointFailureDataDto);

        mockMvc.perform(get("/api/v1/protocol/failure/p/joint?type=CONDUCTING")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTestLocationDto()))
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jointVariants[0].coordinate").value(1.234f))
                .andExpect(jsonPath("$.diodeLocationDescription").value("ТЕСТ"))
                .andExpect(jsonPath("$.participants[0].name").value("Тест"));

        verify(handler, Mockito.times(1))
                .getJointFailureProtCreatingDataDtoByLocationDto(createTestLocationDto(), JointType.CONDUCTING);
    }

    @Test
    public void getJointFailureProtCreatingDataDto_whenServiceThrowsException_thenThrowsException() throws Exception {
        when(handler.getJointFailureProtCreatingDataDtoByLocationDto(createTestLocationDto(), JointType.ISOLATING_SWITCH))
                .thenThrow(new ClassCastException("Test ClassCastException"));

        mockMvc.perform(get("/api/v1/protocol/failure/p/joint?type=ISOLATING_SWITCH")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTestLocationDto()))
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.exception").value("ClassCastException"))
                .andExpect(jsonPath("$.message").value("Test ClassCastException"))
                .andExpect(jsonPath("$.timeStamp").isNotEmpty())
                .andExpect(jsonPath("$.details").isNotEmpty());

        verify(handler, Mockito.times(1))
                .getJointFailureProtCreatingDataDtoByLocationDto(createTestLocationDto(), JointType.ISOLATING_SWITCH);
    }

    private LocationDto createTestLocationDto() {
        return new LocationDto(
                WayType.INTERSTATION_TRACK,
                7L,
                "7",
                7,
                7,
                7,
                LineSide.RIGHT
        );
    }

}