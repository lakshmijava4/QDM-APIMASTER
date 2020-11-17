package com.qdm.api.cg.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdm.api.cg.entity.Certification;
import com.qdm.api.cg.repository.CertificationRepository;
import com.qdm.api.cg.service.CertificationService;

@Service
@Transactional
public class CertificationServiceImpl implements CertificationService {

	@Autowired
	CertificationRepository certificationRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public Certification addCertificateList(Certification certificate) {
		Certification certificateById = certificationRepository.findByCertificateId(certificate.getCertificateId());
		if (certificateById != null) {
			return null;
		} else {
			return certificationRepository.save(certificate);
		}
	}

	@Override
	public List<Certification> getCertificateList() {
		return certificationRepository.findAll();
	}

	@Override
	public Certification updateCertification(Certification certification) {
		Certification certificationList = modelMapper.map(certification, Certification.class);
		if (certification.getCertificateId() != 0) {
			return certificationRepository.save(certificationList);
		}
		return null;
	}

	@Override
	public void findByCertificateId(int certificateId) {
		// TODO Auto-generated method stub
		 certificationRepository.deleteById(certificateId);
	}

	@Override
	public void softdeletecertification(Integer certificateId, boolean status) {
		// TODO Auto-generated method stub
		Certification certificationRes = certificationRepository.getOne(certificateId);
		if (status) {
			certificationRes.setDeleted(true);
		} else {
			certificationRes.setDeleted(false);
		}
		certificationRepository.save(certificationRes);
		
	}
	
	
}


