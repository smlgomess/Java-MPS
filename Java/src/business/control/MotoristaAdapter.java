package business.control;

import business.model.Motorista;
import infra.InfraException;
import java.util.ArrayList;
import business.control.exception.BuscaException;
import business.control.exception.CNHException;
import business.control.exception.LoginException;
import business.control.exception.PassException;

public class MotoristaAdapter extends MotoristaControl {
    public MotoristaAdapter(){
        loadData();        
    }
    
    @Override
    public void loadData() {
        super.loadData();
    }

    @Override
    public void add(Motorista user) throws LoginException, PassException, InfraException, CNHException {       
        validarLogin(user.getLogin());
        validarPass(user.getPass());
        validarCNH(user.getCnh());
        add_Email(user);
    }

    @Override
    public void add_Email(Motorista user) throws LoginException, PassException, InfraException, CNHException {        
        super.add_Email(user);
    }

    @Override
    public void del(String login) throws LoginException, BuscaException, InfraException {        
        super.del(login);
    }

    @Override
    public ArrayList<Motorista> listAll() throws BuscaException {        
        return super.listAll();
    }

    @Override
    public Motorista list(String login) throws BuscaException, LoginException {
        return super.list(login);
    }

    private void validarLogin(String login) throws LoginException{

        if (login.length() == 0){
            throw new LoginException("O login não pode ser vazio.");
        }
    }

    private void validarPass(String pass) throws PassException{

        if(pass.length() < 8 || pass.length() > 12) {
            throw new PassException("A senha deve conter de 8 a 12 caracteres.");
        } else if (!(pass.matches(".*[0-9].*[0-9].*"))) {
            throw new PassException("A senha deve conter pelo menos 2 números.");
        }
    }    

    private void validarCNH(String cnh) throws CNHException{

        if(cnh.length() != 11) {
            throw new CNHException("A CNH deve conter exatamente 11 digitos.");
        } 
        else if (!(cnh.matches("[0-9]+"))) {
            throw new CNHException("A CNH deve conter apenas números.");
        }
    }
}




