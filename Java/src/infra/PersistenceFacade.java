package infra;

import java.util.ArrayList;
import java.util.List;

import business.model.User;
import business.model.Motorista;
import business.model.Pedido;
import business.control.MenuController;
import business.control.Menu_Motorista_Controller;
import business.control.Menu_Pedido_Controller;
import business.control.exception.BuscaException;
import business.control.exception.LoginException;
import business.control.exception.PassException;
import business.control.exception.CNHException;
import infra.factory.infra_business_Factory;

public class PersistenceFacade{

    MenuController menuControl;
    Menu_Pedido_Controller pedidoControl;    
    Menu_Motorista_Controller motoristaControl;

    private PersistenceFacade(){
        infra_business_Factory userFactory = new infra_business_Factory();
        menuControl = userFactory.getUserControl();
        pedidoControl = userFactory.getPedidoControl();   
        motoristaControl = userFactory.getMotoristaControl();     
    }

    private static PersistenceFacade instance = new PersistenceFacade();

    public static synchronized PersistenceFacade obterInstance(){
        return instance;
    }

    //Cliente (User)

    public void cadastrar(User user) throws LoginException, PassException, InfraException{
        addUser(user);
    }

    private void addUser(User user) throws LoginException, PassException, InfraException {
        menuControl.add(user);
    }

    public ArrayList<User> listarAll() throws BuscaException{
        return listAll();
    }

    private ArrayList<User> listAll() throws BuscaException{
        return menuControl.listAll();
    }

    public void del(String login) throws LoginException, BuscaException, InfraException {
        delUser(login);
    }
    
    private void delUser(String login) throws LoginException, BuscaException, InfraException{
        menuControl.del(login);
    }




    //Motorista
    
    public void cadastrar(Motorista user) throws LoginException, PassException, InfraException, CNHException{
        addUser(user);
    }   

    private void addUser(Motorista user) throws LoginException, PassException, InfraException , CNHException{
        motoristaControl.add(user);
    }

    public ArrayList<Motorista> listarAll_Motoristas() throws BuscaException{
        return listAll_Motoristas();
    }

    private ArrayList<Motorista> listAll_Motoristas() throws BuscaException{
        return motoristaControl.listAll();
    }

    public void delMot(String login) throws LoginException, BuscaException, InfraException {
        delMotorista(login);
    }

    private void delMotorista(String login) throws LoginException, BuscaException, InfraException{
        motoristaControl.del(login);
    }


    
    

    // Pedido

    public void cadastrar_Pedido(User user, Pedido pedido) throws LoginException, PassException, InfraException{        
        addPedido(user, pedido);
    }

    private void addPedido(User user, Pedido pedido) throws LoginException, PassException, InfraException {
        pedidoControl.add(user, pedido);
    }

    public void removePedido(User user, Pedido pedido) throws InfraException {
        remPedido(user, pedido);
    }

    private void remPedido(User user, Pedido pedido) throws InfraException {
        pedidoControl.remove(user, pedido);
    }
    
    public List<Pedido> listarPedido(User login) throws BuscaException, LoginException{
        return listPedido(login);
    }

    private List<Pedido> listPedido(User login) throws BuscaException, LoginException {
        return pedidoControl.list(login);
    }

    public void alterPedido(User login, Pedido pedido) throws InfraException, BuscaException, LoginException {
        altPedido(login, pedido);
    }

    private void altPedido(User login, Pedido pedido) throws InfraException, BuscaException, LoginException {
        pedidoControl.alter(login, pedido);
    }

       

}