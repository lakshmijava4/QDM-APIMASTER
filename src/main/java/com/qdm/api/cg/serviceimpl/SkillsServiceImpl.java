package com.qdm.api.cg.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdm.api.cg.entity.Role;
import com.qdm.api.cg.entity.Skills;
import com.qdm.api.cg.repository.SkillsRepository;
import com.qdm.api.cg.service.SkillsService;

@Service
@Transactional
public class SkillsServiceImpl implements SkillsService{

	@Autowired
	SkillsRepository skillsRepository;
	@Autowired
	ModelMapper modelMapper;
	@Override
	public Skills addSkillsList(Skills skills) {
		Skills skillById = skillsRepository.findBySkillId(skills.getSkillId());
		if (skillById != null) {
			return null;
		} else {
			return skillsRepository.save(skills);
		}
	}

	@Override
	public List<Skills> getSkillsList() {
		return skillsRepository.findAll();
	}

	@Override
	public Skills updateSkills(Skills skills) {
		Skills skillsreq = modelMapper.map(skills, Skills.class);
		if(skills.getId()!=0){
			return skillsRepository.save(skillsreq);
		}
		return null;
	}

	
	@Override
		public void deleteSkillsById(int id) {
			skillsRepository.deleteById(id);
		}

		@Override
		public void softdeletecskills(Integer id, boolean status) {
			// TODO Auto-generated method stub
			Skills skills = skillsRepository.getOne(id);
			if (status) {
				skills.setDeleted(true);
			} else {
				skills.setDeleted(false);
			}
			skillsRepository.save(skills);

		}

	}


