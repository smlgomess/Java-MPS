package infra;

import business.model.Pedido;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class PedidoData implements Pedido_Data_Persistent {

    public void salvarDados(HashMap<String, Pedido> pedidos) throws InfraException {
        File file = new File("PedidosFeitos.bin");
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(pedidos);
            out.close();
        } catch (FileNotFoundException e) {
            throw new InfraException("Arquivo não encontrado.");
        } catch (IOException e) {
            throw new InfraException(
                    "Um erro foi encontrado ao salvar os dados." + "\nContate o administrador ou tente mais tarde.");
        }
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, Pedido> carregarDados() throws InfraException {
        HashMap<String, Pedido> pedidos = new HashMap<>();
        File file = new File("PedidosFeitos.bin");
        ObjectInputStream objInput = null;
        InputStream in = null;

        if (!file.exists()) {
            salvarDados(pedidos);
        }

        try {
            in = new FileInputStream(file);
            objInput = new ObjectInputStream(in);
            pedidos = (HashMap<String, Pedido>) objInput.readObject();
            return pedidos;
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
