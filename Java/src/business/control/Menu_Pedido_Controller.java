package business.control;

import business.model.User;

import java.util.List;

import business.model.Pedido;
import infra.InfraException;
import business.control.exception.BuscaException;
import business.control.exception.LoginException;

public interface Menu_Pedido_Controller {

    public void add(User user, Pedido pedido) throws InfraException;

    public List<Pedido> list(User login) throws BuscaException, LoginException;

    public void alter(User user, Pedido pedido) throws InfraException, BuscaException, LoginException;

    public void remove(User user, Pedido pedido) throws InfraException;
}
