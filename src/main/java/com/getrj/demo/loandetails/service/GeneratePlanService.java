package com.getrj.demo.loandetails.service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.getrj.demo.loandetails.model.PlanRequest;
import com.getrj.demo.loandetails.model.PlanResponse;
import com.getrj.demo.loandetails.utility.GeneratorUtility;

@Service
public class GeneratePlanService implements IGeneratePlanService {

	@Override
	public List<PlanResponse> getPlanDetails(PlanRequest planRequest) {
		LocalDateTime startDate = planRequest.getStartDate();
		int duration = planRequest.getDuration();
		int i = 1;
		int month = startDate.getMonthValue();
		int year = startDate.getYear();
		List<PlanResponse> planResponse = new ArrayList<>();
		
		double initialOutstandingPrincipal = 0;
		double remainingOutstandingAmount = 0;
		int currentMonth = month;
		while(i <= duration) {
			
			if(currentMonth > 12) {
				currentMonth = 1;
				year++;
			}
			YearMonth yearMonthObject = YearMonth.of(year,currentMonth);
			int daysInMonth = yearMonthObject.lengthOfMonth();
			int daysInYear = yearMonthObject.lengthOfYear();
			
			PlanResponse createResponse = new PlanResponse();
			
			if(initialOutstandingPrincipal == 0) {
				initialOutstandingPrincipal = planRequest.getLoanAmount();
			} else {
				initialOutstandingPrincipal = remainingOutstandingAmount;
			}
			
			double interestForMonth = calculateInterestForMonth(planRequest, daysInMonth, initialOutstandingPrincipal, daysInYear);
			double annuity = GeneratorUtility.getAnnuity(planRequest.getNominalRate(),planRequest.getLoanAmount(),planRequest.getDuration(), daysInMonth, daysInYear);
			double principal = GeneratorUtility.calculatePrincipalAmount(annuity, interestForMonth);
			remainingOutstandingAmount = initialOutstandingPrincipal - principal;

			if(annuity > initialOutstandingPrincipal) {
				annuity = GeneratorUtility.roundValues(initialOutstandingPrincipal);
				remainingOutstandingAmount = 0;
				principal = GeneratorUtility.calculatePrincipalAmount(annuity, interestForMonth);
			}
			
			getPlanResponse(startDate, year, initialOutstandingPrincipal, remainingOutstandingAmount, currentMonth,
					createResponse, interestForMonth, annuity, principal);
			i++;
			currentMonth++;
			
			planResponse.add(createResponse);
		}
		
		return planResponse;
	}
	
	@Override
	public double calculateInterestForMonth(PlanRequest planRequest,int days, double initialOutstandingPrincipal, int daysInYear) {
		double interestValue = (initialOutstandingPrincipal 
				* (planRequest.getNominalRate()/100 * days)) / daysInYear;
		interestValue = Math.round(interestValue * 100.0) / 100.0;
		return interestValue;
	}

	private void getPlanResponse(LocalDateTime startDate, int year, double initialOutstandingPrincipal,
			double remainingOutstandingAmount, int currentMonth, PlanResponse createResponse, double interestForMonth,
			double annuity, double principal) {
		createResponse.setBorrowerPaymentAmount(annuity);
		createResponse.setInitialOutstandingPrincipal(GeneratorUtility.roundValues(initialOutstandingPrincipal));
		createResponse.setInterest(interestForMonth);
		createResponse.setPrincipal(principal);
		createResponse.setRemainingOutstandingPrincipal(GeneratorUtility.roundValues(remainingOutstandingAmount));
		createResponse.setDate(LocalDateTime.of(year,currentMonth,startDate.getDayOfMonth(),startDate.getHour(),startDate.getMinute()));
	}
	
}
