package com.qdm.api.cg.service;

import java.util.List;

import com.qdm.api.cg.entity.Certification;

public interface CertificationService {

	Certification addCertificateList(Certification certificate);

	List<Certification> getCertificateList();

	Certification updateCertification(Certification certification);

	Certification findByCertificateId(int certificateId);

}
