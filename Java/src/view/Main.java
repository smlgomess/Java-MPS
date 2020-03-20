package view;

import business.control.BuscaException;
import business.control.MenuController;
import business.control.Menu_Pedido_Controller;
import business.control.LoginException;
import business.control.PassException;
import business.control.UserControl;
import business.control.PedidoControl;
import business.model.User;
import business.model.Pedido;
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
        User find_user;
        Pedido pedido = new Pedido();
        ArrayList<User> userList;
        String login, pass, end_saida, end_chegada;
        MenuController menuControl = new UserControl();
        Menu_Pedido_Controller pedidoController = new PedidoControl();

        PersistenceFacade persistenceFacade = PersistenceFacade.obterInstance();
        
        String option = JOptionPane.showInputDialog("Menu"
                                                    + "\nDigite 1 para adicionar usuário"
                                                    + "\nDigite 2 para deletar usuário"
                                                    + "\nDigite 3 para listar usuário especifico"
                                                    + "\nDigite 4 para listar todos os usuários"
                                                    + "\nDigite 5 para realizar um pedido"
                                                    + "\nDigite 6 para listar os pedidos de um usuário"
                                                    + "\nDigite 7 para sair", "Digite sua opção");
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
                login = JOptionPane.showInputDialog("Digite seu login:");                  
                if(login == null){
                    menu();
                }  
                                
                try{
                    find_user = menuControl.list(login);                    
                    end_saida = JOptionPane.showInputDialog("Digite o endereço de saida do produto:"); 
                    end_chegada = JOptionPane.showInputDialog("Digite o endereço para qual o produto será enviado:"); 
                    pedido.setPedido(end_saida, end_chegada);                    
                    persistenceFacade.cadastrar_Pedido(find_user, pedido);
                    System.out.println("O pedido foi feito com sucesso!");                    
                }catch(BuscaException | LoginException | PassException | InfraException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                menu();
                break;     
                
            case 6:                
                login = JOptionPane.showInputDialog("Digite o login para verficiar se existem pedidos feitos:");                  
                if(login == null){
                    menu();
                }
                try{
                    find_user = menuControl.list(login);
                    pedido = pedidoController.list(login);
                    JOptionPane.showMessageDialog(null, pedido);
                }catch(BuscaException | LoginException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                menu();
                break;
                
            case 7:
                System.exit(0);    
            default:
                JOptionPane.showMessageDialog(null, "Comando inválido.");
                menu();
                break;
        }
    }
}
