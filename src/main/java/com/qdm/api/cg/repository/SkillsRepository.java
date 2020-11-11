package com.qdm.api.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qdm.api.cg.entity.Skills;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Integer>{

	Skills findBySkillId(int skillId);

}
