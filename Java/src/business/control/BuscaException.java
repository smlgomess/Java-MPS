package business.control;

public class BuscaException extends Exception{
    
    private static final long serialVersionUID = 1L;
    public BuscaException(){}

    public BuscaException(String message){
        super(message);
    }
}
