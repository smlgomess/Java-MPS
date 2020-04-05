package memento;

public interface MementoState<T> {

    public void saveEstado();

    public T getLastPedido();
}