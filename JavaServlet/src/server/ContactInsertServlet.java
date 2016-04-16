package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ContactController;
import model.Contact;
import util.Conexao;
import util.Return;

@SuppressWarnings("serial")
public class ContactInsertServlet extends HttpServlet {

	ContactController controller;
	private Contact contact = new Contact();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		controller = new ContactController();
		int id = 0;
		contact.setName(request.getParameter("name"));
		contact.setPhone(request.getParameter("phone"));
		contact.setEmail(request.getParameter("email"));
		
		Return ret = controller.salvar(contact);
		if (ret.isValid()) {
			ret = controller.pesquisarPorId(contact);
			if(ret.getList() instanceof Contact){
				id = ((Contact) ret.getList().get(0)).getId();
				PrintWriter out = response.getWriter();
				out.print(id);
				out.flush();
			}
			
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
