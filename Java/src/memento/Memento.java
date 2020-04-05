package memento;

public class Memento<Pedido> {

    private Pedido state;

    public Memento(Pedido obj) {
        this.state = obj;
    }

    public Pedido getState() {
        return state;
    }
}