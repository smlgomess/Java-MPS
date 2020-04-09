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

    MenuController menuControl, UserAdapterControl;
    Menu_Pedido_Controller pedidoControl;    
    Menu_Motorista_Controller motoristaControl, motoristaAdapterControl ;
    infra_business_Factory userFactory;

    private PersistenceFacade(){
        userFactory = new infra_business_Factory();
        menuControl = userFactory.getUserControl();
        UserAdapterControl = userFactory.getUserControl_Adapter();
        pedidoControl = userFactory.getPedidoControl();   
        motoristaControl = userFactory.getMotoristaControl();     
    }

    private static PersistenceFacade instance = new PersistenceFacade();

    public static synchronized PersistenceFacade obterInstance(){
        return instance;
    }

    //Cliente (User)

    public void cadastrar(User user, int i) throws LoginException, PassException, InfraException{
        if (i == 1){
            addUser(user);
        }
        else if (i == 2){
            addUserAdapter(user);
        }
    }

    private void addUser(User user) throws LoginException, PassException, InfraException {
        menuControl.add(user);
    }

    private void addUserAdapter(User user) throws LoginException, PassException, InfraException {
        UserAdapterControl.add(user);
    }

    public ArrayList<User> listarAll() throws BuscaException{
        return listAll();
    }

    private ArrayList<User> listAll() throws BuscaException{
        menuControl = userFactory.getUserControl();
       // UserAdapterControl = userFactory.getUserControl_Adapter();
        return menuControl.listAll();
    }

    public void del(String login) throws LoginException, BuscaException, InfraException {
        delUser(login);
    }
    
    private void delUser(String login) throws LoginException, BuscaException, InfraException{
        menuControl = userFactory.getUserControl();
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