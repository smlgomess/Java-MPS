package business.control;

import business.model.User;
import infra.InfraException;
import java.util.ArrayList;
import business.control.exception.BuscaException;
import business.control.exception.LoginException;
import business.control.exception.PassException;

public class UserAdapter extends UserControl {
      
    public UserAdapter(){
        loadData();        
    }
    
    @Override
    public void loadData() {
        super.loadData();
    }

    @Override
    public void add(User user) throws LoginException, PassException, InfraException {       
        validarLogin(user.getLogin());
        validarPass(user.getPass());
        add_Email(user);
    }

    @Override
    public void add_Email(User user) throws LoginException, PassException, InfraException {        
        super.add_Email(user);
    }

    @Override
    public void del(String login) throws LoginException, BuscaException, InfraException {        
        super.del(login);
    }

    @Override
    public ArrayList<User> listAll() throws BuscaException {        
        return super.listAll();
    }

    @Override
    public User list(String login) throws BuscaException, LoginException {       
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

}