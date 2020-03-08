package business.model;

import java.io.Serializable;


public class User implements Serializable{
    
    private String login;
    private String pass;
    
    public void User(String login, String pass){
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
        return login + "\n" + pass;
    }
}
