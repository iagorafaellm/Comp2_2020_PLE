import java.util.Objects;

public class Hashtag {
    private final String nome;
    private int quant = 0;

    public Hashtag(String nome){
        this.nome = nome;
    }

    public int addquant(){
        return quant+=1;
    }

    public String getNome() {
        return this.nome;
    }

    public int getQuant(){
        return this.quant;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashtag = (Hashtag) o;
        return Objects.equals(nome, hashtag.nome);
    }
}