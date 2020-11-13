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
import com.qdm.api.cg.response.ResponseInfo;
import com.qdm.api.cg.response.ResponseType;
import com.qdm.api.cg.service.CertificationService;

@RestController
@RequestMapping(value = { "/certificate" })
@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
public class CertificationController {
	

	@Autowired
	CertificationService certificationService;

	@PostMapping(value = "/addCertificate", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addCertificateList(@RequestBody Certification Certificate) {
		ResponseEntity response = null;
		try {
			Certification CertificateData = certificationService.addCertificateList(Certificate);
			response = new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "certificate added sucessfully", "record added"), HttpStatus.CREATED);
			return response;
		} catch (Exception e) {
			response = new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", null), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}

	@GetMapping(value = "/getCertificateList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCertificateList() {
		ResponseEntity response = null;
		try {
			List<Certification> CertificateList = certificationService.getCertificateList();
			List<Object> list = new ArrayList<Object>();
			for (Certification Certificate : CertificateList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("label", Certificate.getCertificateName());
				map.put("value", Certificate.getCertificateId());
				list.add(map);
			}
			response = new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "sucussfully got all records", list), HttpStatus.OK);
			return response;
		} catch (Exception e) {
			response = new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error feteching records"), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}
	@PutMapping(value = "/updateCertification", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCategoryById(@RequestBody Certification certification) {
		try {
			Certification certificationdto = certificationService.updateCertification(certification);
			return new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "updated sucessfully", certificationdto), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error while updating"), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	@DeleteMapping(value ="/deleteCertificationById/{certificateId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByCertificateId(@PathVariable("certificateId") int certificateId) {
		try {
			certificationService.findByCertificateId(certificateId);
			return new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "Deleted Successfully", "Record deleted"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error while creating"), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
