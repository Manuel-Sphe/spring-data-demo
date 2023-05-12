package sphe.dev.restdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sphe.dev.restdemo.service.InseeSirenApiClient;

import java.util.Map;

@RequestMapping("feign")
@RestController
public class BuyerController {

    @Autowired
    private InseeSirenApiClient inseeSirenApiClient;

    @GetMapping("/{siren}")
    public ResponseEntity<Map<String,Object>> getEntrepriseBySiren(@PathVariable String siren){
        return ResponseEntity.ok(inseeSirenApiClient.getEntrepriseBySiren(siren));
    }

    @GetMapping("/name/{legalName}")
    public ResponseEntity<Map<String,Object>> getEntrepriseByLegalName(@PathVariable String legalName){
        return ResponseEntity.ok(inseeSirenApiClient.getEntrepriseByLegalName(legalName));
    }

}
