package business.model;

import java.io.Serializable;


public class Pedido implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String endereco_partida; //endereço do qual o produto vai sair
    private String endereco_chegada; //endereço para onde o produto vai ser transportado
    private int id; //id do pedido (unico para cada pedido feito)
    private String estado; //o estado que se encontra o pedido, se está em aberto, andamento, cancelado ou concluido.
    

    public void setPedido(int id, String state, String endpartida, String endchegada){
        setID(id);
        setState(state);
        setEdpartida(endpartida);
        setEdchegada(endchegada);
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

     @Override
     public String toString(){
         return  "\nID: " + id+ 
                 "\nEndereco de partida: " + endereco_partida+   
                 "\nEndereco de chegada: " + endereco_chegada+
                 "\nEstado do pedido: " + estado;
     }
}