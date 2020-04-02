package business.control;

public class CNHException extends Exception{

    private static final long serialVersionUID = 1L;

    public CNHException() {
    }

    public CNHException(String message){
        super(message);
    }
}