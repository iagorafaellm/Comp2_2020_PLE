public class Roupa extends Produto {

    private final String nomeDaPeca;
    private final char tamanho;                 //  'p', 'm', 'g', 'u' etc.
    private String cor;

    public Roupa(String nomeDaPeca, char tamanho, String cor, double preçoPeçaRoupa) {
        super("Vestuário", preçoPeçaRoupa);
        this.nomeDaPeca = nomeDaPeca;
        this.tamanho = tamanho;
        this.cor = cor;
    }

    @Override
    public String toString() {
        return super.toString() + "Roupa { " +
                "Tamanho = " + tamanho +
                ", cor = '" + cor + '\'' +
                '}';
    }

}
