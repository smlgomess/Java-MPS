package business.control;

import java.util.ArrayList;

import business.model.Motorista;
import infra.InfraException;

public interface Menu_Motorista_Controller {

    public void add(Motorista user) throws LoginException, PassException, InfraException, CNHException;

    public void del(String login) throws LoginException, BuscaException, InfraException;

    public ArrayList<Motorista> listAll() throws BuscaException;

    public Motorista list(String login) throws BuscaException, LoginException;
}