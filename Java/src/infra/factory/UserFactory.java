package infra.factory;

import business.control.MenuController;
import business.control.UserControl;

public class UserFactory {

    public MenuController getUserControl(){
        return new UserControl();
    }
}