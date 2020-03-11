package infra;

import business.model.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class UserData {
    
    public void salvarDados(HashMap<String, User> users) {
        File file = new File("DadosUsuario.bin");
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(users);
            out.close();
        } catch(FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
        } catch (IOException e) {
            System.out.println("Um erro foi encontrado ao salvar os dados. Contate o administrador ou tente mais tarde.");
        }
    }
    
    @SuppressWarnings("unchecked")
    public HashMap<String, User> carregarDados() throws InfraException {
        HashMap<String, User> users = new HashMap<>();
        File file = new File("DadosUsuario.bin");
        ObjectInputStream objInput = null;
        InputStream in = null;
        
        if(!file.exists()) {
            salvarDados(users);
        }
        
        try {
            in = new FileInputStream(file);
            objInput = new ObjectInputStream(in);
            users = (HashMap<String, User>) objInput.readObject();
            return users; 
        } catch (NullPointerException | IOException | ClassNotFoundException e) {
            throw new InfraException("Erro de persistência de dados. Contate o administrador ou tente mais tarde.");  
        } finally {
            try {
                objInput.close();
                in.close(); 
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
