package business.model;

import java.io.Serializable;


public class Pedido implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String endereco_partida; //endereço do qual o produto vai sair
    private String endereco_chegada; //endereço para onde o produto vai ser transportado


    // public void UserConstruct(String login, String pass){
    //     this.login = login;
    //     this.pass = pass;
    // }
    
    public void setEdpartida(String endereco_partida) { //endereço de partida
        this.endereco_partida = endereco_partida;
    }

    public void setEdchegada(String endereco_chegada) {
        this.endereco_chegada = endereco_chegada;
    }

    public String getEdpartida() {
        return endereco_partida;
    }

    public String getEdchegada() {
        return endereco_chegada;
    }

    // @Override
    // public String toString(){
    //     return  "\nEndereco de partida: " + endereco_partida+ 
    //             "  Endereco de chegada: " + endereco_chegada;
    // }
}