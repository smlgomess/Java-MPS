package view;
import business.control.UserControl;
import business.control.LoginException;
import business.control.PassException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        menu();
    }
    
    public static void menu(){
        String login, pass;
        Scanner scan = new Scanner(System.in);
        UserControl usercontrol = new UserControl();
        
        System.out.println("\t\tMenu"
            + "\nDigite 1 para adicionar"
            + "\nDigite 2 para deletar"
            + "\nDigite 3 para listar especifico"
            + "\nDigite 4 para listar todos");
        int selec = scan.nextInt();
        scan.nextLine();
            
        switch(selec){
            case 1:
                System.out.println("Digite seu login:");
                login = scan.nextLine();                    
                    
                System.out.println("Digite a senha:");
                pass = scan.nextLine();
                
                try{
                    usercontrol.add(login, pass);
                    break;
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
            default:
                System.out.println("Comando inválido.");
                menu();
                break;
        }
    }
}
