package com.qdm.api.cg.service;

import java.util.List;

import com.qdm.api.cg.entity.Role;

public interface RoleService {

	Role addRoleList(Role role);
	List<Role> getRoleList();
	Role updateRoleById(Role role);
	void deleteRoleById(int roleId);

}
