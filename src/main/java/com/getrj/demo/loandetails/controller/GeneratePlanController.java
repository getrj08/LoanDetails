package com.getrj.demo.loandetails.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.getrj.demo.loandetails.model.PlanRequest;
import com.getrj.demo.loandetails.model.PlanResponse;

@Controller
public class GeneratePlanController {

	@PostMapping(value="/generate-plan")
	public ResponseEntity<PlanResponse> getPlanDetails(@Valid @RequestBody PlanRequest planRequest) {
		return null;
		
	}
}
