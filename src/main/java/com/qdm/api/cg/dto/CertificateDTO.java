package com.qdm.api.cg.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CertificateDTO {

	private String certificateName;
	private int organizationId;
	private String organizationName;
	private String startingFrom;
	private String endingIn;
	private int orderNo;

	private Integer certificateId;
	private boolean status;
	private boolean is_deleted;

}
