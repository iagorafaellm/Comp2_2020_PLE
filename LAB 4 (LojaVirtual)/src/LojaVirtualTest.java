import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

public class LojaVirtualTest {

    Produto produtoTest;
    Livro livroTest;
    Roupa roupaTest;
    LojaVirtual theKikiStore;
    int DELTA = 0;

    @Before
    public void setUp() {
        produtoTest = new Produto("Artigos de Bruxaria", 8.00);
        livroTest = new Livro("Grimório da Kiki", "Kiki", 1989, 1373,79.00);
        roupaTest = new Roupa("Laço de Cabelo Mágico",'u', "vermelho", 4.00); //'u' = tamanho único
        theKikiStore = new LojaVirtual();
    }

    @Test
    public void testarInclusaoProdutoEstoque() {
        theKikiStore.incluirProdutoEstoque(produtoTest, 27);
        Assert.assertEquals(27, theKikiStore.getTamanhoEstoque(produtoTest));
        Assert.assertEquals(27, theKikiStore.getTamanhoEstoque());
        Assert.assertEquals("Artigos de Bruxaria", produtoTest.getCategoria());

        theKikiStore.incluirProdutoEstoque(livroTest, 8);
        Assert.assertEquals(8, theKikiStore.getTamanhoEstoque(livroTest));
        Assert.assertEquals(35, theKikiStore.getTamanhoEstoque());
        Assert.assertEquals("Publicações", livroTest.getCategoria());

        theKikiStore.incluirProdutoEstoque(roupaTest, 54);
        Assert.assertEquals(54, theKikiStore.getTamanhoEstoque(roupaTest));
        Assert.assertEquals(89, theKikiStore.getTamanhoEstoque());
        Assert.assertEquals("Vestuário", roupaTest.getCategoria());
    }

    @Test
    public void testarVendaProduto() {
        theKikiStore.incluirProdutoEstoque(produtoTest, 27);
        Assert.assertEquals("ID = 1 - Categoria = Artigos de Bruxaria - Quantidade = 27 - Preço = 8.0 - " +
                "Preço Total = 216.0", theKikiStore.efetuarVenda(produtoTest, 27));
        theKikiStore.efetuarVenda(produtoTest, 27);
        Assert.assertEquals(0, theKikiStore.getTamanhoEstoque(produtoTest));
        Assert.assertEquals(216.0, theKikiStore.getValorTotalDeVendas(), DELTA);

        theKikiStore.incluirProdutoEstoque(livroTest, 2);
        theKikiStore.efetuarVenda(livroTest, 2);
        Assert.assertEquals(374.0, theKikiStore.getValorTotalDeVendas(), DELTA);

        theKikiStore.incluirProdutoEstoque(roupaTest, 4);
        theKikiStore.efetuarVenda(roupaTest, 3);
        Assert.assertEquals(386.0, theKikiStore.getValorTotalDeVendas(), DELTA);
    }

}