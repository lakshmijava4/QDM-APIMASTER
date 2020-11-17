package com.qdm.api.cg.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qdm.api.cg.entity.Role;
import com.qdm.api.cg.repository.RoleRepository;
import com.qdm.api.cg.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public Role addRoleList(Role role) {
		System.out.println(role.getRoleId());
		Role roleById = roleRepository.findByRoleId(role.getRoleId());
		if (roleById != null) {
			return null;
		} else {
			return roleRepository.save(role);
		}
	}

	@Override
	public List<Role> getRoleList() {
		return roleRepository.findAll();
	}

	@Override
	public Role updateRoleById(Role role) {
		Role rolelist = modelMapper.map(role, Role.class);
		if (role.getRoleId() != 0) {
			return roleRepository.save(rolelist);
		}
		return null;
	}

	@Override
	public void deleteRoleById(int roleId) {
		// TODO Auto-generated method stub
		roleRepository.deleteById(roleId);
	}

	@Override
	public void softdeleteRole(Integer roleId, boolean status) {
		// TODO Auto-generated method stub
		Role role = roleRepository.getOne(roleId);
		if (status) {
			role.setDeleted(true);
		} else {
			role.setDeleted(false);
		}
		roleRepository.save(role);

	}

}
