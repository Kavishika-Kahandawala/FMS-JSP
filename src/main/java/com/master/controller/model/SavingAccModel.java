/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.master.controller.model;

/**
 *
 * @author Kavishika
 */
public class SavingAccModel extends BankAccount{
    private float SavingInterstRate;

    public SavingAccModel( float SavingInterstRate,String username, String account_id, String account_type,String createdDate,String createdTime, float value){
        super(username, account_id, account_type, createdDate, createdTime, value);
        this.SavingInterstRate=SavingInterstRate;
    }
    
    public SavingAccModel() {
        super();
        this.SavingInterstRate=0;
    }
    
    public double getSavingInterstRate() {
        return SavingInterstRate;
    }

    public void setSavingInterstRate(float SavingInterstRate) {
        this.SavingInterstRate = SavingInterstRate;
    }
    
    
}
