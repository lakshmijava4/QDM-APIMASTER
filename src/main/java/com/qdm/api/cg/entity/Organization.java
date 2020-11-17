package com.qdm.api.cg.entity;

import java.io.Serializable;

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
@Table(name = "TB_ORGANIZATION_LIST")
public class Organization  implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Organization_ID")
	private int organizationId;

	@Column(name = "Organization_Name")
	private String organizationName;
	
	@Column(name="is_deleted")
	private boolean isDeleted;

	public Organization(String organizationName) {
		super();
		this.organizationName = organizationName;
	}


}