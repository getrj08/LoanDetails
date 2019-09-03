package com.getrj.demo.loandetails.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.getrj.demo.loandetails.model.PlanRequest;
import com.getrj.demo.loandetails.model.PlanResponse;
import com.getrj.demo.loandetails.service.IGeneratePlanService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class GeneratePlanController {

	@Autowired
	IGeneratePlanService generatePlan;
	
	
	@ApiOperation(value = "Generate Loan Plan", response = PlanResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Plan Generated"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
		
	@PostMapping(value="/generate-plan")
	public ResponseEntity<List<PlanResponse>> getPlanDetails(@Valid @RequestBody PlanRequest planRequest) {
		
			return ResponseEntity.status(HttpStatus.OK).body(generatePlan.getPlanDetails(planRequest));
		 
	}
}
