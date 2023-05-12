package sphe.dev.restdemo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sphe.dev.restdemo.configuration.InseeSirenApiClientConfig;

import java.util.Map;

//insee.api.base-url =https://api.insee.fr/entreprises/sirene/V3
@FeignClient(name = "insee-siren-api", url = "${insee.api.base-url}", configuration = InseeSirenApiClientConfig.class)
public interface InseeSirenApiClient {
    @GetMapping("/siret?q=siren:{siren} AND etablissementSiege:TRUE")
    Map<String,Object> getEntrepriseBySiren(@PathVariable String siren);

    @GetMapping("/siret?q=denominationUniteLegale:\"{legalName}\" AND etablissementSiege:TRUE")
    Map<String,Object> getEntrepriseByLegalName(@PathVariable String legalName);



}
