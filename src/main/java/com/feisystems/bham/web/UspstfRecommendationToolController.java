package com.feisystems.bham.web;

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

import com.feisystems.bham.domain.carerecommendations.UspstfRecommendationTool;
import com.feisystems.bham.service.carerecommendations.UspstfRecommendationToolService;

@Controller
@RequestMapping("/uspstfrecommendationtools")
public class UspstfRecommendationToolController {

	@Autowired
    UspstfRecommendationToolService uspstfRecommendationToolService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public UspstfRecommendationTool showJson(@PathVariable("id") Long id) {
        UspstfRecommendationTool uspstfRecommendationTool = uspstfRecommendationToolService.findUspstfRecommendationTool(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return uspstfRecommendationTool;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<UspstfRecommendationTool> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<UspstfRecommendationTool> result = uspstfRecommendationToolService.findAllUspstfRecommendationTools();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public void createFromJson(@RequestBody UspstfRecommendationTool uspstfRecommendationTool) {
        uspstfRecommendationToolService.saveUspstfRecommendationTool(uspstfRecommendationTool);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody UspstfRecommendationTool uspstfRecommendationTool, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (uspstfRecommendationToolService.updateUspstfRecommendationTool(uspstfRecommendationTool) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        UspstfRecommendationTool uspstfRecommendationTool = uspstfRecommendationToolService.findUspstfRecommendationTool(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (uspstfRecommendationTool == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        uspstfRecommendationToolService.deleteUspstfRecommendationTool(uspstfRecommendationTool);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
