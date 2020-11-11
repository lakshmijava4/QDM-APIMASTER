package com.qdm.api.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qdm.api.cg.entity.Experience;

@Repository

public interface ExperienceRepository extends JpaRepository<Experience, Integer> {
	Experience findById(int categoryData);
}
