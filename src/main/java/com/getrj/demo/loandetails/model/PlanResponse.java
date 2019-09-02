package com.getrj.demo.loandetails.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class PlanResponse {

	
	private double borrowerPaymentAmount;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDateTime date;
	private double initialOutstandingPrincipal;
	private double interest;
	private double principal;
	private double remainingOutstandingPrincipal;
	
	
	public double getBorrowerPaymentAmount() {
		return borrowerPaymentAmount;
	}
	
	public void setBorrowerPaymentAmount(double borrowerPaymentAmount) {
		this.borrowerPaymentAmount = borrowerPaymentAmount;
	}
	
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
	public double getInitialOutstandingPrincipal() {
		return initialOutstandingPrincipal;
	}
	
	public void setInitialOutstandingPrincipal(double initialOutstandingPrincipal) {
		this.initialOutstandingPrincipal = initialOutstandingPrincipal;
	}
	
	
	public double getInterest() {
		return interest;
	}
	
	public void setInterest(double interest) {
		this.interest = interest;
	}
	
	
	public double getPrincipal() {
		return principal;
	}
	
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	
	
	public double getRemainingOutstandingPrincipal() {
		return remainingOutstandingPrincipal;
	}
	
	public void setRemainingOutstandingPrincipal(double remainingOutstandingPrincipal) {
		this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
	}
	
	
}
