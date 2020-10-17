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

    private Set<Usuario> usuarios = new HashSet<>();                // transformar em um Map<String, Usuario> usuarioByEmail;
    private Map<String, Integer> qtdByHashtags = new HashMap<>();   // transformar em um Map<String, Integer> contByHashtag;
    private ArrayList<Tuite> tuites = new ArrayList<>();

    /**
    public TuiterLite() {
        this.usuarios = new ArrayList<>();
        this.hashtags = new ArrayList<>();
    }
    */

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
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) {
        boolean autorDesconhecido = usuario.getNome().equals("Usuário Desconhecido") || usuario.getEmail().equals("unknown@void.com");
        boolean tamanhoMaximoExcedido = texto.length() > TuiterLite.TAMANHO_MAXIMO_TUITES;

        if(autorDesconhecido || tamanhoMaximoExcedido) {
            return null;
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
        return Collections.max(qtdByHashtags.entrySet(), Map.Entry.comparingByValue()).getKey();
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
     * Adiciona novas hashtags ao tuiter
     * @param hashtags
     */
    private void adicionarHashTag(ArrayList<String> hashtags) {
        for (String hashtag : hashtags) {
            boolean hashtagCadastrada = qtdByHashtags.get(hashtag) != null;
            int qtdAtual = hashtagCadastrada ? qtdByHashtags.get(hashtag) : 0;
            qtdByHashtags.put(hashtag, ++qtdAtual);
        }
    }

}