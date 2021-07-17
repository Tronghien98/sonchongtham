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

}
