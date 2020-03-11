package business.control;

import java.util.ArrayList;

import business.model.User;

public interface MenuController {

    public void add(User user) throws LoginException, PassException;

    public void del(String login) throws LoginException, BuscaException;

    public ArrayList<User> listAll() throws BuscaException;

    public User list(String login) throws BuscaException, LoginException;
}
