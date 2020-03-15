package com.epam.engx.cleancode.dry.task1;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator {

    private static final int AGE = 60;
    private static final double INTEREST_PERCENT = 4.5d;
    private static final double SENIOR_PERCENT = 5.5d;
    private static final int BONUS_AGE = 13;


    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        double interest = 0;
        if (isAccountStartedAfterBonusAge(accountDetails)) {
            if (checkSenior(accountDetails)){
                interest = calculateValueOfInterest(accountDetails, SENIOR_PERCENT);
            }
            else {
                interest = calculateValueOfInterest(accountDetails, INTEREST_PERCENT);
            }
        }
        return BigDecimal.valueOf(interest);
    }

    private boolean isAccountStartedAfterBonusAge(AccountDetails accountDetails) {
        return durationBetweenDatesInYears(accountDetails.getBirth(), accountDetails.getStartDate()) > BONUS_AGE;
    }

    private double calculateValueOfInterest(AccountDetails accountDetails, double percent){
        double interest = accountDetails.getBalance().doubleValue()
                * durationSinceStartDateInYears(accountDetails.getStartDate()) * percent / 100;
    }

    private boolean checkSenior(AccountDetails accountDetails){
        return (AGE <= accountDetails.getAge());
    }

    private int durationBetweenDatesInYears(Date from, Date to) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(from);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(to);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        return (checkFullYear(startCalendar, endCalendar)) ? (diffYear - 1) : diffYear;
    }

    private int durationSinceStartDateInYears(Date startDate) {
        return durationBetweenDatesInYears(startDate, new Date());
    }

    private boolean checkFullYear(Calendar startCalendar, Calendar endCalendar){
        return (endCalendar.get(Calendar.DAY_OF_YEAR) < startCalendar.get(Calendar.DAY_OF_YEAR));
    }
}
