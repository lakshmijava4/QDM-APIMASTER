package com.qdm.api.cg.service;

import java.util.List;

import com.qdm.api.cg.entity.Skills;

public interface SkillsService {

	Skills addSkillsList(Skills skills);

	List<Skills> getSkillsList();

	Skills updateSkills(Skills skills);

	void deleteSkillsById(int id);

	void softdeletecskills(Integer id, boolean status);

}
