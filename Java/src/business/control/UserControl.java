package business.control;

import business.model.User;
import infra.InfraException;
import infra.UserData;
import infra.DataPersistent;
import java.util.ArrayList;
import java.util.HashMap;
import business.control.exception.BuscaException;
import business.control.exception.LoginException;
import business.control.exception.PassException;

public class UserControl implements MenuController {
    private HashMap<String, User> login = new HashMap<>();
    DataPersistent arq;
    
    public UserControl(){
        loadData();        
    }
    
    public void loadData(){  
        login = new HashMap<>();  
        arq = new UserData();          
        try{
            login = (HashMap<String, User>) arq.carregarDados();
        } catch (InfraException e){
            System.out.println(e.getMessage());
        }

    }

    public void add(User user) throws LoginException, PassException, InfraException {
        
        validarLogin(user.getLogin());
        validarPass(user.getPass());

        login.put(user.getLogin(), user);        
        arq.salvarDados(login);    
        loadData();
    }

    public void add_Email(User user) throws LoginException, PassException, InfraException {
        login.put(user.getLogin(), user);        
        arq.salvarDados(login);    
        loadData();
    }

    
    public void del(String login) throws LoginException, BuscaException, InfraException{
        User user;
    
        user = this.login.remove(login);
        
        validarBusca(user);

        arq.salvarDados(this.login);    
        loadData();
    }
    
    public ArrayList<User> listAll() throws BuscaException{
        
        ArrayList<User> lista = new ArrayList<>(this.login.values());
        
        validarBusca(lista);

        return lista;
    }
    
    public User list(String login) throws BuscaException, LoginException{
        
        User user = this.login.get(login);
        validarBusca(user);        
        return user;
    }

    private void validarLogin(String login) throws LoginException{

        if(login.length() > 20) {
            throw new LoginException("O login não pode conter mais de 20 caracteres.");
        } else if (login.matches(".*[0-9].*")) {
            throw new LoginException("O login não pode conter números.");
        } else if (login.length() == 0){
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

    private void validarBusca(ArrayList<User> lista) throws BuscaException{
        if(lista == null || lista.isEmpty()){
            throw new BuscaException("Ocorreu um erro ao consultar a base de dados."
                    + "\nContate um administrador ou tente novamente mais tarde.");
        }  
    }

    private void validarBusca(User user) throws BuscaException{
        if(user == null){
            throw new BuscaException("O usuário não existe ou o nome especificado está errado.");
        }
    }
}
