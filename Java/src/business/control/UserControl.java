package business.control;

import business.model.User;
import java.util.HashMap;

public class UserControl {
    HashMap<String, String> login = new HashMap<String, String>();
    
    public boolean add(String login, String pass) throws LoginException, PassException{
        
        if(login.length() > 20) {
            throw new LoginException("O login não pode conter mais de 20 caracteres.");
        } else if (login.matches(".*[0-9].*")) {
            throw new LoginException("O login não pode conter números.");
        } else if (login.length() == 0){
            throw new LoginException("O login não pode ser vázio.");
        }
        
        if(pass.length() < 8 || pass.length() > 12) {
            throw new PassException("A senha deve conter de 8 a 12 caracteres.");
        } else if (!(pass.matches("..*[0-9].*[0-9].*"))) {
            throw new PassException("A senha deve conter pelo menos 2 números.");
        } else {
            this.login.put(login, pass);
            return true;
        }    
    }
    
    public boolean del(String login) throws LoginException{
        if(login.length() > 20) {
            throw new LoginException("O login não pode conter mais de 20 caracteres.");
        } else if (login.matches(".*[0-9].*")) {
            throw new LoginException("O login não pode conter números.");
        } else if (login.length() == 0){
            throw new LoginException("O login não pode ser vázio.");
        } else {
            this.login.remove(login);
            return true;
        }
        
    }
}
