package server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ContactController;
import model.Contact;
import util.Return;

@SuppressWarnings("serial")
public class ContactDeleteServlet extends HttpServlet {
	ContactController controller;
	Contact contact = new Contact();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		Return ret = new Return(true);
		controller = new ContactController();
		
		contact.setId(Integer.parseInt(request.getParameter("id")));

		ret = controller.deletar(contact);
		if (ret.isValid()) {
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
