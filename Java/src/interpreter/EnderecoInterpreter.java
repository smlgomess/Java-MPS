package interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import business.model.Pedido;
import infra.InfraException;

public class EnderecoInterpreter implements Interpretador {
    StringBuilder stringChegadaBuilder, stringSaidaBuilder;
    Pattern p = Pattern.compile("[1-9]");
    
    public EnderecoInterpreter() {}    

    @Override
    public void interpretar(Pedido pedido) throws InfraException {
        stringChegadaBuilder = new StringBuilder(pedido.getEdchegada());
        stringSaidaBuilder = new StringBuilder(pedido.getEdpartida());

        if (pedido.getEdchegada().length() == 0 || pedido.getEdpartida().length() == 0) {
            return;
        }
        if(!pedido.getEdchegada().contains("Rua")) {
            pedido.setEdchegada(adicionaChegadaRua(pedido, stringChegadaBuilder));
        }

        if(!pedido.getEdpartida().contains("Rua")) {
            pedido.setEdpartida(adicionaSaidaRua(pedido, stringSaidaBuilder));
        }

        if(!pedido.getEdchegada().contains(",")) {
            int indice = -1;
            Matcher m = p.matcher(pedido.getEdchegada());

            if (m.find()) {
                indice = m.start();
            }
            if(indice < 0) {
                throw new InfraException("Não foi possível interpretar o endereço. Tente novamente.");
            }
            pedido.setEdchegada(adicionaVirgRua(pedido, stringChegadaBuilder, indice));
        }

        if(!pedido.getEdpartida().contains(",")) {
            int indice = -1;
            Matcher m = p.matcher(pedido.getEdpartida());

            if (m.find()) {
                indice = m.start();
            }
            if(indice < 0) {
                throw new InfraException("Não foi possível interpretar o endereço. Tente novamente.");
            }
            pedido.setEdpartida(adicionaVirgRua(pedido, stringSaidaBuilder, indice));
        }
    }

    private String adicionaVirgRua(Pedido pedido, StringBuilder stringBuilder, int indice) {
        stringBuilder.insert(indice - 1, ",");
        return stringBuilder.toString();
    }

    private String adicionaChegadaRua(Pedido pedido, StringBuilder stringBuilder) {
        stringBuilder.insert(0, "Rua: ");
        return stringBuilder.toString();
    }

    private String adicionaSaidaRua(Pedido pedido, StringBuilder stringBuilder) {
        stringBuilder.insert(0, "Rua: ");
        return stringBuilder.toString();
    }
}