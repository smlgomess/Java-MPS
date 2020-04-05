package memento;

import business.model.Pedido;
import java.io.Serializable;

public class PedidoMemento extends Memento<Pedido> implements Serializable {

    private static final long serialVersionUID = 1L;

    public PedidoMemento(Pedido obj) {
        super(new Pedido(obj.getID(), obj.getState(), obj.getEdpartida(), obj.getEdchegada() ));
    }
}