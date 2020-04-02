package command;

import infra.PersistenceFacade;
import infra.InfraException;

import java.util.List;

import business.control.exception.LoginException;
import business.control.exception.PassException;
import business.model.Pedido;
import business.model.User;

public class PedidoRegistroCommand implements Command {

    private PersistenceFacade facade;

    public PedidoRegistroCommand(PersistenceFacade facade) {
        this.facade = facade;
    }

    public void exec(User user, Pedido pedido) throws InfraException, LoginException, PassException {    
        facade.cadastrar_Pedido(user, pedido);
    }

    @Override
    public void exec() {}

    @Override
    public List<Pedido> exec(User login) { return null; }
}