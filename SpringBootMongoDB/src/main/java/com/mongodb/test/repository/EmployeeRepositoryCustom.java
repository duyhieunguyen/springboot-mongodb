package com.mongodb.test.repository;

import java.util.Date;

public interface EmployeeRepositoryCustom {
	
	public long getMaxEmpld();
	public long updateEmployee(String empNo, String fullName, Date hireDate);
}
