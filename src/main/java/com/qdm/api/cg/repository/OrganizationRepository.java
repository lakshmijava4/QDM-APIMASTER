package com.qdm.api.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qdm.api.cg.entity.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

	Organization findByOrganizationId(int organizationId);

}
