package business.control;

import business.model.Pedido;
import business.model.User;
import infra.InfraException;
import infra.PedidoData;
import infra.Pedido_Data_Persistent;
import java.util.HashMap;

public class PedidoControl  implements Menu_Pedido_Controller {
    private HashMap<String, Pedido> pedidos;
    Pedido_Data_Persistent arq = new PedidoData();
    
    public PedidoControl(){
        arq = new PedidoData();
        pedidos = new HashMap<>();
        
        try{
            pedidos = (HashMap<String, Pedido>) arq.carregarDados();
        } catch (InfraException e){
            System.out.println(e.getMessage());
        }
    }

    public void add(User user, Pedido pedido) throws InfraException {
        pedidos.put(user.getLogin(), pedido);
        arq.salvarDados(pedidos);    
    }

    
    public Pedido list(String login) throws BuscaException, LoginException { 
       
        Pedido pedido = pedidos.get(login);
        validarBusca(pedido);
        
        return pedido;
        
    }

    
    private void validarBusca(Pedido pedido) throws BuscaException{
        if(pedido == null){
            throw new BuscaException("Nenhum pedido encontrado para o usuário.");
        }
    }



}
