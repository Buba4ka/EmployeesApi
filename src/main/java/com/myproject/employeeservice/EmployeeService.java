package com.myproject.employeeservice;

import com.myproject.ui.model.request.EmployeeDetailsRequestModel;
import com.myproject.ui.model.response.Employee;

public interface EmployeeService {

    Employee createEmployee (EmployeeDetailsRequestModel employeeDetails);

}
