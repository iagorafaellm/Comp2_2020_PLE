public class LojaVirtual {

    private int tamanhoEstoque = 0;
    private double valorTotalDeVendas = 0;

    public LojaVirtual() {
        this.tamanhoEstoque = tamanhoEstoque;
        this.valorTotalDeVendas = valorTotalDeVendas;
    }

    public int getTamanhoEstoque() {                            //overload (geral)
        return tamanhoEstoque;
    }

    public int getTamanhoEstoque(Produto produto) {             //overload (produto específico)
        return produto.getQuantidadeEmEstoque();
    }

    public void incluirProdutoEstoque(Produto produto, int quantidade) {
        produto.adicionarQuantEstoque(quantidade);
        this.tamanhoEstoque += quantidade;
    }

    private boolean receberPagamento(double valor) {
        this.valorTotalDeVendas += valor;
        return true;
    }

    public String efetuarVenda(Produto produto, int quantidade) {   //
        if (produto.getQuantidadeEmEstoque() < quantidade) {
            return "A compra não pôde ser efetuada!"; //lançar uma exceção
        }
        receberPagamento(produto.getPreçoEmReais()*quantidade);
        produto.removerProdutoEstoque(quantidade);
        this.tamanhoEstoque -= quantidade;
        return "ID = " + produto.getId() +
                " - Categoria = " + produto.getCategoria() +
                " - Quantidade = " + quantidade +
                " - Preço = " + produto.getPreçoEmReais() +
                " - Preço Total = " + (produto.getPreçoEmReais()*quantidade);
    }

    public double getValorTotalDeVendas() {    //retornar o valor total de todas as vendas efetuadas pela loja
        return this.valorTotalDeVendas;
    }

}
