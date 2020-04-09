package memento;

import infra.InfraException;

public interface MementoState<T> {

    public void saveEstado();

    public T getLastPedido() throws InfraException;
}