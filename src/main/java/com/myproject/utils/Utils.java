package com.myproject.utils;

import org.springframework.stereotype.Service;


@Service
public class Utils {

    private int id = 1000;
    private String employeeID;

    public String generatedEmployeeId() {
        id++;
        this.employeeID = "" + id;


        return employeeID;
    }
}
