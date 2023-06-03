package sphe.dev.restdemo.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import sphe.dev.restdemo.service.BuyerInfoDTO;
import sphe.dev.restdemo.service.InseeSirenApiClient;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(BuyerController.class)
class BuyerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InseeSirenApiClient inseeSirenApiClient;


    @Test
    public void testGetEnterpriseBySiren_Success() throws Exception {
        String siren = "511199226";
        String legalName = null;
        String numeroVoieEtablissement = "25";
        String typeVoieEtablissement = "BD";
        String libelleVoieEtablissement = "EUGENE DERUELLE";
        String codePostalEtablissement = "69003";
        String libelleCommuneEtablissement = "LYON 3EME";

        // Prepare the mock response from inseeSirenApiClient
        Map<String, Object> entreprise = new HashMap<>();
        Map<String, Object> legalAddress = new HashMap<>();
        legalAddress.put("numeroVoieEtablissement", numeroVoieEtablissement);
        legalAddress.put("typeVoieEtablissement", typeVoieEtablissement);
        legalAddress.put("libelleVoieEtablissement", libelleVoieEtablissement);
        legalAddress.put("codePostalEtablissement", codePostalEtablissement);
        legalAddress.put("libelleCommuneEtablissement", libelleCommuneEtablissement);
        entreprise.put("legalAddress", legalAddress);
        when(inseeSirenApiClient.getEntrepriseBySiren(eq(siren))).thenReturn(entreprise);

        // Perform the GET request
        mockMvc.perform(get("/feign/{siren}", siren))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.siren").value(siren))
                .andExpect(jsonPath("$.legalName").value(legalName))
                .andExpect(jsonPath("$.legalAddress.numeroVoieEtablissement").value(numeroVoieEtablissement))
                .andExpect(jsonPath("$.legalAddress.typeVoieEtablissement").value(typeVoieEtablissement))
                .andExpect(jsonPath("$.legalAddress.libelleVoieEtablissement").value(libelleVoieEtablissement))
                .andExpect(jsonPath("$.legalAddress.codePostalEtablissement").value(codePostalEtablissement))
                .andExpect(jsonPath("$.legalAddress.libelleCommuneEtablissement").value(libelleCommuneEtablissement));
    }


}