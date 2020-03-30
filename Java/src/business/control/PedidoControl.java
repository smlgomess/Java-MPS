package business.control;

import business.model.Pedido;
import business.model.User;
import infra.InfraException;
import infra.PedidoData;
import infra.Pedido_Data_Persistent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PedidoControl  implements Menu_Pedido_Controller {
    private HashMap<String, List<Pedido>> pedidos;
    Pedido_Data_Persistent arq = new PedidoData();
    

    public PedidoControl(){
        arq = new PedidoData();
        pedidos = new HashMap<>();       
        
        try{
            pedidos = (HashMap<String, List<Pedido>>) arq.carregarDados();
        } catch (InfraException e){
            System.out.println(e.getMessage());
        }
    }

    public void add(User user, Pedido pedido) throws InfraException {
        List<Pedido> ped = pedidos.get(user.getLogin());       
        if (ped == null){
            ped = new ArrayList<>();
        }
        ped.add(pedido);
        pedidos.put(user.getLogin(), ped);
        arq.salvarID(pedido.getID());
        arq.salvarDados(pedidos);    
    }

    
    public List<Pedido> list(String login) throws BuscaException, LoginException { 
       
        List<Pedido> ped = pedidos.get(login);        
        validarBusca(ped);        
        return ped;
        
    }

    
    private void validarBusca(List<Pedido> pedido) throws BuscaException{
        if(pedido == null){
            throw new BuscaException("Nenhum pedido encontrado para o usuário.");
        }
    }



}
