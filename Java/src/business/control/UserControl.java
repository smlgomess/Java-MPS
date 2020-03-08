package business.control;

import business.model.User;
import infra.InfraException;
import infra.UserData;
import java.util.ArrayList;
import java.util.HashMap;

public class UserControl{
    private HashMap<String, User> login;
    UserData arq;
    
    public UserControl(){
        arq = new UserData();
        login = new HashMap<>();
        
        try{
            login = (HashMap<String, User>) arq.carregarDados();
        } catch (InfraException e){
            System.out.println(e.getMessage());
        }
    }
    
    public boolean add(User user) throws LoginException, PassException{
        
        if(user.getLogin().length() > 20) {
            throw new LoginException("O login não pode conter mais de 20 caracteres.");
        } else if (user.getLogin().matches(".*[0-9].*")) {
            throw new LoginException("O login não pode conter números.");
        } else if (user.getLogin().length() == 0){
            throw new LoginException("O login não pode ser vázio.");
        }
        
        if(user.getPass().length() < 8 || user.getPass().length() > 12) {
            throw new PassException("A senha deve conter de 8 a 12 caracteres.");
        } else if (!(user.getPass().matches(".*[0-9].*[0-9].*"))) {
            throw new PassException("A senha deve conter pelo menos 2 números.");
        } else {
            login.put(user.getLogin(), user);
            arq.salvarDados(login);
            return true;
        }    
    }
    
    
    
    public boolean del(String login) throws LoginException{
        if(login.length() > 20) {
            throw new LoginException("O login não pode conter mais de 20 caracteres.");
        } else if (login.matches(".*[0-9].*")) {
            throw new LoginException("O login não pode conter números.");
        } else if (login.length() == 0){
            throw new LoginException("O login não pode ser vázio.");
        } else {
            this.login.remove(login);
            arq.salvarDados(this.login);
            return true;
        }        
    }
    
    public ArrayList<User> listAll() throws BuscaException{
        ArrayList lista = new ArrayList<>(this.login.values());
        if(lista == null || lista.isEmpty()){
            throw new BuscaException("Ocorreu um erro ao consultar a base de dados."
                    + "\nContate um administrador ou tente novamente mais tarde.");
        }
        return lista;
    }
    
    public User list(String login) throws BuscaException{
        User user = this.login.get(login);
        if(user == null){
            throw new BuscaException("O usuário não existe ou o nome especificado está errado.");
        }
        return user;
    }
}
