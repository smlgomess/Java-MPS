package command;

import java.util.List;

import business.control.exception.BuscaException;
import business.control.exception.LoginException;
import business.control.exception.PassException;
import business.model.Pedido;
import business.model.User;
import infra.InfraException;
import infra.PersistenceFacade;

public class PedidoAlterarCommand implements Command {

    private PersistenceFacade facade;

    public PedidoAlterarCommand(PersistenceFacade facade) {
        this.facade = facade;
    }

    @Override
    public void exec() {
    }

    @Override
    public List<Pedido> exec(User string) throws BuscaException, LoginException {
        return null;
    }

    @Override
    public void exec(User user, Pedido pedido) throws InfraException, LoginException, PassException, BuscaException {
        facade.alterPedido(user, pedido);
    }
}