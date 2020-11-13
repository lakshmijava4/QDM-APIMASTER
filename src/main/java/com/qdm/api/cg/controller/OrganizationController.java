package com.qdm.api.cg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qdm.api.cg.entity.Certification;
import com.qdm.api.cg.entity.Organization;
import com.qdm.api.cg.response.ResponseInfo;
import com.qdm.api.cg.response.ResponseType;
import com.qdm.api.cg.service.OrganizationService;

@RestController
@RequestMapping(value = { "/organization" })
@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
public class OrganizationController {
	

	@Autowired
	OrganizationService organizationService;

	@PostMapping(value = "/addOrganization", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addOrganizationList(@RequestBody Organization organization) {
		ResponseEntity response = null;
		try {
			Organization organizationData = organizationService.addOrganizationList(organization);
			response = new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "added sucessfully", organizationData), HttpStatus.CREATED);
			return response;
		} catch (Exception e) {
			response = new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error"), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}

	@GetMapping(value = "/getOrganizationList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getOrganizationList() {
		ResponseEntity response = null;
		try {
			List<Organization> organizationList = organizationService.getOrganizationList();
			List<Object> list=new ArrayList<Object>();
			for (Organization organization : organizationList) {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("label", organization.getOrganizationName());
				map.put("value",organization.getOrganizationId());
				list.add(map);
			}
			response = new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "getting records", list), HttpStatus.OK);
			return response;
		} catch (Exception e) {
			response = new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error"), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
	@PutMapping(value = "/updateOrganization", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCategoryById(@RequestBody Organization organization) {
		try {
			Organization organizationres = organizationService.updateOrganization(organization);
			return new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "", organizationres), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", null), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	@DeleteMapping(value ="/deleteorganization/{organizationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteorganizationById(@PathVariable("organizationId") int certificateId) {
		try {
			organizationService.findByorganizationId(certificateId	);
			return new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "Deleted Successfully", "Record deleted"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error"), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
