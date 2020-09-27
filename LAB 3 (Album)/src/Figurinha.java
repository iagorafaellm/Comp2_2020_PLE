public class Figurinha {

    private final int posicao;
    private final String urlImagem;
    private boolean colada, repetida;

    public Figurinha(int posicao, String urlImagem) {
        this.posicao = posicao;
        this.urlImagem = urlImagem;
    }

    /**
     * Indica a posição, no álbum, que esta figurinha deve ocupar.
     *
     * @return Um int dizendo a posição da figurinha.
     */
    public int getPosicao() {
        return this.posicao;
    }

    /**
     * Retorna a URL de onde a imagem associada a esta figurinha deverá ser baixada.
     *
     * @return uma String com o endereço desejado
     */
    public String getUrlImagem() {
        return this.urlImagem;
    }

    public boolean isColada() {
        return colada;
    }

    public boolean isRepetida() {
        return repetida;
    }

    public void setColada(boolean colada) {
        this.colada = colada;
    }

    public void setRepetida(boolean repetida) {
        this.repetida = repetida;
    }
}
