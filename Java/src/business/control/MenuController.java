package business.control;

import java.util.ArrayList;

import business.model.User;
import infra.InfraException;
import business.control.exception.BuscaException;
import business.control.exception.LoginException;
import business.control.exception.PassException;

public interface MenuController {

    public void add(User user) throws LoginException, PassException, InfraException;

    public void del(String login) throws LoginException, BuscaException, InfraException;

    public ArrayList<User> listAll() throws BuscaException;

    public User list(String login) throws BuscaException, LoginException;
}
