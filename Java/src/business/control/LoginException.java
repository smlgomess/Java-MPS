package business.control;

public class LoginException extends Exception{

    private static final long serialVersionUID = 1L;
    public LoginException(){}

    public LoginException(String message){
        super(message);
    }
}