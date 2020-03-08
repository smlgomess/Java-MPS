package business.control;

public class PassException extends Exception{

    public PassException(){}

    public PassException(String message){
        super(message);
    }
}