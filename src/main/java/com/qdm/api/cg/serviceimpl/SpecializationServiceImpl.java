package com.qdm.api.cg.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdm.api.cg.entity.Skills;
import com.qdm.api.cg.entity.SpecializationList;
import com.qdm.api.cg.repository.SpecializationRepository;
import com.qdm.api.cg.service.SpecializationService;

@Service
@Transactional
public class SpecializationServiceImpl implements SpecializationService {

	@Autowired
	SpecializationRepository specializationRepository;

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public SpecializationList addSpecializationList(SpecializationList specialization) {
		SpecializationList specById = specializationRepository.findByValue(specialization.getValue());
		if (specById != null) {
			return null;
		} else {
			return specializationRepository.save(specialization);
		}
	}

	@Override
	public List<SpecializationList> getSpecializationList() {
		return specializationRepository.findAll();
	}

	@Override
	public SpecializationList updateSpecialization(SpecializationList specializationList) {
		SpecializationList specialization = modelMapper.map(specializationList, SpecializationList.class);
		if (specializationList.getValue() != 0) {
			return specializationRepository.save(specialization);
		}
		return null;
	}
	@Override
	public void deleteSpecialization(int value) {
		specializationRepository.deleteById(value);
	}

	@Override
	public Optional<SpecializationList> findById(Integer id) {
		// TODO Auto-generated method stub
		return specializationRepository.findById(id);
	}

	@Override
	public void softdeleteSpecialization(Integer value, boolean status) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		SpecializationList specializationList = specializationRepository.getOne(value);
					if (status) {
						specializationList.setDeleted(true);
					} else {
						specializationList.setDeleted(false);
					}
					specializationRepository.save(specializationList);

				}
	}

