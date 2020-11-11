package com.qdm.api.cg.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdm.api.cg.entity.Experience;
import com.qdm.api.cg.entity.Organization;
import com.qdm.api.cg.repository.ExperienceRepository;
import com.qdm.api.cg.service.ExperinceService;
@Service
@Transactional
public class ExperinceServiceimpl implements ExperinceService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ExperienceRepository repo;
	

	@Override
	public Experience addExperinceddemoList(Experience experince) {
		Experience categoryById = repo.findById(experince.getId());
		if (categoryById != null) {
			return null;
		} else {
			return repo.save(experince);
		}
	}


	@Override
	public List<Experience> getExperinceList() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}


	@Override
	public Experience updateExperinceDetails(Experience experience) {
		Experience experienceList = modelMapper.map(experience, Experience.class);
		if(experience.getId()!=0){
			return repo.save(experienceList);
		}
		return null;
	}


	@Override
	public void deleteExperienceById(int id) {
		repo.deleteById(id);
	}
}
