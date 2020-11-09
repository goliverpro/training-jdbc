package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		Department dep = new Department();
		try {
			st = conn.prepareStatement("SELECT * from department WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				dep = new Department(rs.getInt("Id"), rs.getString("Name"));
			}
			if (dep.getId() == null) {
				throw new SQLException("Unexpected error! Id not exist.");
			}
			return dep;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Department> findAll() {
		Statement st = null;
		ResultSet rs = null;
		List<Department> list = new ArrayList<>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("Select * from department ORDER by Name");
			while (rs.next()) {
				Department dp = new Department(rs.getInt("Id"), rs.getString("Name"));
				list.add(dp);
				/*if (list.get(0) == null) {
					throw new SQLException("DB empty");
				}*/
				
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

}
