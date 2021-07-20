package spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import spring.model.Role;
import spring.model.User;

@Repository
public class UserDAO extends AbstractDAO<User> {

	@Override
	public List<User> getAll() {
		String sql = "SELECT * FROM users u INNER JOIN roles r ON u.roleId = r.id ORDER BY u.id DESC";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {
			List<User> list = new ArrayList<User>();

			@Override
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
							rs.getString("password"), rs.getString("email"), rs.getString("phone"),
							rs.getString("avatar"), new Role(rs.getInt("r.id"), rs.getString("r.name")),
							rs.getInt("enabled")));
				}
				return list;
			}
		});
	}

	@Override
	public List<User> getList(int offset, int rowCount) {
		String sql = "SELECT * FROM users u INNER JOIN roles r ON u.roleId = r.id ORDER BY u.id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {
			List<User> list = new ArrayList<User>();

			@Override
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
							rs.getString("password"), rs.getString("email"), rs.getString("phone"),
							rs.getString("avatar"), new Role(rs.getInt("r.id"), rs.getString("r.name")),
							rs.getInt("enabled")));
				}
				return list;
			}
		}, offset, rowCount);
	}

	@Override
	public int totalRow() {
		try {
			String sql = "SELECT COUNT(*) FROM users u INNER JOIN roles r ON u.roleId = r.id";
			return jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			System.out.println("Total row user: No data");
		}
		return 0;
	}

	@Override
	public int save(User user) {
		String sql = "INSERT INTO users(username,fullname,password,email,phone,avatar) VALUES (?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, user.getUsername(), user.getFullname(), user.getPassword(), user.getEmail(),
				user.getPhone(), user.getAvatar());
	}

	@Override
	public int update(User user) {
		String sql = "UPDATE users SET fullname = ?, password = ?, email = ?, phone = ?, avatar = ? WHERE id = ?";
		return jdbcTemplate.update(sql, user.getFullname(), user.getPassword(), user.getEmail(), user.getPhone(),
				user.getAvatar(), user.getId());
	}

	public int updateEnabled(User user) {
		String sql = "UPDATE users SET enabled = ? WHERE id = ?";
		return jdbcTemplate.update(sql, user.getEnabled(), user.getId());
	}

	@Override
	public int del(int id) {
		String sql = "DELETE FROM users WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public User findById(int id) {
		try {
			String sql = "SELECT * FROM users WHERE id = ?";
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
		} catch (Exception e) {
			System.out.println("User by ID " + id + ": No data");
		}
		return null;
	}

	// find by username => check user duplicate
	public User findByUsername(String username) {
		try {
			String sql = "SELECT * FROM users WHERE username = ?";
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
		} catch (Exception e) {
			System.out.println("User by username '" + username + "': No data");
		}
		return null;
	}

}
