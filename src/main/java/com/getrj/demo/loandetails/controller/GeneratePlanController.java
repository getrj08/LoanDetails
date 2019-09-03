package com.getrj.demo.loandetails.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.getrj.demo.loandetails.model.PlanRequest;
import com.getrj.demo.loandetails.model.PlanResponse;
import com.getrj.demo.loandetails.service.IGeneratePlanService;

@RestController
public class GeneratePlanController {

	@Autowired
	IGeneratePlanService generatePlan;
	
	@PostMapping(value="/generate-plan")
	public ResponseEntity<List<PlanResponse>> getPlanDetails(@Valid @RequestBody PlanRequest planRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(generatePlan.getPlanDetails(planRequest));
		
	}
}
