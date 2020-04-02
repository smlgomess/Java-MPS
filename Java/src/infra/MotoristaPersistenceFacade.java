package infra;

import java.util.ArrayList;

import business.control.BuscaException;
import business.control.LoginException;
import business.control.CNHException;
import business.control.Menu_Motorista_Controller;
import business.control.PassException;
import infra.factory.infra_business_Factory;
import business.model.Motorista;

public class MotoristaPersistenceFacade{
   
    Menu_Motorista_Controller motoristaControl;

    private MotoristaPersistenceFacade(){
        infra_business_Factory userFactory = new infra_business_Factory();        
        motoristaControl = userFactory.getMotoristaControl();
    }

    private static MotoristaPersistenceFacade instance = new MotoristaPersistenceFacade();

    public static synchronized MotoristaPersistenceFacade obterInstance(){
        return instance;
    }

    public void cadastrar(Motorista user) throws LoginException, PassException, InfraException, CNHException{
        addUser(user);
    }   

    public ArrayList<Motorista> listarAll() throws BuscaException{
        return listAll();
    }

    public void del(String login) throws LoginException, BuscaException, InfraException {
        delUser(login);
    }


    private void addUser(Motorista user) throws LoginException, PassException, InfraException , CNHException {
        motoristaControl.add(user);
    }
 
    private ArrayList<Motorista> listAll() throws BuscaException{
        return motoristaControl.listAll();
    }

    private void delUser(String login) throws LoginException, BuscaException, InfraException{
        motoristaControl.del(login);
    }

}