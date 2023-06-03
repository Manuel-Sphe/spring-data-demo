package sphe.dev.restdemo.controller;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sphe.dev.restdemo.exception.exceptions.BadRequestException;
import sphe.dev.restdemo.exception.exceptions.BuyerNotException;
import sphe.dev.restdemo.service.BuyerInfoDTO;
import sphe.dev.restdemo.service.InseeSirenApiClient;

import java.util.List;
import java.util.Map;

@RequestMapping("feign")
@RestController
public class BuyerController {

    @Autowired
    private InseeSirenApiClient inseeSirenApiClient;

    @GetMapping("/{siren}")
    public ResponseEntity<BuyerInfoDTO> getEntrepriseBySiren(@PathVariable String siren){


        try{
            Map<String,Object> entreprise = inseeSirenApiClient.getEntrepriseBySiren(siren);

            System.out.println(entreprise);

            List<Map<String, Object>> etablissements;
            etablissements = (List<Map<String, Object>>) entreprise.get("etablissements");
            Map<String,Object> map = etablissements.get(0);

            Map<String, Object> map2  = (Map<String, Object>) map.get("uniteLegale");
            BuyerInfoDTO buyerInfoDTO = new BuyerInfoDTO(
                    siren,
                    (String) map2.get("denominationUniteLegale" +
                            ""),
                    map.get("adresseEtablissement"));
            return ResponseEntity.ok(buyerInfoDTO);
        }
        catch(FeignException e){
            if(e.status() == 404){
                throw  new BuyerNotException("Buyer not found for Siren : " + siren);
            }
            // there's an error but not 404
            else if(e.status() == 400){
                throw  new BadRequestException("Bad request for : " + siren+", please double check the siren");
            }
           else{
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }


    }

    @GetMapping("/name/{legalName}")
    public ResponseEntity<BuyerInfoDTO> getEntrepriseByLegalName(@PathVariable String legalName){

        try{
            Map<String,Object> entreprise = inseeSirenApiClient.getEntrepriseByLegalName(legalName);

            List<Map<String, Object>> etablissements;
            etablissements = (List<Map<String, Object>>) entreprise.get("etablissements");
            Map<String,Object> map = etablissements.get(0);
            BuyerInfoDTO buyerInfoDTO = new BuyerInfoDTO((String) map.get("siren"),legalName, map.get("adresseEtablissement"));
            return ResponseEntity.ok(buyerInfoDTO);
        }
        catch(FeignException e){
            if(e.status() == 404){
                throw  new BuyerNotException("Buyer not found for legal name : " + legalName);
            }
            // there's an error but not 404
            else if(e.status() == 400){
                throw  new BadRequestException("Bad request for legal Name :" + legalName+", please double check the legal name");
            }

            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

}
