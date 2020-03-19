package business.control;

//import java.util.ArrayList;

import business.model.User;
import business.model.Pedido;
import infra.InfraException;

public interface Menu_Pedido_Controller {

    public void add(User user, Pedido pedido) throws InfraException;

    //public void del(String login) throws LoginException, BuscaException, InfraException;

    //public ArrayList<User> listAll() throws BuscaException;

    public Pedido list(String login) throws BuscaException, LoginException;
}
