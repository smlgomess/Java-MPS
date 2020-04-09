package infra.factory;

import business.control.MenuController;
import business.control.Menu_Pedido_Controller;
import business.control.Menu_Motorista_Controller;
import business.control.UserControl;
import business.control.UserAdapter;
import business.control.PedidoControl;
import business.control.MotoristaControl;

public class infra_business_Factory {

    public MenuController getUserControl(){
        return new UserControl();
    }

    public MenuController getUserControl_Adapter(){
        return new UserAdapter();
    }

    public Menu_Pedido_Controller getPedidoControl(){
        return new PedidoControl();
    }

    public Menu_Motorista_Controller getMotoristaControl(){
        return new MotoristaControl();
    }
}