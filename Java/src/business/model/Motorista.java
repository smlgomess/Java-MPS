package business.model;

import java.io.Serializable;

public class Motorista implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String login;
    private String pass;
    private String cnh;

    public void UserConstruct(String login, String pass, String cnh){
        this.login = login;
        this.pass = pass;
        this.cnh = cnh;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setCNH(String cnh) {
        this.cnh = cnh;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getCnh() {
        return cnh;
    }

    @Override
    public String toString(){
        return  "\nLogin: " + login+ 
                "  Senha: " + pass+ 
                "  CNH: " + cnh;
    }
}