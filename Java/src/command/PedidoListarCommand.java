package command;

import java.util.List;

import business.control.BuscaException;
import business.control.LoginException;
import business.model.Pedido;
import business.model.User;
import infra.PersistenceFacade;

public class PedidoListarCommand implements Command {

    private PersistenceFacade facade;

    public PedidoListarCommand(PersistenceFacade facade) {
        this.facade = facade;
    }

    public List<Pedido> exec(User login) throws BuscaException, LoginException {
        return facade.listarPedido(login);
    }

    public void exec() {}

    public void exec(User user, Pedido pedido) {}
}