package infra;

import business.model.Motorista;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class MotoristaData implements Data_Motorista_Persistent{
    
    public void salvarDados(HashMap<String, Motorista> users) throws InfraException{
        File file = new File("DadosMotorista.bin");
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(users);
            out.close();
        } catch(FileNotFoundException e) {
            throw new InfraException("Arquivo não encontrado.");
        } catch (IOException e) {
            throw new InfraException("Um erro foi encontrado ao salvar os dados." +
                                     "\nContate o administrador ou tente mais tarde.");
        }
    }
    
    @SuppressWarnings("unchecked")
    public HashMap<String, Motorista> carregarDados() throws InfraException {
        HashMap<String, Motorista> users = new HashMap<>();
        File file = new File("DadosMotorista.bin");
        ObjectInputStream objInput = null;
        InputStream in = null;
        
        if(!file.exists()) {
            salvarDados(users);
        }
        
        try {
            in = new FileInputStream(file);
            objInput = new ObjectInputStream(in);
            users = (HashMap<String, Motorista>) objInput.readObject();
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
