package com.qdm.api.cg.service;

import java.util.List;

import com.qdm.api.cg.entity.Organization;

public interface OrganizationService {
	Organization addOrganizationList(Organization organization);
	List<Organization> getOrganizationList();
	Organization updateOrganization(Organization organization);
	Organization findByorganizationId(int organizationId);
}
