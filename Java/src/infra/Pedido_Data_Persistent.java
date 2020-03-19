package infra;

import java.util.HashMap;

import business.model.Pedido;

public interface Pedido_Data_Persistent {

    public void salvarDados(HashMap<String, Pedido> pedidos) throws InfraException;

    public HashMap<String, Pedido> carregarDados() throws InfraException ;
}
