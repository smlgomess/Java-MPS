package business.model;

import memento.CareTaker;
import memento.MementoState;
import memento.PedidoMemento;

import java.io.Serializable;

import infra.InfraException;


public class Pedido implements Serializable, MementoState<Pedido> {
    
    private static final long serialVersionUID = 1L;
    private CareTaker caretaker;
    private String endereco_partida; //endereço do qual o produto vai sair
    private String endereco_chegada; //endereço para onde o produto vai ser transportado
    private int id; //id do pedido (unico para cada pedido feito)
    private String estado; //o estado que se encontra o pedido, se está em aberto, andamento, cancelado ou concluido.
    
    public Pedido(){}

    public Pedido(int id, String state, String endpartida, String endchegada){
        this.setID(id);
        this.setState(state);
        this.setEdpartida(endpartida);
        this.setEdchegada(endchegada);
        caretaker = new CareTaker();
    }

    public void setPedido(int id, String state, String endpartida, String endchegada){
        this.setID(id);
        this.setState(state);
        this.setEdpartida(endpartida);
        this.setEdchegada(endchegada);
        
    }
       
    public void setEdpartida(String endereco_partida) { //endereço de partida
        this.endereco_partida = endereco_partida;
    }

    public void setEdchegada(String endereco_chegada) {//endereço de "chegada", que é onde vai ser levado o produto
        this.endereco_chegada = endereco_chegada;
    }

    public String getEdpartida() {
        return endereco_partida;
    }

    public String getEdchegada() {
        return endereco_chegada;
    }

    public void setID(int id){
        this.id = id;

    }

    public int getID(){
        return id;
    }

    public void setState(String state){
        this.estado = state;
    }

    public String getState(){
        return estado;
    }

    public void saveEstado() {
        caretaker.add( new PedidoMemento(this) );
    }

    public Pedido getLastPedido() throws InfraException {
        if(caretaker == null){
            throw new InfraException("Nenhum pedido encontrado para o usuário nesta sessão.");
        }
        return (Pedido) caretaker.getLast().getState();
    }

     @Override
     public String toString(){
         return  "\nID: " + id+ 
                 "\nEndereco de partida: " + endereco_partida+   
                 "\nEndereco de chegada: " + endereco_chegada+
                 "\nEstado do pedido: " + estado;
     }
}