import java.util.*;

public class MapaCidades {
    private Set<Cidade> cidades;
    private Map<Cidade, Set<Rota>> rotas;

    public MapaCidades() {
        cidades = new TreeSet<>();
        rotas = new HashMap<>();
    }

    public void adicionarCidade(Cidade cidade) {
        cidades.add(cidade);
        rotas.putIfAbsent(cidade, new HashSet<>());
    }

    public void conectarCidades(Cidade origem, Cidade destino, double distancia) {
        rotas.get(origem).add(new Rota(destino, distancia));
        rotas.get(destino).add(new Rota(origem, distancia));
    }

    public void listarConexoes(Cidade cidade) {
        System.out.println("Conexões de " + cidade.getNome() + ":");
        Set<Rota> conexoes = rotas.get(cidade);
        if (conexoes == null || conexoes.isEmpty()) {
            System.out.println("Nenhuma conexão encontrada.");
            return;
        }
        for (Rota rota : conexoes) {
            System.out.println("  " + rota);
        }
    }

    public boolean existeCaminho(Cidade origem, Cidade destino) {
        Set<Cidade> visitadas = new HashSet<>();
        return buscaProfundidade(origem, destino, visitadas);
    }

    private boolean buscaProfundidade(Cidade atual, Cidade destino, Set<Cidade> visitadas) {
        if (atual.equals(destino)) return true;
        visitadas.add(atual);
        for (Rota rota : rotas.getOrDefault(atual, new HashSet<>())) {
            if (!visitadas.contains(rota.getDestino())) {
                if (buscaProfundidade(rota.getDestino(), destino, visitadas)) return true;
            }
        }
        return false;
    }

    public void listarCidadesSemConexao() {
        for (Cidade cidade : cidades) {
            if (rotas.get(cidade).isEmpty()) {
                System.out.println(cidade);
            }
        }
    }

    public Set<Cidade> getCidades() {
        return cidades;
    }

    public Set<Rota> getConexoes(Cidade cidade) {
        return rotas.getOrDefault(cidade, new HashSet<>());
    }
}
