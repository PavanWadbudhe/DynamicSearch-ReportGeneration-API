package com.ashokit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.binding.SearchOutput;
import com.ashokit.binding.SearchRequest;
import com.ashokit.entity.InsurancePlanEntity;
import com.ashokit.repository.IInsurancePlaneRepository;

@Service
public class DynamicSearchServiceImpl implements IDynamicSearchService {
	@Autowired
	private IInsurancePlaneRepository repo;

	@Override
	public List<SearchOutput> searchPlans(SearchRequest request) {
		InsurancePlanEntity planEntity=new InsurancePlanEntity();
		
		if(request != null && request.getPlanName() != null  &&  !request.getPlanName().equals("")) {
			planEntity.setPlanName(request.getPlanName());
		}
		if(request != null && request.getPlanStatus() != null  &&  !request.getPlanStatus().equals("")) {
			planEntity.setPlanStatus(request.getPlanStatus());
		}
		
		Example<InsurancePlanEntity> example=Example.of(planEntity);
		//use repo
		List<InsurancePlanEntity> list=repo.findAll(example); 
		List<SearchOutput> outputList=new ArrayList<>();
		list.forEach(plan->{
			SearchOutput output=new SearchOutput();
			BeanUtils.copyProperties(plan, output);
			outputList.add(output);
		});
		return outputList;
	}

	@Override
	public List<String> getUniquePlanNames() {
		//use repo
		return repo.getPlanName();
	}

	@Override
	public List<String> getUniquePlanStatuses() {
		//use repo
		return repo.getPlaneStatus();
	}

}
