package com.qdm.api.cg.contorller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qdm.api.cg.entity.Category;
import com.qdm.api.cg.response.ResponseInfo;
import com.qdm.api.cg.response.ResponseType;
import com.qdm.api.cg.service.CategoryService;


@RestController
@RequestMapping(value = { "/category" })
@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
public class CategoryController {

	@Autowired
	CategoryService categoryService;


	@PostMapping(value = "/addCategoryList", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = {MediaType.APPLICATION_JSON_VALUE }) 
	public ResponseEntity<?> addCategoryList( @RequestBody Category category) 
	{
		ResponseEntity response =null;
	 try { 
		Category categoryData = categoryService.addCategoryList(category);
		response = new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),ResponseType.SUCCESS.getResponseCode(), "added sucussfully",
					"adding category sucessfully"), HttpStatus.CREATED); 
	return response; } catch
	(Exception e) {
		response = new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error"),
			HttpStatus.INTERNAL_SERVER_ERROR); return response; 
			} 
	 }
	
	@GetMapping(value = "/getCategoryList",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCategoryList() {
		ResponseEntity response = null;
		try {
			List<Category> categoryList = categoryService.getCategoryList();
			List<Object> list=new ArrayList<Object>();
			for (Category category : categoryList) {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("label", category.getCategoryName());
				map.put("value",category.getCategoryId());
				list.add(map);
			}
			response = new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "sucussfully got categoryList", list), HttpStatus.OK);
			return response;
		} catch (Exception e) {
			response = new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error Not getting values"), HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}

	@PutMapping(value = "/updateCategory", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCategoryById(@RequestBody Category category) {
		try {
			Category categorydto = categoryService.updateCategory(category);
			return new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "updated sucessfully", categorydto), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Not able to update"), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	@DeleteMapping(value ="/deleteCategoryById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
		try {
			categoryService.deleteById(id);
			return new ResponseEntity(new ResponseInfo(ResponseType.SUCCESS.getResponseMessage(),
					ResponseType.SUCCESS.getResponseCode(), "Deleted Successfully", "Record deleted"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new ResponseInfo(ResponseType.ERROR.getResponseMessage(),
					ResponseType.ERROR.getResponseCode(), "Try Again", "Error while creating"), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

}
