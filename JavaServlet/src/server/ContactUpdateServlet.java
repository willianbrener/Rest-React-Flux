package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ContactController;
import model.Contact;
import util.Conexao;
import util.Return;

@SuppressWarnings("serial")
public class ContactUpdateServlet extends HttpServlet {

	ContactController controller;
	private Contact contact = new Contact();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		Return ret = new Return(true);
		controller = new ContactController();
		contact.setId(Integer.parseInt(request.getParameter("id")));
		contact.setName(request.getParameter("name"));
		contact.setPhone(request.getParameter("phone"));
		contact.setEmail(request.getParameter("email"));
		ret = controller.alterar(contact);
		if (ret.isValid()) {
			
		}else{
			PrintWriter out = response.getWriter();
			out.print(ret.getMensagem());
			out.flush();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
