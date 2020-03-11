package business.control;

public class PassException extends Exception{

    private static final long serialVersionUID = 1L;

    public PassException() {
    }

    public PassException(String message){
        super(message);
    }
}