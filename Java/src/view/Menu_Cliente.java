package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import business.control.BuscaException;
import business.control.LoginException;
import business.control.MenuController;
import business.control.Menu_Pedido_Controller;
import business.control.PassException;
import business.control.PedidoControl;
import business.control.UserControl;
import business.model.Pedido;
import business.model.User;
import infra.InfraException;
import infra.PersistenceFacade;
import infra.PedidoData;

public class Menu_Cliente {

        private int id;
        public boolean firstimeExecution = true;        
        public String login, pass, end_saida, end_chegada, option;
        public int op;

        public PedidoData data = new PedidoData(); 
        public User usuario, find_user;       
        public Pedido pedido;
        public ArrayList<User> userList;
        public List<Pedido> lista_pedidos;
        public MenuController menuControl;
        public Menu_Pedido_Controller pedidoController;      
        public PersistenceFacade persistenceFacade = PersistenceFacade.obterInstance();

        
        public int getID(){
            return id;
        }

        public void setID(int i){
            this.id = i;
        }


        public void menu() throws InfraException {
            //vai tentar obter id do último pedido. Se não tiver nenhum pedido feito, vai criar um arquivo com 0 nele, para o primeiro pedido ter id 1
            //cada pedido feito incrementa o valor do ID em 1 e salva nesse arquivo para ser acessado quando o programa for re iniciado.
            //só executa uma vez o trecho abaixo, que é quando o main chamar menu
            if (firstimeExecution){                
                firstimeExecution = false;
                setID(data.Obter_ID());
            }
            
            option = JOptionPane.showInputDialog("Menu"
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

            if (!(option.matches("[0-9]+"))){
                JOptionPane.showMessageDialog(null, "Comando inválido.");
                menu();
            }

            else{
                op = Integer.parseInt(option); 
                
                switch(op){
                    case 1:
                        usuario = new User();
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
                        menuControl = new UserControl();                  
                        login = JOptionPane.showInputDialog("Digite o login para buscar:");                  
                        if(login == null){
                            menu();
                        }
                        try{
                            find_user = menuControl.list(login);
                            JOptionPane.showMessageDialog(null, find_user);
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
                        pedido = new Pedido(); 
                        menuControl = new UserControl();                             
                        login = JOptionPane.showInputDialog("Digite seu login:");                  
                        if(login == null){
                            menu();
                        }  
                                        
                        try{                        
                            find_user = menuControl.list(login);                    
                            end_saida = JOptionPane.showInputDialog("Digite o endereço de saida do produto:"); 
                            end_chegada = JOptionPane.showInputDialog("Digite o endereço para qual o produto será enviado:"); 
                            setID(getID()+1);
                            pedido.setPedido(getID() ,end_saida, end_chegada);                    
                            persistenceFacade.cadastrar_Pedido(find_user, pedido);
                            System.out.println("O pedido foi feito com sucesso!");                    
                        }catch(BuscaException | LoginException | PassException | InfraException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                        menu();
                        break;     
                        
                    case 6:  
                        menuControl = new UserControl(); 
                        pedidoController = new PedidoControl();             
                        login = JOptionPane.showInputDialog("Digite o login para verficiar se existem pedidos feitos:");                  
                        if(login == null){
                            menu();
                        }
                        try{
                            find_user = menuControl.list(login);
                            lista_pedidos = pedidoController.list(login);
                            JOptionPane.showMessageDialog(null, lista_pedidos);
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
    }