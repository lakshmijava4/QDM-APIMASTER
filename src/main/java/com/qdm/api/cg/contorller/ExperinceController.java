package com.qdm.api.cg.contorller;

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

import com.qdm.api.cg.entity.Category;
import com.qdm.api.cg.entity.Experience;
import com.qdm.api.cg.entity.Organization;
import com.qdm.api.cg.response.ResponseInfo;
import com.qdm.api.cg.response.ResponseType;
import com.qdm.api.cg.service.ExperinceService;

@RestController
@RequestMapping("/experince")
@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
public class ExperinceController {
	
	@Autowired
	ExperinceService  service;
	
	@PostMapping(value = "/addExperincedemoList", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addExperinceddemoList(@RequestBody Experience experience) {
		ResponseEntity response = null;
		try {
			Experience categoryData = service.addExperinceddemoList(experience);
			response = new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "Experince List adding Successfully", "Adding Successfully"), HttpStatus.CREATED);
			return response;
		} catch (Exception e) {
			response = new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error while adding record"), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
	
	@GetMapping(value = "/getExperinceList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getExperinceList() {
		ResponseEntity response = null;
		try {
			List<Experience> categoryList = service.getExperinceList();
			List<Object> list=new ArrayList<Object>();
			for (Experience category : categoryList) {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("label", category.getEndingIn());
				map.put("value",category.getOrganizationName());
				list.add(map);
			}
			response = new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "got records", list), HttpStatus.OK);
			return response;
		} catch (Exception e) {
			response = new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Erroor while fetching details"), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
	
	@PutMapping(value = "/updateExperince", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateExperince(@RequestBody Experience experience) {
		try {
			Experience experiencedto = service.updateExperinceDetails(experience);
			return new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "updated sucessfully", experiencedto), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error"), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@DeleteMapping(value ="/deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
		try {
			service.deleteExperienceById(id);
			return new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "Deleted Successfully", "Record Deleted"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error"), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

}
