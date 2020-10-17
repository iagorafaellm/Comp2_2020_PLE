import java.util.*;

/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 */
public class TuiterLite<T> {

    public static final int TAMANHO_MAXIMO_TUITES = 120;

    private Set<Usuario> usuarios;                  // transformar em um Map<String, Usuario> usuarioByEmail;
    private Map<String, Integer> qtdByHashtags;     // transformar em um Map<String, Integer> contByHashtag;
    private ArrayList<Tuite> tuites;
    private String hashtagMaisComum;
    private int quantOcorrenciasHashtagMaisComum;

    public TuiterLite() {
        this.tuites = new ArrayList<>();
        this.usuarios = new HashSet<>();
        this.qtdByHashtags = new HashMap<>();
    }

    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * Se o email informado já estiver em uso, não faz nada e retorna null.
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) {
        Usuario novoUsuario = new Usuario(nome, email);
        this.usuarios.add(novoUsuario);
        return novoUsuario;
    }

    /**
     * Tuíta algo, retornando o objeto Tuíte criado.
     * Se o tamanho do texto exceder o limite pré-definido, não faz nada e retorna null.
     * Se o usuário não estiver cadastrado, não faz nada e retorna null.
     *
     * @param usuario O autor do tuíte
     * @param texto O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     * @throws UsuarioDesconhecidoException caso o autor do tuite seja um usuário desconhecido
     * @throws TamanhoMaximoExcedidoException caso o tamanho máximo do tuite seja excedido
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) throws UsuarioDesconhecidoException, TamanhoMaximoExcedidoException {
        boolean autorDesconhecido = usuario.getNome().equals("Usuário Desconhecido") || usuario.getEmail().equals("unknown@void.com");
        boolean tamanhoMaximoExcedido = texto.length() > TuiterLite.TAMANHO_MAXIMO_TUITES;

        if(autorDesconhecido) {
            throw new UsuarioDesconhecidoException("Um usuário desconhecido não pode fazer um tuite");
        }

        if(tamanhoMaximoExcedido) {
            throw new TamanhoMaximoExcedidoException("Um tuite não pode ultrapassar o tamanho de 120 caracteres");
        }

        atualizaUsuario(usuario);
        Tuite tuite = new Tuite<>(usuario, texto);
        tuites.add(tuite);
        adicionarHashTag(tuite.getHashtags());
        return tuite;
    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */
    public String getHashtagMaisComum() {
        return this.hashtagMaisComum;
    }

    /**
     * Incrementa a quantidade de tuites do usuário e o promove, se for o caso
     * @param usuario o usuário a ser atualizado
     */
    private void atualizaUsuario(Usuario usuario) {
        int qtdTuitesUsuario = usuario.getQtdTuites();

        qtdTuitesUsuario++;
        usuario.setQtdTuites(qtdTuitesUsuario);
        verificaPromocaoUsuarioASenior(usuario, qtdTuitesUsuario);
        verificaPromocaoUsuarioANinja(usuario, qtdTuitesUsuario);
    }

    /**
     * Verifica se o usuário chegou no mínimo de tuites para ser promovido a senior e o promove, se for o caso
     * @param usuario o usuário em questão
     * @param qtdTuitesUsuario a quantidade de tuites desse usuário
     */
    private void verificaPromocaoUsuarioASenior(Usuario usuario, int qtdTuitesUsuario) {
        boolean promoverUsuarioASenior = qtdTuitesUsuario >= Usuario.MIN_TUITES_SENIOR;

        if(promoverUsuarioASenior) {
            usuario.setNivel(NivelUsuario.SENIOR);
        }
    }

    /**
     * Verifica se o usuário chegou no mínimo de tuites para ser promovido a ninja e o promove, se for o caso
     * @param usuario o usuário em questão
     * @param qtdTuitesUsuario a quantidade de tuites desse usuário
     */
    private void verificaPromocaoUsuarioANinja(Usuario usuario, int qtdTuitesUsuario) {
        boolean promoverUsuarioANinja = qtdTuitesUsuario >= Usuario.MIN_TUITES_NINJA;

        if(promoverUsuarioANinja) {
            usuario.setNivel(NivelUsuario.NINJA);
        }
    }

    /**
     * Adiciona novas hashtags ao tuiter - é uma arraylist de hashtags, só que de um novo tuite
     * @param hashtags
     */
    private void adicionarHashTag(ArrayList<String> hashtags) {
        for (String hashtag : hashtags) {
            boolean hashtagCadastrada = qtdByHashtags.get(hashtag) != null;
            int qtdAtual = hashtagCadastrada ? qtdByHashtags.get(hashtag) : 0;
            qtdByHashtags.put(hashtag, ++qtdAtual);

            if (qtdAtual > this.quantOcorrenciasHashtagMaisComum) {
                this.hashtagMaisComum = hashtag;
                this.quantOcorrenciasHashtagMaisComum = qtdAtual;
            }
        }
    }

}