package com.myproject.employeeservice;

import com.myproject.ui.model.request.EmployeeDetailsRequestModel;
import com.myproject.ui.model.response.Employee;
import com.myproject.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmployeeService {



    Map<String, Employee> employees;
    Utils utils;


    public EmpServiceImpl(){}

    @Autowired
    public EmpServiceImpl(Utils utils){
        this.utils = utils;
    }

    @Override
    public Employee createEmployee(EmployeeDetailsRequestModel employeeDetails) {

        Employee returnValue = new Employee();
        returnValue.setFirstName(employeeDetails.getFirstName());
        returnValue.setLastName(employeeDetails.getLastName());
        returnValue.setDateOfBirth(employeeDetails.getDateOfBirth());
        returnValue.setEmail(employeeDetails.getEmail());


        returnValue.setDepartment(employeeDetails.getDepartment());
        returnValue.setPosition(employeeDetails.getPosition());

        String employeeID = utils.generatedEmployeeId();
        returnValue.setEmployeeID(employeeID);
        if (employees == null) {
            employees = new HashMap<>();
            employees.put(employeeID,returnValue);

        }

        return returnValue;

    }
}
