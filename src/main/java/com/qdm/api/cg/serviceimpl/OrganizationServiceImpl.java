package com.qdm.api.cg.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdm.api.cg.entity.Organization;
import com.qdm.api.cg.repository.OrganizationRepository;
import com.qdm.api.cg.service.OrganizationService;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService{

	@Autowired
	OrganizationRepository organizationRepository;
	@Autowired
	ModelMapper modelMapper;
	@Override
	public Organization addOrganizationList(Organization organization) {
		Organization organizationById = organizationRepository.findByOrganizationId(organization.getOrganizationId());
		if (organizationById != null) {
			return null;
		} else {
			return organizationRepository.save(organization);
		}
	}
	@Override
	public List<Organization> getOrganizationList() {
		return organizationRepository.findAll();
	}
	@Override
	public Organization updateOrganization(Organization organization) {
		Organization organizationlist = modelMapper.map(organization, Organization.class);
		if(organization.getOrganizationId()!=0){
			return organizationRepository.save(organizationlist);
		}
		return null;
	}

	@Override
	public Organization findByorganizationId(int organizationId) {
		return organizationRepository.findByOrganizationId(organizationId);

	}

}
