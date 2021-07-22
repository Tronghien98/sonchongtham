package spring.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import spring.model.Category;

@Repository
public class CategoryDAO extends AbstractDAO<Category> {

	@Override
	public List<Category> getAll() {
		String sql = "SELECT * FROM categories ORDER BY id DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
	}

	@Override
	public List<Category> getList(int offset, int rowCount) {
		String sql = "SELECT * FROM categories ORDER BY id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class), offset, rowCount);
	}

	@Override
	public int totalRow() {
		try {
			String sql = "SELECT COUNT(*) FROM categories";
			return jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			System.out.println("Total row category: No data");
		}
		return 0;
	}

	@Override
	public int save(Category category) {
		String sql = "INSERT INTO categories(name) VALUES (?)";
		return jdbcTemplate.update(sql, category.getName());
	}

	@Override
	public int update(Category category) {
		String sql = "UPDATE categories SET name = ? WHERE id = ?";
		return jdbcTemplate.update(sql, category.getName(), category.getId());
	}

	@Override
	public int del(int id) {
		String sql = "DELETE FROM categories WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public Category findById(int id) {
		try {
			String sql = "SELECT * FROM categories WHERE id = ?";
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), id);
		} catch (Exception e) {
			System.out.println("Category by ID " + id + ": No data");
		}
		return null;
	}

	// find by name => check category duplicate
	public Category findByName(String name) {
		try {
			String sql = "SELECT * FROM categories WHERE name = ?";
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), name);
		} catch (Exception e) {
			System.out.println("Category by name '" + name + "': No data");
		}
		return null;
	}

	// search (có phân trang)
	public List<Category> searchByName(String name, int offset, int rowCount) {
		String sql = "SELECT * FROM categories WHERE name LIKE ? ORDER BY id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class), "%" + name + "%", offset, rowCount);
	}

	public int totalRowByName(String name) {
		try {
			String sql = "SELECT COUNT(*) FROM categories WHERE name LIKE ?";
			return jdbcTemplate.queryForObject(sql, Integer.class, "%" + name + "%");
		} catch (Exception e) {
			System.out.println("Total row category by name: No data");
		}
		return 0;
	}

}
