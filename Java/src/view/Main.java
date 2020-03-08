package view;
import business.control.UserControl;
import business.control.LoginException;
import business.control.PassException;
import business.model.User;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        menu();
    }
    
    public static void menu(){
        User usuario = new User();
        ArrayList<User> userList = new ArrayList();
        String login, pass;
        Scanner scan = new Scanner(System.in);
        UserControl usercontrol = new UserControl();
        
        System.out.println("\t\tMenu"
            + "\nDigite 1 para adicionar"
            + "\nDigite 2 para deletar"
            + "\nDigite 3 para listar especifico"
            + "\nDigite 4 para listar todos"
            + "\nDigite 5 para sair");
        int selec = scan.nextInt();
        scan.nextLine();
            
        switch(selec){
            case 1:
                System.out.println("Digite seu login:");
                login = scan.nextLine();                    
                    
                System.out.println("Digite a senha:");
                pass = scan.nextLine();
                
                try{
                    usuario.User(login, pass);
                    usercontrol.add(usuario);
                    System.out.println("O usuário foi adicionado com sucesso!");
                } catch (LoginException e){
                    System.out.println(e.getMessage());
                } catch (PassException e){
                    System.out.println(e.getMessage());
                }
                menu();
                break;
            case 2:
                System.out.println("Digite seu login:");
                login = scan.nextLine();

                try{
                    usercontrol.del(login);
                    System.out.println("O usuário foi removido com sucesso.");
                } catch (LoginException e){
                    System.out.println(e.getMessage());
                }
                menu();
                break;
            case 3:
                User user;
                System.out.println("Digite qual login deseja buscar:");
                login = scan.nextLine();  
                user = usercontrol.list(login);
                System.out.println(user);
                menu();
                break;
            case 4: 
                userList = usercontrol.listAll();
                System.out.println(userList);
                menu();
                break;
            case 5:
                System.exit(0);
            default:
                System.out.println("Comando inválido.");
                menu();
                break;
        }
    }
}
