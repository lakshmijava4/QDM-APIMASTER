package com.qdm.api.cg.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExperienceDTO {

	private Integer id;
	private Integer roleId;
	private String roleName;
	private Integer organizationId;
	private String organizationName;
	private String startingFrom;
	private String endingIn;
	private Integer orderNo;
	private boolean status;
	private boolean isDeleted;

}