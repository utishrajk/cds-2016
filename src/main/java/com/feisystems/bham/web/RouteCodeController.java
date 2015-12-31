package com.feisystems.bham.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisystems.bham.domain.reference.RouteCode;
import com.feisystems.bham.service.reference.RouteCodeService;

@Controller
@RequestMapping("/routecodes")
public class RouteCodeController {

	@Autowired
    RouteCodeService routeCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public RouteCode showJson(@PathVariable("id") Long id) {
        RouteCode routeCode = routeCodeService.findRouteCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return routeCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<RouteCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<RouteCode> result = routeCodeService.findAllRouteCodes();
        return result;
    }

}
