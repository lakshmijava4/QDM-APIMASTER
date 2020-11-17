package com.qdm.api.cg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "TB_CERTIFICATION_CAREGIVER")
public class Certification {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Certificate_ID")
	private int certificateId;
	
	@Column(name="Certificate_Name")
	private String certificateName;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	@Column(name="status")
	private boolean status;

	
}
