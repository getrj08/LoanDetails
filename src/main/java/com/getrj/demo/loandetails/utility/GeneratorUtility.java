package com.getrj.demo.loandetails.utility;

public class GeneratorUtility {

	public static double calculatePrincipalAmount(double annuity, double interest) {
		double principal = annuity-interest;
		return Math.round(principal * 100.0) / 100.0;
	}
	
	public static double getAnnuity(double nominalRate,double loanAmount,int duration, int daysInMonth, int daysInYear) {
		double rate = nominalRate / 100;
        double perPeriodRate = (rate * daysInMonth) / daysInYear;
        double presentValue = loanAmount * perPeriodRate;
        double annuity = presentValue /
            (1 - Math.pow(1 + perPeriodRate, -duration));
        annuity = Math.round(annuity * 100.0) / 100.0;
		return annuity;
	}
	
	public static double roundValues(double value) {
		value = Math.round(value * 100.0) / 100.0;
		return value;
	}
}
