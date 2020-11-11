package com.qdm.api.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qdm.api.cg.entity.SpecializationList;

@Repository
public interface SpecializationRepository extends JpaRepository<SpecializationList, Integer> {

	SpecializationList findByValue(int value);

}
