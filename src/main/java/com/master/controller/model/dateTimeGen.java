/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.master.controller.model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Kavishika
 */
public class dateTimeGen {
    
    public String getDate(){
        LocalDate today=LocalDate.now();
        DateTimeFormatter formatter=DateTimeFormatter.ISO_DATE;
        return today.format(formatter);
    } 
    
    public String getTime(){
        LocalTime time=LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return time.format(formatter);
    }
    
}
