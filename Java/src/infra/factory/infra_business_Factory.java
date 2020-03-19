package infra.factory;

import business.control.MenuController;
import business.control.Menu_Pedido_Controller;
import business.control.UserControl;
import business.control.PedidoControl;

public class infra_business_Factory {

    public MenuController getUserControl(){
        return new UserControl();
    }

    public Menu_Pedido_Controller getPedidoControl(){
        return new PedidoControl();
    }
}