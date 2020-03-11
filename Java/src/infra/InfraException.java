package infra;

public class InfraException extends Exception {

    private static final long serialVersionUID = 1L;
    public InfraException(){}

    public InfraException(String message) {
	super(message);
    }
    
}
