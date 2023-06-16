/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.master.controller.model;

/**
 *
 * @author Kavishika
 */
public class FDAccModel extends BankAccount {
    private double FdInterstRate;
    private int FdTime;
    
    public FDAccModel(double FdInterstRate,int FdTime, String username, String account_id, String account_type,String createdDate,String createdTime, float value){
        super(username, account_id, account_type, createdDate, createdTime, value);
        this.FdInterstRate=FdInterstRate;
        this.FdTime=FdTime;
    }
    
    public FDAccModel() {
        super();
        this.FdInterstRate=0;
        this.FdTime=0;
    }
    
    public double getFdInterstRate() {
        return FdInterstRate;
    }

    public void setFdInterstRate(double FdInterstRate) {
        this.FdInterstRate = FdInterstRate;
    }

    public int getFdTime() {
        return FdTime;
    }

    public void setFdTime(int FdTime) {
        this.FdTime = FdTime;
    }

}
