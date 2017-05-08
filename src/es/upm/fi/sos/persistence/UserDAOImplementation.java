package es.upm.fi.sos.persistence;

import java.util.List;


import es.upm.fi.sos.model.User;
import java.util.ArrayList;
import java.sql.*;

public class UserDAOImplementation extends AbstractDAO implements UserDAO {
	@Override
	public List<User> findAll() {
		List<User> entities = new ArrayList<User>();
		String sqlFindAll = "select * from user";
		try {
			this.openConnection();

			Statement st = this.getConnection().createStatement();

			ResultSet result = st.executeQuery(sqlFindAll);
			while (result.next()) {
				User entity = new User();
				entity.setId(result.getInt("user_id"));
				entity.setName(result.getString("user_name"));
				entity.setSchool(result.getString("user_school"));
				entity.setPhoto(result.getString("user_photo"));

				entities.add(entity);
			}
			result.close();
			st.close();
			this.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entities;
	}

	public User findByPK(int p_id) {
		User entity = null;
		String sqlFindByPK = "select * from user where user_id = ?";
		try {
			this.openConnection();

			PreparedStatement ps = this.getConnection().prepareStatement(sqlFindByPK);

			ps.setInt(1, p_id);
			ResultSet result = ps.executeQuery();
			result.next();
			entity = new User();

			entity.setId(result.getInt("user_id"));
			entity.setName(result.getString("user_name"));
			entity.setSchool(result.getString("user_school"));
			entity.setPhoto(result.getString("user_photo"));

			result.close();
			ps.close();
			this.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}


	public User save(User p_client) {
		String insertSql = "INSERT INTO user(name, school, photo) VALUES (?, ?, ?)";
		User savedUser = null;
		try {
			this.openConnection();
			PreparedStatement ps = getConnection().prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p_client.getName());
			ps.setString(2, p_client.getSchool());
			ps.setString(3, p_client.getPhoto());

			ps.executeUpdate();
			ResultSet result = ps.getGeneratedKeys();
			if (result.next()) {
				savedUser = this.findByPK(result.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savedUser;
	}

	@Override
	public User update(User p_client) {
		User updatedUser = null;
		String updateSql = "update user set name = ?,"
				+ "school = ?,"
				+ "photo = ? "
				+ "where user_id = ?";
		try {
			this.openConnection();
			PreparedStatement ps = getConnection().prepareStatement(updateSql);
			ps.setString(1, p_client.getName());
			ps.setString(2, p_client.getSchool());
			ps.setString(3, p_client.getPhoto());
			ps.setInt(5, p_client.getId());

			ps.executeUpdate();

			ps.close();
			closeConnection();

			updatedUser = this.findByPK(p_client.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updatedUser;
	}

	@Override
	public void delete(User p_client) {
		String sqlDelete = "delete from user where user_id = ?";
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection().prepareStatement(sqlDelete);

			ps.setInt(1, p_client.getId());
			ps.executeUpdate();
			ps.close();
			this.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> findSomeOfThem(String name) {
		List<User> entities = new ArrayList<User>();
		String sqlFindSome = "select * from user where user_name = ? ";
		try {
			this.openConnection();
			PreparedStatement ps = this.getConnection().prepareStatement(sqlFindSome);
			ps.setString(1, name);
			ResultSet result = ps.executeQuery(sqlFindSome);
			while (result.next()) {
				User entity = new User();
				entity.setId(result.getInt("user_id"));
				entity.setName(result.getString("user_name"));
				entity.setSchool(result.getString("user_school"));
				entity.setPhoto(result.getString("user_photo"));
				entities.add(entity);
			}
			result.close();
			ps.close();
			this.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entities;
	}
}
