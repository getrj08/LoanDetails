package com.getrj.demo.loandetails.service;

import java.util.List;

import com.getrj.demo.loandetails.model.PlanRequest;
import com.getrj.demo.loandetails.model.PlanResponse;

public interface IGeneratePlanService {

	public List<PlanResponse> getPlanDetails(PlanRequest planRequest);
	
	public double calculateInterestForMonth(PlanRequest planRequest,int days, double initialOutstandingPrincipal, int daysInYear);
}
