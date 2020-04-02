package business.control;

import business.model.Motorista;
import infra.InfraException;
import infra.MotoristaData;
import infra.Data_Motorista_Persistent;
import java.util.ArrayList;
import java.util.HashMap;

public class MotoristaControl implements Menu_Motorista_Controller {
    private HashMap<String, Motorista> login;
    Data_Motorista_Persistent arq = new MotoristaData();
    
    public MotoristaControl(){
        arq = new MotoristaData();
        login = new HashMap<>();
        
        try{
            login = (HashMap<String, Motorista>) arq.carregarDados();
        } catch (InfraException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void add(Motorista user) throws LoginException, PassException, InfraException, CNHException {
        
        validarLogin(user.getLogin());
        validarPass(user.getPass());
        validarCNH(user.getCnh());

        login.put(user.getLogin(), user);        
        arq.salvarDados(login);    
    }
    
    public void del(String login) throws LoginException, BuscaException, InfraException{
        Motorista user;
        validarLogin(login);

        user = this.login.remove(login);
        
        validarBusca(user);

        arq.salvarDados(this.login);    
    }
    
    public ArrayList<Motorista> listAll() throws BuscaException{
        
        ArrayList<Motorista> lista = new ArrayList<>(this.login.values());
        
        validarBusca(lista);

        return lista;
    }
    
    public Motorista list(String login) throws BuscaException, LoginException{
        
        validarLogin(login);

        Motorista user = this.login.get(login);
        validarBusca(user);        
        return user;
    }

    private void validarCNH(String cnh) throws CNHException{

        if(cnh.length() != 11) {
            throw new CNHException("A CNH deve conter exatamente 11 digitos.");
        } 
        else if (!(cnh.matches("[0-9]+"))) {
            throw new CNHException("A CNH deve conter apenas números.");
        }
    }

    private void validarLogin(String login) throws LoginException{

        if(login.length() > 20) {
            throw new LoginException("O login não pode conter mais de 20 caracteres.");
        } else if (login.matches(".*[0-9].*")) {
            throw new LoginException("O login não pode conter números.");
        } else if (login.length() == 0){
            throw new LoginException("O login não pode ser vázio.");
        }
    }

    private void validarPass(String pass) throws PassException{

        if(pass.length() < 8 || pass.length() > 12) {
            throw new PassException("A senha deve conter de 8 a 12 caracteres.");
        } else if (!(pass.matches(".*[0-9].*[0-9].*"))) {
            throw new PassException("A senha deve conter pelo menos 2 números.");
        }
    }

    private void validarBusca(ArrayList<Motorista> lista) throws BuscaException{
        if(lista == null || lista.isEmpty()){
            throw new BuscaException("Ocorreu um erro ao consultar a base de dados."
                    + "\nContate um administrador ou tente novamente mais tarde.");
        }  
    }

    private void validarBusca(Motorista user) throws BuscaException{
        if(user == null){
            throw new BuscaException("O usuário não existe ou o nome especificado está errado.");
        }
    }
}
