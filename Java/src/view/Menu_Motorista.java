package view;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import business.control.exception.BuscaException;
import business.control.exception.CNHException;
import business.control.exception.LoginException;
import business.control.exception.PassException;
import business.control.Menu_Motorista_Controller;
import business.control.MotoristaAdapter;
import business.control.MotoristaControl;
import business.model.Motorista;
import infra.InfraException;
import infra.PersistenceFacade;

public class Menu_Motorista {
                  
        public String login, pass, option, cnh;
        public int op;        
        public Motorista usuario, find_user;  
        public ArrayList<Motorista> userList;        
        public Menu_Motorista_Controller menuControl = new MotoristaControl();  
        public MotoristaAdapter adaptador_usuario = new MotoristaAdapter();   
        public PersistenceFacade persistenceFacade = PersistenceFacade.obterInstance();

        public void sub_menu_usuario() throws InfraException {
            option = JOptionPane.showInputDialog("Menu"
                                                        + "\nDigite 1 para criar um usuário Motorista com um nickname"
                                                        + "\nDigite 2 para criar um usuário Motorista com um email"                                                        
                                                        + "\nDigite 3 para cancelar", "Digite sua opção");
            if(option == null){
                menu();
            }

            if (!(option.matches("[0-9]+"))){
                JOptionPane.showMessageDialog(null, "Comando inválido.");
                sub_menu_usuario();
            }

            else{
                op = Integer.parseInt(option); 
                switch(op){
                    case 1:                    
                        usuario = new Motorista();
                        login = JOptionPane.showInputDialog("Digite seu login:");                  
                        if(login == null){
                            menu();
                        }    
                        
                        pass = JOptionPane.showInputDialog("Digite sua senha:");
                        if(pass == null){
                            menu();
                        }

                        cnh = JOptionPane.showInputDialog("Digite o numero de Registro da CNH (11 digitos numericos):");
                        if(cnh == null){
                            menu();
                        }  
                        
                        try{
                            usuario.UserConstruct(login, pass, cnh);
                            persistenceFacade.cadastrar(usuario, 1);
                            System.out.println("O Motorista foi adicionado com sucesso!");
                        } catch (InfraException | LoginException | PassException | CNHException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                        break;
                    case 2: 
                        usuario = new Motorista();
                        login = JOptionPane.showInputDialog("Digite seu email:");                  
                        if(login == null){
                            menu();
                        }    
                        
                        pass = JOptionPane.showInputDialog("Digite sua senha:");
                        if(pass == null){
                            menu();
                        }
                        
                        cnh = JOptionPane.showInputDialog("Digite o numero de Registro da CNH (11 digitos numericos):");
                        if(cnh == null){
                            menu();
                        }  

                        try{
                            usuario.UserConstruct(login, pass, cnh);
                            persistenceFacade.cadastrar(usuario, 2);
                            System.out.println("O Motorista foi adicionado com sucesso!");
                        } catch (InfraException | LoginException | PassException | CNHException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                        break;
                    case 3:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Comando inválido.");
                        sub_menu_usuario();
                }
            }

            
        }
        
        
        public void menu() throws InfraException {
                        
            option = JOptionPane.showInputDialog("Menu"
                                                        + "\nDigite 1 para adicionar um motorista"
                                                        + "\nDigite 2 para deletar um motorista"
                                                        + "\nDigite 3 para listar um motorista especifico"
                                                        + "\nDigite 4 para listar todos os motoristas" 
                                                        + "\nDigite 5 para listar as solicitações de um motorista"                                                 
                                                        + "\nDigite 6 para sair", "Digite sua opção");
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
                        sub_menu_usuario();    
                        menu();
                        break;
                    case 2:
                        login = JOptionPane.showInputDialog("Digite o login para deletar:");                  
                        if(login == null){
                            menu();
                        }

                        try{
                            persistenceFacade.delMot(login);
                            JOptionPane.showMessageDialog(null, "O Motorista foi removido com sucesso.");
                        } catch (InfraException | BuscaException | LoginException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                        menu();
                        break;
                    case 3:  
                        menuControl = new MotoristaControl();                  
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
                            userList = persistenceFacade.listarAll_Motoristas();
                            JOptionPane.showMessageDialog(null, userList);
                        } catch(BuscaException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                        menu();
                        break;
                    case 5:   
                        JOptionPane.showMessageDialog(null, "Nas próximas versões.");
                        menu();
                        break;       
                    case 6: 
                        System.exit(0); 
                    default:
                        JOptionPane.showMessageDialog(null, "Comando inválido.");
                        menu();
                        break;
                }
            }
        }
    }