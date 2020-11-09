package application;

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
	}

}
