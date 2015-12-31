package com.feisystems.bham.web;
import com.feisystems.bham.domain.carerecommendations.UspstfGeneralRecommendation;
import com.feisystems.bham.service.carerecommendations.UspstfGeneralRecommendationService;
import com.feisystems.bham.service.carerecommendations.UspstfSpecificRecommendationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/uspstfgeneralrecommendations")
public class UspstfGeneralRecommendationController {
	
	@Autowired
    UspstfGeneralRecommendationService uspstfGeneralRecommendationService;

	@Autowired
    UspstfSpecificRecommendationService uspstfSpecificRecommendationService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public UspstfGeneralRecommendation showJson(@PathVariable("id") Long id) {
        UspstfGeneralRecommendation uspstfGeneralRecommendation = uspstfGeneralRecommendationService.findUspstfGeneralRecommendation(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return uspstfGeneralRecommendation;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<UspstfGeneralRecommendation> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<UspstfGeneralRecommendation> result = uspstfGeneralRecommendationService.findAllUspstfGeneralRecommendations();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public void createFromJson(@RequestBody UspstfGeneralRecommendation uspstfGeneralRecommendation) {
        uspstfGeneralRecommendationService.saveUspstfGeneralRecommendation(uspstfGeneralRecommendation);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody UspstfGeneralRecommendation uspstfGeneralRecommendation, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (uspstfGeneralRecommendationService.updateUspstfGeneralRecommendation(uspstfGeneralRecommendation) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        UspstfGeneralRecommendation uspstfGeneralRecommendation = uspstfGeneralRecommendationService.findUspstfGeneralRecommendation(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (uspstfGeneralRecommendation == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        uspstfGeneralRecommendationService.deleteUspstfGeneralRecommendation(uspstfGeneralRecommendation);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

}
