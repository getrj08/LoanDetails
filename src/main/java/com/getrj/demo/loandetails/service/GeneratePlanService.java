package com.getrj.demo.loandetails.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.getrj.demo.loandetails.model.PlanRequest;
import com.getrj.demo.loandetails.model.PlanResponse;

@Service
public class GeneratePlanService implements IGeneratePlanService {

	@Override
	public List<PlanResponse> getPlanDetails(PlanRequest planRequest) {
		// TODO Auto-generated method stub
		LocalDateTime startDate = planRequest.getStartDate();
		int duration = planRequest.getDuration();
		int i = 1;
		int month = startDate.getMonthValue();
		int year = startDate.getYear();
		List<PlanResponse> response1 = new ArrayList<>();
		
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
			double annuity = getAnnuity(planRequest, daysInMonth, daysInYear);
			System.out.println(annuity);
			//double annuity = calculateAnnuityForMonth(planRequest);
			double principal = calculatePrincipalAmount(annuity, interestForMonth);
			remainingOutstandingAmount = initialOutstandingPrincipal - principal;

			if(annuity > initialOutstandingPrincipal) {
				annuity = roundValues(initialOutstandingPrincipal);
				remainingOutstandingAmount = 0;
				principal = calculatePrincipalAmount(annuity, interestForMonth);
			}
			
			
			
			
			
			createResponse.setBorrowerPaymentAmount(annuity);
			createResponse.setInitialOutstandingPrincipal(roundValues(initialOutstandingPrincipal));
			createResponse.setInterest(interestForMonth);
			createResponse.setPrincipal(principal);
			createResponse.setRemainingOutstandingPrincipal(roundValues(remainingOutstandingAmount));
			createResponse.setDate(LocalDateTime.of(year,currentMonth,startDate.getDayOfMonth(),0,0));
			
			
			//System.out.println(daysInMonth);
			i++;
			currentMonth++;
			
			response1.add(createResponse);
		}
		
		//int month = startDate.getMonthValue();
		/*System.out.println(month);
		int year = startDate.getYear();
		YearMonth yearMonthObject = YearMonth.of(year, month);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		
		PlanResponse response = new PlanResponse();
		double interestForMonth = calculateInterestForMonth(planRequest, daysInMonth, initialOutstandingPrincipal);
		response.setInterest(interestForMonth);
		double annuity = calculateAnnuityForMonth(planRequest);
		response.setPrincipal(annuity);
		response.setPrincipal(calculatePrincipalAmount(annuity, interestForMonth));
		response.setBorrowerPaymentAmount(annuity);*/
		
		
		
		
		
		return response1;
	}
	
	private double calculateInterestForMonth(PlanRequest planRequest,int days, double initialOutstandingPrincipal, int daysInYear) {
		double interestValue = (initialOutstandingPrincipal 
				* (planRequest.getNominalRate()/100 * days)) / daysInYear;
		interestValue = Math.round(interestValue * 100.0) / 100.0;
		return interestValue;
	}
	
	private double calculateAnnuityForMonth(PlanRequest planRequest) {
		double principal = planRequest.getLoanAmount();
		double rate = getMonthlyRate((float) planRequest.getNominalRate());
		int duration = planRequest.getDuration();
		double annuity = (double) ((double) 
				(principal*rate*Math.pow((1+rate), duration))/(Math.pow((1+rate),duration)-1));
		annuity = Math.round(annuity * 100.0) / 100.0;
		return annuity;
	}
	
	private float getMonthlyRate(float rate) {
		
		return rate/12/100;
	}
	
	private double calculatePrincipalAmount(double annuity, double interest) {
		double principal = annuity-interest;
		return Math.round(principal * 100.0) / 100.0;
	}
	
	private double getAnnuity(PlanRequest planRequest, int daysInMonth, int daysInYear) {
		double rate = planRequest.getNominalRate() / 100;
        double perPeriodRate = (rate * daysInMonth) / daysInYear;
        double presentValue = planRequest.getLoanAmount() * perPeriodRate;
        double annuity = presentValue /
            (1 - Math.pow(1 + perPeriodRate, -planRequest.getDuration()));
        annuity = Math.round(annuity * 100.0) / 100.0;
		return annuity;
	}
	
	private double roundValues(double value) {
		value = Math.round(value * 100.0) / 100.0;
		return value;
	}

}
