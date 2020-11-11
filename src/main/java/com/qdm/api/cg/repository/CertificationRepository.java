package com.qdm.api.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qdm.api.cg.entity.Certification;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Integer> {

	Certification findByCertificateId(int certificateId);

}
