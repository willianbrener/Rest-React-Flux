package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;

import model.Contact;
import util.Conexao;

@SuppressWarnings("serial")
public class ContactListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

		/* USANDO A BIBLIOTECA GON DO GOOGLE PARA MANIPULAR OS JSON'S */

		Gson gson = new Gson();
		List<Contact> contacts = new ArrayList<Contact>();
		ContactDAO dao = new ContactDAO(Conexao.fabricar());
		try {
			contacts = dao.listarTodos();

		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = gson.toJson(contacts);
		PrintWriter out = response.getWriter();

		out.print(json);
		out.flush();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
