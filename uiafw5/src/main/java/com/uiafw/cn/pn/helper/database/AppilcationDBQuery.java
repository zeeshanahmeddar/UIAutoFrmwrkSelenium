package com.uiafw.cn.pn.helper.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppilcationDBQuery {
	
	public Double getEmpSalary(int empId) {
		Double salary = 0.0;
		String dbQuery = "select salary from demo.employees where id="+empId;
		ResultSet result = DatabaseHelper.getResultSet(dbQuery);
		try {
			while(result.next()) {
				try {
					salary = Double.parseDouble(result.getString("salary"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salary;
	}
	
	public List<Employee> getAllEmployee() {
		List<Employee> data = new ArrayList<Employee>();
		String dbQuery = "select * from demo.employees";
		ResultSet result = DatabaseHelper.getResultSet(dbQuery);
		try {
			while (result.next()) {
				Employee emp = new Employee();
				emp.setId(Integer.parseInt(result.getString("id")));
				emp.setName(result.getString("first_name"));
				emp.setAddress(result.getString("email"));
				emp.setSalary(Double.parseDouble(result.getString("salary")));
				data.add(emp);
			}
		} catch (Exception e) {
			
		}
		return data;
	}
	
	public static void main(String[] args) {
		AppilcationDBQuery appilcationDBQuery = new AppilcationDBQuery();
		Double s = appilcationDBQuery.getEmpSalary(2);
		System.out.println(s);
		List<Employee> listEmp = appilcationDBQuery.getAllEmployee();
		for(Employee e : listEmp) {
			System.out.print(e.getId()+" ");
			System.out.print(e.getAddress()+" ");
			System.out.print(e.getName()+" ");
			System.out.print(e.getSalary()+" ");
			System.out.println();
		}
	}

}
