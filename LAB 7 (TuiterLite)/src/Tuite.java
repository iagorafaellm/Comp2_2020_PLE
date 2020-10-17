import java.util.ArrayList;

public class Tuite<T> {

    private final Usuario autor;
    private final String texto;
    private ArrayList<String> hashTags;
    private T anexo;

    public Tuite(Usuario autor, String texto) {
        this.autor = autor;
        this.texto = texto;
        this.hashTags = new ArrayList<>();
        extrairHashtags(texto);
    }

    public void anexarAlgo(T anexo) {
        this.anexo = anexo;
    }

    public Object getAnexo() {
        return this.anexo;
    }

    public Usuario getAutor() {
        return this.autor;
    }

    public String getTexto() {
        return this.texto;
    }

    public ArrayList<String> getHashtags() {
        return hashTags;
    }

    private void extrairHashtags(String texto) {
        String[] palavras = texto.split(" ");
        for (String palavra : palavras) {
            if (palavra.startsWith("#")) {
                hashTags.add(palavra);
            }
        }
    }

}
