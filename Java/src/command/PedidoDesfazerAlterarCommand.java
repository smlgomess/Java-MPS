package command;

import java.util.List;

import business.control.exception.BuscaException;
import business.control.exception.LoginException;
import business.control.exception.PassException;
import business.model.Pedido;
import business.model.User;
import infra.InfraException;
import infra.PersistenceFacade;
import memento.MementoState;

public class PedidoDesfazerAlterarCommand implements Command {

    private PersistenceFacade facade;

    public PedidoDesfazerAlterarCommand(PersistenceFacade facade) {
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
    public void exec(User user, Pedido pedido) throws InfraException, LoginException, PassException {
        Object estado = ((MementoState) pedido.getLastPedido());
        if(estado != null) {
            facade.removePedido(user, pedido);
        } else {
            System.out.println("Primeira execução!");
        }
    }
}