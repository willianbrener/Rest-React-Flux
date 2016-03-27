package server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Contact;
import util.Conexao;

@SuppressWarnings("serial")
public class ContactUpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		Contact contact = new Contact();
		
		contact.setId(Integer.parseInt(request.getParameter("id")));
		contact.setName(request.getParameter("name"));
		contact.setPhone(request.getParameter("phone"));
		contact.setEmail(request.getParameter("email"));
		
		System.out.println(contact.getName());
		System.out.println(contact.getPhone());
		System.out.println(contact.getEmail());
		
		ContactDAO dao = new ContactDAO(Conexao.fabricar());
		try {
			dao.update(contact);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
