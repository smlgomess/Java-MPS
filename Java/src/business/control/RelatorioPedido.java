package business.control;

public class RelatorioPedido extends RelatorioTemplate{

    @Override
    protected String head() {
        return "Dados sobre pedidos realizados no Aplicativo";
    }

    @Override
    protected String body() {
        return "Dados em porcentagem sobre os pedidos";
    }

    @Override
    protected String footer() {
        return "Dados sobre a empresa e aplicativo";
    }

    
}