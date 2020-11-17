package com.qdm.api.cg.service;

import java.util.List;

import com.qdm.api.cg.entity.Category;

public interface CategoryService {
	Category addCategoryList(Category category);
	List<Category> getCategoryList();
	Category updateCategory(Category categoryId);
	void softdeleteCategory(Integer id, boolean status);
	void deleteById(Integer id); 
	
	

}
