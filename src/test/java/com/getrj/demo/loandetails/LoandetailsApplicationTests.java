package com.getrj.demo.loandetails;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.getrj.demo.loandetails.model.PlanRequest;
import com.getrj.demo.loandetails.model.PlanResponse;
import com.getrj.demo.loandetails.service.IGeneratePlanService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoandetailsApplicationTests {

	@Autowired
	IGeneratePlanService planService;
	
	@Test
	public void getPlanDetails() {
		PlanRequest request = new PlanRequest();
		request.setDuration(24);
		request.setLoanAmount(5000);
		request.setNominalRate(5);
		LocalDateTime date = LocalDateTime.of(2019, 01, 01, 0, 0);
		request.setStartDate(date);
		
		List<PlanResponse> generatedPlan = planService.getPlanDetails(request);
		assertEquals(generatedPlan.size(), request.getDuration());
		
		//for 31 days since we are starting from january
		PlanResponse janResponse = generatedPlan.get(0);
		assertEquals(janResponse.getBorrowerPaymentAmount(), 219.57,0.001);
		assertEquals(janResponse.getPrincipal(),198.34, 0.001);
		assertEquals(janResponse.getInterest(),21.23, 0.001);
		assertEquals(janResponse.getDate(), date);
		assertEquals(janResponse.getInitialOutstandingPrincipal(),5000, 0.001);
		assertEquals(janResponse.getRemainingOutstandingPrincipal(),4801.66, 0.001);
		
		//for 28 days feb
		PlanResponse febResponse = generatedPlan.get(1);
		assertEquals(febResponse.getBorrowerPaymentAmount(), 218.47,0.001);
		assertEquals(febResponse.getPrincipal(),200.05, 0.001);
		assertEquals(febResponse.getInterest(),18.42, 0.001);
		assertEquals(febResponse.getDate(), LocalDateTime.of(2019, 02, 01, 0, 0));
		assertEquals(febResponse.getInitialOutstandingPrincipal(),4801.66, 0.001);
		assertEquals(febResponse.getRemainingOutstandingPrincipal(),4601.61, 0.001);
		
		//for 30days april
		PlanResponse aprilResponse = generatedPlan.get(3);
		assertEquals(aprilResponse.getBorrowerPaymentAmount(), 219.2,0.001);
		assertEquals(aprilResponse.getPrincipal(),201.11, 0.001);
		assertEquals(aprilResponse.getInterest(),18.09, 0.001);
		assertEquals(aprilResponse.getDate(), LocalDateTime.of(2019, 04, 01, 0, 0));
		assertEquals(aprilResponse.getInitialOutstandingPrincipal(),4401.58, 0.001);
		assertEquals(aprilResponse.getRemainingOutstandingPrincipal(),4200.47, 0.001);
		
		//for final installment 
		PlanResponse lastResponse = generatedPlan.get(23);
		assertEquals(lastResponse.getBorrowerPaymentAmount(), 218.21,0.001);
		assertEquals(lastResponse.getPrincipal(),217.29, 0.001);
		assertEquals(lastResponse.getInterest(),0.92, 0.001);
		assertEquals(lastResponse.getDate(), LocalDateTime.of(2020, 12, 01, 0, 0));   // date for 24th month
		assertEquals(lastResponse.getInitialOutstandingPrincipal(),218.21, 0.001);
		assertEquals(lastResponse.getRemainingOutstandingPrincipal(),0, 0.001);  //since it is last installment
		
		
	}
	
	@Test
	public void calculateMonthInterestTest() {
		PlanRequest request = new PlanRequest();
		request.setDuration(24);
		request.setLoanAmount(5000);
		request.setNominalRate(5);
		LocalDateTime date = LocalDateTime.of(2019, 01, 01, 0, 0);
		request.setStartDate(date);
		
		double interestFor31days = planService.calculateInterestForMonth(request, 31, 5000, 365);
		assertEquals(interestFor31days,21.23, 0.001);
		
		double interestFor30days = planService.calculateInterestForMonth(request, 30, 5000, 365);
		System.out.println(interestFor30days);
		assertEquals(interestFor30days,20.55, 0.001);
		
		double interestFor28days = planService.calculateInterestForMonth(request, 28, 5000, 365);
		System.out.println(interestFor28days);
		assertEquals(interestFor28days, 19.18,0.001);
		
	}

}
