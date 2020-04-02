package infra;

import java.util.HashMap;

import business.model.Motorista;

public interface Data_Motorista_Persistent {

    public void salvarDados(HashMap<String, Motorista> users) throws InfraException;

    public HashMap<String, Motorista> carregarDados() throws InfraException ;
}
