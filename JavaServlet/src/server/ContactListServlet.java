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

import controller.ContactController;
import model.Contact;
import util.Return;

@SuppressWarnings("serial")
public class ContactListServlet extends HttpServlet {
	List<Contact> contacts = new ArrayList<Contact>();
	ContactController controller;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		Return ret = new Return(true);

		Gson gson = new Gson();
		controller = new ContactController();
		
		ret = controller.listarTodos();
		
		if(ret.getList() != null && ret.getList().size() > 0){
			String json = gson.toJson(ret.getList());
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			
		}else{
			
		}


		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	
}
