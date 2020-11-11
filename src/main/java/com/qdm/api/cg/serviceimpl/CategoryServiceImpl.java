package com.qdm.api.cg.serviceimpl;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdm.api.cg.entity.Category;
import com.qdm.api.cg.repository.CategoryRepository;
import com.qdm.api.cg.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	CategoryRepository categoryRepository;


	@Override
	public Category addCategoryList(Category category) { 
		Category categoryById = categoryRepository.findByCategoryId(category.getCategoryId());
		if (categoryById != null) { return null; } else { return
				categoryRepository.save(category); 
		}
	}


	@Override
	public List<Category> getCategoryList() {
		return categoryRepository.findAll();
	}

	@Override
	public Category updateCategory(Category category) {
		Category categoryupdateList = modelMapper.map(category, Category.class);
		if(category.getCategoryId()!=0){
			return categoryRepository.save(categoryupdateList);
		}
		return null;
	}

	@Override
	public void deleteById(int id) {
		categoryRepository.deleteById(id);
	}


}

