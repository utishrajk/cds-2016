package com.feisystems.bham.web;
import com.feisystems.bham.domain.carerecommendations.UspstfSpecificRecommendation;
import com.feisystems.bham.service.carerecommendations.UspstfGeneralRecommendationService;
import com.feisystems.bham.service.carerecommendations.UspstfRecommendationToolService;
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
@RequestMapping("/uspstfspecificrecommendations")
public class UspstfSpecificRecommendationController {
	
	@Autowired
    UspstfSpecificRecommendationService uspstfSpecificRecommendationService;

	@Autowired
    UspstfGeneralRecommendationService uspstfGeneralRecommendationService;

	@Autowired
    UspstfRecommendationToolService uspstfRecommendationToolService;

	@RequestMapping(value = "/{id_}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public UspstfSpecificRecommendation showJson(@PathVariable("id_") Long id_) {
        UspstfSpecificRecommendation uspstfSpecificRecommendation = uspstfSpecificRecommendationService.findUspstfSpecificRecommendation(id_);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return uspstfSpecificRecommendation;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<UspstfSpecificRecommendation> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<UspstfSpecificRecommendation> result = uspstfSpecificRecommendationService.findAllUspstfSpecificRecommendations();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public void createFromJson(@RequestBody UspstfSpecificRecommendation uspstfSpecificRecommendation) {
        uspstfSpecificRecommendationService.saveUspstfSpecificRecommendation(uspstfSpecificRecommendation);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
    }

	@RequestMapping(value = "/{id_}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody UspstfSpecificRecommendation uspstfSpecificRecommendation, @PathVariable("id_") Long id_) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (uspstfSpecificRecommendationService.updateUspstfSpecificRecommendation(uspstfSpecificRecommendation) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id_}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id_") Long id_) {
        UspstfSpecificRecommendation uspstfSpecificRecommendation = uspstfSpecificRecommendationService.findUspstfSpecificRecommendation(id_);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (uspstfSpecificRecommendation == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        uspstfSpecificRecommendationService.deleteUspstfSpecificRecommendation(uspstfSpecificRecommendation);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }


}
