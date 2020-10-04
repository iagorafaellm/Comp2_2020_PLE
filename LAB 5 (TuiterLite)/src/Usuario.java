import java.awt.*;
import java.util.Objects;

public class Usuario {

    public static final int MIN_TUITES_SENIOR = 200;
    public static final int MIN_TUITES_NINJA = 1000;

    private final String email;
    private String nome;
    private Image foto;
    private int tuitesFeitos = 0;

    // Pode ser INICIANTE, SENIOR ou NINJA
    private NivelUsuario nivel;

    public Usuario(String nome, String email) {
        this.email = email;
        this.nome = nome;
        this.nivel = NivelUsuario.INICIANTE;
    }

    public void addTuitesFeitos(){
        tuitesFeitos+=1;
        if(this.nivel != NivelUsuario.NINJA) {
            if (this.tuitesFeitos >= Usuario.MIN_TUITES_SENIOR && this.tuitesFeitos < Usuario.MIN_TUITES_NINJA)
                this.nivel = NivelUsuario.SENIOR;
            else if (this.tuitesFeitos >= Usuario.MIN_TUITES_NINJA)
                this.nivel = NivelUsuario.NINJA;
        }
    }

    public int getTuitesFeitos() {
        return tuitesFeitos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email);
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public Image getFoto() {
        return this.foto;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNome() {
        return this.nome;
    }

    public NivelUsuario getNivel() {
        return nivel;
    }
}
