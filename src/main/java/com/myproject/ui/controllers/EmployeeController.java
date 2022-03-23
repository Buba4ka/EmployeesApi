package com.myproject.ui.controllers;


import com.myproject.employeeservice.EmployeeService;
import com.myproject.ui.model.request.EmployeeDetailsRequestModel;
import com.myproject.ui.model.request.UpdateEmployeeDetailsRequestModel;
import com.myproject.ui.model.response.Employee;
import com.myproject.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("employees")//http://localhost:8080/employees
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;
    Map <String, Employee> employees;


//    @GetMapping(path="/{employeeID}")
//    public ResponseEntity<Employee> getUser (@PathVariable String employeeID){
//        Employee returnValue = new Employee();
//        returnValue.setFirstName("Oksana");
//        returnValue.setLastName("Popova");
//
//        return new ResponseEntity<>(returnValue, HttpStatus.OK);
//    }

    @GetMapping(path="/{employeeID}", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Employee> getEmployee(@PathVariable String employeeID) {

        //if(true) throw new EmployeeServiceException ("A user service exception is thrown");

        if(employees.containsKey(employeeID)) {
            return new ResponseEntity<>(employees.get(employeeID), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Employee> createEmployee (@Valid @RequestBody EmployeeDetailsRequestModel employeeDetails) {

//        Employee returnValue = employeeService.createEmployee(employeeDetails);

        Employee returnValue = new Employee();
        returnValue.setFirstName(employeeDetails.getFirstName());
        returnValue.setLastName(employeeDetails.getLastName());
        returnValue.setDateOfBirth(employeeDetails.getDateOfBirth());
        returnValue.setDepartment(employeeDetails.getDepartment());
        returnValue.setPosition(employeeDetails.getPosition());
        returnValue.setEmail(employeeDetails.getEmail());

        Utils utils = new Utils();
        String employeeID = utils.generatedEmployeeId();
        returnValue.setEmployeeID(employeeID);

        if(employees == null) {
            employees = new HashMap<>();
            employees.put(employeeID,returnValue);
        }

        return new ResponseEntity<>(returnValue, HttpStatus.OK);

    }

    @PutMapping(path="/{employeeID}", consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})

    public Employee updateEmployee(@PathVariable String employeeID,
                                   @Valid @RequestBody UpdateEmployeeDetailsRequestModel employeeDetails) {

        Employee storedEmployeeDetails = employees.get(employeeID);
        storedEmployeeDetails.setLastName(employeeDetails.getLastName());
        storedEmployeeDetails.setFirstName(employeeDetails.getFirstName());
        storedEmployeeDetails.setDepartment(employeeDetails.getDepartment());
        storedEmployeeDetails.setPosition(employeeDetails.getPosition());

        employees.put(employeeID,storedEmployeeDetails);

        return storedEmployeeDetails;
    }

    @DeleteMapping(path="/{deleteID}", consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteEmployee (@PathVariable String employeeID) {

        employees.remove(employeeID);

        return ResponseEntity.noContent().build();
    }





}
