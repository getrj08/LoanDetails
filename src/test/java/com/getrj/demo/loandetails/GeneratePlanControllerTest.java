package com.getrj.demo.loandetails;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.getrj.demo.loandetails.controller.GeneratePlanController;
import com.getrj.demo.loandetails.model.PlanRequest;
import com.getrj.demo.loandetails.model.PlanResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneratePlanControllerTest {

	@Autowired
	GeneratePlanController generateController;
	
	
	@Test
	public void getPlanControllerTest() {
		PlanRequest request = new PlanRequest();
		request.setDuration(24);
		request.setLoanAmount(5000);
		request.setNominalRate(5);
		LocalDateTime date = LocalDateTime.of(2019, 01, 01, 0, 0);
		request.setStartDate(date);
		
		ResponseEntity<List<PlanResponse>> planResponse = generateController.getPlanDetails(request);
		assertEquals(HttpStatus.OK,planResponse.getStatusCode());
		assertEquals(planResponse.getBody().size() , request.getDuration());
	}
	
}
