package infra;

import java.util.HashMap;

import business.model.User;

public interface DataPersistent {

    public void salvarDados(HashMap<String, User> users) throws InfraException;

    public HashMap<String, User> carregarDados() throws InfraException ;
}
