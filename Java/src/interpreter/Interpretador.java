package interpreter;

import business.model.Pedido;
import infra.InfraException;

public interface Interpretador {

    void interpretar(Pedido pedido) throws InfraException;
}