package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.BlogDAO;
import spring.model.Blog;

@Service
public class BlogService implements ICRUDService<Blog> {

	@Autowired
	private BlogDAO blogDAO;

	@Override
	public List<Blog> getAll() {
		return blogDAO.getAll();
	}

	@Override
	public int save(Blog blog) {
		return blogDAO.save(blog);
	}

	@Override
	public int update(Blog blog) {
		return blogDAO.update(blog);
	}

	public int updateView(int id) {
		return blogDAO.updateView(id);
	}

	@Override
	public int del(int id) {
		return blogDAO.del(id);
	}

	@Override
	public Blog findById(int id) {
		return blogDAO.findById(id);
	}

	@Override
	public List<Blog> getList(int offset, int rowCount) {
		return blogDAO.getList(offset, rowCount);
	}

	@Override
	public int totalRow() {
		return blogDAO.totalRow();
	}

	public List<Blog> getListNew() {
		return blogDAO.getListNew();
	}

	public List<Blog> getListByViews() {
		return blogDAO.getListByViews();
	}

	public List<Blog> getListRelate(int id, int catId) {
		return blogDAO.getListRelate(id, catId);
	}

	public List<Blog> getListByCat(int catId, int offset, int rowCount) {
		return blogDAO.getListByCat(catId, offset, rowCount);
	}

	public int totalRowByCat(int catId) {
		return blogDAO.totalRowByCat(catId);
	}

}
