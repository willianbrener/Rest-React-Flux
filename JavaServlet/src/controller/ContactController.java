package controller;


import model.Contact;
import server.ContactDAO;
import util.Conexao;
import util.Return;
import validator.ContactValidator;

public class ContactController {
	ContactDAO dao;
	ContactValidator validator;
	public Return salvar(Contact contact) {
		Return ret = new Return(true);
		dao = new ContactDAO(Conexao.fabricar());
		validator = new ContactValidator();
		try {
			ret = validator.validateContact(contact);
			if (ret.isValid()) {
				ret = dao.cadastrar(contact);
			} else {
				return ret;
			}
		} catch (Exception e) {
			ret.setValid(false);
			e.printStackTrace();
		}
		return ret;
	}

	public Return alterar(Contact contact) {
		Return ret = new Return(true);
		dao = new ContactDAO(Conexao.fabricar());
		try {
			validator = new ContactValidator();
			ret = validator.validateContact(contact);
			if (ret.isValid()) {
				dao.update(contact);
			} else {
				return ret;
			}
		} catch (Exception e) {
			ret.setValid(false);
			e.printStackTrace();
		}
		return ret;
	}

	public Return listarTodos() {
		Return ret = new Return(true);
		ContactDAO dao = new ContactDAO(Conexao.fabricar());
		try {
				ret = dao.listarTodos();
		} catch (Exception e) {
			ret.setValid(false);
			e.printStackTrace();
		}
		return ret;
	}

	public Return deletar(Contact contact) {
		Return ret = new Return(true);
		dao = new ContactDAO(Conexao.fabricar());
		try {
				dao.deletar(contact);
		} catch (Exception e) {
			ret.setValid(false);
			e.printStackTrace();
		}
		return ret;
	}

	public Return pesquisarPorId(Contact contact) {
		Return ret = new Return(true);
		try {
			ret = dao.searchById(contact);
		} catch (Exception e) {
			ret.setValid(false);
			e.printStackTrace();
		}
		return ret;
	}
}
