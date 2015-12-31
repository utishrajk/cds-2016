package com.feisystems.bham.web;
import com.feisystems.bham.domain.reference.ActStatusCode;
import com.feisystems.bham.service.reference.ActStatusCodeService;
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
@RequestMapping("/actstatuscodes")
public class ActStatusCodeController {

	@Autowired
    ActStatusCodeService actStatusCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ActStatusCode showJson(@PathVariable("id") Long id) {
        ActStatusCode actStatusCode = actStatusCodeService.findActStatusCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return actStatusCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<ActStatusCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<ActStatusCode> result = actStatusCodeService.findAllActStatusCodes();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody ActStatusCode actStatusCode) {
        actStatusCodeService.saveActStatusCode(actStatusCode);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody ActStatusCode actStatusCode, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (actStatusCodeService.updateActStatusCode(actStatusCode) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        ActStatusCode actStatusCode = actStatusCodeService.findActStatusCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (actStatusCode == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        actStatusCodeService.deleteActStatusCode(actStatusCode);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
