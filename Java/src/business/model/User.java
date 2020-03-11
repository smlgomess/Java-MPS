package business.model;

import java.io.Serializable;


public class User implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String login;
    private String pass;

    public void UserConstruct(String login, String pass){
        this.login = login;
        this.pass = pass;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    @Override
    public String toString(){
        return  "\nLogin: " + login+ 
                "  Senha: " + pass;
    }
}
