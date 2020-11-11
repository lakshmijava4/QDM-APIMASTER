package com.qdm.api.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qdm.api.cg.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findByCategoryId(int categoryData);

}
