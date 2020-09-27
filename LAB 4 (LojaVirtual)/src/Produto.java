public class Produto {

    private static long nextSeqNum = 1;
    protected final long id;
    private int pesoEmGramas;
    private double preçoEmReais;
    private String categoria;
    private int quantidadeEmEstoque;

    public Produto(String categoria, double preçoEmReais) {
        this.id = nextSeqNum++;
        this.pesoEmGramas = pesoEmGramas;
        this.preçoEmReais = preçoEmReais;
        this.categoria = categoria;
        this.quantidadeEmEstoque = 0;
    }

    public long getId() {
        return id;
    }

    public int getQuantidadeEmEstoque() {
        return this.quantidadeEmEstoque;
    }

    public void adicionarQuantEstoque(int quantidade) {
        this.quantidadeEmEstoque += quantidade;
    }

    public void removerProdutoEstoque(int quantidade) {
        this.quantidadeEmEstoque -= quantidade;
    }

    public double getPreçoEmReais() {
        return this.preçoEmReais;
    }

    public String getCategoria() {
        return this.categoria;
    }

    @Override
    public String toString() {
        return "Produto {" +
                "Peso em gramas = " + pesoEmGramas +
                ", preço em reais = " + preçoEmReais +
                ", categoria = '" + categoria + '\'' +
                '}';
    }

}
