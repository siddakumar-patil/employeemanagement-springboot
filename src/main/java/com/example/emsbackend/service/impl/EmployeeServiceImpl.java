package com.example.emsbackend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.emsbackend.dto.EmployeeDto;
import com.example.emsbackend.entity.Employee;
import com.example.emsbackend.exception.ResourceNotFoundException;
import com.example.emsbackend.mapper.EmployeeMapper;
import com.example.emsbackend.repository.EmployeeRepository;
import com.example.emsbackend.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee emp = EmployeeMapper.mapToEmployee(employeeDto);

		Employee savedEmp = employeeRepository.save(emp);

		return EmployeeMapper.mapToEmployeeDto(savedEmp);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {

		Employee emp = employeeRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("Exployee Data doesn't exists for Emp Id:" + employeeId));
		return EmployeeMapper.mapToEmployeeDto(emp);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> EmpList = employeeRepository.findAll();
		return EmpList.stream().map((emp) -> EmployeeMapper.mapToEmployeeDto(emp)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
		Employee emp = employeeRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("Exployee Data doesn't exists for Emp Id:" + employeeId));
		
		emp.setFirstName(updatedEmployee.getFirstName());
		emp.setSecondName(updatedEmployee.getSecondName());
		emp.setEmail(updatedEmployee.getEmail());
		
		Employee updatedEmp= employeeRepository.save(emp);
		
		return EmployeeMapper.mapToEmployeeDto(updatedEmp);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}

}
