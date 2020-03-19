package infra;

import java.util.ArrayList;

import business.control.BuscaException;
import business.control.LoginException;
import business.control.MenuController;
import business.control.PassException;
import infra.factory.infra_business_Factory;
import business.model.User;

public class PersistenceFacade{

    MenuController menuControl;

    private PersistenceFacade(){
        infra_business_Factory userFactory = new infra_business_Factory();
        menuControl = userFactory.getUserControl();
    }

    private static PersistenceFacade instance = new PersistenceFacade();

    public static synchronized PersistenceFacade obterInstance(){
        return instance;
    }

    public void cadastrar(User user) throws LoginException, PassException, InfraException{
        addUser(user);
    }

    public ArrayList<User> listarAll() throws BuscaException{
        return listAll();
    }

    public void del(String login) throws LoginException, BuscaException, InfraException {
        delUser(login);
    }


    private void addUser(User user) throws LoginException, PassException, InfraException {
        menuControl.add(user);
    }

    private ArrayList<User> listAll() throws BuscaException{
        return menuControl.listAll();
    }

    private void delUser(String login) throws LoginException, BuscaException, InfraException{
        menuControl.del(login);
    }

}