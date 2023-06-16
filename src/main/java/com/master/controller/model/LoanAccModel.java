/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.master.controller.model;

/**
 *
 * @author Kavishika
 */
public class LoanAccModel extends BankAccount{
    
    private double LoanInterstRate;
    private int LoanTime;
    
    public LoanAccModel(double LoanInterstRate,int LoanTime, String username, String account_id, String account_type,String createdDate,String createdTime, float value){
        super(username, account_id, account_type, createdDate, createdTime, value);
        this.LoanInterstRate=LoanInterstRate;
        this.LoanTime=LoanTime;
    }
    
    public LoanAccModel() {
        super();
        this.LoanInterstRate=0;
        this.LoanTime=0;
    }
    
    public double getLoanInterstRate() {
        return LoanInterstRate;
    }

    public void setLoanInterstRate(double LoanInterstRate) {
        this.LoanInterstRate = LoanInterstRate;
    }

    public int getLoanTime() {
        return LoanTime;
    }

    public void setLoanTime(int LoanTime) {
        this.LoanTime = LoanTime;
    }
    
}
