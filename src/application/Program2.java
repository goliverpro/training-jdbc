package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao dp = DaoFactory.createDepartmentDao();
		Department dep = new Department();
		dep = dp.findById(2);
		System.out.println("===== Department findById Test 1 =====");
		System.out.println(dep);
		
		System.out.println("\n===== Department findAll Test 2 =====");
		List<Department> list = new ArrayList<>();
		list.addAll(dp.findAll());
		list.forEach(System.out :: println);
		
		System.out.println("\n===== Department insert Test 3 =====");
		Department depa = new Department(null, "Lavandery");
		dp.insert(depa);
		System.out.println("Sucessful insert! New department id: " + depa.getId());
		
		System.out.println("\n===== Department update Test 4 =====");
		Department depar = new Department(16, "Kitchen");
		dp.update(depar);
	}

}
