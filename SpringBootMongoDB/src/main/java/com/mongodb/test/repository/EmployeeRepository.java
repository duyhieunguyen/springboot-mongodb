package com.mongodb.test.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mongodb.test.document.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, Long>{
	Employee findByEmpNo(String empNo);
	
	List<Employee> findByFullNameLike(String fullName);
	
	List<Employee> findByHireDateGreaterThan(Date hireDate);
	
	//JSON query string
	@Query("{fullName:'?0'}")
	List<Employee> findCustomByFullName(String fullName);

}
