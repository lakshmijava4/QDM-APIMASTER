package com.qdm.api.cg.service;

import java.util.List;

import com.qdm.api.cg.entity.Experience;

public interface ExperinceService {

	Experience addExperinceddemoList(Experience category);

	List<Experience> getExperinceList();
	Experience updateExperinceDetails(Experience experience);

	void deleteExperienceById(int id);

	void softdeletexperince(Integer id, boolean status);

	

}
