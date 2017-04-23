package tarefaprincipal;

import java.io.*;
import java.util.*;

public class ArmazenamentoImpl implements Armazenamento {

    private static final String DABASE_NAME = "dados.db";

    private final File db;


    private LinkedHashMap<Usuario, LinkedHashMap<Ponto, Integer>> _usuariosPontosMap;

    public ArmazenamentoImpl(File dir) {

        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }

        db = new File(dir, DABASE_NAME);

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(db));
            Object object = ois.readObject();
            ois.close();
            _usuariosPontosMap = (LinkedHashMap<Usuario, LinkedHashMap<Ponto, Integer>>) object;
        } catch (Exception e) {
            _usuariosPontosMap = new LinkedHashMap<>();
        }
    }

    @Override
    public void salvarPontoDoUsuario(Usuario usuario, Ponto ponto, int quantidade) {
        LinkedHashMap<Ponto, Integer> pontosDoUsuario = _usuariosPontosMap.getOrDefault(usuario, new LinkedHashMap<>());
        int pontos = pontosDoUsuario.getOrDefault(ponto, 0);
        pontosDoUsuario.put(ponto, pontos + quantidade);
        _usuariosPontosMap.put(usuario, pontosDoUsuario);
        persistir();
    }

    @Override
    public int recuperarPontosPorUsuario(Ponto ponto, Usuario usuario) {
        Map<Ponto, Integer> pontosDoUsuario = _usuariosPontosMap.getOrDefault(usuario, new LinkedHashMap<>());
        return pontosDoUsuario.getOrDefault(ponto, 0);
    }

    @Override
    public List<Usuario> recuperarTodosUsuarios() {
        return new ArrayList<>(_usuariosPontosMap.keySet());
    }

    @Override
    public Map<Ponto, Integer> recuperarPontosPorUsuario(Usuario usuario) {
        return _usuariosPontosMap.get(usuario);
    }

    private void persistir() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(db));
            oos.writeObject(_usuariosPontosMap);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
