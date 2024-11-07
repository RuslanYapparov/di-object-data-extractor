package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.StationNameDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.infr.StationWayMiniDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.infr.LocationService;

import java.nio.charset.StandardCharsets;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiodeCommonObjectController.class)
public class DiodeCommonObjectControllerMockTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @MockBean
    private LocationService locationService;

    @Autowired
    public DiodeCommonObjectControllerMockTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void getStationWaysMiniInfoByStationId_whenHaveNoProblem_thenReturnCorrectDto() throws Exception {
        when(locationService.getStationWaysMiniDtoByStationId(777L))
                .thenReturn(Set.of(new StationWayMiniDto(777L, "ТЕСТ", new StationNameDto(777L, "ТЕСТ"))));

        mockMvc.perform(get("/api/v1/objects/common/stationway/mini/777")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(777))
                .andExpect(jsonPath("$[0].number").value("ТЕСТ"))
                .andExpect(jsonPath("$[0].station.id").value(777))
                .andExpect(jsonPath("$[0].station.name").value("ТЕСТ"));

        verify(locationService, Mockito.times(1))
                .getStationWaysMiniDtoByStationId(777L);
    }

    @Test
    public void getStationWaysMiniInfoByStationId_whenServiceThrowsException_thenThrowsException() throws Exception {
        when(locationService.getStationWaysMiniDtoByStationId(Mockito.anyLong()))
                .thenThrow(new StringIndexOutOfBoundsException(14));

        mockMvc.perform(get("/api/v1/objects/common/stationway/mini/777")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().is(500))
                .andExpect(jsonPath("$.exception").value("StringIndexOutOfBoundsException"))
                .andExpect(jsonPath("$.message").value("String index out of range: 14"))
                .andExpect(jsonPath("$.timeStamp").isNotEmpty())
                .andExpect(jsonPath("$.details").isNotEmpty());

        verify(locationService, Mockito.times(1))
                .getStationWaysMiniDtoByStationId(777L);
    }

    @Test
    public void getStationWaysMiniInfoByStationId_whenServiceReturnNull_thenJsonPathNotExist() throws Exception {
        when(locationService.getStationWaysMiniDtoByStationId(777L))
                .thenReturn(null);

        mockMvc.perform(get("/api/v1/objects/common/stationway/mini/777")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());

        verify(locationService, Mockito.times(1))
                .getStationWaysMiniDtoByStationId(777L);
    }

}