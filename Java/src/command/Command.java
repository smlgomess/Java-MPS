package command;

import java.util.List;

import business.control.exception.BuscaException;
import business.control.exception.LoginException;
import business.control.exception.PassException;
import business.model.Pedido;
import business.model.User;
import infra.InfraException;

public interface Command {

    public void exec();

    public List<Pedido> exec(User string) throws BuscaException, LoginException;

    public void exec(User user, Pedido pedido) throws InfraException, LoginException, PassException;
}