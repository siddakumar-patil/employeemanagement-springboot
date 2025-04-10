package com.example.emsbackend.mapper;

import com.example.emsbackend.dto.EmployeeDto;
import com.example.emsbackend.entity.Employee;

public class EmployeeMapper {

	public static EmployeeDto mapToEmployeeDto(Employee emp) {
		return new EmployeeDto(emp.getEmp_id(), emp.getFirstName(), emp.getSecondName(), emp.getEmail()

		);
	}

	public static Employee mapToEmployee(EmployeeDto emp) {
		return new Employee(emp.getEmp_id(), emp.getFirstName(), emp.getSecondName(), emp.getEmail());
	}
}
