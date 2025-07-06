import java.util.Objects;

public class Cidade implements Comparable<Cidade> {
    private String nome;
    private String estado;
    private int populacao;

    public Cidade(String nome, String estado, int populacao) {
        this.nome = nome;
        this.estado = estado;
        this.populacao = populacao;
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public int getPopulacao() {
        return populacao;
    }

    @Override
    public String toString() {
        return nome + " (" + estado + ") - População: " + populacao;
    }

    @Override
    public int compareTo(Cidade outra) {
        return this.nome.compareToIgnoreCase(outra.nome); // ou comparar por população
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cidade)) return false;
        Cidade cidade = (Cidade) o;
        return nome.equalsIgnoreCase(cidade.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase());
    }
}
