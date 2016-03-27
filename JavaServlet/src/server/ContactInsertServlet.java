package server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Contact;
import util.Conexao;

@SuppressWarnings("serial")
public class ContactInsertServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		Contact contact = new Contact();
		int id = 0;
		contact.setName(request.getParameter("name"));
		contact.setPhone(request.getParameter("phone"));
		contact.setEmail(request.getParameter("email"));
		
		System.out.println(contact.getName());
		System.out.println(contact.getPhone());
		System.out.println(contact.getEmail());
		
		ContactDAO dao = new ContactDAO(Conexao.fabricar());
		try {
			dao.cadastrar(contact);
			 id = dao.searchById(contact).get(0).getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();

		out.print(id);
		out.flush();
		
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
