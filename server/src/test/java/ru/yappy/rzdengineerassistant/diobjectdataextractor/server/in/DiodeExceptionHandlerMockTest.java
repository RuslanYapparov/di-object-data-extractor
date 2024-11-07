package ru.yappy.rzdengineerassistant.diobjectdataextractor.server.in;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yappy.rzdengineerassistant.commonclasses.exception.ObjectNotFoundException;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.dtos.common.location.WayType;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.in.p.DiodePObjInfoController;
import ru.yappy.rzdengineerassistant.diobjectdataextractor.server.service.handler.DiodePObjInfoHandler;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = { DiodeExceptionHandler.class, DiodePObjInfoController.class })
public class DiodeExceptionHandlerMockTest {
    private final MockMvc mockMvc;

    @MockBean
    private DiodePObjInfoHandler pobjInfoHandlerDiode;

    @Autowired
    public DiodeExceptionHandlerMockTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void handleIllegalStateException_whenServiceThrowsException_thenReturnsCorrectExceptionResponse()
            throws Exception {
        when(pobjInfoHandlerDiode.handleRailInfoRequest(Mockito.anyLong(), Mockito.any(WayType.class)))
                .thenThrow(new IllegalStateException("Test IllegalStateException"));

        mockMvc.perform(get("/api/v1/objinfo/p/rail/777?waytype=INTERSTATION_TRACK")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.exception").value("IllegalStateException"))
                .andExpect(jsonPath("$.message").value("Test IllegalStateException"))
                .andExpect(jsonPath("$.timeStamp").isNotEmpty())
                .andExpect(jsonPath("$.details").isNotEmpty());

        verify(pobjInfoHandlerDiode, Mockito.times(1))
                .handleRailInfoRequest(777L, WayType.INTERSTATION_TRACK);
    }

    @Test
    public void handleIllegalArgumentException_whenServiceThrowsException_thenReturnsCorrectExceptionResponse()
            throws Exception {
        when(pobjInfoHandlerDiode.handleRailInfoRequest(Mockito.anyLong(), Mockito.any(WayType.class)))
                .thenThrow(new IllegalArgumentException("Test IllegalArgumentException"));

        mockMvc.perform(get("/api/v1/objinfo/p/rail/1?waytype=INTERSTATION_TRACK")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.exception").value("IllegalArgumentException"))
                .andExpect(jsonPath("$.message").value("Test IllegalArgumentException"))
                .andExpect(jsonPath("$.timeStamp").isNotEmpty())
                .andExpect(jsonPath("$.details").isNotEmpty());

        verify(pobjInfoHandlerDiode, Mockito.times(1))
                .handleRailInfoRequest(1L, WayType.INTERSTATION_TRACK);
    }

    @Test
    public void handleObjectNotException_whenServiceThrowsException_thenReturnsCorrectExceptionResponse()
            throws Exception {
        when(pobjInfoHandlerDiode.handleRailInfoRequest(Mockito.anyLong(), Mockito.any(WayType.class)))
                .thenThrow(new ObjectNotFoundException("testObject", "testCriteriaDescription"));

        mockMvc.perform(get("/api/v1/objinfo/p/rail/777?waytype=INTERSTATION_TRACK")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.exception").value("ObjectNotFoundException"))
                .andExpect(jsonPath("$.message").value("Не удалось найти в базе данных " +
                        "объект 'testObject' со следующими критериями выборки: testCriteriaDescription"))
                .andExpect(jsonPath("$.timeStamp").isNotEmpty())
                .andExpect(jsonPath("$.details").isNotEmpty());

        verify(pobjInfoHandlerDiode, Mockito.times(1))
                .handleRailInfoRequest(777L, WayType.INTERSTATION_TRACK);
    }

    @Test
    public void handleRuntimeException_whenServiceThrowsException_thenReturnsCorrectExceptionResponse()
            throws Exception {
        when(pobjInfoHandlerDiode.handleRailInfoRequest(Mockito.anyLong(), Mockito.any(WayType.class)))
                .thenThrow(new IndexOutOfBoundsException(777));

        mockMvc.perform(get("/api/v1/objinfo/p/rail/777?waytype=INTERSTATION_TRACK")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.exception").value("IndexOutOfBoundsException"))
                .andExpect(jsonPath("$.message").value("Index out of range: 777"))
                .andExpect(jsonPath("$.timeStamp").isNotEmpty())
                .andExpect(jsonPath("$.details").isNotEmpty());

        verify(pobjInfoHandlerDiode, Mockito.times(1))
                .handleRailInfoRequest(777L, WayType.INTERSTATION_TRACK);
    }

}