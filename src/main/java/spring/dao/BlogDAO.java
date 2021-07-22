package spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import spring.model.Blog;
import spring.model.Category;
import spring.model.User;

@Repository
public class BlogDAO extends AbstractDAO<Blog> {

	@Override
	public List<Blog> getAll() {
		String sql = "SELECT * FROM blogs b INNER JOIN categories c ON b.catId = c.id"
				+ " INNER JOIN users u ON b.userId = u.id ORDER BY b.id DESC";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Blog>>() {
			List<Blog> list = new ArrayList<Blog>();

			@Override
			public List<Blog> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new Blog(rs.getInt("b.id"), rs.getString("title"),
							new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("detail"),
							new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
									rs.getString("avatar")),
							rs.getString("picture"), rs.getInt("views"), rs.getTimestamp("b.createAt")));
				}
				return list;
			}
		});
	}

	@Override
	public Blog findById(int id) {
		String sql = "SELECT * FROM blogs b INNER JOIN categories c ON b.catId = c.id"
				+ " INNER JOIN users u ON b.userId = u.id WHERE b.id = ?";
		List<Blog> list = jdbcTemplate.query(sql, new ResultSetExtractor<List<Blog>>() {
			List<Blog> list = new ArrayList<Blog>();

			@Override
			public List<Blog> extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					list.add(new Blog(rs.getInt("b.id"), rs.getString("title"),
							new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("detail"),
							new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
									rs.getString("avatar")),
							rs.getString("picture"), rs.getInt("views"), rs.getTimestamp("b.createAt")));
				}
				return list;
			}
		}, id);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Blog> getList(int offset, int rowCount) {
		String sql = "SELECT * FROM blogs b INNER JOIN categories c ON b.catId = c.id"
				+ " INNER JOIN users u ON b.userId = u.id ORDER BY b.id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Blog>>() {
			List<Blog> list = new ArrayList<Blog>();

			@Override
			public List<Blog> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new Blog(rs.getInt("b.id"), rs.getString("title"),
							new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("detail"),
							new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
									rs.getString("avatar")),
							rs.getString("picture"), rs.getInt("views"), rs.getTimestamp("b.createAt")));
				}
				return list;
			}
		}, offset, rowCount);
	}

	@Override
	public int totalRow() {
		try {
			String sql = "SELECT COUNT(*) FROM blogs b INNER JOIN categories c ON b.catId = c.id"
					+ " INNER JOIN users u ON b.userId = u.id";
			return jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			System.out.println("Total row blog: No data");
		}
		return 0;
	}

	@Override
	public int save(Blog blog) {
		String sql = "INSERT INTO blogs(title,catId,detail,userId,picture) VALUES (?,?,?,?,?)";
		return jdbcTemplate.update(sql, blog.getTitle(), blog.getCat().getId(), blog.getDetail(),
				blog.getUser().getId(), blog.getPicture());
	}

	@Override
	public int update(Blog blog) {
		String sql = "UPDATE blogs SET title = ?, catId = ?, detail = ?, picture = ? WHERE id = ?";
		return jdbcTemplate.update(sql, blog.getTitle(), blog.getCat().getId(), blog.getDetail(), blog.getPicture(),
				blog.getId());
	}

	public int updateView(int id) {
		String sql = "UPDATE blogs SET views = views + 1 WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int del(int id) {
		String sql = "DELETE FROM blogs WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	// Lấy tin tức mới nhất
	public List<Blog> getListNew() {
		String sql = "SELECT * FROM blogs b INNER JOIN categories c ON b.catId = c.id"
				+ " INNER JOIN users u ON b.userId = u.id ORDER BY b.id DESC LIMIT 8";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Blog>>() {
			List<Blog> list = new ArrayList<Blog>();

			@Override
			public List<Blog> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new Blog(rs.getInt("b.id"), rs.getString("title"),
							new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("detail"),
							new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
									rs.getString("avatar")),
							rs.getString("picture"), rs.getInt("views"), rs.getTimestamp("b.createAt")));
				}
				return list;
			}
		});
	}

	// Lấy tin tức nổi bật (theo lượt xem)
	public List<Blog> getListByViews() {
		String sql = "SELECT * FROM blogs b INNER JOIN categories c ON b.catId = c.id"
				+ " INNER JOIN users u ON b.userId = u.id ORDER BY b.views DESC LIMIT 8";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Blog>>() {
			List<Blog> list = new ArrayList<Blog>();

			@Override
			public List<Blog> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new Blog(rs.getInt("b.id"), rs.getString("title"),
							new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("detail"),
							new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
									rs.getString("avatar")),
							rs.getString("picture"), rs.getInt("views"), rs.getTimestamp("b.createAt")));
				}
				return list;
			}
		});
	}

	// Lấy tin tức liên quan
	public List<Blog> getListRelate(int id, int catId) {
		String sql = "SELECT * FROM blogs b INNER JOIN categories c ON b.catId = c.id"
				+ " INNER JOIN users u ON b.userId = u.id WHERE b.id != ? AND b.catId = ? ORDER BY RAND() DESC LIMIT 4";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Blog>>() {
			List<Blog> list = new ArrayList<Blog>();

			@Override
			public List<Blog> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new Blog(rs.getInt("b.id"), rs.getString("title"),
							new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("detail"),
							new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
									rs.getString("avatar")),
							rs.getString("picture"), rs.getInt("views"), rs.getTimestamp("b.createAt")));
				}
				return list;
			}
		}, id, catId);
	}

	// phân trang theo danh mục
	public List<Blog> getListByCat(int catId, int offset, int rowCount) {
		String sql = "SELECT * FROM blogs b INNER JOIN categories c ON b.catId = c.id"
				+ " INNER JOIN users u ON b.userId = u.id WHERE b.catId = ? ORDER BY b.id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Blog>>() {
			List<Blog> list = new ArrayList<Blog>();

			@Override
			public List<Blog> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new Blog(rs.getInt("b.id"), rs.getString("title"),
							new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("detail"),
							new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
									rs.getString("avatar")),
							rs.getString("picture"), rs.getInt("views"), rs.getTimestamp("b.createAt")));
				}
				return list;
			}
		}, catId, offset, rowCount);
	}

	public int totalRowByCat(int catId) {
		try {
			String sql = "SELECT COUNT(*) FROM blogs b INNER JOIN categories c ON b.catId = c.id"
					+ " INNER JOIN users u ON b.userId = u.id WHERE b.catId = ?";
			return jdbcTemplate.queryForObject(sql, Integer.class, catId);
		} catch (Exception e) {
			System.out.println("Total row blog by ID: No data");
		}
		return 0;
	}

	// tìm kiếm, phân trang tìm kiếm (theo title)
	public List<Blog> searchByTitle(String title, int offset, int rowCount) {
		String sql = "SELECT * FROM blogs b INNER JOIN categories c ON b.catId = c.id"
				+ " INNER JOIN users u ON b.userId = u.id WHERE b.title LIKE ? ORDER BY b.id DESC LIMIT ?,?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Blog>>() {
			List<Blog> list = new ArrayList<Blog>();

			@Override
			public List<Blog> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					list.add(new Blog(rs.getInt("b.id"), rs.getString("title"),
							new Category(rs.getInt("c.id"), rs.getString("c.name")), rs.getString("detail"),
							new User(rs.getInt("u.id"), rs.getString("username"), rs.getString("fullname"),
									rs.getString("avatar")),
							rs.getString("picture"), rs.getInt("views"), rs.getTimestamp("b.createAt")));
				}
				return list;
			}
		}, "%" + title + "%", offset, rowCount);
	}

	public int totalRowByTitle(String title) {
		try {
			String sql = "SELECT COUNT(*) FROM blogs b INNER JOIN categories c ON b.catId = c.id"
					+ " INNER JOIN users u ON b.userId = u.id WHERE b.title LIKE ?";
			return jdbcTemplate.queryForObject(sql, Integer.class, "%" + title + "%");
		} catch (Exception e) {
			System.out.println("Total row blog by title: No data");
		}
		return 0;
	}

}
