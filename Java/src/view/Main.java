package view;

import business.control.BuscaException;
import business.control.MenuController;
import business.control.LoginException;
import business.control.PassException;
import business.control.UserControl;
import business.model.User;
import infra.InfraException;
import infra.PersistenceFacade;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args){
        menu();
    }
    
    public static void menu(){
        User usuario = new User();
        ArrayList<User> userList;
        String login, pass;
        MenuController menuControl = new UserControl();
        PersistenceFacade persistenceFacade = PersistenceFacade.obterInstance();
        
        String option = JOptionPane.showInputDialog("Menu"
                                                    + "\nDigite 1 para adicionar"
                                                    + "\nDigite 2 para deletar"
                                                    + "\nDigite 3 para listar especifico"
                                                    + "\nDigite 4 para listar todos"
                                                    + "\nDigite 5 para sair", "Digite sua opção");
        if(option == null){
            System.exit(0);
        }

        int op = Integer.parseInt(option); 
          
        switch(op){
            case 1:
                login = JOptionPane.showInputDialog("Digite seu login:");                  
                if(login == null){
                    menu();
                }    
                
                pass = JOptionPane.showInputDialog("Digite sua senha:");
                if(pass == null){
                    menu();
                }
                
                try{
                    usuario.UserConstruct(login, pass);
                    persistenceFacade.cadastrar(usuario);
                    System.out.println("O usuário foi adicionado com sucesso!");
                } catch (InfraException | LoginException | PassException e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                menu();
                break;
            case 2:
                login = JOptionPane.showInputDialog("Digite o login para deletar:");                  
                if(login == null){
                    menu();
                }

                try{
                    persistenceFacade.del(login);
                    JOptionPane.showMessageDialog(null, "O usuário foi removido com sucesso.");
                } catch (InfraException | BuscaException | LoginException e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                menu();
                break;
            case 3:
                User user;
                login = JOptionPane.showInputDialog("Digite o login para buscar:");                  
                if(login == null){
                    menu();
                }
                try{
                    user = menuControl.list(login);
                    JOptionPane.showMessageDialog(null, user);
                }catch(BuscaException | LoginException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                menu();
                break;
            case 4:
                try{
                    userList = persistenceFacade.listarAll();
                    JOptionPane.showMessageDialog(null, userList);
                } catch(BuscaException e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                menu();
                break;
            case 5:
                System.exit(0);
            default:
                JOptionPane.showMessageDialog(null, "Comando inválido.");
                menu();
                break;
        }
    }
}
