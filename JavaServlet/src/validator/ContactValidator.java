package validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Contact;
import util.Return;

public class ContactValidator {

	public Return validateContact(Contact contact){
		Return ret = new Return(true);
		if(contact.getName() == null || contact.getName().equalsIgnoreCase("")){
			return new Return(false, "Campo nome vazio!");
		}else if(contact.getPhone() == null || contact.getPhone().equalsIgnoreCase("")){
			return new Return(false, "Campo contato vazio!");
		}else if(contact.getEmail() == null || contact.getEmail().equalsIgnoreCase("")){
			return new Return(false, "Campo email vazio!");
		}else if(!isEmailValid(contact.getEmail())){
			return new Return(false, "Email inálido!");
		}
		return ret;
	}
	
	public static boolean isEmailValid(String email) {
        if ((email == null) || (email.trim().length() == 0))
            return false;

        String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
