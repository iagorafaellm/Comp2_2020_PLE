public class ArquivoCorrompidoException extends Exception {

    private final int linhasCorrompidas;

    public ArquivoCorrompidoException(int linhas) {
        this.linhasCorrompidas = linhas;
    }

    public int getLinhasCorrompidas() {
        return linhasCorrompidas;
    }

}
