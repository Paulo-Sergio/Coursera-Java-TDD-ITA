package tarefaprincipal;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Usuario && ((Usuario) obj).nome.equals(nome);
    }
}
