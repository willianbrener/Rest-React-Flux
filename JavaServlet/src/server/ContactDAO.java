package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.List;

import model.Contact;
import util.Return;

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

public Return cadastrar(Contact contact) throws Exception {
	Return ret = new Return(true);
   PreparedStatement p =
   con.prepareStatement("insert into contact (name, phone, email) values (?,?,?)");
   p.setString(1, contact.getName());
   p.setString(2, contact.getPhone());
   p.setString(3, contact.getEmail());
   p.executeUpdate();
   p.close();
   return ret;
}

public Return deletar(Contact contact) throws Exception {
	Return ret = new Return(true);
   PreparedStatement p = con.prepareStatement("delete from contact where id = ?");
   p.setInt(1, contact.getId());
   p.executeUpdate();
   p.close();
   return ret;
}

public Return update(Contact contact) throws Exception {
	Return ret = new Return(true);
   PreparedStatement p = 
   con.prepareStatement("update contact set name = ?, phone = ?, email = ? where id = ?");
   p.setString(1, contact.getName());
   p.setString(2, contact.getPhone());
   p.setString(3, contact.getEmail());
   p.setInt(4, contact.getId());
   p.executeUpdate();
   p.close();
   return ret;
}

public Return listarTodos() throws Exception{
	Return ret = new Return(true);
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
   ret.setList(contacts);
   rs.close();
   p.close();
   return ret;
}

public Return searchById( Contact contact) throws Exception{
	Return ret = new Return(true);
	 Contact contactN = new Contact();
	 List<Contact> contacts = new ArrayList<Contact>();
	   PreparedStatement p = con.prepareStatement("select * from contact where id = ? ");
	   p.setInt(1, contact.getId());
	   ResultSet rs = p.executeQuery();
	   while(rs.next()){
	   contactN.setId(rs.getInt("id"));
	   contactN.setName(rs.getString("name"));
	   contactN.setPhone(rs.getString("phone"));
	   contactN.setEmail(rs.getString("email"));
	   contacts.add(contactN);
	   }
	   
	   ret.setList(contacts);
	   rs.close();
	   p.close();
	   return ret;
	}

}
