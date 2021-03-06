package com.qdm.api.cg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.qdm.api.cg.dto.SpecializationDTO;
import com.qdm.api.cg.entity.SpecializationList;
import com.qdm.api.cg.response.ResponseInfo;
import com.qdm.api.cg.response.ResponseType;
import com.qdm.api.cg.service.SpecializationService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = { "/specialization" })
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SpecializationController {
	@Autowired
	SpecializationService specializationService;
	
	@GetMapping("/addSpecializationList/{id}")
	public SpecializationList retrieveSpecializationById(@PathVariable int id) throws NotFoundException {
		Optional<SpecializationList> student = specializationService.findById(id);
		if (!student.isPresent())
			throw new NotFoundException("id-" + id);
		return student.get();
	}

	@PostMapping(value = "/addSpecializationList", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addSpecializationList(@RequestBody SpecializationList specialization) {
		ResponseEntity response = null;
		try {
			SpecializationList special = specializationService.addSpecializationList(specialization);
			response = new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "added sucessfully", special), HttpStatus.CREATED);
			return response;
		} catch (Exception e) {
			response = new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error"), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}

	@GetMapping(value = "/getSpecializationList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSpecializationList() {
		ResponseEntity response = null;
		try {
			List<SpecializationList> specializationList = specializationService.getSpecializationList();
			List<Object> list=new ArrayList<Object>();
			for (SpecializationList specialization : specializationList) {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("label", specialization.getLabel());
				map.put("value",specialization.getValue());
				list.add(map);
			}
			response = new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "getting records sucessfully", list), HttpStatus.OK);
			return response;
		} catch (Exception e) {
			response = new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error"), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
	
	@PutMapping(value = "/updateSpecalization", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateSpecalizationById(@RequestBody SpecializationList specializationList) {
		try {
			SpecializationList specializationdto = specializationService.updateSpecialization(specializationList);
			return new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "updated sucessfully", specializationdto), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error"), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	
	// Soft delete operation
	@PutMapping("/deleteSkills")
	public ResponseEntity<?> softdeleteSpecialization(@RequestBody SpecializationDTO specializationDTO) {
		try {
			specializationService.softdeleteSpecialization(specializationDTO.getValue(), specializationDTO.isStatus());
			return new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "soft  record  deleted sucessfully ", "soft deleting  done"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "softdeleted Not able to delete"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value ="/deleteSpecializationById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
		try {
			specializationService.deleteSpecialization(id);
			return new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "Deleted Successfully", "Record deleted"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error"), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
