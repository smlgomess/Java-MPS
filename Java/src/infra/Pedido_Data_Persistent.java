package infra;

import java.util.HashMap;
import java.util.List;

import business.model.Pedido;

public interface Pedido_Data_Persistent {

    public void salvarDados(HashMap<String, List <Pedido>> pedidos) throws InfraException;

    public void salvarID(int id) throws InfraException;

    public HashMap<String,  List <Pedido>> carregarDados() throws InfraException ;
}
