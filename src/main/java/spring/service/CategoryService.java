package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.CategoryDAO;
import spring.model.Category;

@Service
public class CategoryService implements ICRUDService<Category> {

	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	public List<Category> getAll() {
		return categoryDAO.getAll();
	}

	@Override
	public int save(Category category) {
		return categoryDAO.save(category);
	}

	@Override
	public int update(Category category) {
		return categoryDAO.update(category);
	}

	@Override
	public int del(int id) {
		return categoryDAO.del(id);
	}

	@Override
	public Category findById(int id) {
		return categoryDAO.findById(id);
	}

	@Override
	public List<Category> getList(int offset, int rowCount) {
		return categoryDAO.getList(offset, rowCount);
	}

	@Override
	public int totalRow() {
		return categoryDAO.totalRow();
	}

	public Category checkCatDuplicateByName(String name) {
		return categoryDAO.checkCatDuplicateByName(name);
	}

}
