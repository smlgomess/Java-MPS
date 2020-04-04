package command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.control.exception.BuscaException;
import business.control.exception.LoginException;
import business.control.exception.PassException;
import business.model.Pedido;
import business.model.User;
import infra.InfraException;
import infra.PersistenceFacade;

public class Caller {

    private final Map<Commands, Command> comandos;

    public Caller(PersistenceFacade persistence) {
        comandos = new HashMap<>();
        comandos.put(Commands.REGISTRAR, new PedidoRegistroCommand(persistence));
        comandos.put(Commands.LISTAR, new PedidoListarCommand(persistence));
        comandos.put(Commands.ALTERAR, new PedidoAlterarCommand(persistence));
    }

    public List<Pedido> service(Commands cmd, User user) throws BuscaException, LoginException {
        Command command = comandos.get(cmd);
        return command.exec(user);
    }

    public void service(Commands cmd, User user, Pedido pedido) throws InfraException, LoginException, PassException,
            BuscaException {
        Command command = comandos.get(cmd);
        command.exec(user, pedido);
    }
}