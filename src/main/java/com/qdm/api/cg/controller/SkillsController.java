package com.qdm.api.cg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import com.qdm.api.cg.dto.SkillsDTO;
import com.qdm.api.cg.entity.Skills;
import com.qdm.api.cg.response.ResponseInfo;
import com.qdm.api.cg.response.ResponseType;
import com.qdm.api.cg.service.SkillsService;

@RestController
@RequestMapping(value = { "/skills" })
@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
public class SkillsController {

	@Autowired
	SkillsService skillsService;

	@PostMapping(value = "/addSkillsList", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addSkillsList(@RequestBody Skills skills) {
		ResponseEntity response = null;
		try {
			Skills skillsData = skillsService.addSkillsList(skills);
			response = new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "added sucessfully", skillsData), HttpStatus.CREATED);
			return response;
		} catch (Exception e) {
			response = new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error"), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}

	@GetMapping(value = "/getSkillsList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSkillapiList() {
		ResponseEntity response = null;
		try {
			List<Skills> skillsList = skillsService.getSkillsList();
			List<Object> skillList = new ArrayList<Object>();
			for (Skills skills : skillsList) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("label", skills.getSkillName());
				map.put("value", skills.getSkillId());
				skillList.add(map);
			}
			response = new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "getting records sucessfully", skillList), HttpStatus.OK);
			return response;
		} catch (Exception e) {
			response = new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error while fetching records"), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
	@PutMapping(value = "/updateSKills", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateSkillsById(@RequestBody Skills skills) {
		try {
			Skills skillsdto = skillsService.updateSkills(skills);
			return new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "updated sucessfully", skillsdto), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error"), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	//Soft delete operation
		@PutMapping("/deleteSkills")
		public ResponseEntity<?> softdeletecskills(@RequestBody SkillsDTO skillsDTO) {
			try {
				skillsService.softdeletecskills(skillsDTO.getId(),skillsDTO.isStatus());
				return new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
						ResponseType.SUCCESS.getResponseCode(), "soft  record  deleted sucessfully ", "soft deleting  done"), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
						ResponseType.ERROR.getResponseCode(), "Try Again", "softdeleted Not able to delete"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
	@DeleteMapping(value ="/deleteSkillsById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteRoleById(@PathVariable("id") int id) {
		try {
			skillsService.deleteSkillsById(id);
			return new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "Deleted Successfully", "Record deleted"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error"), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
