package view;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import business.control.exception.BuscaException;
import business.control.exception.CNHException;
import business.control.exception.LoginException;
import business.control.exception.PassException;
import business.control.Menu_Motorista_Controller;
import business.control.MotoristaControl;
import business.model.Motorista;
import infra.InfraException;
import infra.MotoristaPersistenceFacade;

public class Menu_Motorista {
                  
        public String login, pass, option, cnh;
        public int op;        
        public Motorista usuario, find_user;  
        public ArrayList<Motorista> userList;        
        public Menu_Motorista_Controller menuControl;     
        public MotoristaPersistenceFacade persistenceFacade = MotoristaPersistenceFacade.obterInstance();

               
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
                        if(pass == null){
                            menu();
                        }                    
                        try{
                            usuario.UserConstruct(login, pass, cnh);
                            persistenceFacade.cadastrar(usuario);
                            System.out.println("O Motorista foi adicionado com sucesso!");
                        } catch (InfraException | LoginException | PassException | CNHException e){
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
                            userList = persistenceFacade.listarAll();
                            JOptionPane.showMessageDialog(null, userList);
                        } catch(BuscaException e){
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                        menu();
                        break;
                    case 5:   
                        JOptionPane.showMessageDialog(null, "Ainda não implementado.");
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