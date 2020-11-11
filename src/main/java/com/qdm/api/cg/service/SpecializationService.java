package com.qdm.api.cg.service;

import java.util.List;

import com.qdm.api.cg.entity.SpecializationList;

public interface SpecializationService {

	SpecializationList addSpecializationList(SpecializationList specialization);

	List<SpecializationList> getSpecializationList();

	SpecializationList updateSpecialization(SpecializationList specializationList);
	void deleteSpecialization(int value);
}
