package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.List;

import model.Contact;

import java.util.ArrayList;


public class ContactDAO{

private Connection con;
private static ContactDAO instance;

public static ContactDAO getInstance() {
    if (instance == null)
       instance = new ContactDAO();
    return instance;
 }

public ContactDAO(Connection con){
   this.con = con;
}

public ContactDAO() {
}

public void cadastrar(Contact contact) throws Exception {
   PreparedStatement p =
   con.prepareStatement("insert into contact (name, phone, email) values (?,?,?)");
   p.setString(1, contact.getName());
   p.setString(2, contact.getPhone());
   p.setString(3, contact.getEmail());
   p.executeUpdate();
   p.close();
}

public void deletar(Contact contact) throws Exception {
   PreparedStatement p = con.prepareStatement("delete from contact where id = ?");
   p.setInt(1, contact.getId());
   p.executeUpdate();
   p.close();
}

public void update(Contact contact) throws Exception {
   PreparedStatement p = 
   con.prepareStatement("update contact set name = ?, phone = ?, email = ? where id = ?");
   p.setString(1, contact.getName());
   p.setString(2, contact.getPhone());
   p.setString(3, contact.getEmail());
   p.setInt(4, contact.getId());
   p.executeUpdate();
   p.close();
}

public List<Contact> listarTodos() throws Exception{
   List<Contact> contacts = new ArrayList<Contact>();
   PreparedStatement p = con.prepareStatement("select * from contact");
   ResultSet rs = p.executeQuery();
   while(rs.next()){
	   Contact contact = new Contact();
	   contact.setId(rs.getInt("id"));
	   contact.setName(rs.getString("name"));
	   contact.setPhone(rs.getString("phone"));
	   contact.setEmail(rs.getString("email"));
	   contacts.add(contact);
   }
   rs.close();
   p.close();
   return contacts;
}

public List<Contact> searchById( Contact contact) throws Exception{
	 List<Contact> contacts = new ArrayList<Contact>();
	   PreparedStatement p = con.prepareStatement("select * from contact where name = ? and phone = ? and email = ?");
	   p.setString(1, contact.getName());
	   p.setString(2, contact.getPhone());
	   p.setString(3, contact.getEmail());
	   ResultSet rs = p.executeQuery();
	   while(rs.next()){
		   Contact contactAux = new Contact();
		   contactAux.setId(rs.getInt("id"));
		   contactAux.setName(rs.getString("name"));
		   contactAux.setPhone(rs.getString("phone"));
		   contactAux.setEmail(rs.getString("email"));
		   contacts.add(contactAux);
	   }
	   rs.close();
	   p.close();
	   return contacts;
	}

}//fim da classe ProdutoDAO
