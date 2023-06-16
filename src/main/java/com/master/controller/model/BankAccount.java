/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.master.controller.model;

/**
 *
 * @author Kavishika
 */
public abstract class BankAccount {

    private String username;
    private String account_id;
    private String account_type;
    private String createdDate;
    private String createdTime;
    private float value;

    public BankAccount(String username, String account_id, String account_type, String createdDate, String createdTime, float value) { //method overloading
        this.username = username;
        this.account_id = account_id;
        this.account_type = account_type;
        this.createdDate=createdDate;
        this.createdTime=createdTime;
        this.value = value;
    }

    public BankAccount() {
        this.username = null;
        this.account_id = null;
        this.account_type = null;
        this.createdDate=null;
        this.createdTime=null;
        this.value = 0.0f;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

}
