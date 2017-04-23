package tarefaprincipal;

import java.io.Serializable;

public abstract class Ponto implements Serializable {

    private final String nome;

    protected Ponto(String nome) {
        this.nome = nome;
    }

    public final String getNome() {
        return nome;
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ponto && ((Ponto) obj).nome.equals(nome);
    }
}
