package view;

import javax.swing.JOptionPane;

import infra.InfraException;

public class Menu {
    public String option;
    public int op;
    Menu_Cliente menu_Cliente = new Menu_Cliente();
    Menu_Motorista menu_motorista = new Menu_Motorista();

    public void menu() throws InfraException {
        
        option = JOptionPane.showInputDialog("Menu"
                                                        + "\nDigite 1 para abrir o menu do Cliente (usuário)"
                                                        + "\nDigite 2 para abrir o menu do Motorista"                                                        
                                                        + "\nDigite 3 para sair", "Digite sua opção");


        if(option == null){
            System.exit(0);
        }

        if (!(option.matches("[0-9]+"))){
            JOptionPane.showMessageDialog(null, "Comando inválido.");
            menu();
        }
        
        else{
            op = Integer.parseInt(option); 

            switch(op){
                case 1:
                    menu_Cliente.menu();                  
                    break;
                case 2:                
                    menu_motorista.menu();
                    break;
                case 3:
                    System.exit(0);  
                default:
                    JOptionPane.showMessageDialog(null, "Comando inválido.");
                    menu();
                    break;
            }
        }
    }

}
