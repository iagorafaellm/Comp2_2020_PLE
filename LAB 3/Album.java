public class Album {

    Figurinha[] todasAsFigurinhas;
    private int totalFigurinhas;
    private int quantFigurinhasPorPacotinho;
    private int totalPacotinhosRecebidos;
    private int quantFigurinhasColadas;
    private int quantFigurinhasRepetidas;
    private int quantFigurinhasFaltando;
    public static final float PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR = 0.9f;  // 90%

    public Album(int totalFigurinhas, int quantFigurinhasPorPacotinho) {
        this.totalFigurinhas = totalFigurinhas;
        this.quantFigurinhasPorPacotinho = quantFigurinhasPorPacotinho;
        this.todasAsFigurinhas = new Figurinha[totalFigurinhas + 1];
        for (int i = 0; i <= totalFigurinhas; i++) {
            todasAsFigurinhas[i] = new Figurinha(i, "http://urlFakeDaFigurinha" + i + ".jpg");
        }
    }

    public void receberNovoPacotinho(Figurinha[] pacotinho) {
        totalPacotinhosRecebidos++;
        for (int i = 0; i < pacotinho.length; i++) {
            if (!possuiFigurinhaColada(pacotinho[i])) {
               todasAsFigurinhas[pacotinho[i].getPosicao()].setColada(true);
               quantFigurinhasColadas++;
            }
            else {
                todasAsFigurinhas[pacotinho[i].getPosicao()].setRepetida(true);
                quantFigurinhasRepetidas++;
            }
        }
    }

    public int getTotalPacotinhosRecebidos() {
        return totalPacotinhosRecebidos;
    }

    /**
     * Termina de preencher o álbum, desde que ele já esteja preenchido além de certa fração
     * mínima definida em Album.PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR.
     *
     * Se o álbum ainda não estiver completo o suficiente para isso, este método simplesmente
     * não faz nada.
     */
    public void encomendarFigurinhasRestantes() {
        if (quantFigurinhasColadas >= PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR*totalFigurinhas) {
            quantFigurinhasColadas = totalFigurinhas;
        }
    }

    public boolean possuiFigurinhaColada(int posicao) {
        return this.todasAsFigurinhas[posicao].isColada();
    }

    public boolean possuiFigurinhaColada(Figurinha figurinha) {         // overload
        return possuiFigurinhaColada(figurinha.getPosicao());
    }

    public boolean possuiFigurinhaRepetida(int posicao) {
        return this.todasAsFigurinhas[posicao].isRepetida();
    }

    public boolean possuiFigurinhaRepetida(Figurinha figurinha) {       // overload
        return possuiFigurinhaRepetida(figurinha.getPosicao());
    }

    public int getQuantFigurinhasColadas() {
        return quantFigurinhasColadas;
    }

    public int getQuantFigurinhasRepetidas() {
        return quantFigurinhasRepetidas;
    }

    public int getQuantFigurinhasFaltando() {
        return quantFigurinhasFaltando;
    }

    public void setQuantFigurinhasFaltando(int quantFigurinhasFaltando) {
        this.quantFigurinhasFaltando = totalFigurinhas - quantFigurinhasColadas;
    }
}
