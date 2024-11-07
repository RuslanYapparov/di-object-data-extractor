package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.in.p;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.JointType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.handler.DiodePObjInfoHandler;
import ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.objinfo.p.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiodePObjInfoController.class)
public class DiodePObjInfoControllerMockTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @MockBean
    private DiodePObjInfoHandler diodePObjInfoHandler;

    @Autowired
    public DiodePObjInfoControllerMockTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void getFullRailInfo_whenHaveNoProblems_thenReturnCorrectDto() throws Exception {
        when(diodePObjInfoHandler.handleRailInfoRequest(777L, WayType.INTERSTATION_TRACK))
                .thenReturn(createTestRailInfoDto());

        mockMvc.perform(get("/api/v1/objinfo/p/rail/777?waytype=INTERSTATION_TRACK")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.locationDescription").value("перегон ТЕСТ - ТЕСТ"))
                .andExpect(jsonPath("$.wayCharacteristics").doesNotExist())
                .andExpect(jsonPath("$.brokenRailExtraInfo.railBreakDate").value("01-01-2024"))
                .andExpect(jsonPath("$.brokenRailExtraInfo.railTemp").value(0));

        verify(diodePObjInfoHandler, Mockito.times(1))
                .handleRailInfoRequest(777L, WayType.INTERSTATION_TRACK);
    }

    @Test
    public void getFullRailInfo_whenServiceThrowsException_thenThrowsException() throws Exception {
        when(diodePObjInfoHandler.handleRailInfoRequest(777L, WayType.INTERSTATION_TRACK))
                .thenThrow(new RuntimeException("Test Exception"));

        mockMvc.perform(get("/api/v1/objinfo/p/rail/777?waytype=INTERSTATION_TRACK")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.exception").value("RuntimeException"))
                .andExpect(jsonPath("$.message").value("Test Exception"))
                .andExpect(jsonPath("$.timeStamp").isNotEmpty())
                .andExpect(jsonPath("$.details").isNotEmpty());

        verify(diodePObjInfoHandler, Mockito.times(1))
                .handleRailInfoRequest(777L, WayType.INTERSTATION_TRACK);
    }

    @Test
    public void getFullJointInfo_whenHaveNoProblems_thenReturnCorrectDto() throws Exception {
        when(diodePObjInfoHandler.handleJointInfoRequest(777L,
                WayType.INTERSTATION_TRACK, JointType.ISOLATING_SIGNAL))
                .thenReturn(createTestJointInfoDto());

        mockMvc.perform(get("/api/v1/objinfo/p/joint/777?waytype=INTERSTATION_TRACK&" +
                        "jointtype=ISOLATING_SIGNAL")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.locationDescription").value("перегон ТЕСТ - ТЕСТ"))
                .andExpect(jsonPath("$.joint").doesNotExist())
                .andExpect(jsonPath("$.wayCharacteristics").doesNotExist());


        verify(diodePObjInfoHandler, Mockito.times(1))
                .handleJointInfoRequest(777L, WayType.INTERSTATION_TRACK, JointType.ISOLATING_SIGNAL);
    }

    @Test
    public void getFullJointInfo_whenServiceThrowsException_thenThrowsException() throws Exception {
        when(diodePObjInfoHandler.handleJointInfoRequest(777L,
                WayType.INTERSTATION_TRACK, JointType.ISOLATING_SIGNAL))
                .thenThrow(new RuntimeException("Test Exception"));

        mockMvc.perform(get("/api/v1/objinfo/p/joint/777?waytype=INTERSTATION_TRACK&" +
                        "jointtype=ISOLATING_SIGNAL")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.exception").value("RuntimeException"))
                .andExpect(jsonPath("$.message").value("Test Exception"))
                .andExpect(jsonPath("$.timeStamp").isNotEmpty())
                .andExpect(jsonPath("$.details").isNotEmpty());

        verify(diodePObjInfoHandler, Mockito.times(1))
                .handleJointInfoRequest(777L, WayType.INTERSTATION_TRACK, JointType.ISOLATING_SIGNAL);
    }

    private RailInfoDto createTestRailInfoDto() {
        return new RailInfoDto(
                "перегон ТЕСТ - ТЕСТ",
                null,
                null,
                new BrokenRailExtraInfoDto(LocalDate.of(2024, 1, 1), 0, 0),
                null
        );
    }

    private JointInfoDto createTestJointInfoDto() {
        return new JointInfoDto(
                "перегон ТЕСТ - ТЕСТ",
                null,
                null,
                null,
                null
        );
    }

}