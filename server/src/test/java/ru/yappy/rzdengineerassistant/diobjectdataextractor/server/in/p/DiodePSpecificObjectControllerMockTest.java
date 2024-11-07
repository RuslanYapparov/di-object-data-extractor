package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.in.p;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.adm.WayMaintenanceDistanceDto;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.full.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.mini.rail.*;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.adm.StructuralEnterpriseService;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.adm.impl.WayMaintenanceDistanceService;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.extractor.rail.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiodePSpecificObjectController.class)
public class DiodePSpecificObjectControllerMockTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @MockBean(classes = { WayMaintenanceDistanceService.class })
    private StructuralEnterpriseService wayMaintenanceDistanceService;
    @MockBean
    private RailService railService;
    @MockBean
    private JointService jointService;

    @Autowired
    public DiodePSpecificObjectControllerMockTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void getFullStructuralEnterpriseInfoById_whenHaveNoProblem_thenReturnCorrectDto() throws Exception {
        when(wayMaintenanceDistanceService.getFullStructuralEnterpriseInfoById(777L))
                .thenReturn(createTestWayMaintenanceDistanceDto());

        mockMvc.perform(get("/api/v1/objects/p/distance/777")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(777L))
                .andExpect(jsonPath("$.name").value("ТЕСТ"))
                .andExpect(jsonPath("$.managers").isArray())
                .andExpect(jsonPath("$.managers").isEmpty())
                .andExpect(jsonPath("$.number").value(7))
                .andExpect(jsonPath("$.isIch").value(true))
                .andExpect(jsonPath("$.mainWays").isArray())
                .andExpect(jsonPath("$.mainWays").isEmpty())
                .andExpect(jsonPath("$.stations").isArray())
                .andExpect(jsonPath("$.stations").isEmpty());

        verify(wayMaintenanceDistanceService, Mockito.times(1))
                .getFullStructuralEnterpriseInfoById(777L);
    }

    @Test
    public void getFullStructuralEnterpriseInfoById_whenServiceThrowsException_thenThrowsException() throws Exception {
        when(wayMaintenanceDistanceService.getFullStructuralEnterpriseInfoById(Mockito.anyLong()))
                .thenThrow(new NullPointerException("Test NPE"));

        mockMvc.perform(get("/api/v1/objects/p/distance/777")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.exception").value("NullPointerException"))
                .andExpect(jsonPath("$.message").value("Test NPE"))
                .andExpect(jsonPath("$.timeStamp").isNotEmpty())
                .andExpect(jsonPath("$.details").isNotEmpty());

        verify(wayMaintenanceDistanceService, Mockito.times(1))
                .getFullStructuralEnterpriseInfoById(777L);
    }

    @Test
    public void getAllStructuralEnterprisesMiniInfoByDirectorateAbbreviation_whenHaveNoProblem_thenReturnCorrectDto()
            throws Exception {
        when(wayMaintenanceDistanceService.getAllStructuralEnterprisesMiniInfoByDirectorateAbbreviation("ДИ"))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/v1/objects/p/distance?dirabbrev=ДИ")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(wayMaintenanceDistanceService, Mockito.times(1))
                .getAllStructuralEnterprisesMiniInfoByDirectorateAbbreviation("ДИ");
    }

    @Test
    public void getAllStructuralEnterprisesMiniInfoByDirectorateAbbreviation_whenServiceThrowsException_thenThrowException()
            throws Exception {
        when(wayMaintenanceDistanceService.getAllStructuralEnterprisesMiniInfoByDirectorateAbbreviation("ДИ"))
                .thenThrow(new RuntimeException("Test Exception"));

        mockMvc.perform(get("/api/v1/objects/p/distance?dirabbrev=ДИ")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.exception").value("RuntimeException"))
                .andExpect(jsonPath("$.message").value("Test Exception"))
                .andExpect(jsonPath("$.timeStamp").isNotEmpty())
                .andExpect(jsonPath("$.details").isNotEmpty());

        verify(wayMaintenanceDistanceService, Mockito.times(1))
                .getAllStructuralEnterprisesMiniInfoByDirectorateAbbreviation("ДИ");
    }

    @Test
    public void getRailVariantsByLocationDto_whenHaveNoProblem_thenReturnCorrectDtoSet() throws Exception {
        LocationDto locationDto = createTestLocationDto();
        when(railService.getRailMiniInfoVariantsByLocationDto(locationDto))
                .thenReturn(List.of(createTestRailMiniDto()));

        mockMvc.perform(get("/api/v1/objects/p/rail")
                        .content(objectMapper.writeValueAsBytes(locationDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(777L))
                .andExpect(jsonPath("$[0].startCoordinate").value(1.234f));

        verify(railService, Mockito.times(1))
                .getRailMiniInfoVariantsByLocationDto(locationDto);
    }

    @ParameterizedTest
    @EnumSource(JointType.class)
    public void getJointVariantsByLocationDto_whenHaveNoProblem_thenReturnCorrectDtoList(JointType type)
            throws Exception {
        LocationDto locationDto = createTestLocationDto();
        when(jointService.getJointMiniInfoVariantsByLocationDto(locationDto, type))
                .thenReturn(List.of(createTestJointMiniDto()));

        mockMvc.perform(get("/api/v1/objects/p/joint?type=" + type.name())
                        .content(objectMapper.writeValueAsBytes(locationDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(777L))
                .andExpect(jsonPath("$[0].type").value("ТОКОПРОВОДЯЩИЙ"))
                .andExpect(jsonPath("$[0].lineSide").value("ЛЕВАЯ"))
                .andExpect(jsonPath("$[0].coordinate").value(1.234f));

        verify(jointService, Mockito.times(1))
                .getJointMiniInfoVariantsByLocationDto(locationDto, type);
    }

    private WayMaintenanceDistanceDto createTestWayMaintenanceDistanceDto() {
        return new WayMaintenanceDistanceDto(
                777L,
                "ТЕСТ",
                "ДИ",
                Set.of(),
                7,
                true,
                Set.of(),
                Set.of()
        );
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

    private RailMiniDto createTestRailMiniDto() {
        return new RailMiniDto(
                777L,
                "ЛЕВАЯ",
                "БЕССТЫКОВОЙ",
                1.234f,
                4.321f
        );
    }

    private JointMiniDto createTestJointMiniDto() {
        return new JointMiniDto(
                777L,
                "ТОКОПРОВОДЯЩИЙ",
                "ЛЕВАЯ",
                1.234f
        );
    }

}