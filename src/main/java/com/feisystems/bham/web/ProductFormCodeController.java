package com.feisystems.bham.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisystems.bham.domain.reference.ProductFormCode;
import com.feisystems.bham.service.reference.ProductFormCodeService;

@Controller
@RequestMapping("/productformcodes")
public class ProductFormCodeController {

	@Autowired
    ProductFormCodeService productFormCodeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ProductFormCode showJson(@PathVariable("id") Long id) {
        ProductFormCode productFormCode = productFormCodeService.findProductFormCode(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return productFormCode;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<ProductFormCode> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<ProductFormCode> result = productFormCodeService.findAllProductFormCodes();
        return result;
    }

}
