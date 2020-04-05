package infra;

import java.util.ArrayList;
import java.util.List;

import business.model.User;
import business.model.Pedido;
import business.control.MenuController;
import business.control.Menu_Pedido_Controller;
import business.control.exception.BuscaException;
import business.control.exception.LoginException;
import business.control.exception.PassException;
import infra.factory.infra_business_Factory;

public class PersistenceFacade{

    MenuController menuControl;
    Menu_Pedido_Controller pedidoControl;    

    private PersistenceFacade(){
        infra_business_Factory userFactory = new infra_business_Factory();
        menuControl = userFactory.getUserControl();
        pedidoControl = userFactory.getPedidoControl();        
    }

    private static PersistenceFacade instance = new PersistenceFacade();

    public static synchronized PersistenceFacade obterInstance(){
        return instance;
    }

    public void cadastrar(User user) throws LoginException, PassException, InfraException{
        addUser(user);
    }

    public void cadastrar_Pedido(User user, Pedido pedido) throws LoginException, PassException, InfraException{        
        addPedido(user, pedido);
    }

    public void removePedido(User user, Pedido pedido) throws InfraException {
        remPedido(user, pedido);
    }

    public ArrayList<User> listarAll() throws BuscaException{
        return listAll();
    }

    public void del(String login) throws LoginException, BuscaException, InfraException {
        delUser(login);
    }

    public List<Pedido> listarPedido(User login) throws BuscaException, LoginException{
        return listPedido(login);
    }

    public void alterPedido(User login, Pedido pedido) throws InfraException, BuscaException, LoginException {
        altPedido(login, pedido);
    }

    private void remPedido(User user, Pedido pedido) throws InfraException {
        pedidoControl.remove(user, pedido);
    }

    private List<Pedido> listPedido(User login) throws BuscaException, LoginException {
        return pedidoControl.list(login);
    }

    private void addUser(User user) throws LoginException, PassException, InfraException {
        menuControl.add(user);
    }

    private void addPedido(User user, Pedido pedido) throws LoginException, PassException, InfraException {
        pedidoControl.add(user, pedido);
    }

    private ArrayList<User> listAll() throws BuscaException{
        return menuControl.listAll();
    }

    private void delUser(String login) throws LoginException, BuscaException, InfraException{
        menuControl.del(login);
    }

    private void altPedido(User login, Pedido pedido) throws InfraException, BuscaException, LoginException {
        pedidoControl.alter(login, pedido);
    }

}