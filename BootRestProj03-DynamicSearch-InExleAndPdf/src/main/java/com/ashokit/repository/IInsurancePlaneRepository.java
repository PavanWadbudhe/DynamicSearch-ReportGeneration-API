package com.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.InsurancePlanEntity;

public interface IInsurancePlaneRepository extends JpaRepository<InsurancePlanEntity, Integer>{
	@Query("SELECT DISTINCT planName FROM InsurancePlanEntity")
	public List<String> getPlanName();
	@Query("SELECT DISTINCT planStatus FROM InsurancePlanEntity")
	public List<String> getPlaneStatus();

}
