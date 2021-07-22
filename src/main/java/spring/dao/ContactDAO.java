package spring.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import spring.model.Contact;

@Repository
public class ContactDAO extends AbstractDAO<Contact> {

	@Override
	public List<Contact> getAll() {
		String sql = "SELECT * FROM contacts ORDER BY id DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contact.class));
	}

	@Override
	public List<Contact> getList(int offset, int rowCount) {
		String sql = "SELECT * FROM contacts ORDER BY id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contact.class), offset, rowCount);
	}

	@Override
	public int totalRow() {
		try {
			String sql = "SELECT COUNT(*) FROM contacts";
			return jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			System.out.println("Total row contact: No data");
		}
		return 0;
	}

	@Override
	public int save(Contact contact) {
		String sql = "INSERT INTO contacts(name,email,phone,content) VALUES (?,?,?,?)";
		return jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getPhone(),
				contact.getContent());
	}

	@Override
	public int update(Contact contact) {
		String sql = "UPDATE contacts SET name = ?, email = ?, phone = ?, content = ? WHERE id = ?";
		return jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getPhone(), contact.getContent(),
				contact.getId());
	}

	public int updateStatus(Contact contact) {
		String sql = "UPDATE contacts SET status = ? WHERE id = ?";
		return jdbcTemplate.update(sql, contact.getStatus(), contact.getId());
	}

	@Override
	public int del(int id) {
		String sql = "DELETE FROM contacts WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public Contact findById(int id) {
		try {
			String sql = "SELECT * FROM contacts WHERE id = ?";
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Contact.class), id);
		} catch (Exception e) {
			System.out.println("Contact by ID " + id + ": No data");
		}
		return null;
	}

	// search (có phân trang)
	public List<Contact> searchByName(String name, int offset, int rowCount) {
		String sql = "SELECT * FROM contacts WHERE name LIKE ? ORDER BY id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contact.class), "%" + name + "%", offset, rowCount);
	}

	public int totalRowByName(String name) {
		try {
			String sql = "SELECT COUNT(*) FROM contacts WHERE name LIKE ?";
			return jdbcTemplate.queryForObject(sql, Integer.class, "%" + name + "%");
		} catch (Exception e) {
			System.out.println("Total row contact by name: No data");
		}
		return 0;
	}

}
