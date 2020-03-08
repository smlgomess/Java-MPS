package business.model;


public class User {
    
    private String login;
    private String pass;
    
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

    public String toString(){
        return login + "\n" + pass;
    }
}
