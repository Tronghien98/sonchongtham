package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.ContactDAO;
import spring.model.Contact;

@Service
public class ContactService implements ICRUDService<Contact> {

	@Autowired
	private ContactDAO contactDAO;

	@Override
	public List<Contact> getAll() {
		return contactDAO.getAll();
	}

	@Override
	public int save(Contact contact) {
		return contactDAO.save(contact);
	}

	@Override
	public int update(Contact contact) {
		return contactDAO.update(contact);
	}

	public int updateStatus(Contact contact) {
		return contactDAO.updateStatus(contact);
	}

	@Override
	public int del(int id) {
		return contactDAO.del(id);
	}

	@Override
	public Contact findById(int id) {
		return contactDAO.findById(id);
	}

	@Override
	public List<Contact> getList(int offset, int rowCount) {
		return contactDAO.getList(offset, rowCount);
	}

	@Override
	public int totalRow() {
		return contactDAO.totalRow();
	}

	public List<Contact> searchByName(String name, int offset, int rowCount) {
		return contactDAO.searchByName(name, offset, rowCount);
	}

	public int totalRowByName(String name) {
		return contactDAO.totalRowByName(name);
	}

}
