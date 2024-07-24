package com.ashokit.service;

import java.util.List;

import com.ashokit.binding.SearchOutput;
import com.ashokit.binding.SearchRequest;

public interface IDynamicSearchService {
	public List<SearchOutput> searchPlans(SearchRequest request);
	public List<String> getUniquePlanNames();
	public List<String> getUniquePlanStatuses();

}
