package com.feisystems.bham.web;
import com.feisystems.bham.domain.provider.OrganizationalProvider;
import com.feisystems.bham.domain.reference.ProviderEntityType;
import com.feisystems.bham.service.provider.IndividualProviderService;
import com.feisystems.bham.service.provider.OrganizationalProviderService;
import com.feisystems.bham.service.reference.ProviderTaxonomyCodeService;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/organizationalproviders")
public class OrganizationalProviderController {
	
	@Autowired
    OrganizationalProviderService organizationalProviderService;

	@Autowired
    ProviderTaxonomyCodeService providerTaxonomyCodeService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public OrganizationalProvider showJson(@PathVariable("id") Long id) {
        OrganizationalProvider organizationalProvider = organizationalProviderService.findOrganizationalProvider(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return organizationalProvider;
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public List<OrganizationalProvider> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<OrganizationalProvider> result = organizationalProviderService.findAllOrganizationalProviders();
        return result;
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public void createFromJson(@RequestBody OrganizationalProvider organizationalProvider) {
        organizationalProviderService.saveOrganizationalProvider(organizationalProvider);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody OrganizationalProvider organizationalProvider, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (organizationalProviderService.updateOrganizationalProvider(organizationalProvider) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        OrganizationalProvider organizationalProvider = organizationalProviderService.findOrganizationalProvider(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (organizationalProvider == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        organizationalProviderService.deleteOrganizationalProvider(organizationalProvider);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid OrganizationalProvider organizationalProvider, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, organizationalProvider);
            return "organizationalproviders/create";
        }
        uiModel.asMap().clear();
        organizationalProviderService.saveOrganizationalProvider(organizationalProvider);
        return "";
//        return "redirect:/organizationalproviders/" + encodeUrlPathSegment(organizationalProvider.getId().toString(), httpServletRequest);
    }

	void populateEditForm(Model uiModel, OrganizationalProvider organizationalProvider) {
        uiModel.addAttribute("organizationalProvider", organizationalProvider);
        uiModel.addAttribute("providerentitytypes", Arrays.asList(ProviderEntityType.values()));
        uiModel.addAttribute("providertaxonomycodes", providerTaxonomyCodeService.findAllProviderTaxonomyCodes());
    }

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
}
