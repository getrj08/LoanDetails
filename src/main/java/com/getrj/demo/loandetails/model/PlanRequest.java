package com.getrj.demo.loandetails.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PlanRequest {

	@Positive (message = "Loan Amount must be greater than 0")
	private double loanAmount;
	@Positive (message = "Nominal Rate cannot be less or equal 0")
	@Max(value = 100, message = "Nominal Rate cannot exceed 100%")
	private double nominalRate;
	@Positive (message = "Duration cannot be less or equal 0")
	private int duration;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDateTime startDate;
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public double getNominalRate() {
		return nominalRate;
	}
	public void setNominalRate(double nominalRate) {
		this.nominalRate = nominalRate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	
	
}
