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
import db.DbIntegrityException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			int rows = st.executeUpdate();
			if (rows > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					obj.setId(rs.getInt(1));
				}
				DB.closeResultSet(rs);
			}
		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			int rows = st.executeUpdate();
			System.out.println("Rows affected: " + rows);
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE from department  WHERE Id = ?");
			st.setInt(1, id);
			int rows = st.executeUpdate();
			System.out.println("Rows affected: " + rows);
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
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
		try {
			st = conn.createStatement();
			rs = st.executeQuery("Select * from department ORDER by Name");
			List<Department> list = new ArrayList<>();
			while (rs.next()) {
				Department dp = new Department(rs.getInt("Id"), rs.getString("Name"));
				list.add(dp);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

}
