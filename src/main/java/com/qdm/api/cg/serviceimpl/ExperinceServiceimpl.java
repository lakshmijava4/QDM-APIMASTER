package com.qdm.api.cg.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdm.api.cg.entity.Experience;
import com.qdm.api.cg.repository.ExperienceRepository;
import com.qdm.api.cg.service.ExperinceService;
@Service
@Transactional
public class ExperinceServiceimpl implements ExperinceService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ExperienceRepository experienceRepository;
	

	@Override
	public Experience addExperinceddemoList(Experience experince) {
		Experience categoryById = experienceRepository.findById(experince.getId());
		if (categoryById != null) {
			return null;
		} else {
			return experienceRepository.save(experince);
		}
	}


	@Override
	public List<Experience> getExperinceList() {
		// TODO Auto-generated method stub
		return experienceRepository.findAll();
	}


	@Override
	public Experience updateExperinceDetails(Experience experience) {
		Experience experienceList = modelMapper.map(experience, Experience.class);
		if(experience.getId()!=0){
			return experienceRepository.save(experienceList);
		}
		return null;
	}


	@Override
	public void deleteExperienceById(int id) {
		experienceRepository.deleteById(id);
	}


	@Override
	public void softdeletexperince(Integer id, boolean status) {
		// TODO Auto-generated method stub
		Experience experience = experienceRepository.getOne(id);
		if (status) {
			experience.setDeleted(true);
		} else {
			experience.setDeleted(false);
		}
		experienceRepository.save(experience);
		
	}
}
