package business.control;

import business.model.Relatorio;

public abstract class RelatorioTemplate {
    
    protected abstract String head();
    protected abstract String body();
    protected abstract String footer();

    protected final Relatorio gerarRelatorio(){
        StringBuilder sb = new StringBuilder();

        sb.append(head());
        sb.append(body());
        sb.append(footer());

        return new Relatorio(sb.toString());
    }
}