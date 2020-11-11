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
@Table(name = "TB_EXPERIENCE_LIST")
public class Experience  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "Role_ID")
	int roleId;

	@Column(name = "Role_Name")
	String roleName;	
	
	@Column(name = "Organization_ID")
	int organizationId;

	@Column(name = "Organization_Name")
	String organizationName;
	
	@Column(name = "Starting_From")
	String startingFrom;
	
	@Column(name = "Ending_From")
	String endingIn;

	@Column(name = "Order_No")
	int orderNo;

	public Experience(int orderNo) {
		super();
		this.orderNo = orderNo;
	}

}

